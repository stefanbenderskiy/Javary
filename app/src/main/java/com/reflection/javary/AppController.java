package com.reflection.javary;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.view.ContextThemeWrapper;

import androidx.appcompat.app.AppCompatDelegate;

import com.reflection.javary.activities.main.MainActivity;
import com.reflection.javary.data.Dataset;
import com.reflection.javary.data.DataBase;
import com.reflection.javary.lesson.Lesson;

import java.io.IOException;

public class AppController {
    private Context context;
    private DataBase lessonsDB;
    private DataBase appDB;
    private AssetManager assetManager;
    
    public static final int THEME_SYSTEM= 0;
    public static final int THEME_LIGHT= 1;
    public static final int THEME_DARK= 2;
    public static final int COLOR_BLUE= 0;
    public static final int COLOR_ORANGE =1;
    public static final int COLOR_GREEN =2;
    public static final int COLOR_YELLOW =3;
    public static final int COLOR_RED =4;
    public static final int COLOR_PURPLE =5;
    public static final int LANGUAGE_SYSTEM = 0;
    public static final int LANGUAGE_ENGLISH = 1;
    public static final int LANGUAGE_RUSSIAN = 2;



    public AppController(Context context) {
        this.context = context;
        assetManager= context.getAssets();
        appDB= new DataBase(context, context.getString(R.string.app_database_name));
        lessonsDB = new DataBase(context, context.getString(R.string.lessons_database_name));
    }
    public void setTheme(int theme){

        switch (theme){
            case THEME_SYSTEM:

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
            case THEME_LIGHT:

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case THEME_DARK:

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;


        }

    }
    public  void clearAllData(){
        lessonsDB.clearAll();
        appDB.clearAll();

    }
    public void setThemePrimaryColor(int color){



        switch (color){
            case COLOR_BLUE:
                context.setTheme(R.style.Theme_Javary);
                break;
            case COLOR_ORANGE:

                context.setTheme(R.style.Theme_Javary_Orange);
                break;
            case COLOR_GREEN:
                context.setTheme(R.style.Theme_Javary_Green);
                break;
            case COLOR_YELLOW:
                context.setTheme(R.style.Theme_Javary_Yellow);
                break;
            case COLOR_RED:
                context.setTheme(R.style.Theme_Javary_Red);
                break;
            case COLOR_PURPLE:
                context.setTheme(R.style.Theme_Javary_Purple);
                break;
        }


    }

    public void setLanguage(int language){
        switch (language){
            case LANGUAGE_SYSTEM:
                break;
        }
    }

    public void initApp(){
        Dataset main= new Dataset(lessonsDB,"","main");
        Dataset settings =new Dataset(appDB,"","settings");



        if (!main.contains("current_lesson")){
            main.setInt("current_module",1);
            main.setInt("current_lesson",1);

        }
        setTheme(settings.getInt("theme",0));
        setThemePrimaryColor(settings.getInt("primary_color",0));
    }
}
