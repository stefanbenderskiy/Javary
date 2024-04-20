package com.reflection.javary.activities.lesson;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.reflection.javary.R;
import com.reflection.javary.lesson.Element;
import com.reflection.javary.lesson.Lesson;
import com.reflection.javary.lesson.Page;

import java.util.ArrayList;

public class LessonPagerAdapter extends PagerAdapter {
    private Context context;
    private Lesson lesson;

    public LessonPagerAdapter(Context context, Lesson lesson) {
        this.context = context;
        this.lesson = lesson;
    }

    @Override
    public int getCount() {
        return lesson.getPages().size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
    @SuppressLint("MissingInflatedId")
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.lesson_page,container,false);
        Page page = lesson.getPage(position);

        LinearLayout content  = view.findViewById(R.id.page_content);
        for (Element e : page.getAll()){
            View v = e.toView(context);
            v.setPadding(0,context.getResources().getDimensionPixelSize(R.dimen.container_padding),0,0);
            content.addView(v);

        }

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
