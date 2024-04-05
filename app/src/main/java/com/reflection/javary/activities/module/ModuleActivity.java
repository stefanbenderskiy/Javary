package com.reflection.javary.activities.module;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.reflection.javary.LessonsController;
import com.reflection.javary.R;
import com.reflection.javary.activities.lesson.LessonActivity;
import com.reflection.javary.data.Dataset;
import com.reflection.javary.lesson.Module;

public class ModuleActivity extends AppCompatActivity {
    private ImageButton closeBtn;
    private TextView moduleTitle;
    private TextView moduleProgressValue;
    private ProgressBar moduleProgressBar;
    private SearchView moduleContentSearch;
    private LessonsController lessonsController;
    private ListView contentList;

    protected void update(int index){
        Module module = lessonsController.getModule(index);
        Dataset data = lessonsController.getModuleData(index);
        LessonsListAdapter contentAdapter = new LessonsListAdapter(this,index);

        moduleTitle.setText(module.getTitle());
        moduleProgressBar.setMax(module.getSize());
        moduleProgressBar.setProgress(data.getInt("progress",0));
        moduleProgressValue.setText((data.getInt("progress",0)/module.getSize() *100)+"%");
        contentList.setAdapter(contentAdapter);
        moduleContentSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        contentList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lessonsController.setSelectedLesson(position+1);
                startActivity(new Intent(ModuleActivity.this, LessonActivity.class));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);
        lessonsController = new LessonsController(this);
        closeBtn = findViewById(R.id.module_close);
        moduleTitle = findViewById(R.id.module_title);
        moduleProgressBar= findViewById(R.id.module_progress_bar);
        moduleProgressValue =findViewById(R.id.module_progress_value);
        contentList = findViewById(R.id.module_content_list);
        moduleContentSearch =findViewById(R.id.module_content_search);
        update(lessonsController.getSelectedModule());





        closeBtn.setOnClickListener(v->{
            finish();
        });
    }
}