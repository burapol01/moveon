package com.example.MoveOn.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MoveOn.Adapter.ReadnewsAdapter;
import com.example.MoveOn.ApiClient;
import com.example.MoveOn.Interface.ApiInterface;
import com.example.MoveOn.Model.ApiResponse;
import com.example.MoveOn.Model.DataNews;
import com.example.MoveOn.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataReadNews extends AppCompatActivity {

    private final List<DataNews> readnewz = new ArrayList<>();
    private ReadnewsAdapter readnewsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_read_news);

        RecyclerView rcv = findViewById(R.id.recyclereadnews);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcv.setLayoutManager(layoutManager);
        readnewsAdapter = new ReadnewsAdapter(readnewz, this);
        rcv.setAdapter(readnewsAdapter);

        Intent toread = getIntent();
        String dataNewsId = toread.getStringExtra("datanews_id");

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ApiResponse<List<DataNews>>> call = apiInterface.getReadnews(dataNewsId);
        call.enqueue(new Callback<ApiResponse<List<DataNews>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<DataNews>>> call, Response<ApiResponse<List<DataNews>>> response) {
                ApiResponse<List<DataNews>> body = response.body();
                readnewz.clear();
                if (body != null && body.getData() != null) {
                    readnewz.addAll(body.getData());
                }
                readnewsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ApiResponse<List<DataNews>>> call, Throwable t) {
                Toast.makeText(DataReadNews.this, "ERROR On :" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
