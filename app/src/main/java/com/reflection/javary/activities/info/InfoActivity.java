package com.reflection.javary.activities.info;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.reflection.javary.AppController;
import com.reflection.javary.R;
import com.reflection.javary.activities.settings.SettingsActivity;

public class InfoActivity extends AppCompatActivity {
    private ImageButton closeBtn;
    private AppController appController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        appController = new AppController(InfoActivity.this);
        appController.initApp();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        closeBtn = findViewById(R.id.info_close);
        closeBtn.setOnClickListener(v -> {
            finish();
        });
    }
}