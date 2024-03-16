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

public class SettingsActivity extends AppCompatActivity {
    private ImageButton closeBtn;
    private Spinner themeSpinner;
    private Spinner primarycolorSpinner;
    private AppController appController;


    private SharedPreferences appPreferences;
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
        appPreferences= getSharedPreferences(getString(R.string.app_preferences_name),MODE_PRIVATE);
        themeSpinner.setSelection(appPreferences.getInt("theme",0));
        themeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences.Editor editor =appPreferences.edit();
                editor.remove("theme");
                editor.putInt("theme", (int) id);
                editor.apply();
                appController.setTheme((int) id);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        primarycolorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences.Editor editor =appPreferences.edit();
                appController.setThemePrimaryColor((int)id);


                editor.remove("primarycolor");
                editor.putInt("primarycolor",(int)id);
                editor.apply();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }
}