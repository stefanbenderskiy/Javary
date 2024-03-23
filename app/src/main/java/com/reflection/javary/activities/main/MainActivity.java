package com.reflection.javary.activities.main;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.reflection.javary.AppController;
import com.reflection.javary.R;
import com.reflection.javary.activities.registration.RegistrationActivity;
import com.reflection.javary.data.DataBase;
import com.reflection.javary.data.Dataset;
import com.reflection.javary.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private DataBase appDB;
    private AppController appController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        NavController navController = Navigation.findNavController(this, R.id.nav_host);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavigationUI.setupWithNavController(navView, navController);
        appDB = new DataBase(this,getString(R.string.app_database_name));
        appController = new AppController(MainActivity.this);
        Dataset userdata= new Dataset(appDB,"","userdata");
        appController.initApp();

        if (userdata.contains("username")){
            Intent intent= new Intent(this, RegistrationActivity.class);
            startActivity(intent);
            finish();
        }

    }

}