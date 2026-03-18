package com.example.MoveOn.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MoveOn.Adapter.HistoryAdapter;
import com.example.MoveOn.ApiClient;
import com.example.MoveOn.Interface.ApiInterface;
import com.example.MoveOn.Model.ApiResponse;
import com.example.MoveOn.Model.History;
import com.example.MoveOn.Model.UserModel;
import com.example.MoveOn.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryPlay extends AppCompatActivity {

    private final List<History> workoutDay = new ArrayList<>();
    private HistoryAdapter historyAdapter;
    private ApiInterface apiInterface;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_play);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        progressBar = findViewById(R.id.progress);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        historyAdapter = new HistoryAdapter(workoutDay, this);
        recyclerView.setAdapter(historyAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPets();
    }

    private void getPets() {
        Intent intent = getIntent();
        UserModel userHead = (UserModel) intent.getSerializableExtra("user_head");
        if (userHead == null) {
            progressBar.setVisibility(View.GONE);
            return;
        }

        Call<ApiResponse<List<History>>> call = apiInterface.getWorkoutday(userHead.getUser_id());
        call.enqueue(new Callback<ApiResponse<List<History>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<History>>> call, Response<ApiResponse<List<History>>> response) {
                progressBar.setVisibility(View.GONE);
                ApiResponse<List<History>> body = response.body();
                workoutDay.clear();
                if (body != null && body.getData() != null) {
                    workoutDay.addAll(body.getData());
                }
                Log.i(HistoryPlay.class.getSimpleName(), String.valueOf(body));
                historyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ApiResponse<List<History>>> call, Throwable t) {
                Toast.makeText(HistoryPlay.this, "rp :" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
