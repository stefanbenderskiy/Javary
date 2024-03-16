package com.reflection.javary;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import androidx.appcompat.app.AppCompatActivity;

public class ListMenuItem {
    private Drawable icon;
    private String title;
    private Class activity;

    public ListMenuItem(Drawable icon, String title,Class activity) {
        this.icon = icon;
        this.title = title;
        this.activity = activity;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;

    }

    public Class getActivity() {
        return activity;
    }

    public void setActivity(Class activity) {
        this.activity = activity;
    }
}
