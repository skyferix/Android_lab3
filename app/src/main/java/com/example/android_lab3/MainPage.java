package com.example.android_lab3;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import elements.Course;
import elements.recyclerAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class MainPage extends AppCompatActivity implements recyclerAdapter.OnNoteListener {

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
        int id = ((Conf) this.getApplication()).getUserId();
        switch (type){
            case "own":
                setCourseList(Constants.USER_OWN_URL + id);
                break;
            case "participate":
                setCourseList(Constants.USER_PARTICIPATE_URL + id);
                break;
            case "moderate":
                setCourseList(Constants.USER_MODERATE_URL + id);
                break;
            case "all":
                setCourseList(Constants.COURSE_ALL);
                break;
            default:
                break;
        }
    }

    private void setNewCourseList(String response) throws JSONException {
        JSONArray jsonArray = new JSONArray(response);
        courseList.clear();
        for(int i=0;i<jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                courseList.add(new Course(jsonObject.get("id").toString(), jsonObject.get("title").toString(), jsonObject.get("owner").toString(), jsonObject.get("startDate").toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        synchronized (recyclerView){
            recyclerView.notify();
        }
    }

    private void setCourseList(String URL){
        Executor executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {
            try {
                String response = RESTController.sendGet(URL);
                setNewCourseList(response);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        });
        try {
            synchronized (recyclerView){
                recyclerView.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    private void setAdapter() {
        recyclerAdapter adapter = new recyclerAdapter(courseList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setUserInfo(){
        courseList.add(new Course("1", "name", "ow", "startDate"));
        courseList.add(new Course("1", "name", "ow", "startDate"));
    }

    @Override
    public void onNoteClick(int position){
        courseList.get(position).getId();
        Intent intent = new Intent(this, SingleCourse.class);
        intent.putExtra("id", courseList.get(position).getId());
        startActivity(intent);
    }



}
