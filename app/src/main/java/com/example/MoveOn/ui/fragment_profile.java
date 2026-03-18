package com.example.MoveOn.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MoveOn.Activity.BMI;
import com.example.MoveOn.Activity.ChangePassword;
import com.example.MoveOn.Activity.HistoryPlay;
import com.example.MoveOn.Activity.LoginActivity;
import com.example.MoveOn.Activity.SettingPage;
import com.example.MoveOn.Activity.ShowData;
import com.example.MoveOn.Adapter.ProfileAdapter;
import com.example.MoveOn.ApiClient;
import com.example.MoveOn.Interface.ApiInterface;
import com.example.MoveOn.Model.ApiResponse;
import com.example.MoveOn.Model.UserModel;
import com.example.MoveOn.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragment_profile extends Fragment {

    private final List<UserModel> profile = new ArrayList<>();
    private ProfileAdapter profileAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        RecyclerView rcv = root.findViewById(R.id.Rv_profile);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcv.setLayoutManager(layoutManager);

        profileAdapter = new ProfileAdapter(profile, getContext());
        rcv.setAdapter(profileAdapter);
        loadProfile();

        GridLayout historyPlay = root.findViewById(R.id.history_play);
        historyPlay.setOnClickListener(v -> openWithUser(HistoryPlay.class));

        GridLayout dataPersonal = root.findViewById(R.id.data_personal);
        dataPersonal.setOnClickListener(v -> openWithUser(ShowData.class));

        GridLayout bmi = root.findViewById(R.id.bmi);
        bmi.setOnClickListener(v -> openWithUser(BMI.class));

        GridLayout setting = root.findViewById(R.id.setting);
        setting.setOnClickListener(v -> openWithUser(SettingPage.class));

        GridLayout changPass = root.findViewById(R.id.chang_pass);
        changPass.setOnClickListener(v -> openWithUser(ChangePassword.class));

        GridLayout logOut = root.findViewById(R.id.log_out);
        logOut.setOnClickListener(v -> startActivity(new Intent(getActivity(), LoginActivity.class)));

        return root;
    }

    private void loadProfile() {
        UserModel userHead = getUserHead();
        if (userHead == null) {
            return;
        }

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ApiResponse<List<UserModel>>> call = apiInterface.getProfile(userHead.getUser_id());
        call.enqueue(new Callback<ApiResponse<List<UserModel>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<UserModel>>> call, Response<ApiResponse<List<UserModel>>> response) {
                ApiResponse<List<UserModel>> body = response.body();
                profile.clear();
                if (body != null && body.getData() != null) {
                    profile.addAll(body.getData());
                }
                Log.i(fragment_profile.class.getSimpleName(), String.valueOf(body));
                profileAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ApiResponse<List<UserModel>>> call, Throwable t) {
                Toast.makeText(getContext(), "ERROR On :" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openWithUser(Class<?> target) {
        UserModel userHead = getUserHead();
        if (userHead == null || getActivity() == null) {
            return;
        }
        Intent intent = new Intent(getActivity(), target);
        intent.putExtra("user_head", userHead);
        startActivity(intent);
    }

    private UserModel getUserHead() {
        if (getActivity() == null) {
            return null;
        }
        return (UserModel) getActivity().getIntent().getSerializableExtra("user_head");
    }
}
