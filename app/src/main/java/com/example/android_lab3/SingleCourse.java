package com.example.android_lab3;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SingleCourse extends AppCompatActivity {

    private TextView course_title, course_owner, course_start_date, course_end_date, course_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int id = Integer.parseInt(getIntent().getExtras().getString("id"));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_course);
        findFields();
        setFields(Constants.COURSE_BY_ID + id);
    }
    private void findFields(){
        course_title = findViewById(R.id.course_title);
        course_owner = findViewById(R.id.course_owner);
        course_start_date = findViewById(R.id.course_start_date);
        course_end_date = findViewById(R.id.course_end_date);
        course_description = findViewById(R.id.course_description);
    }

    private void setFields(String URL){
        Executor executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {
            try {
                String response = RESTController.sendGet(URL);
                setFieldsValues(response);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        });
    }

    private void setFieldsValues(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        course_title.setText(jsonObject.get("title").toString());
        course_owner.setText(jsonObject.get("owner").toString());
        course_start_date.setText(jsonObject.get("startDate").toString());
        course_end_date.setText(jsonObject.get("endDate").toString());
        course_description.setText(jsonObject.get("description").toString());
    }
}
