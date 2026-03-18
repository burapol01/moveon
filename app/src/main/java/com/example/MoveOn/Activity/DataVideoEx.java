package com.example.MoveOn.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MoveOn.Adapter.ViewVideoAdapter;
import com.example.MoveOn.ApiClient;
import com.example.MoveOn.Interface.ApiInterface;
import com.example.MoveOn.Model.ApiResponse;
import com.example.MoveOn.Model.Videofile;
import com.example.MoveOn.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataVideoEx extends AppCompatActivity {

    private final List<Videofile> viewvdo = new ArrayList<>();
    private ViewVideoAdapter viewVideoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_video_ex);

        VideoView playerView = findViewById(R.id.vdi);
        RecyclerView rcv = findViewById(R.id.Rv_video_data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcv.setLayoutManager(layoutManager);

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.ex1;
        Uri uri = Uri.parse(videoPath);
        playerView.setVideoURI(uri);
        playerView.start();
        MediaController mediaController = new MediaController(this);
        playerView.setMediaController(mediaController);
        mediaController.setAnchorView(playerView);

        viewVideoAdapter = new ViewVideoAdapter(viewvdo, this);
        rcv.setAdapter(viewVideoAdapter);

        datavideoview();
    }

    private void datavideoview() {
        Intent toread = getIntent();
        String videoId = toread.getStringExtra("video_id");

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ApiResponse<List<Videofile>>> call = apiInterface.getviewvideex(videoId);
        call.enqueue(new Callback<ApiResponse<List<Videofile>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Videofile>>> call, Response<ApiResponse<List<Videofile>>> response) {
                ApiResponse<List<Videofile>> body = response.body();
                viewvdo.clear();
                if (body != null && body.getData() != null) {
                    viewvdo.addAll(body.getData());
                }
                viewVideoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Videofile>>> call, Throwable t) {
                Toast.makeText(DataVideoEx.this, "ERROR On :" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
