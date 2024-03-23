package com.reflection.javary;

import android.content.Context;
import android.content.res.AssetManager;

import com.reflection.javary.data.DataBase;
import com.reflection.javary.data.Dataset;
import com.reflection.javary.lesson.Lesson;
import com.reflection.javary.lesson.Module;

import java.io.IOException;

public class LessonsController {
    private Context context;
    private DataBase lessonsDB;

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




    public LessonsController(Context context) {
        this.context = context;
        assetManager= context.getAssets();

        lessonsDB = new DataBase(context, context.getString(R.string.lessons_database_name));
    }
    public int getCurrentLesson(){
        return new Dataset(lessonsDB,"","main").getInt("current_lesson",1);
    }
    public int getCurrentModule(){
        return new Dataset(lessonsDB,"","main").getInt("current_module",1);
    }
    public void nextLesson(){
        Dataset main = new Dataset(lessonsDB,"","main");

        int module = main.getInt("current_module",1);
        int lesson = main.getInt("current_lesson",1);
        try {
            assetManager.open("lesson"+(module+1)+".1.xml");
            main.setInt("current_module",module+1);
            main.setInt("current_lesson",1);
        } catch (IOException e) {
            try {
                assetManager.open("lesson"+module+"."+(lesson+1)+".xml");
                main.setInt("current_module",module);
                main.setInt("current_lesson",lesson+1);
            } catch (IOException ex) {

            }

        }


    }

    public Dataset getModuleData(int index){
         return new Dataset(lessonsDB,"modules",String.valueOf(index));
    }
    public Module getModule(int index){
        String[] names = context.getResources().getStringArray(R.array.modules);
        if (index<= names.length){
            Module  module = new Module();
            module.setTitle(names[index-1]);
            for (int i =1;true;i++){
                if (getLesson(index,i)!= null){
                    module.addLesson(getLesson(index,i));

                }else {
                    return module;
                }
            }

        }
        return null;
    }
    public Lesson getLesson(int module,int index){
        Lesson lesson;
        try {
            lesson = Lesson.parseFrom(assetManager.open("lesson"+module+"."+index+".xml"));

        } catch (IOException e) {
            return null;
        }
        return lesson;
    }
    public Dataset getLessonData(int module, int index){

        return new Dataset(lessonsDB,"modules."+module+".lessons",String.valueOf(index));
    }
}
