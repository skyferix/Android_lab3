package com.example.android_lab3;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class SingleCourse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int id = getIntent().getExtras().getInt("id");
        System.out.println(id);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_course);
    }
}
