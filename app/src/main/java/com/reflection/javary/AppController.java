package com.reflection.javary;

import android.content.Context;
import android.content.res.AssetManager;
import android.view.ContextThemeWrapper;

import androidx.appcompat.app.AppCompatDelegate;

import com.reflection.javary.lesson.Lesson;

import java.io.IOException;

public class AppController {
    private Context context;
    private DataController lessonsDC;
    private DataController appDC;
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
        appDC=  new DataController(context.getSharedPreferences(context.getString(R.string.app_preferences_name),Context.MODE_PRIVATE));
        lessonsDC = new DataController(context.getSharedPreferences(context.getString(R.string.lessons_preferences_name),Context.MODE_PRIVATE));
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
        lessonsDC.clear();
        appDC.clear();
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
    public void nextLesson(){
        int module = appDC.getInt("current_module",1);
        int lesson = appDC.getInt("current_lesson",1);
        try {
            assetManager.open("lesson"+(module+1)+".1.xml");
            appDC.setInt("current_module",module+1);
            appDC.setInt("current_lesson",1);
        } catch (IOException e) {
            try {
                assetManager.open("lesson"+module+"."+(lesson+1)+".xml");
                appDC.setInt("current_module",module);
                appDC.setInt("current_lesson",lesson+1);
            } catch (IOException ex) {

            }

        }


    }
    public float getModuleProgress(int module){
        float progress=0;
        for (int i=1;true;i++ ){

            Lesson lesson = getLesson(module,i);
            if (lesson == null) {
                return progress/(i+1);
            }else{
                progress+=appDC.getInt("lesson"+module+"."+i+"_progress",0)/lesson.getSize();

            }
        }
    }
    public int getModulesCount(){
        for (int i=1;true;i++){
            try {
                assetManager.open("lesson"+i+".1.xml");
            } catch (IOException e) {
                return i-1;
            }
        }

    }
    public Lesson getLesson(int module,int index){
        Lesson lesson;
        try {
            lesson = Lesson.parseFrom(assetManager.open("lesson"+module+index+".xml"));

        } catch (IOException e) {
            return null;
        }
        return lesson;
    }
    public void initApp(){


        if (!appDC.contains("current_lesson")){
            appDC.setInt("current_module",1);
            appDC.setInt("current_lesson",1);

        }
        setTheme(appDC.getInt("theme",0));

    }
}
