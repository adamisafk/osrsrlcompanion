package com.sirgar.kadill.osrs_rl_companion;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        //default fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                new ToolsFragment()).commit();

    }

    //listener that opens any of the fragments depending on which item of the navbar is selected
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    //works out what to do for each fragment
                    switch (menuItem.getItemId()) {
                        case R.id.action_tools:
                            selectedFragment = new ToolsFragment();
                            break;
                        case R.id.action_accounts:
                            selectedFragment = new AccountsFragment();
                            break;
                        case R.id.action_settings:
                            selectedFragment = new SettingsFragment();
                            break;
                        case R.id.action_info:
                            selectedFragment = new InfoFragment();
                            break;
                    }
                    //shows the fragment
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                            selectedFragment).commit();
                    return true;
                }
            };


}
