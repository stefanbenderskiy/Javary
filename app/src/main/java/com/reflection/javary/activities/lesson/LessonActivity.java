package com.reflection.javary.activities.lesson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ProgressBar;

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
    private DataBase appDB;
    private String  LESSON_NAME;
    private Lesson lesson;
    private Dataset lessonData;
    private LessonPagerAdapter pagerAdapter;


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
        appController= new AppController(this);
        lessonsController = new LessonsController(this);
        viewPager = findViewById(R.id.lesson_viewpager);
        AssetManager assetManager = getAssets();
        Dataset lessons = new Dataset(lessonsDB,"","");
        lesson = lessonsController.getLesson(lessonsController.getCurrentModule(),lessonsController.getCurrentLesson());
        lessonData = lessonsController.getLessonData(lessonsController.getCurrentModule(),lessonsController.getCurrentLesson());
        pagerAdapter = new LessonPagerAdapter(LessonActivity.this,lesson);
        viewPager.setAdapter(pagerAdapter);
        progressBar.setMax(pagerAdapter.getCount());
        progressBar.setProgress(lessonData.getInt("progress",0));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                int progress= lessonData.getInt("progress",0);
                if (progress-1 <= position){
                    lessonData.setInt(LESSON_NAME+"_progress",progress+1);
                    progressBar.setProgress(progress+1);

                }
                if (progress+1 == pagerAdapter.getCount()){
                    lessonsController.nextLesson();
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        closeButton.setOnClickListener(v -> {
            finish();
        });


    }


}