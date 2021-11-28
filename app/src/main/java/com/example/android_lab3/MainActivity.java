package com.example.android_lab3;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import helpers.Helper;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void login(View view){
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);

        String hashedPassword = Helper.hashPwd(password.getText().toString());

        String signInData = "{" +
                "\"login\":\"" + username.getText().toString() + "\"," +
                "\"password\":\"" + hashedPassword + "\"" +
                "}";
        System.out.println(signInData);
        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            try {
                String response = RESTController.sendPost(Constants.USER_LOGIN_URL, signInData);

                handler.post(() -> {
                    if(!response.equals("")){
                        Intent intent = new Intent(MainActivity.this, MainPage.class);
                        ((Conf) this.getApplication()).setUserId(Integer.parseInt(response));
                        startActivity(intent);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }
}