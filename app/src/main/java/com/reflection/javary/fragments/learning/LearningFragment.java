package com.reflection.javary.fragments.learning;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import com.reflection.javary.LessonsController;
import com.reflection.javary.R;
import com.reflection.javary.data.DataBase;
import com.reflection.javary.data.Dataset;
import com.reflection.javary.lesson.Lesson;


public class LearningFragment extends Fragment {


    private Lesson currentLesson;
    private AssetManager assetManager;
    private DataBase appDB;

    private DataBase lessonsDB;
    private LessonsController lessonsController;
    private RecyclerView modulesList;

    private ViewPager currentLessonPager;

    private void update(){
        Dataset userdata = new Dataset(appDB,"","userdata");


        ModulePagerAdapter modulePagerAdapter= new ModulePagerAdapter(getContext(),lessonsController.getCurrentModule());
        currentLessonPager.setAdapter(modulePagerAdapter);
        currentLessonPager.setCurrentItem(lessonsController.getCurrentLesson()-1);
        ModulesListAdapter modulesListAdapter = new ModulesListAdapter(getContext());
        modulesList.setAdapter(modulesListAdapter);
        modulesList.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        Log.i("LEARNING", String.valueOf(modulesListAdapter.getItemCount()));
        currentLessonPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                lessonsController.setSelectedModule(lessonsController.getCurrentModule());
                lessonsController.setSelectedLesson(position+1);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        Log.i("MODULE_ADAPTER", String.valueOf(modulePagerAdapter.getCount()));
    }


    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_learning, container, false);
        lessonsController = new LessonsController(getContext());
        currentLessonPager = root.findViewById(R.id.current_lesson_pager);
        appDB =new DataBase(getContext(),getString(R.string.app_database_name)) ;
        lessonsDB=new DataBase(getContext(),getString(R.string.lessons_database_name));
        modulesList  = root.findViewById(R.id.modules_list);
        assetManager= getContext().getAssets();

        update();
        return root;
    }

    @Override
    public void onResume() {
        update();
        super.onResume();
    }


}