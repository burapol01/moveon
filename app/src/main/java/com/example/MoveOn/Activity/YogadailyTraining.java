package com.example.MoveOn.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.MoveOn.ApiClient;
import com.example.MoveOn.Interface.ApiInterface;
import com.example.MoveOn.Model.ApiResponse;
import com.example.MoveOn.Model.Exercise;
import com.example.MoveOn.Model.SettingModel;
import com.example.MoveOn.Model.UserModel;
import com.example.MoveOn.Model.WorkoutDay;
import com.example.MoveOn.R;
import com.example.MoveOn.Utils.Common;
import com.example.MoveOn.databases.MoveOnDB;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YogadailyTraining extends AppCompatActivity {
    private static final String TAG = "YogaTraining";

    private Button btnStart;
    private ImageView exImage;
    private TextView txtGetReady;
    private TextView txtCountdown;
    private TextView txtTimer;
    private TextView exName;
    private ProgressBar progressBar;
    private LinearLayout layoutGetReady;

    private int exId = 0;
    private static final int CYOGA = 2;

    private final List<Exercise> list = new ArrayList<>();
    private MoveOnDB moveOnDB;
    private int workoutMode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_yogadaily_training);

        initData();
        moveOnDB = new MoveOnDB(this);
        workoutMode = moveOnDB.getSettingMode();
        loadWorkoutMode();

        btnStart = findViewById(R.id.btnStart);
        exImage = findViewById(R.id.detail_image);
        txtCountdown = findViewById(R.id.txtCountdown);
        txtGetReady = findViewById(R.id.txtGetReady);
        txtTimer = findViewById(R.id.timer);
        exName = findViewById(R.id.title_d);
        layoutGetReady = findViewById(R.id.layout_get_ready);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setMax(list.size());
        setExerciseInformation(exId);

        btnStart.setOnClickListener(v -> {
            String action = btnStart.getText().toString().toLowerCase();
            if (action.equals("start")) {
                showGetReady();
                btnStart.setText("done");
            } else if (action.equals("done")) {
                cancelExerciseCountdown();
                restTimeCountDown.cancel();

                if (exId < list.size()) {
                    showRestTime();
                    exId++;
                    progressBar.setProgress(exId);
                    txtTimer.setText("");
                } else {
                    showFinished();
                }
            } else {
                cancelExerciseCountdown();
                restTimeCountDown.cancel();

                if (exId < list.size()) {
                    setExerciseInformation(exId);
                } else {
                    showFinished();
                }
            }
        });
    }

    private void cancelExerciseCountdown() {
        if (workoutMode == 0) {
            exercisesEasyModeCountDown.cancel();
        } else if (workoutMode == 1) {
            exercisesMediumModeCountDown.cancel();
        } else if (workoutMode == 2) {
            exercisesHardModeCountDown.cancel();
        }
    }

    private void showRestTime() {
        exImage.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.INVISIBLE);
        btnStart.setText("Skip");
        btnStart.setVisibility(View.VISIBLE);
        layoutGetReady.setVisibility(View.VISIBLE);
        restTimeCountDown.start();
        txtGetReady.setText("REST TIME");
    }

    private void showGetReady() {
        exImage.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.VISIBLE);
        layoutGetReady.setVisibility(View.VISIBLE);
        txtGetReady.setText("GET READY");

        new CountDownTimer(6000, 1000) {
            @Override
            public void onTick(long l) {
                txtCountdown.setText(String.valueOf((l - 1000) / 1000));
            }

            @Override
            public void onFinish() {
                showExercises();
            }
        }.start();
    }

    private void showExercises() {
        if (exId < list.size()) {
            exImage.setVisibility(View.VISIBLE);
            btnStart.setVisibility(View.VISIBLE);
            layoutGetReady.setVisibility(View.INVISIBLE);

            if (workoutMode == 0) {
                exercisesEasyModeCountDown.start();
            } else if (workoutMode == 1) {
                exercisesMediumModeCountDown.start();
            } else if (workoutMode == 2) {
                exercisesHardModeCountDown.start();
            }

            exImage.setImageResource(list.get(exId).getImage_id());
            exName.setText(list.get(exId).getName());
        } else {
            showFinished();
        }
    }

    private void showFinished() {
        exImage.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        exName.setVisibility(View.INVISIBLE);
        layoutGetReady.setVisibility(View.VISIBLE);

        txtGetReady.setText("FINISHED !!!");
        txtCountdown.setText("ยินดีด้วย ! \nคุณได้ออกกำลังกายในวันนี้ครบแล้ว");
        txtCountdown.setTextSize(20);

        if (getIntent().getSerializableExtra("user_games") == null) {
            moveOnDB.saveDay(String.valueOf(Calendar.getInstance().getTimeInMillis()));
        }

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving...");
        progressDialog.show();

        UserModel userGames = (UserModel) getIntent().getSerializableExtra("user_games");
        if (userGames != null) {
            ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
            Call<ApiResponse<WorkoutDay>> call = apiInterface.daydone(
                    String.valueOf(Calendar.getInstance().getTimeInMillis()),
                    CYOGA,
                    String.valueOf(userGames.getUser_id()));
            call.enqueue(new Callback<ApiResponse<WorkoutDay>>() {
                @Override
                public void onResponse(Call<ApiResponse<WorkoutDay>> call, Response<ApiResponse<WorkoutDay>> response) {
                    progressDialog.dismiss();
                    ApiResponse<WorkoutDay> body = response.body();
                    if (body == null || !body.isSuccess()) {
                        Toast.makeText(YogadailyTraining.this, body != null ? body.getMessage() : "Save failed", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse<WorkoutDay>> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(YogadailyTraining.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            progressDialog.dismiss();
        }

        Dialog dialog = new Dialog(YogadailyTraining.this);
        dialog.setContentView(R.layout.item_dialog_box);
        ImageView close = dialog.findViewById(R.id.close_m);
        close.setOnClickListener(v -> dialog.dismiss());
        Button okM = dialog.findViewById(R.id.ok_m);
        okM.setOnClickListener(v -> onBackPressed());
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        dialog.show();
    }

    CountDownTimer exercisesEasyModeCountDown = new CountDownTimer(Common.TIME_LIMIT_EASY, 1000) {
        @Override
        public void onTick(long l) {
            txtTimer.setText(String.valueOf(l / 1000));
        }

        @Override
        public void onFinish() {
            if (exId < list.size() - 1) {
                exId++;
                progressBar.setProgress(exId);
                txtTimer.setText("");
                setExerciseInformation(exId);
                btnStart.setText("Start");
            } else {
                showFinished();
            }
        }
    };

    CountDownTimer exercisesMediumModeCountDown = new CountDownTimer(Common.TIME_LIMIT_MEDIUM, 1000) {
        @Override
        public void onTick(long l) {
            txtTimer.setText(String.valueOf(l / 1000));
        }

        @Override
        public void onFinish() {
            if (exId < list.size() - 1) {
                exId++;
                progressBar.setProgress(exId);
                txtTimer.setText("");
                setExerciseInformation(exId);
                btnStart.setText("Start");
            } else {
                showFinished();
            }
        }
    };

    CountDownTimer exercisesHardModeCountDown = new CountDownTimer(Common.TIME_LIMIT_HARD, 1000) {
        @Override
        public void onTick(long l) {
            txtTimer.setText(String.valueOf(l / 1000));
        }

        @Override
        public void onFinish() {
            if (exId < list.size() - 1) {
                exId++;
                progressBar.setProgress(exId);
                txtTimer.setText("");
                setExerciseInformation(exId);
                btnStart.setText("Start");
            } else {
                showFinished();
            }
        }
    };

    CountDownTimer restTimeCountDown = new CountDownTimer(20000, 1000) {
        @Override
        public void onTick(long l) {
            txtCountdown.setText(String.valueOf(l / 1000));
        }

        @Override
        public void onFinish() {
            setExerciseInformation(exId);
            showExercises();
        }
    };

    private void setExerciseInformation(int id) {
        Log.d(TAG, "Loading yoga image resource id: " + list.get(id).getImage_id());
        exImage.setImageResource(list.get(id).getImage_id());
        exName.setText(list.get(id).getName());
        btnStart.setText("Start");
        exImage.setVisibility(View.VISIBLE);
        btnStart.setVisibility(View.VISIBLE);
        txtTimer.setVisibility(View.VISIBLE);
        layoutGetReady.setVisibility(View.INVISIBLE);
    }

    private void initData() {
        list.add(new Exercise(R.drawable.half_pigeon, "ท่าที่ 1"));
    }

    private void loadWorkoutMode() {
        UserModel userGames = (UserModel) getIntent().getSerializableExtra("user_games");
        if (userGames == null) {
            return;
        }

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ApiResponse<SettingModel>> call = apiInterface.getSettingMode(userGames.getUser_id());
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
