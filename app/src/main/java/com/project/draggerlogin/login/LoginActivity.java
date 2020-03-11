package com.project.draggerlogin.login;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.draggerlogin.AccueilActivity;
import com.project.draggerlogin.R;
import com.project.draggerlogin.root.App;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements LoginActivityMVP.View{

    @Inject
    LoginActivityMVP.Presenter  presenter;

    EditText identifiant, mdp;
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

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                presenter.loginButtonClicked();
            }
        });
    }

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
        Toast.makeText(this,"Cet user n'existe pas",Toast.LENGTH_SHORT).show();
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
        Toast.makeText(this,"Server Error",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void accueil() {
        Intent intent = new Intent(this, AccueilActivity.class);
        startActivity(intent);
    }

    @Override
    public void showUserError() {
        Toast.makeText(this,"Il y a un error dans votre information",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setIdentifiant(String firstName) {
        this.identifiant.setText(firstName);
    }

    @Override
    public void setMdp(String lastName) {
        this.mdp.setText(lastName);
    }

}
