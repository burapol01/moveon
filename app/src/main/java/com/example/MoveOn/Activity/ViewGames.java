package com.example.MoveOn.Activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.MoveOn.ApiClient;
import com.example.MoveOn.Interface.ApiInterface;
import com.example.MoveOn.Model.ApiResponse;
import com.example.MoveOn.Model.SettingModel;
import com.example.MoveOn.Model.UserModel;
import com.example.MoveOn.R;
import com.example.MoveOn.Utils.Common;
import com.example.MoveOn.databases.MoveOnDB;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewGames extends AppCompatActivity {

    int image_id;
    int workoutMode = 0;
    String name;
    TextView timer;
    TextView title;
    ImageView detail_image;

    Button btnStart;
    boolean isRunning = false;

    MoveOnDB moveOnDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_view_games);

        moveOnDB = new MoveOnDB(this);
        workoutMode = moveOnDB.getSettingMode();
        loadWorkoutMode();

        timer = findViewById(R.id.timer);
        title = findViewById(R.id.title_d);
        detail_image = findViewById(R.id.detail_image);
        btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(v -> {
            if (!isRunning) {
                btnStart.setText("DONE");

                int timeLimit = 0;
                if (workoutMode == 0) {
                    timeLimit = Common.TIME_LIMIT_EASY;
                } else if (workoutMode == 1) {
                    timeLimit = Common.TIME_LIMIT_MEDIUM;
                } else if (workoutMode == 2) {
                    timeLimit = Common.TIME_LIMIT_HARD;
                }

                new CountDownTimer(timeLimit, 1000) {
                    @Override
                    public void onTick(long l) {
                        timer.setText(String.valueOf(l / 1000));
                    }

                    @Override
                    public void onFinish() {
                        Toast.makeText(ViewGames.this, "Finish", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }.start();
            } else {
                Toast.makeText(ViewGames.this, "Finish", Toast.LENGTH_SHORT).show();
                finish();
            }
            isRunning = !isRunning;
        });

        timer.setText("");

        if (getIntent() != null) {
            image_id = getIntent().getIntExtra("image_id", -1);
            name = getIntent().getStringExtra("name");

            detail_image.setImageResource(image_id);
            title.setText(name);
        }
    }

    private void loadWorkoutMode() {
        UserModel userHead = (UserModel) getIntent().getSerializableExtra("user_head");
        if (userHead == null) {
            return;
        }

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ApiResponse<SettingModel>> call = apiInterface.getSettingMode(userHead.getUser_id());
        call.enqueue(new Callback<ApiResponse<SettingModel>>() {
            @Override
            public void onResponse(Call<ApiResponse<SettingModel>> call, Response<ApiResponse<SettingModel>> response) {
                ApiResponse<SettingModel> body = response.body();
                if (body != null && body.getData() != null) {
                    workoutMode = body.getData().getMode();
                    moveOnDB.saveSettingMode(workoutMode);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<SettingModel>> call, Throwable t) {
            }
        });
    }
}
