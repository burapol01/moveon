package com.example.MoveOn;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MoveOn.Adapter.DataNewsAdapter;
import com.example.MoveOn.Interface.ApiInterface;
import com.example.MoveOn.Model.ApiResponse;
import com.example.MoveOn.Model.DataNews;
import com.example.MoveOn.Model.UserModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class testProfile extends AppCompatActivity {

    public static UserModel uid;

    private final List<DataNews> news = new ArrayList<>();
    private DataNewsAdapter dataNewsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_test_profile);

        Intent intent = getIntent();
        uid = (UserModel) intent.getSerializableExtra("user_head");

        RecyclerView rcv = findViewById(R.id.recyclenews);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        dataNewsAdapter = new DataNewsAdapter(news, this);
        rcv.setAdapter(dataNewsAdapter);

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ApiResponse<List<DataNews>>> call = apiInterface.getNews();
        call.enqueue(new Callback<ApiResponse<List<DataNews>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<DataNews>>> call, Response<ApiResponse<List<DataNews>>> response) {
                ApiResponse<List<DataNews>> body = response.body();
                news.clear();
                if (body != null && body.getData() != null) {
                    news.addAll(body.getData());
                }
                dataNewsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ApiResponse<List<DataNews>>> call, Throwable t) {
            }
        });
    }
}
