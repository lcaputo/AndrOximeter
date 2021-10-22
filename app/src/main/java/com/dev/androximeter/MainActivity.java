package com.dev.androximeter;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary);

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new firstFragment()).commit();
        getSupportActionBar().setTitle("Home");

        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                String NameHeader = "";
                switch (item.getItemId()){
                    case R.id.firstFragment:
                        fragment = new firstFragment();
                        NameHeader = "Tutorial";
                        break;
                    case R.id.secondFragment:
                        fragment = new secondFragment();
                        NameHeader = "Home";
                        break;
                    case R.id.thirdFragment:
                        fragment = new thirdFragment();
                        NameHeader = "History";
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
                getSupportActionBar().setTitle(NameHeader);
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Salir")
                .setMessage("Estas seguro que deseas salir de la aplicación?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, (arg0, arg1) -> {

                    MainActivity.super.onBackPressed();
                    finish();
                    System.exit(0);
                }).create().show();
    }


}