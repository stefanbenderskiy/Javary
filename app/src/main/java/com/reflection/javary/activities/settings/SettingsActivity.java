package com.reflection.javary.activities.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.reflection.javary.R;
import com.reflection.javary.AppController;
import com.reflection.javary.data.DataBase;
import com.reflection.javary.data.Dataset;

public class SettingsActivity extends AppCompatActivity {
    private ImageButton closeBtn;
    private Spinner themeSpinner;
    private Spinner primarycolorSpinner;
    private AppController appController;


    private DataBase appDB;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        appController = new AppController(SettingsActivity.this);
        closeBtn = findViewById(R.id.lesson_close);
        themeSpinner =findViewById(R.id.settings_theme_spinner);
        primarycolorSpinner = findViewById(R.id.settings_primarycolor_spinner);
        closeBtn.setOnClickListener(v -> {
            finish();
        });
        appDB= new DataBase(this,getString(R.string.app_database_name));
        Dataset settings= new Dataset(appDB,"","settings");

        themeSpinner.setSelection(settings.getInt("theme",0));
        themeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                settings.setInt("theme",(int) id);
                appController.setTheme((int) id);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        primarycolorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //settings.setInt("primarycolor",position);
                appController.setThemePrimaryColor((int)id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }
}