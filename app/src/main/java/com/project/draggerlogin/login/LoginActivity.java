package com.project.draggerlogin.login;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.draggerlogin.AccueilActivity;
import com.project.draggerlogin.R;
import com.project.draggerlogin.addAccount.AddAccountActivity;
import com.project.draggerlogin.root.App;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements LoginActivityMVP.View{

    @Inject
    LoginActivityMVP.Presenter  presenter;

    EditText identifiant, mdp;
    TextView addAccounttextView;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ((App) getApplication()).getComponent().inject(this);
        identifiant = findViewById(R.id.editTextIdentifiant);
        mdp = findViewById(R.id.editTextMdp);
        loginButton = findViewById(R.id.buttonLogin);
        addAccounttextView = findViewById(R.id.textViewAddAccount);

        identifiant.addTextChangedListener(loginTextWatcher);
        mdp.addTextChangedListener(loginTextWatcher);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loginButtonClicked();
            }
        });
        addAccounttextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addAccount();
            }
        });

    }
    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String mailCorrect = identifiant.getText().toString().trim();
            String passwordCorrect = mdp.getText().toString().trim();
            if (mailCorrect.matches("^[^@]+@[^@]+\\.[a-zA-Z]{2,}$")) {
                if (passwordCorrect.matches("^(?=.*\\d)(?=.*[\\u0021-\\u002b\\u003c-\\u0040])(?=.*[A-Z])(?=.*[a-z])\\S{8,16}$")) {
                    validateDone();
                } else {
                    showPasswordValidateError();
                }
            } else {
                showEmailValidateError();
                if (!passwordCorrect.matches("^(?=.*\\d)(?=.*[\\u0021-\\u002b\\u003c-\\u0040])(?=.*[A-Z])(?=.*[a-z])\\S{8,16}$")) {
                    showPasswordValidateError();
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        //presenter.getCurrentUser();
    }

    @Override
    public String getIdentifiant() {
        return this.identifiant.getText().toString().trim();
    }

    @Override
    public String getMdp() { return this.mdp.getText().toString().trim(); }

    @Override
    public void showUserNotAvaible() {
        Toast.makeText(this,"Cet utilisateur n'existe pas",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInputError() {
        Toast.makeText(this,"Erreur, les champs ne sont pas remplis correctement",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserSaved() {
        Toast.makeText(this,"Bienvenue",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showServerError() {
        Toast.makeText(this,"Erreur serveur",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void accueil() {
        Intent intent = new Intent(this, AccueilActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("mail", getIdentifiant());
        bundle.putString("password", getMdp());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void showUserError() {
        Toast.makeText(this,"Il y a une erreur dans votre information",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setIdentifiant(String firstName) {
        this.identifiant.setText(firstName);
    }

    @Override
    public void setMdp(String lastName) {
        this.mdp.setText(lastName);
    }

    @Override
    public void account() {
        Intent intent = new Intent(this, AddAccountActivity.class);
        startActivity(intent);
    }
    @Override
    public void showEmailValidateError() {
        identifiant.setError("Bien saisir votre identifiant");
        loginButton.setEnabled(false);
        loginButton.setBackgroundColor(Color.parseColor("#DFEDF2"));
        loginButton.setTextColor(Color.parseColor("#023859"));
    }

    @Override
    public void showPasswordValidateError() {
        mdp.setError("Bien saisir votre mot de passe");
        loginButton.setEnabled(false);
        loginButton.setBackgroundColor(Color.parseColor("#DFEDF2"));
        loginButton.setTextColor(Color.parseColor("#023859"));
    }

    @Override
    public void validateDone() {
        loginButton.setEnabled(true);
        loginButton.setBackgroundColor(Color.parseColor("#023859"));
        loginButton.setTextColor(Color.parseColor("#DFEDF2"));
    }

}
