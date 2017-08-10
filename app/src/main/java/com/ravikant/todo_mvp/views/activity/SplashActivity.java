package com.ravikant.todo_mvp.views.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ravikant.todo_mvp.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        /** HIDE TOOL BAR*/
        if (getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setTitle("");
            getSupportActionBar().setElevation(0);
            // getSupportActionBar().setHomeAsUpIndicator(null);
        }
        renderToNextScreen();
    }

    private void renderToNextScreen(){
        /** METHOD TO CHECK IF USER EXISTS THEN SHOW DASHBOARD OR ELSE WELCOME SCREEN*/
        final int splashDisplayLength = 2000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user!=null){
                    finish();
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }else{
                    finish();
                    startActivity(new Intent(SplashActivity.this, WelcomeActivity.class));
                }
            }
        }, splashDisplayLength);
    }
}
