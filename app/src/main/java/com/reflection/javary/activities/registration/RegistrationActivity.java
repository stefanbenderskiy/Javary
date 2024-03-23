package com.reflection.javary.activities.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.reflection.javary.R;
import com.reflection.javary.activities.main.MainActivity;
import com.reflection.javary.data.DataBase;
import com.reflection.javary.data.Dataset;

public class RegistrationActivity extends AppCompatActivity {

    //private UsersDH usersDH = new UsersDH(this);
    private FloatingActionButton fab;
    private EditText input;
    private DataBase appDB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        appDB = new DataBase(this,getString(R.string.app_database_name)) ;
        input=  findViewById(R.id.activity_registration_edit);
        fab = findViewById(R.id.registration_fab_continue);
        fab.setBackgroundColor(getColor(R.color.grey));
        Dataset userdata = new Dataset(appDB,"","userdata");







        fab.setOnClickListener(v -> {
            String name = input.getText().toString();
            if ((name.replace(" ","").isEmpty())){

            } else{



                userdata.setString("user_name",name);
                finish();
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            }
        });

    }
}