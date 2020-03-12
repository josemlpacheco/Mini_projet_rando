package com.project.draggerlogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.project.draggerlogin.ui.dashboard.DashboardFragment;
import com.project.draggerlogin.ui.home.HomeFragment;
import com.project.draggerlogin.ui.notifications.NotificationsFragment;
import com.project.draggerlogin.ui.randonnee.AddRandoFragment;

public class AccueilActivity extends AppCompatActivity {
    private ActionBar toolbar;
    private FloatingActionButton addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        toolbar = getSupportActionBar();
        addButton = findViewById(R.id.addBtn);

        BottomNavigationView navigation = findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toolbar.setTitle("Mes randonnées");
        loadFragment(new DashboardFragment());

        /*addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new AddRandoFragment());
            }
        });*/
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
