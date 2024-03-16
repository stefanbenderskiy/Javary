package com.reflection.javary.lesson.elements;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.reflection.javary.R;
import com.reflection.javary.lesson.Element;

public class Image implements Element {
    private String src;
    private SHAPE shape= SHAPE.SQUARE;

    @Override
    public View toView(Context context) {
        ImageView view= new ImageView(context);
        view.setImageDrawable(Drawable.createFromPath(src));
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        view.setClipToOutline(true);
        switch (shape){
            case SQUARE:
                break;
            case ROUNDED:
                view.setBackground(context.getDrawable(R.drawable.rounded_corners));
                break;
            case CIRCLE:
                view.setBackground(context.getDrawable(R.drawable.circle));
                break;
        }
        return view;
    }

    public enum SHAPE{
            SQUARE,
            CIRCLE,
            ROUNDED,
    }

    public Image(String src) {
        this.src = src;
    }

    public Image(String src, SHAPE shape) {
        this.src = src;
        this.shape = shape;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public SHAPE getShape() {
        return shape;
    }

    public void setShape(SHAPE shape) {
        this.shape = shape;
    }

}
