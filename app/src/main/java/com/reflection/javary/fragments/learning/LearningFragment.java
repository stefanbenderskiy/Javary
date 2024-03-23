package com.reflection.javary.fragments.learning;

import static android.content.Context.MODE_PRIVATE;

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
import com.reflection.javary.R;
import com.reflection.javary.lesson.Lesson;


public class LearningFragment extends Fragment {


    private Lesson currentLesson;
    private AssetManager assetManager;
    private DataController appDC;
    private DataController lessonsDC;
    private TextView welcomeText;
    private ViewPager currentLessonPager;

    private void Update(){


    }

    private FloatingActionButton currentLessonFAB;
    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_learning, container, false);


        appDC =new DataController(getContext().getSharedPreferences(getString(R.string.app_preferences_name), MODE_PRIVATE)) ;
        lessonsDC=new DataController(getContext().getSharedPreferences(getString(R.string.lessons_preferences_name), MODE_PRIVATE)) ;
        assetManager= getContext().getAssets();
        welcomeText = root.findViewById(R.id.welcome);
        welcomeText.setText(getString(R.string.welcome_text)+appDC.getString("user_name","")+"!");
        Update();
        return root;
    }

    @Override
    public void onResume() {
        Update();
        super.onResume();
    }
}