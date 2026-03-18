package com.example.MoveOn.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.MoveOn.ApiClient;
import com.example.MoveOn.Interface.ApiInterface;
import com.example.MoveOn.Model.ApiResponse;
import com.example.MoveOn.Model.Datauser;
import com.example.MoveOn.Model.UserModel;
import com.example.MoveOn.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button btnExercise = findViewById(R.id.btnExercise);
        Button btnSetting = findViewById(R.id.btnSetting);
        ImageView btnTraining = findViewById(R.id.btnTraining);
        Button btnCalender = findViewById(R.id.btncalender);

        btnCalender.setOnClickListener(v -> {
            Intent intent = new Intent(Main2Activity.this, Calender.class);
            UserModel userHead = (UserModel) getIntent().getSerializableExtra("user_head");
            intent.putExtra("user_head", userHead);
            startActivity(intent);
        });
        btnTraining.setOnClickListener(v -> startActivity(new Intent(Main2Activity.this, Daily_Training.class)));
        btnSetting.setOnClickListener(v -> {
            Intent intent = new Intent(Main2Activity.this, SettingPage.class);
            UserModel userHead = (UserModel) getIntent().getSerializableExtra("user_head");
            intent.putExtra("user_head", userHead);
            startActivity(intent);
        });
        btnExercise.setOnClickListener(v -> startActivity(new Intent(Main2Activity.this, ListExercises.class)));

        UserModel userHead = (UserModel) getIntent().getSerializableExtra("user_head");
        if (userHead == null) {
            return;
        }

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ApiResponse<Datauser>> call = apiInterface.datauser(userHead.getUser_id());
        call.enqueue(new Callback<ApiResponse<Datauser>>() {
            @Override
            public void onResponse(Call<ApiResponse<Datauser>> call, Response<ApiResponse<Datauser>> response) {
                ApiResponse<Datauser> body = response.body();
                Datauser profile = body != null ? body.getData() : null;
                if (profile == null) {
                    return;
                }

                Glide.with(getApplicationContext())
                        .load(ApiClient.resolveUrl(profile.getPicture()))
                        .into(btnTraining);
            }

            @Override
            public void onFailure(Call<ApiResponse<Datauser>> call, Throwable t) {
            }
        });
    }
}
