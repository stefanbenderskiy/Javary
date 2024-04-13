package com.reflection.javary.activities.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.reflection.javary.R;
import com.reflection.javary.AppController;

import com.reflection.javary.activities.main.MainActivity;
import com.reflection.javary.data.DataBase;
import com.reflection.javary.data.Dataset;

public class SettingsActivity extends AppCompatActivity {
    private ImageButton closeBtn;
    private Spinner themeSpinner;
    private Spinner primarycolorSpinner;
    private Button clearDataBtn;
    private AppController appController;
    private boolean confirmed=false;

    private DataBase appDB;
    protected void restartAlert(boolean cancel){
        AlertDialog alertDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
        builder.setTitle(getString(R.string.title_restart_alert));
        builder.setMessage(getString(R.string.message_restart_alert));
        builder.setPositiveButton(getString(R.string.positive_text_restart_alert), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(i);
                dialog.cancel();

            }
        });
        if (cancel){builder.setNegativeButton(getString(R.string.negative_text_restart_alert), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });}


        alertDialog= builder.create();
        alertDialog.show();

    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        appController = new AppController(SettingsActivity.this);
        appController.initApp();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        closeBtn = findViewById(R.id.settings_close);
        clearDataBtn = findViewById(R.id.settings_cleardata_btn);
        themeSpinner =findViewById(R.id.settings_theme_spinner);

        primarycolorSpinner = findViewById(R.id.settings_primarycolor_spinner);ArrayAdapter<String> themeSpinnerAdapter= new ArrayAdapter<String>(this,R.layout.spinner_item,getResources().getStringArray(R.array.themes));
        ArrayAdapter<String> primaryColorSpinnerAdapter= new ArrayAdapter<String>(this,R.layout.spinner_item,getResources().getStringArray(R.array.colors));
        closeBtn.setOnClickListener(v -> {
            finish();
        });
        appDB= new DataBase(this,getString(R.string.app_database_name));
        Dataset settings= new Dataset(appDB,"","settings");

        themeSpinner.setSelection(settings.getInt("theme",0));
        themeSpinner.setAdapter(themeSpinnerAdapter);
        primarycolorSpinner.setAdapter(primaryColorSpinnerAdapter);
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
        primarycolorSpinner.setSelection(settings.getInt("primary_color",0));
        primarycolorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (settings.getInt("primary_color",0) != position){
                    settings.setInt("primary_color",position);
                    restartAlert(false);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        clearDataBtn.setOnClickListener(v->{
            appController.clearAllData();
            restartAlert(false);

        });



    }
}