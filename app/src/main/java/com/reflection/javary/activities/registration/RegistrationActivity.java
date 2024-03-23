package com.reflection.javary.activities.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.reflection.javary.R;
import com.reflection.javary.activities.main.MainActivity;

public class RegistrationActivity extends AppCompatActivity {

    //private UsersDH usersDH = new UsersDH(this);
    private FloatingActionButton fab;
    private EditText input;
    private DataController appDC;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        appDC = new DataController(getSharedPreferences(getString(R.string.app_preferences_name),MODE_PRIVATE)) ;
        input=  findViewById(R.id.activity_registration_edit);
        fab = findViewById(R.id.registration_fab_continue);
        fab.setBackgroundColor(getColor(R.color.grey));

        fab.setOnClickListener(v -> {
            String name = input.getText().toString();
            if ((name.replace(" ","").isEmpty())){

            } else{



                appDC.setString("user_name",name);
                finish();
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            }
        });

    }
}