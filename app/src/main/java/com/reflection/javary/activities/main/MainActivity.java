package com.reflection.javary.activities.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.reflection.javary.AppController;
import com.reflection.javary.DataController;
import com.reflection.javary.R;
import com.reflection.javary.activities.registration.RegistrationActivity;
import com.reflection.javary.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private DataController appDC;
    private AppController appController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        NavController navController = Navigation.findNavController(this, R.id.nav_host);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavigationUI.setupWithNavController(navView, navController);
        appDC = new DataController(getSharedPreferences(getString(R.string.app_preferences_name),MODE_PRIVATE));
        appController = new AppController(MainActivity.this);


        appController.initApp();
        if (appDC.getString("user_name","").isEmpty()){
            Intent intent= new Intent(this, RegistrationActivity.class);
            startActivity(intent);
            finish();
        }

    }

}