package com.example.MoveOn.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.MoveOn.Adapter.DataVideoAdapter;
import com.example.MoveOn.Adapter.VideoPagerAdapter;
import com.example.MoveOn.ApiClient;
import com.example.MoveOn.Interface.ApiInterface;
import com.example.MoveOn.Interface.VideoItemClickListener;
import com.example.MoveOn.Model.ApiResponse;
import com.example.MoveOn.Model.UserModel;
import com.example.MoveOn.Model.Videofile;
import com.example.MoveOn.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoMain extends AppCompatActivity implements VideoItemClickListener {

    public static UserModel uid1;

    private final List<Videofile> videofiles = new ArrayList<>();
    private final List<Videofile> slideVideos = new ArrayList<>();
    private DataVideoAdapter dataVideoAdapter;
    private VideoPagerAdapter videoPagerAdapter;
    private ViewPager sliderpager;
    private ApiInterface apiInterface;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_main);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        sliderpager = findViewById(R.id.slider_pager2);
        TabLayout indicator = findViewById(R.id.indicator2);
        RecyclerView rcv = findViewById(R.id.Rv_video);

        dataVideoAdapter = new DataVideoAdapter(this, videofiles, this);
        rcv.setAdapter(dataVideoAdapter);
        rcv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        videoPagerAdapter = new VideoPagerAdapter(this, slideVideos);
        sliderpager.setAdapter(videoPagerAdapter);

        loadVideoMenu();
        loadVideoSlides();

        timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);
        indicator.setupWithViewPager(sliderpager, true);
    }

    @Override
    protected void onDestroy() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        super.onDestroy();
    }

    private void loadVideoMenu() {
        Call<ApiResponse<List<Videofile>>> call = apiInterface.videofile();
        call.enqueue(new Callback<ApiResponse<List<Videofile>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Videofile>>> call, Response<ApiResponse<List<Videofile>>> response) {
                ApiResponse<List<Videofile>> body = response.body();
                videofiles.clear();
                if (body != null && body.getData() != null) {
                    videofiles.addAll(body.getData());
                }
                dataVideoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Videofile>>> call, Throwable t) {
            }
        });
    }

    private void loadVideoSlides() {
        Call<ApiResponse<List<Videofile>>> call = apiInterface.videopage();
        call.enqueue(new Callback<ApiResponse<List<Videofile>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Videofile>>> call, Response<ApiResponse<List<Videofile>>> response) {
                ApiResponse<List<Videofile>> body = response.body();
                slideVideos.clear();
                if (body != null && body.getData() != null) {
                    slideVideos.addAll(body.getData());
                }
                videoPagerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Videofile>>> call, Throwable t) {
                Toast.makeText(VideoMain.this, "rp :" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onVideoClick(Videofile vdofile, ImageView vdoImageView) {
        uid1 = (UserModel) getIntent().getSerializableExtra("user_head");
        if (uid1 != null) {
            Call<ApiResponse<Videofile>> call = apiInterface.setviewvideo(vdofile.getVideo_id(), uid1.getUser_id());
            call.enqueue(new Callback<ApiResponse<Videofile>>() {
                @Override
                public void onResponse(Call<ApiResponse<Videofile>> call, Response<ApiResponse<Videofile>> response) {
                }

                @Override
                public void onFailure(Call<ApiResponse<Videofile>> call, Throwable t) {
                }
            });
        }

        Intent toread = new Intent(this, DataVideoEx.class);
        toread.putExtra("video_id", vdofile.getVideo_id());
        startActivity(toread);

        Toast.makeText(this, "item clicked : " + vdofile.getName_vd(), Toast.LENGTH_LONG).show();
    }

    public class SliderTimer extends TimerTask {
        @Override
        public void run() {
            VideoMain.this.runOnUiThread(() -> {
                if (slideVideos.isEmpty()) {
                    return;
                }
                if (sliderpager.getCurrentItem() < slideVideos.size() - 1) {
                    sliderpager.setCurrentItem(sliderpager.getCurrentItem() + 1);
                } else {
                    sliderpager.setCurrentItem(0);
                }
            });
        }
    }
}
