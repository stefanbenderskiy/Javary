package com.reflection.javary.lesson;

import java.util.ArrayList;
import java.util.List;

public class Page {
    private List<Element> content =new ArrayList<>();

    public List<Element> getAll() {
        return content;
    }
    public Element get(int index){
        return content.get(index);

    }
    public void add(Element e){
        content.add(e);
    }
    public void remove(int index){
        content.remove(index);
    }
    public void remove(Element element){
        content.remove(element);
    }

}
