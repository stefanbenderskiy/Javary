package com.reflection.javary;

import android.content.Context;
import android.content.res.AssetManager;
import android.view.ContextThemeWrapper;

import androidx.appcompat.app.AppCompatDelegate;

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
    public  void resetAllData(){


    }
    public void setThemePrimaryColor(int color){
        ContextThemeWrapper themeWrapper = new ContextThemeWrapper(context,context.getTheme());


        switch (color){
            case COLOR_BLUE:

                break;
            case COLOR_ORANGE:


                break;
            case COLOR_GREEN:

                break;
            case COLOR_YELLOW:

                break;
            case COLOR_RED:

                break;
            case COLOR_PURPLE:

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

    }
}
