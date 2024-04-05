package com.reflection.javary.activities.module;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.reflection.javary.LessonsController;
import com.reflection.javary.R;
import com.reflection.javary.lesson.Lesson;
import com.reflection.javary.lesson.Module;

public class LessonsListAdapter extends BaseAdapter {
    private Context context;
    private Module module;
    private final int index;
    private LessonsController lessonsController;


    public LessonsListAdapter(Context context, int module) {
        lessonsController = new LessonsController(context);
        this.module = lessonsController.getModule(module);
        this.context = context;

        this.index =module;

    }

    @Override
    public int getCount() {
        return module.getSize();
    }

    @Override
    public Object getItem(int position) {
        return module.getLesson(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Lesson lesson=  module.getLesson(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.item_lesson,parent,false);

        TextView title = convertView.findViewById(R.id.lesson_item_title);
        ProgressBar progressBar = convertView.findViewById(R.id.lesson_item_progress_bar);
        title.setText(lesson.getTitle());
        progressBar.setMax(lesson.getSize());
        progressBar.setProgress(lessonsController.getLessonData(index,position+1).getInt("progress",0));





        return convertView;
    }
}
