package com.example.MoveOn.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


import com.example.MoveOn.R;

import java.util.Locale;

public class gamesplay extends AppCompatActivity {

    //ประกาศตัวแปร
    private VideoView videoView1;
    private Button start1, pause1, next1;
    //    private Chronometer chronometer;
    //    private boolean running;
    private static final long START_TIME_IN_MILLIS = 600000;
    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private Button mButtonReset;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    //Event All แสดงหน้า Games play
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_gamesplay);
//        showVideo();

//        videoView1 = (VideoView)findViewById(R.id.videoview2);
//        start1 = (Button)findViewById(R.id.start12);
//        pause1 = (Button)findViewById(R.id.pause1);
//        next1 = (Button)findViewById(R.id.next1);
//
//        String URIpath1 = "android.resource://" + getPackageName() + "/" + R.raw.ex1;
//        Uri uri1  =  Uri.parse(URIpath1);
//        videoView1.setVideoURI(uri1);
//        videoView1.requestFocus();
//        MediaController mediaController = new MediaController(this);
//        videoView1.setMediaController(mediaController);
//        mediaController.setAnchorView(videoView1);
//
//
//
//
//        start1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                videoView1.start();
//            }
//
//        });
//        pause1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                videoView1.pause();
//            }
//
//        });
//        next1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(gamesplay.this, congrats.class);
//                startActivity(i);
//                Toast.makeText(getApplicationContext(), "เสร็จสิ้น", Toast.LENGTH_LONG).show();
//            }
//
//        });
//
//        videoView1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                Intent i = new Intent(gamesplay.this, congrats.class);
//                startActivity(i);
//                Toast.makeText(getApplicationContext(), "เสร็จสิ้น", Toast.LENGTH_LONG).show();
//
//
//            }
//        });


        //ปุ่มเวลา เปิด
//        mTextViewCountDown = findViewById(R.id.text_view_countdown);
//        mButtonStartPause = findViewById(R.id.button_start_pause);
//        mButtonReset = findViewById(R.id.gifrt3);
//
//        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                    if (mTimerRunning) {
//                        pauseTimer();
//                    }else {
//                        startTimer();
//                    }
//            }
//        });
//
//        mButtonReset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                    resetTimer();
//            }
//        });
//            updateCountDownText();

        //ปุ่มเวลา เปิด
    }
    //ปุ่มเวลา เปิด
//    private void startTimer() {
//        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis,1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                mTimeLeftInMillis = millisUntilFinished;
//                updateCountDownText();
//
//            }
//
//            @Override
//            public void onFinish() {
//                mTimerRunning = false;
//                mButtonStartPause.setVisibility(View.VISIBLE);
//                mButtonReset.setVisibility(View.VISIBLE);
//            }
//        }.start();
//        mTimerRunning = true;
//        mButtonStartPause.setText("หยุด");
//        mButtonReset.setVisibility(View.INVISIBLE);
//    }
//    private  void pauseTimer(){
//        mCountDownTimer.cancel();
//        mTimerRunning = false;
//        mButtonStartPause.setText("เริ่ม");
//        mButtonReset.setVisibility(View.VISIBLE);
//        mButtonStartPause.setVisibility(View.VISIBLE);
//
//
//    }
//    private void resetTimer(){
//        mTimeLeftInMillis = START_TIME_IN_MILLIS;
//        updateCountDownText();
//        mButtonReset.setVisibility(View.VISIBLE);
//
//    }
//    private void updateCountDownText(){
//        int minutes = (int) (mTimeLeftInMillis / 1000) / 1000;
//        int seconds = (int) (mTimeLeftInMillis / 1000) % 20;
//
//        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes,seconds);
//        mTextViewCountDown.setText(timeLeftFormatted);
//    }
    //ปุ่มเวลา เปิด

//    private void showVideo() {
////        VideoView vd = (VideoView) findViewById(R.id.videoview);
////        Uri uri = Uri.parse("android.resource://package/" + R.raw.test);
////        MediaController mc = new MediaController(this);
////        vd.setMediaController(mc);
////        vd.setVideoURI(uri);
////        vd.start();
//
//
////การเล่นวิดีโอ
//        VideoView videoView = findViewById(R.id.videoview);
//        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.koo;
//        Uri uri = Uri.parse(videoPath);
//        videoView.setVideoURI(uri);
//        MediaController mediaController = new MediaController(this);
//        videoView.setMediaController(mediaController);
//        mediaController.setAnchorView(videoView);

//
////เช็ควิดีโอว่าเล่นจบเเล้ว
////        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
////            @Override
////            public void onCompletion(MediaPlayer mp) {
////                Intent i = new Intent(gamesplay.this, congrats.class);
////                startActivity(i);
////                Toast.makeText(getApplicationContext(), "เสร็จสิ้น", Toast.LENGTH_LONG).show();
////
////
////            }
////        });
//
//    }

    //เปลี่ยนโหมด ซ่อน/โชว์ ของ Action Bar
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
//            showSystemUI();
        }
    }

    //ซ่อน UI
    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
    // Shows the system bars by removing all the flags
// except for the ones that make the content appear under the system bars.
//    private void showSystemUI() {
//        View decorView = getWindow().getDecorView();
//        decorView.setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//    }

}
