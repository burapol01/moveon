package com.example.MoveOn.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.MoveOn.Adapter.DataFoodsAdapter;
import com.example.MoveOn.Adapter.FoodsPagerAdapter;
import com.example.MoveOn.ApiClient;
import com.example.MoveOn.Interface.ApiInterface;
import com.example.MoveOn.Interface.FoodsItemClickListener;
import com.example.MoveOn.Model.ApiResponse;
import com.example.MoveOn.Model.DataFoods;
import com.example.MoveOn.Model.UserModel;
import com.example.MoveOn.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodsMain extends AppCompatActivity implements FoodsItemClickListener {

    public static UserModel uid1;

    private final List<DataFoods> lstSlides = new ArrayList<>();
    private final List<DataFoods> lstFoods = new ArrayList<>();
    private ViewPager sliderpager;
    private FoodsPagerAdapter foodsPagerAdapter;
    private DataFoodsAdapter dataFoodsAdapter;
    private ApiInterface apiInterface;
    private Timer timer;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_foods);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        sliderpager = findViewById(R.id.slider_pager1);
        TabLayout indicator = findViewById(R.id.indicator1);
        RecyclerView foodsrcv = findViewById(R.id.Rv_movies);

        foodsPagerAdapter = new FoodsPagerAdapter(this, lstSlides);
        sliderpager.setAdapter(foodsPagerAdapter);

        dataFoodsAdapter = new DataFoodsAdapter(this, lstFoods, this);
        foodsrcv.setAdapter(dataFoodsAdapter);
        foodsrcv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        loadSlideFoods();
        loadMenuFoods();

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

    private void loadSlideFoods() {
        Call<ApiResponse<List<DataFoods>>> call = apiInterface.foodspage();
        call.enqueue(new Callback<ApiResponse<List<DataFoods>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<DataFoods>>> call, Response<ApiResponse<List<DataFoods>>> response) {
                ApiResponse<List<DataFoods>> body = response.body();
                lstSlides.clear();
                if (body != null && body.getData() != null) {
                    lstSlides.addAll(body.getData());
                }
                foodsPagerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ApiResponse<List<DataFoods>>> call, Throwable t) {
                Toast.makeText(FoodsMain.this, "rp :" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadMenuFoods() {
        Call<ApiResponse<List<DataFoods>>> call = apiInterface.foodspage();
        call.enqueue(new Callback<ApiResponse<List<DataFoods>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<DataFoods>>> call, Response<ApiResponse<List<DataFoods>>> response) {
                ApiResponse<List<DataFoods>> body = response.body();
                lstFoods.clear();
                if (body != null && body.getData() != null) {
                    lstFoods.addAll(body.getData());
                }
                dataFoodsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ApiResponse<List<DataFoods>>> call, Throwable t) {
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onFoodsClick(DataFoods foods, ImageView foodsImageView) {
        Intent intent = getIntent();
        uid1 = (UserModel) intent.getSerializableExtra("user_head");
        if (uid1 != null) {
            Call<ApiResponse<DataFoods>> call = apiInterface.setReadfoods(foods.getDatafood_id(), uid1.getUser_id());
            call.enqueue(new Callback<ApiResponse<DataFoods>>() {
                @Override
                public void onResponse(Call<ApiResponse<DataFoods>> call, Response<ApiResponse<DataFoods>> response) {
                }

                @Override
                public void onFailure(Call<ApiResponse<DataFoods>> call, Throwable t) {
                }
            });
        }

        Intent toread = new Intent(this, DataReadFoods.class);
        toread.putExtra("datafood_id", foods.getDatafood_id());
        startActivity(toread);
        Toast.makeText(this, "item clicked : " + foods.getToppic_fd(), Toast.LENGTH_LONG).show();
    }

    public class SliderTimer extends TimerTask {
        @Override
        public void run() {
            FoodsMain.this.runOnUiThread(() -> {
                if (lstSlides.isEmpty()) {
                    return;
                }
                if (sliderpager.getCurrentItem() < lstSlides.size() - 1) {
                    sliderpager.setCurrentItem(sliderpager.getCurrentItem() + 1);
                } else {
                    sliderpager.setCurrentItem(0);
                }
            });
        }
    }
}
