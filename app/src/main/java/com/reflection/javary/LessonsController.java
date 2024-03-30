package com.reflection.javary;

import android.content.Context;
import android.content.res.AssetManager;

import com.reflection.javary.data.DataBase;
import com.reflection.javary.data.Dataset;
import com.reflection.javary.lesson.Lesson;
import com.reflection.javary.lesson.Module;
import com.reflection.javary.lesson.ModulesListAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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


        int module = getCurrentModule();
        int lesson = getCurrentLesson();
        if (lesson==getModule(module).getSize()){
            if (getModule(module+1)!= null ){
                setCurrentModule(module+1);
                setCurrentLesson(1);
            }
        }else {
            setCurrentLesson(lesson+1);

        }

    }
    public void previousLesson(){


        int module = getCurrentModule();
        int lesson = getCurrentLesson();
        if (lesson !=1){
            setCurrentLesson(lesson-1);
        }else {
            if (module !=1 ){
                setCurrentModule(module-1);
                setCurrentLesson(1);
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
    public List<Module> getModules(){
        List<Module> modules = new ArrayList<>();

        for (int i=1;true;i++){
            if (getModule(i)!= null){
                modules.add(getModule(i));
            }else {
                return modules;
            }
        }

    }
    public Dataset getLessonData(int module, int index){

        return new Dataset(lessonsDB,"modules."+module+".lessons",String.valueOf(index));
    }

    public void setCurrentLesson(int value) {
        new Dataset(lessonsDB,"","main").setInt("current_lesson",value);
    }
    public void setCurrentModule(int value){
        new Dataset(lessonsDB,"","main").setInt("current_module",value);
    }
}
