package com.example.android_lab3;

import android.app.Application;

public class Conf extends Application {
    private int userId;

    public int getUserId(){
        return userId;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }
}
