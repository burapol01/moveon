package com.example.MoveOn.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.MoveOn.ApiClient;
import com.example.MoveOn.Interface.ApiInterface;
import com.example.MoveOn.Model.ApiResponse;
import com.example.MoveOn.Model.Datapersonal;
import com.example.MoveOn.Model.UserModel;
import com.example.MoveOn.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteDatapersonal extends AppCompatActivity {

    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_datapersonal);
        deletedatapn();
    }

    private void deletedatapn() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Deleting...");
        progressDialog.show();

        Intent i = getIntent();
        UserModel user_datapn = (UserModel) i.getSerializableExtra("user_head");
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ApiResponse<Datapersonal>> call = apiInterface.Deletedatapn(user_datapn.getUser_id());
        call.enqueue(new Callback<ApiResponse<Datapersonal>>() {
            @Override
            public void onResponse(Call<ApiResponse<Datapersonal>> call, Response<ApiResponse<Datapersonal>> response) {
                progressDialog.dismiss();
                Log.i(DeleteDatapersonal.class.getSimpleName(), response.toString());

                ApiResponse<Datapersonal> body = response.body();
                String value = body != null ? body.getValue() : "0";
                String message = body != null ? body.getMassage() : "Delete failed";

                Toast.makeText(DeleteDatapersonal.this, message, Toast.LENGTH_SHORT).show();
                if ("1".equals(value)) {
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Datapersonal>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DeleteDatapersonal.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
