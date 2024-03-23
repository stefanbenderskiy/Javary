package com.reflection.javary.lesson;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.reflection.javary.LessonsController;
import com.reflection.javary.R;
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
        View view = LayoutInflater.from(context).inflate(R.layout.lesson_box,container);
        TextView title =view.findViewById(R.id.lesson_box_title);
        ProgressBar progressBar = view.findViewById(R.id.lesson_box_progress_bar);
        title.setText(lesson.getTitle());
        progressBar.setMax(lesson.getSize());
        progressBar.setProgress(lessonData.getInt("progress",0));


        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
