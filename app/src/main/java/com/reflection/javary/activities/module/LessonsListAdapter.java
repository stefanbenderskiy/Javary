package com.reflection.javary.activities.module;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.reflection.javary.LessonsController;
import com.reflection.javary.R;
import com.reflection.javary.lesson.Lesson;
import com.reflection.javary.lesson.Module;

import java.util.List;

public class LessonsListAdapter extends ArrayAdapter<String> {
    private final int module ;
    private LessonsController lessonsController;

    private static String[] getLessonsTitles(Context context,int module){
        List<Lesson> content = new LessonsController(context).getModule(module).getLessons();
        String[] titles = new String[content.size()];
        for (int i =0; i<content.size();i++){
            titles[i]= content.get(i).getTitle();
        }
        return titles;
    }
    public LessonsListAdapter(@NonNull Context context, int resource,int module) {
        super(context, resource, getLessonsTitles(context,module));
        this.module =module;
        lessonsController = new LessonsController(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Lesson lesson= lessonsController.getModule(module).getLesson(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_lesson,parent,false);

        TextView title = convertView.findViewById(R.id.lesson_item_title);
        ProgressBar progressBar = convertView.findViewById(R.id.lesson_item_progress_bar);
        title.setText(position+1+ ". "+ lesson.getTitle());
        progressBar.setMax(lesson.getSize());
        progressBar.setProgress(lessonsController.getLessonData(module,position+1).getInt("progress",0));
        return convertView;
    }
}
