package com.example.MoveOn.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.MoveOn.Activity.FoodsMain;
import com.example.MoveOn.Activity.NewsMain;
import com.example.MoveOn.Activity.VideoMain;
import com.example.MoveOn.Adapter.HomePagerAdapter;
import com.example.MoveOn.ApiClient;
import com.example.MoveOn.Custom.WorkoutDoneDecorator;
import com.example.MoveOn.Interface.ApiInterface;
import com.example.MoveOn.Model.ApiResponse;
import com.example.MoveOn.Model.DataNews;
import com.example.MoveOn.Model.UserModel;
import com.example.MoveOn.R;
import com.example.MoveOn.databases.MoveOnDB;
import com.google.android.material.tabs.TabLayout;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragment_home extends Fragment {

    private final List<DataNews> lstSlides = new ArrayList<>();
    private ViewPager sliderpager;
    private HomePagerAdapter homePagerAdapter;
    private CardView crdvideo;
    private CardView crdnews;
    private CardView crdfoods;
    private Timer timer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        sliderpager = root.findViewById(R.id.slider_pager);
        TabLayout indicator = root.findViewById(R.id.indicator);
        crdnews = root.findViewById(R.id.crdnews);
        crdfoods = root.findViewById(R.id.crdfoods);
        crdvideo = root.findViewById(R.id.crdvideo);

        homePagerAdapter = new HomePagerAdapter(getActivity(), lstSlides);
        sliderpager.setAdapter(homePagerAdapter);
        indicator.setupWithViewPager(sliderpager, true);

        timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);

        MaterialCalendarView materialCalendarView = root.findViewById(R.id.calender);
        loadWorkoutCalendar(materialCalendarView);

        loadHomeSlides();
        buttonmore();
        return root;
    }

    @Override
    public void onDestroyView() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        super.onDestroyView();
    }

    private void loadHomeSlides() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ApiResponse<List<DataNews>>> call = apiInterface.datahome();
        call.enqueue(new Callback<ApiResponse<List<DataNews>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<DataNews>>> call, Response<ApiResponse<List<DataNews>>> response) {
                ApiResponse<List<DataNews>> body = response.body();
                lstSlides.clear();
                if (body != null && body.getData() != null) {
                    lstSlides.addAll(body.getData());
                }
                homePagerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ApiResponse<List<DataNews>>> call, Throwable t) {
                if (getActivity() != null) {
                    Toast.makeText(getActivity(), "rp :" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadWorkoutCalendar(MaterialCalendarView materialCalendarView) {
        UserModel userHead = getUserHead();
        if (userHead == null) {
            MoveOnDB moveOnDB = new MoveOnDB(getActivity());
            List<String> workoutDay = moveOnDB.getWorkoutDays();
            HashSet<CalendarDay> convertedList = new HashSet<>();
            for (String value : workoutDay) {
                convertedList.add(CalendarDay.from(new Date(Long.parseLong(value))));
            }
            materialCalendarView.addDecorator(new WorkoutDoneDecorator(convertedList));
            return;
        }

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ApiResponse<List<String>>> call = apiInterface.getWorkoutCalendar(userHead.getUser_id());
        call.enqueue(new Callback<ApiResponse<List<String>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<String>>> call, Response<ApiResponse<List<String>>> response) {
                ApiResponse<List<String>> body = response.body();
                HashSet<CalendarDay> convertedList = new HashSet<>();
                if (body != null && body.getData() != null) {
                    for (String value : body.getData()) {
                        try {
                            convertedList.add(CalendarDay.from(new Date(Long.parseLong(value))));
                        } catch (NumberFormatException ignored) {
                        }
                    }
                }
                materialCalendarView.addDecorator(new WorkoutDoneDecorator(convertedList));
            }

            @Override
            public void onFailure(Call<ApiResponse<List<String>>> call, Throwable t) {
                materialCalendarView.addDecorator(new WorkoutDoneDecorator(new HashSet<>()));
            }
        });
    }

    private void buttonmore() {
        crdvideo.setOnClickListener(v -> {
            UserModel userHead = getUserHead();
            if (userHead == null || getActivity() == null) {
                return;
            }
            Intent intent = new Intent(getActivity(), VideoMain.class);
            intent.putExtra("user_head", userHead);
            startActivity(intent);
        });

        crdnews.setOnClickListener(v -> {
            UserModel userHead = getUserHead();
            if (userHead == null || getActivity() == null) {
                return;
            }
            Intent intent = new Intent(getActivity(), NewsMain.class);
            intent.putExtra("user_head", userHead);
            startActivity(intent);
        });

        crdfoods.setOnClickListener(v -> {
            UserModel userHead = getUserHead();
            if (userHead == null || getActivity() == null) {
                return;
            }
            Intent intent = new Intent(getActivity(), FoodsMain.class);
            intent.putExtra("user_head", userHead);
            startActivity(intent);
        });
    }

    private UserModel getUserHead() {
        if (getActivity() == null) {
            return null;
        }
        return (UserModel) getActivity().getIntent().getSerializableExtra("user_head");
    }

    public class SliderTimer extends TimerTask {
        @Override
        public void run() {
            if (getActivity() == null || lstSlides.isEmpty()) {
                return;
            }
            getActivity().runOnUiThread(() -> {
                if (sliderpager == null || lstSlides.isEmpty()) {
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
