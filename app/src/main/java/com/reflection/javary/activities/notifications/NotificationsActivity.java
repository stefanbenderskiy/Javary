package com.reflection.javary.activities.notifications;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

import com.reflection.javary.AppController;
import com.reflection.javary.R;
import com.reflection.javary.activities.settings.SettingsActivity;

public class NotificationsActivity extends AppCompatActivity {
    private ImageButton closeBtn;
    private AppController appController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        appController = new AppController(NotificationsActivity.this);
        appController.initApp();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        closeBtn = findViewById(R.id.notifications_close);
        closeBtn.setOnClickListener(v -> {
            finish();
        });
    }
}