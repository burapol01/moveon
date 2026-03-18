package com.example.MoveOn.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.MoveOn.Adapter.DataNewsAdapter;
import com.example.MoveOn.ApiClient;
import com.example.MoveOn.Interface.ApiInterface;
import com.example.MoveOn.Model.DataNews;
import com.example.MoveOn.Model.UserModel;
import com.example.MoveOn.R;
import com.example.MoveOn.testProfile;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TsetNews extends AppCompatActivity {


//    RecyclerView rcv;
//    DataNewsAdapter dataNewsAdapter;
//    List<DataNews> newz;
//    ProgressBar pgbnews;
//    public static UserModel uid;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tset_news);
//
//        Intent n = getIntent();
//        UserModel cur_user = (UserModel) n.getSerializableExtra("user_head");
//        uid = cur_user;
//
//
//        newz = new ArrayList<>();
//        rcv = (RecyclerView) findViewById(R.id.recyclenews);
//        LinearLayoutManager layoutManager = new LinearLayoutManager( this);
//        rcv.setLayoutManager(layoutManager);
//        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
//        Call<List<DataNews>> call = apiInterface.getNews();
//        call.enqueue(new Callback<List<DataNews>>() {
//            @Override
//            public void onResponse(Call<List<DataNews>> call, Response<List<DataNews>> response) {
//
//                newz = response.body();
//
//                dataNewsAdapter = new DataNewsAdapter(newz, TsetNews.this);
//                rcv.setAdapter(dataNewsAdapter);
//                dataNewsAdapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onFailure(Call<List<DataNews>> call, Throwable t) {
//
//            }
//        });
//
//    }
}
