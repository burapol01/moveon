package com.example.MoveOn.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MoveOn.Adapter.DataYogaAdapter;
import com.example.MoveOn.ApiClient;
import com.example.MoveOn.Interface.ApiInterface;
import com.example.MoveOn.Interface.ExerciseItemClickListener;
import com.example.MoveOn.Model.ApiResponse;
import com.example.MoveOn.Model.DataExercise;
import com.example.MoveOn.Model.UserModel;
import com.example.MoveOn.R;

import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataYoga extends AppCompatActivity implements ExerciseItemClickListener {

    public static UserModel uid1;

    private final List<DataExercise> datayoga = new ArrayList<>();
    private DataYogaAdapter dataYogaAdapter;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_yoga);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        RecyclerView rcv = findViewById(R.id.recyclereadnews_yoga);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcv.setLayoutManager(layoutManager);

        dataYogaAdapter = new DataYogaAdapter(this, datayoga, this);
        rcv.setAdapter(dataYogaAdapter);

        Call<ApiResponse<List<DataExercise>>> call = apiInterface.datayoga();
        call.enqueue(new Callback<ApiResponse<List<DataExercise>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<DataExercise>>> call, Response<ApiResponse<List<DataExercise>>> response) {
                ApiResponse<List<DataExercise>> body = response.body();
                datayoga.clear();
                if (body != null && body.getData() != null) {
                    datayoga.addAll(body.getData());
                }
                dataYogaAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ApiResponse<List<DataExercise>>> call, Throwable t) {
                Toast.makeText(DataYoga.this, "ERROR On :" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void todailyyoga(View view) {
        UserModel userHead = (UserModel) getIntent().getSerializableExtra("user_head");
        Intent intent = new Intent(this, YogadailyTraining.class);
        intent.putExtra("user_games", userHead);
        startActivity(intent);
    }

    @Override
    public void onExerciseClick(DataExercise exercise, GifImageView gifImageView) {
        uid1 = (UserModel) getIntent().getSerializableExtra("user_head");
        if (uid1 != null) {
            Call<ApiResponse<DataExercise>> call = apiInterface.setReadex(exercise.getDataex_id(), uid1.getUser_id());
            call.enqueue(new Callback<ApiResponse<DataExercise>>() {
                @Override
                public void onResponse(Call<ApiResponse<DataExercise>> call, Response<ApiResponse<DataExercise>> response) {
                }

                @Override
                public void onFailure(Call<ApiResponse<DataExercise>> call, Throwable t) {
                }
            });
        }

        Intent toread = new Intent(this, DataReadYoga.class);
        toread.putExtra("dataex_id", exercise.getDataex_id());
        startActivity(toread);
        Toast.makeText(this, "item clicked : " + exercise.getName_ex(), Toast.LENGTH_LONG).show();
    }
}
