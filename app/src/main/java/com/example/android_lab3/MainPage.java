package com.example.android_lab3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import elements.Course;
import elements.recyclerAdapter;

import java.util.ArrayList;

public class MainPage extends AppCompatActivity {

    private ArrayList<Course> courseList;
    private RecyclerView recyclerView;
    private Button own, participate, moderate, all;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        courseList = new ArrayList<>();
        setContentView(R.layout.course);
        recyclerView = findViewById(R.id.recyclerView);
        setUserInfo();
        setAdapter();
        setButtons();
    }

    private void setButtons() {
        own = findViewById(R.id.own);
        participate = findViewById(R.id.participte);
        moderate = findViewById(R.id.moderate);
        all = findViewById(R.id.all);
        own.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               reloadContent("own");
            }
        });
        participate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reloadContent("participate");
            }
        });
        moderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reloadContent("moderate");
            }
        });
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reloadContent("all");
            }
        });
    }

    public void reloadContent(String type){
        switch (type){
            case "own":
                courseList.clear();
                for()
                break;
            case "participate":
                break;
            case "moderate":
                break;
            case "all":
                break;
            default:
                break;
        }
    }

    private void setAdapter() {
        recyclerAdapter adapter = new recyclerAdapter(courseList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setUserInfo(){
        courseList.add(new Course(1, "name", "ow", "startDate"));
    }


}
