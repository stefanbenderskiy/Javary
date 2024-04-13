package com.reflection.javary.lesson.elements;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.reflection.javary.R;
import com.reflection.javary.lesson.Element;

public class Text implements Element {
    private String text;
    private TYPE type= TYPE.PARAGRAPH;



    @Override
    public View toView(Context context) {
        TextView view = null;


        switch (type){
            case HEADER:

                view = (TextView) LayoutInflater.from(context).inflate(R.layout.header,null);

                break;
            case TITLE:
                view = (TextView) LayoutInflater.from(context).inflate(R.layout.title,null);
                break;
            case PARAGRAPH:
                view = (TextView) LayoutInflater.from(context).inflate(R.layout.paragraph,null);
                break;

        }
        view.setText(text);
        return view;
    }

    public enum TYPE{
        HEADER,
        TITLE,
        PARAGRAPH
    }


    public Text(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

}
