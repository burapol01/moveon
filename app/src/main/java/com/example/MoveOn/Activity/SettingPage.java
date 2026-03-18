package com.example.MoveOn.Activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.MoveOn.ApiClient;
import com.example.MoveOn.Interface.ApiInterface;
import com.example.MoveOn.Model.ApiResponse;
import com.example.MoveOn.Model.SettingModel;
import com.example.MoveOn.Model.UserModel;
import com.example.MoveOn.R;
import com.example.MoveOn.databases.MoveOnDB;

import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingPage extends AppCompatActivity {
    Button btnSave;
    RadioButton rdiEasy;
    RadioButton rdiMedium;
    RadioButton rdiHard;
    RadioGroup rdiGroup;
    MoveOnDB moveOnDB;
    ToggleButton switchAlarm;
    TimePicker timePicker;
    UserModel userHead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_setting_page);

        btnSave = findViewById(R.id.btnSave);
        rdiGroup = findViewById(R.id.rdiGroup);
        rdiEasy = findViewById(R.id.rdiEasy);
        rdiMedium = findViewById(R.id.rdiMedium);
        rdiHard = findViewById(R.id.rdiHard);
        switchAlarm = findViewById(R.id.switchAlarm);
        timePicker = findViewById(R.id.timePicker);

        moveOnDB = new MoveOnDB(this);
        userHead = (UserModel) getIntent().getSerializableExtra("user_head");

        if (userHead != null) {
            loadWorkoutModeFromApi();
        } else {
            setRadioButton(moveOnDB.getSettingMode());
        }

        btnSave.setOnClickListener(v -> {
            saveWorkoutMode();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                saveAlarm(switchAlarm.isChecked());
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void saveAlarm(boolean checked) {
        if (checked) {
            AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(SettingPage.this, AlarmNotificationReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

            Calendar calendar = Calendar.getInstance();
            Date toDay = Calendar.getInstance().getTime();
            calendar.set(toDay.getYear(), toDay.getMonth(), toDay.getDay(), timePicker.getHour(), timePicker.getMinute());
            manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
            Log.d("DEBUG", "Alarm will wake at : " + timePicker.getHour() + ":" + timePicker.getMinute());
        } else {
            Intent intent = new Intent(SettingPage.this, AlarmNotificationReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
            AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            manager.cancel(pendingIntent);
        }
    }

    private void saveWorkoutMode() {
        int mode = getSelectedMode();
        moveOnDB.saveSettingMode(mode);

        if (userHead == null) {
            Toast.makeText(SettingPage.this, "SAVED", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ApiResponse<SettingModel>> call = apiInterface.saveSettingMode(userHead.getUser_id(), mode);
        call.enqueue(new Callback<ApiResponse<SettingModel>>() {
            @Override
            public void onResponse(Call<ApiResponse<SettingModel>> call, Response<ApiResponse<SettingModel>> response) {
                ApiResponse<SettingModel> body = response.body();
                if (body != null && body.isSuccess()) {
                    Toast.makeText(SettingPage.this, "SAVED", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(SettingPage.this, body != null ? body.getMessage() : "Save failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<SettingModel>> call, Throwable t) {
                Toast.makeText(SettingPage.this, "Save failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadWorkoutModeFromApi() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ApiResponse<SettingModel>> call = apiInterface.getSettingMode(userHead.getUser_id());
        call.enqueue(new Callback<ApiResponse<SettingModel>>() {
            @Override
            public void onResponse(Call<ApiResponse<SettingModel>> call, Response<ApiResponse<SettingModel>> response) {
                ApiResponse<SettingModel> body = response.body();
                if (body != null && body.getData() != null) {
                    int mode = body.getData().getMode();
                    moveOnDB.saveSettingMode(mode);
                    setRadioButton(mode);
                    return;
                }
                setRadioButton(moveOnDB.getSettingMode());
            }

            @Override
            public void onFailure(Call<ApiResponse<SettingModel>> call, Throwable t) {
                setRadioButton(moveOnDB.getSettingMode());
            }
        });
    }

    private int getSelectedMode() {
        int selectedID = rdiGroup.getCheckedRadioButtonId();
        if (selectedID == rdiEasy.getId()) {
            return 0;
        } else if (selectedID == rdiMedium.getId()) {
            return 1;
        } else if (selectedID == rdiHard.getId()) {
            return 2;
        }
        return 0;
    }

    private void setRadioButton(int mode) {
        if (mode == 0) {
            rdiGroup.check(R.id.rdiEasy);
        } else if (mode == 1) {
            rdiGroup.check(R.id.rdiMedium);
        } else if (mode == 2) {
            rdiGroup.check(R.id.rdiHard);
        }
    }
}
