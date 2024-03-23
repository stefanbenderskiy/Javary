package com.reflection.javary.lesson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.reflection.javary.R;

public class ModulePagerAdapter extends PagerAdapter {
    private Context context;
    private Module module;

    public ModulePagerAdapter(Context context, Module module) {
        this.context = context;
        this.module = module;
    }

    @Override
    public int getCount() {
        return module.getSize();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Lesson lesson = module.getLesson(position);
        View view = LayoutInflater.from(context).inflate(R.layout.lesson_box,container);
        TextView title =view.findViewById(R.id.lesson_box_title);
        ProgressBar progressBar = view.findViewById(R.id.lesson_box_progress_bar);
        title.setText(lesson.getTitle());
        progressBar.setMax(lesson.getSize());


        container.addView(view);
        return view;
    }
}
