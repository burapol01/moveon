package com.example.MoveOn.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MoveOn.Adapter.ShowdatapnAdapter;
import com.example.MoveOn.ApiClient;
import com.example.MoveOn.Interface.ApiInterface;
import com.example.MoveOn.Model.ApiResponse;
import com.example.MoveOn.Model.Datapersonal;
import com.example.MoveOn.Model.UserModel;
import com.example.MoveOn.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowData extends AppCompatActivity {

    private final List<Datapersonal> datapn = new ArrayList<>();
    private ShowdatapnAdapter showdatapnAdapter;
    private ApiInterface apiInterface;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdatapn);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        progressBar = findViewById(R.id.progress2);

        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        showdatapnAdapter = new ShowdatapnAdapter(datapn, this);
        recyclerView.setAdapter(showdatapnAdapter);

        datapn();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getShoedatapn();
    }

    private void getShoedatapn() {
        UserModel userHead = (UserModel) getIntent().getSerializableExtra("user_head");
        if (userHead == null) {
            progressBar.setVisibility(View.GONE);
            return;
        }

        Call<ApiResponse<List<Datapersonal>>> call = apiInterface.datapnsh(userHead.getUser_id());
        call.enqueue(new Callback<ApiResponse<List<Datapersonal>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Datapersonal>>> call, Response<ApiResponse<List<Datapersonal>>> response) {
                progressBar.setVisibility(View.GONE);
                ApiResponse<List<Datapersonal>> body = response.body();
                datapn.clear();
                if (body != null && body.getData() != null) {
                    datapn.addAll(body.getData());
                }
                Log.i(ShowData.class.getSimpleName(), String.valueOf(body));
                showdatapnAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Datapersonal>>> call, Throwable t) {
                Toast.makeText(ShowData.this, "rp :" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void datapn() {
        Button insertDatapn = findViewById(R.id.insert_datapn);
        insertDatapn.setOnClickListener(v -> {
            UserModel userHead = (UserModel) getIntent().getSerializableExtra("user_head");
            Intent intent = new Intent(ShowData.this, DatapersonalActivity.class);
            intent.putExtra("user_head", userHead);
            startActivity(intent);
        });

        Button editDatapn = findViewById(R.id.edit_datapn);
        editDatapn.setOnClickListener(v -> {
            UserModel userHead = (UserModel) getIntent().getSerializableExtra("user_head");
            Intent intent = new Intent(ShowData.this, EditDatapersonal.class);
            intent.putExtra("user_head", userHead);
            startActivity(intent);
        });

        Button delDatapn = findViewById(R.id.del_datapn);
        delDatapn.setOnClickListener(v -> {
            UserModel userHead = (UserModel) getIntent().getSerializableExtra("user_head");
            Intent intent = new Intent(ShowData.this, DeleteDatapersonal.class);
            intent.putExtra("user_head", userHead);
            startActivity(intent);
        });
    }
}
