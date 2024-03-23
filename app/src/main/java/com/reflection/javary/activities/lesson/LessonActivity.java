package com.reflection.javary.activities.lesson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.reflection.javary.AppController;
import com.reflection.javary.R;
import com.reflection.javary.lesson.Lesson;
import com.reflection.javary.lesson.LessonPagerAdapter;

import java.io.IOException;

public class LessonActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private ProgressBar progressBar;

    private ImageButton closeButton;
    private AppController appController;
    private DataController lessonsDC;
    private DataController appDC;
    private String  LESSON_NAME;
    private Lesson lesson;
    private LessonPagerAdapter pagerAdapter;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        //initialization

        appDC = new DataController(getSharedPreferences(getString(R.string.app_preferences_name),MODE_PRIVATE));
        lessonsDC = new DataController(getSharedPreferences(getString(R.string.lessons_preferences_name),MODE_PRIVATE));

        closeButton = findViewById(R.id.lesson_close);
        progressBar = findViewById(R.id.lesson_progress_bar);
        appController= new AppController(this);
        viewPager = findViewById(R.id.lesson_viewpager);
        AssetManager assetManager = getAssets();

        LESSON_NAME= "lesson"+ appDC.getInt("current_module",1) +"."+ appDC.getInt("current_lesson",1);


        try {
            lesson = Lesson.parseFrom(assetManager.open(LESSON_NAME+".xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        pagerAdapter = new LessonPagerAdapter(LessonActivity.this,lesson);
        viewPager.setAdapter(pagerAdapter);
        progressBar.setMax(pagerAdapter.getCount());
        progressBar.setProgress(lessonsDC.getInt(LESSON_NAME+"_progress",0));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                int progress= lessonsDC.getInt(LESSON_NAME+"_progress",0);
                if (progress-1 <= position){
                    lessonsDC.setInt(LESSON_NAME+"_progress",progress+1);
                    progressBar.setProgress(progress+1);

                }
                if (progress+1 == pagerAdapter.getCount()){
                    appController.nextLesson();
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