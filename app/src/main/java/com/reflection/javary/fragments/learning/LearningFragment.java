package com.reflection.javary.fragments.learning;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.reflection.javary.DataController;
import com.reflection.javary.R;
import com.reflection.javary.activities.lesson.LessonActivity;
import com.reflection.javary.lesson.Lesson;
import com.reflection.javary.lesson.LessonExpandableListAdapter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;


public class LearningFragment extends Fragment {


    private Lesson currentLesson;
    private AssetManager assetManager;
    private DataController appDC;
    private DataController lessonsDC;
    private TextView currentLessonTitle;
    private TextView currentLessonProgressValue;
    private ExpandableListView lessonsListView;
    private ProgressBar currentLessonProgressBar;
    private String CURRENT_LESSON_NAME;
    private void Update(){
        if (currentLesson != null){
            Log.i("LESSON", String.valueOf(currentLesson.getTitle()));
            CURRENT_LESSON_NAME ="lesson"+appDC.getInt("current_module",1)+"."+appDC.getInt("current_lesson",1);
            LessonExpandableListAdapter adapter = new LessonExpandableListAdapter(getContext());
            try {


                currentLesson = Lesson.parseFrom(assetManager.open(CURRENT_LESSON_NAME+".xml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            currentLessonTitle.setText(currentLesson.getTitle());
            currentLessonProgressBar.setMax(currentLesson.getSize());
            currentLessonProgressBar.setProgress(lessonsDC.getInt(CURRENT_LESSON_NAME+"_progress",0));
            currentLessonProgressValue.setText((lessonsDC.getInt(CURRENT_LESSON_NAME+"_progress",0)/ currentLesson.getSize() *100) +"%");
            lessonsListView.setAdapter(adapter);

        }

    }

    private FloatingActionButton currentLessonFAB;
    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_learning, container, false);
        currentLessonTitle= root.findViewById(R.id.current_lesson_title);
        currentLessonProgressBar =root.findViewById(R.id.current_lesson_progress_bar);
        currentLessonProgressValue = root.findViewById(R.id.current_lesson_progress_value);
        currentLessonFAB = root.findViewById(R.id.current_lesson_fab);
        lessonsListView = root.findViewById(R.id.lessons_list_view);
        appDC =new DataController(getContext().getSharedPreferences(getString(R.string.app_preferences_name), MODE_PRIVATE)) ;
        lessonsDC=new DataController(getContext().getSharedPreferences(getString(R.string.lessons_preferences_name), MODE_PRIVATE)) ;
        assetManager= getContext().getAssets();


        currentLessonFAB.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), LessonActivity.class);
            startActivity(intent);

        });
        Update();
        return root;
    }

    @Override
    public void onResume() {
        Update();
        super.onResume();
    }
}