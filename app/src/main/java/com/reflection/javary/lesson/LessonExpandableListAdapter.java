package com.reflection.javary.lesson;

import android.content.Context;
import android.content.res.AssetManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.reflection.javary.AppController;
import com.reflection.javary.DataController;
import com.reflection.javary.R;
import com.reflection.javary.lesson.Lesson;

import java.io.IOException;
import java.util.List;

public class LessonExpandableListAdapter extends BaseExpandableListAdapter {

    private  Context context;
    private DataController lessonsDC;
    private AppController appController;
    private AssetManager assetManager;
    public LessonExpandableListAdapter(Context context) {

        this.context = context;
        lessonsDC = new DataController(context.getSharedPreferences(context.getString(R.string.lessons_preferences_name) ,Context.MODE_PRIVATE));
        assetManager= context.getAssets();
        appController = new AppController(context);
    }

    @Override
    public int getGroupCount() {
        return appController.getModulesCount();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        for (int i=1;true;i++){
            if (appController.getLesson(groupPosition,i) == null){
                return i;
            }
        }

    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return appController.getLesson(groupPosition+1,childPosition+1);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String text = context.getResources().getStringArray(R.array.modules)[groupPosition];
        convertView = LayoutInflater.from(context).inflate(R.layout.lesson_group,parent);
        TextView title = convertView.findViewById(R.id.lesson_group_title);
        ProgressBar progressBar =convertView.findViewById(R.id.lesson_group_progress_bar);
        progressBar.setProgress((int) appController.getModuleProgress(groupPosition+1)*100);
        progressBar.setMax(100);
        title.setText(text);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Lesson lesson;
        String name ="lesson"+(groupPosition+1)+"."+(childPosition+1);
        try {
             lesson= Lesson.parseFrom(assetManager.open(name+".xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        convertView = LayoutInflater.from(context).inflate(R.layout.lesson_item,parent);
        TextView title = convertView.findViewById(R.id.lesson_item_title);
        ProgressBar progressBar = convertView.findViewById(R.id.lesson_item_progress_bar);
        title.setText(lesson.getTitle());
        progressBar.setProgress(lessonsDC.getInt(name+"_progress",0));
        progressBar.setMax(lesson.getSize());


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
