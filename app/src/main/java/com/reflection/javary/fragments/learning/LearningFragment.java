package com.reflection.javary.fragments.learning;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.reflection.javary.LessonsController;
import com.reflection.javary.R;
import com.reflection.javary.data.DataBase;
import com.reflection.javary.data.Dataset;
import com.reflection.javary.lesson.Lesson;
import com.reflection.javary.lesson.LessonPagerAdapter;
import com.reflection.javary.lesson.ModulePagerAdapter;


public class LearningFragment extends Fragment {


    private Lesson currentLesson;
    private AssetManager assetManager;
    private DataBase appDB;

    private DataBase lessonsDB;
    private LessonsController lessonsController;

    private TextView welcomeText;
    private ViewPager currentLessonPager;

    private void Update(){
        Dataset userdata = new Dataset(appDB,"","userdata");
        welcomeText.setText(getString(R.string.welcome_text)+userdata.getString("username","")+"!");
        currentLessonPager.setAdapter(new ModulePagerAdapter(getContext(),lessonsController.getCurrentModule()));
        currentLessonPager.setCurrentItem(lessonsController.getCurrentLesson());

    }

    private FloatingActionButton currentLessonFAB;
    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_learning, container, false);
        lessonsController = new LessonsController(getContext());
        currentLessonPager = root.findViewById(R.id.current_lesson_pager);
        appDB =new DataBase(getContext(),getString(R.string.app_database_name)) ;
        lessonsDB=new DataBase(getContext(),getString(R.string.lessons_database_name));
        assetManager= getContext().getAssets();
        welcomeText = root.findViewById(R.id.welcome);
        Update();
        return root;
    }

    @Override
    public void onResume() {
        Update();
        super.onResume();
    }
}