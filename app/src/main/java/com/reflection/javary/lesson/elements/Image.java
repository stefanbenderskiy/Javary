package com.reflection.javary.lesson.elements;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.reflection.javary.R;
import com.reflection.javary.lesson.Element;

import java.io.IOException;

public class Image implements Element {
    private String src;
    private SHAPE shape= SHAPE.RECT;

    @Override
    public View toView(Context context) {
        ImageView view= new ImageView(context);
        try {
            view.setImageDrawable(Drawable.createFromStream(context.getAssets().open(src),src));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1000));
        view.setClipToOutline(true);
        switch (shape){
            case RECT:
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
            RECT,
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
