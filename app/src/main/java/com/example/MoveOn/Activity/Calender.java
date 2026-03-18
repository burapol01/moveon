package com.example.MoveOn.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.MoveOn.ApiClient;
import com.example.MoveOn.Custom.WorkoutDoneDecorator;
import com.example.MoveOn.Interface.ApiInterface;
import com.example.MoveOn.Model.ApiResponse;
import com.example.MoveOn.Model.UserModel;
import com.example.MoveOn.R;
import com.example.MoveOn.databases.MoveOnDB;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Calender extends AppCompatActivity {

    private MaterialCalendarView materialCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        materialCalendarView = findViewById(R.id.calender);
        loadWorkoutCalendar();
    }

    private void loadWorkoutCalendar() {
        UserModel userHead = (UserModel) getIntent().getSerializableExtra("user_head");
        if (userHead == null) {
            MoveOnDB moveOnDB = new MoveOnDB(this);
            List<String> workoutDay = moveOnDB.getWorkoutDays();
            HashSet<CalendarDay> convertedList = new HashSet<>();
            for (String value : workoutDay) {
                convertedList.add(CalendarDay.from(new Date(Long.parseLong(value))));
            }
            materialCalendarView.addDecorator(new WorkoutDoneDecorator(convertedList));
            return;
        }

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ApiResponse<List<String>>> call = apiInterface.getWorkoutCalendar(userHead.getUser_id());
        call.enqueue(new Callback<ApiResponse<List<String>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<String>>> call, Response<ApiResponse<List<String>>> response) {
                ApiResponse<List<String>> body = response.body();
                HashSet<CalendarDay> convertedList = new HashSet<>();
                if (body != null && body.getData() != null) {
                    for (String value : body.getData()) {
                        try {
                            convertedList.add(CalendarDay.from(new Date(Long.parseLong(value))));
                        } catch (NumberFormatException ignored) {
                        }
                    }
                }
                materialCalendarView.addDecorator(new WorkoutDoneDecorator(convertedList));
            }

            @Override
            public void onFailure(Call<ApiResponse<List<String>>> call, Throwable t) {
                materialCalendarView.addDecorator(new WorkoutDoneDecorator(new HashSet<>()));
            }
        });
    }
}
