package com.project.draggerlogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.project.draggerlogin.Asso.AddAssoFragment;
import com.project.draggerlogin.ui.dashboard.DashboardFragment;
import com.project.draggerlogin.ui.home.HomeFragment;
import com.project.draggerlogin.ui.notifications.NotificationsFragment;
import com.project.draggerlogin.ui.randonnee.AddRandoFragment;

public class AccueilActivity extends AppCompatActivity {
    private ActionBar toolbar;
    private FloatingActionButton addbtn,addRando, addAsso;
    boolean isOpen = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        toolbar = getSupportActionBar();

        BottomNavigationView navigation = findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toolbar.setTitle("Mes randonnées");
        loadFragment(new HomeFragment());

        addbtn = (FloatingActionButton) findViewById(R.id.addBtn);
        addRando = (FloatingActionButton) findViewById(R.id.addRando);
        addAsso = (FloatingActionButton) findViewById(R.id.addAsso);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isOpen){
                    openMenu();
                } else {
                    closeMenu();
                }
                //loadFragment(new AddRandoFragment());
            }
        });
    }

    private void closeMenu() {
        isOpen = false;
        addRando.animate().translationY(0);
        addAsso.animate().translationY(0);
    }

    private void openMenu() {
        isOpen = true;
        addRando.animate().translationY(-getResources().getDimension(R.dimen.stan_55));
        addAsso.animate().translationY(-getResources().getDimension(R.dimen.stan_105));
        addRando.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeMenu();
                loadFragment(new AddRandoFragment());
            }
        });
        addAsso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeMenu();
                loadFragment(new AddAssoFragment());
            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toolbar.setTitle("Accueil");
                    loadFragment(new HomeFragment());
                    return true;
                case R.id.navigation_asso:
                    toolbar.setTitle("Mon associations");
                    loadFragment(new DashboardFragment());
                    return true;
                case R.id.navigation_notifications:
                    toolbar.setTitle("Mon profil");
                    loadFragment(new NotificationsFragment());
                    return true;
            }
            return false;
        }
    };
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
