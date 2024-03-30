package com.reflection.javary.activities.lesson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.reflection.javary.AppController;
import com.reflection.javary.LessonsController;
import com.reflection.javary.R;
import com.reflection.javary.data.DataBase;
import com.reflection.javary.data.Dataset;
import com.reflection.javary.lesson.Lesson;
import com.reflection.javary.lesson.LessonPagerAdapter;

import java.io.IOException;

public class LessonActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private ProgressBar progressBar;

    private ImageButton closeButton;
    private AppController appController;
    private LessonsController lessonsController;
    private DataBase lessonsDB;
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
        pagerAdapter = new LessonPagerAdapter(LessonActivity.this,lesson);
        viewPager.setAdapter(pagerAdapter);

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

        if (lessonData.getInt("progress",0)==lesson.getSize()){
            viewPager.setCurrentItem(0);
        }else {
            viewPager.setCurrentItem(lessonData.getInt("progress",0)-1);
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            @Override
            public void onPageSelected(int position) {

                int progress= lessonData.getInt("progress",0);
                if (position+1> progress){
                    lessonData.setInt("progress",progress+1);
                    progressBar.setProgress(progress+1);

                }
                if (progress+1 == pagerAdapter.getCount()){
                    lessonsController.nextLesson();
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
        nextFAB.setOnClickListener(v -> {
            lessonsController.nextLesson();
            update(module,index+1);
        });
        previousFAB.setOnClickListener(view -> {
            lessonsController.previousLesson();
            update(module,index-1);
        });
    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        //initialization

        appDB= new DataBase(this,getString(R.string.app_database_name));
        lessonsDB = new DataBase(this,getString(R.string.lessons_database_name));
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


        update(lessonsController.getCurrentModule(),lessonsController.getCurrentLesson());
    }


}