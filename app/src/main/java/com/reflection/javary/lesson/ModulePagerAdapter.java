package com.reflection.javary.lesson;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.reflection.javary.LessonsController;
import com.reflection.javary.R;
import com.reflection.javary.activities.lesson.LessonActivity;
import com.reflection.javary.data.Dataset;

public class ModulePagerAdapter extends PagerAdapter {
    private Context context;
    private int module;
    private LessonsController lessonsConrtoller;

    public ModulePagerAdapter(Context context, int module) {
        this.context = context;
        this.module = module;
        lessonsConrtoller = new LessonsController(context);
    }

    @Override
    public int getCount() {

        return lessonsConrtoller.getModule(module).getSize();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Lesson lesson = lessonsConrtoller.getLesson(module,position+1);
        Dataset lessonData = lessonsConrtoller.getLessonData(module,position+1);
        View view = LayoutInflater.from(context).inflate(R.layout.lesson_box,container,false);
        TextView title =view.findViewById(R.id.lesson_box_title);
        TextView progressValue = view.findViewById(R.id.lesson_box_progress_value);
        ProgressBar progressBar = view.findViewById(R.id.lesson_box_progress_bar);
        FloatingActionButton fab = view.findViewById(R.id.lesson_box_fab);
        title.setText(lesson.getTitle());
        int progress =lessonData.getInt("progress",0) ;
        progressValue.setText((progress/lesson.getSize() *100) +"%");
        progressBar.setMax(lesson.getSize());
        progressBar.setProgress(lessonData.getInt("progress",0));
        fab.setOnClickListener(listener -> {
            context.startActivity(new Intent(context,LessonActivity.class));
        });
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
