package com.reflection.javary.lesson.elements;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.reflection.javary.R;
import com.reflection.javary.lesson.Element;

public class Text implements Element {
    private String text;
    private TYPE type= TYPE.PARAGRAPH;

    @Override
    public View toView(Context context) {
        TextView view=new TextView(context);


        switch (type){
            case HEADER:

                view.setTextAppearance(R.style.Header);
                break;
            case TITLE:
                view.setTextAppearance(R.style.Title);
                break;
            case PARAGRAPH:
                view.setTextAppearance(R.style.Paragraph);
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
