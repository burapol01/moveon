package com.example.MoveOn.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MoveOn.Adapter.ReadExerciseAdapter;
import com.example.MoveOn.ApiClient;
import com.example.MoveOn.Interface.ApiInterface;
import com.example.MoveOn.Model.ApiResponse;
import com.example.MoveOn.Model.DataExercise;
import com.example.MoveOn.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataReadExercise extends AppCompatActivity {

    private final List<DataExercise> readEx = new ArrayList<>();
    private ReadExerciseAdapter readExerciseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_read_exercise);

        RecyclerView rcv = findViewById(R.id.recyclereadexercise);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcv.setLayoutManager(layoutManager);
        readExerciseAdapter = new ReadExerciseAdapter(readEx, this);
        rcv.setAdapter(readExerciseAdapter);

        Intent toread = getIntent();
        String dataExId = toread.getStringExtra("dataex_id");

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ApiResponse<List<DataExercise>>> call = apiInterface.getReadex(dataExId);
        call.enqueue(new Callback<ApiResponse<List<DataExercise>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<DataExercise>>> call, Response<ApiResponse<List<DataExercise>>> response) {
                ApiResponse<List<DataExercise>> body = response.body();
                readEx.clear();
                if (body != null && body.getData() != null) {
                    readEx.addAll(body.getData());
                }
                readExerciseAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ApiResponse<List<DataExercise>>> call, Throwable t) {
                Toast.makeText(DataReadExercise.this, "ERROR On :" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
