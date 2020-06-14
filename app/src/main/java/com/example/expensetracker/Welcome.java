package com.example.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Welcome extends AppCompatActivity {
    Animation topAnim, bottomAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView expenseTracker, welc;
            expenseTracker = findViewById(R.id.et);
            welc = findViewById(R.id.welcome);
            topAnim = AnimationUtils.loadAnimation(Welcome.this,R.anim.top_animation);
            bottomAnim = AnimationUtils.loadAnimation(Welcome.this,R.anim.bottom_animation);
            expenseTracker.setAnimation(topAnim);
            welc.setAnimation(bottomAnim);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(Welcome.this,Login.class));
                finish();
            }
        },3000);
    }
}
