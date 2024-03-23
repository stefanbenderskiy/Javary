package com.reflection.javary.lesson;

import java.util.ArrayList;
import java.util.List;

public class Module {

    private String title;
    private List<Lesson> lessons =new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public List<Lesson> getLessons() {
        return lessons;
    }
    public Lesson getLesson(int index){
        return lessons.get(index);
    }
    public void addLesson(Lesson lesson){
        lessons.add(lesson);
    }
    public void addLesson(Lesson lesson,int index){
        lessons.add(index,lesson);
    }
    public int getSize(){
        return lessons.size();
    }
}
