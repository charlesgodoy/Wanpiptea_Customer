package com.burning_glow.wanpipteacustomer.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.burning_glow.wanpipteacustomer.R;
import com.burning_glow.wanpipteacustomer.ui.fragments.NewsFragment;
import com.burning_glow.wanpipteacustomer.ui.fragments.LoyaltyFragment;
import com.burning_glow.wanpipteacustomer.ui.fragments.MenuFragment;
import com.burning_glow.wanpipteacustomer.ui.fragments.ContactFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WelcomeScreen extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new NewsFragment()).commit();

        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_rewards:
                            selectedFragment = new NewsFragment();
                            break;

                        case R.id.nav_menu:
                            selectedFragment = new MenuFragment();
                            break;

                        case R.id.nav_loyalty:
                            selectedFragment = new LoyaltyFragment();
                            break;

                        case R.id.nav_contact:
                            selectedFragment = new ContactFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

                    return true;
                }
    };
}

