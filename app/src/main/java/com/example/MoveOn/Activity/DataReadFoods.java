package com.example.MoveOn.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MoveOn.Adapter.ReadFoodsAdapter;
import com.example.MoveOn.ApiClient;
import com.example.MoveOn.Interface.ApiInterface;
import com.example.MoveOn.Model.ApiResponse;
import com.example.MoveOn.Model.DataFoods;
import com.example.MoveOn.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataReadFoods extends AppCompatActivity {

    private final List<DataFoods> readfoods = new ArrayList<>();
    private ReadFoodsAdapter readFoodsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods_detail);

        RecyclerView rcv = findViewById(R.id.recyclereadnews_fd);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcv.setLayoutManager(layoutManager);
        readFoodsAdapter = new ReadFoodsAdapter(readfoods, this);
        rcv.setAdapter(readFoodsAdapter);

        Intent toread = getIntent();
        String dataFoodId = toread.getStringExtra("datafood_id");

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ApiResponse<List<DataFoods>>> call = apiInterface.getReadfoods(dataFoodId);
        call.enqueue(new Callback<ApiResponse<List<DataFoods>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<DataFoods>>> call, Response<ApiResponse<List<DataFoods>>> response) {
                ApiResponse<List<DataFoods>> body = response.body();
                readfoods.clear();
                if (body != null && body.getData() != null) {
                    readfoods.addAll(body.getData());
                }
                readFoodsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ApiResponse<List<DataFoods>>> call, Throwable t) {
                Toast.makeText(DataReadFoods.this, "ERROR On :" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
