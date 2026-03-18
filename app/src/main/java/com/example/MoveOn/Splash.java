package com.example.MoveOn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.MoveOn.Activity.LoginActivity;

public class Splash extends AppCompatActivity {
    private TextView welcom;
    private ImageView splash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //ซ่อน ActionBar

        setContentView(R.layout.activity_splash);
        welcom = (TextView) findViewById(R.id.welcom);
        splash = (ImageView) findViewById(R.id.splash);
        Animation myanima = AnimationUtils.loadAnimation(this,R.anim.anime);
        welcom.startAnimation(myanima);
        splash.startAnimation(myanima);
        final Intent i = new Intent(Splash.this, LoginActivity.class);
        Thread timer = new Thread(){
            @Override/*กำหนดเวลา*/
            public void run() {
                try {
                    sleep(2000);
                }catch(InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        }; timer.start();
    }
}
