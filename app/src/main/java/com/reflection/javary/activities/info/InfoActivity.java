package com.reflection.javary.activities.info;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.reflection.javary.R;

public class InfoActivity extends AppCompatActivity {
    private ImageButton closeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        closeBtn = findViewById(R.id.info_close);
        closeBtn.setOnClickListener(v -> {
            finish();
        });
    }
}