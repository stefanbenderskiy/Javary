package com.reflection.javary.activities.notifications;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

import com.reflection.javary.R;

public class NotificationsActivity extends AppCompatActivity {
    private ImageButton closeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        closeBtn = findViewById(R.id.notifications_close);
        closeBtn.setOnClickListener(v -> {
            finish();
        });
    }
}