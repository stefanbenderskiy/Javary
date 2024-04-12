package com.reflection.javary.activities.lesson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.reflection.javary.AppController;
import com.reflection.javary.LessonsController;
import com.reflection.javary.R;
import com.reflection.javary.activities.settings.SettingsActivity;
import com.reflection.javary.data.DataBase;
import com.reflection.javary.data.Dataset;
import com.reflection.javary.lesson.Lesson;

public class LessonActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private ProgressBar progressBar;

    private ImageButton closeButton;
    private AppController appController;
    private LessonsController lessonsController;
    private DataBase lessonsDB;
    private TextView lessonTitle;
    private FloatingActionButton nextFAB;
    private FloatingActionButton previousFAB;
    private DataBase appDB;
    private String  LESSON_NAME;
    private Lesson lesson;
    private Dataset lessonData;
    private LessonPagerAdapter pagerAdapter;
    protected void update(int module,int index){
        lesson = lessonsController.getLesson(module,index);

        lessonData = lessonsController.getLessonData(module,index);
        int progress= lessonData.getInt("progress",0);
        pagerAdapter = new LessonPagerAdapter(LessonActivity.this,lesson);
        viewPager.setAdapter(pagerAdapter);
        lessonTitle.setText(lesson.getTitle());
        progressBar.setMax(pagerAdapter.getCount());
        progressBar.setProgress(lessonData.getInt("progress",0));
        if (lessonsController.getLesson(module,index+1)==null){
            nextFAB.setVisibility(View.INVISIBLE);
        }else {
            nextFAB.setVisibility(View.VISIBLE);
        }

        if (lessonsController.getLesson(module,index-1)==null){
            previousFAB.setVisibility(View.INVISIBLE);
        }else{
            previousFAB.setVisibility(View.VISIBLE);
        }


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            @Override
            public void onPageSelected(int position) {
                int progress= lessonData.getInt("progress",0);
                if (position==progress){
                    lessonData.setInt("progress",progress+1);
                    progressBar.setProgress(progress+1);
                    if (progress+1 == lesson.getSize()){
                        lessonsController.nextLesson();
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
        nextFAB.setOnClickListener(v -> {

            update(module,index+1);
        });
        previousFAB.setOnClickListener(view -> {

            update(module,index-1);
        });
    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        appController = new AppController(LessonActivity.this);
        appController.initApp();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        //initialization

        appDB= new DataBase(this,getString(R.string.app_database_name));
        lessonsDB = new DataBase(this,getString(R.string.lessons_database_name));
        lessonTitle = findViewById(R.id.lesson_title);
        closeButton = findViewById(R.id.lesson_close);
        progressBar = findViewById(R.id.lesson_progress_bar);
        nextFAB= findViewById(R.id.lesson_fab_next);
        previousFAB = findViewById(R.id.lesson_fab_previous);
        appController= new AppController(this);
        lessonsController = new LessonsController(this);
        viewPager = findViewById(R.id.lesson_viewpager);



        closeButton.setOnClickListener(v -> {
            finish();
        });


        update(lessonsController.getSelectedModule(),lessonsController.getSelectedLesson());
    }


}