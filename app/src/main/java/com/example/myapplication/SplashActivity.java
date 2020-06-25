package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {


    WebView middle_Web_View;


@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashAction();

    }
    protected void splashAction(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                finish();
            }

        }, 4000);

    }


}
