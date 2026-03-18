package com.example.MoveOn.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.MoveOn.ApiClient;
import com.example.MoveOn.Interface.ApiInterface;
import com.example.MoveOn.Model.ApiResponse;
import com.example.MoveOn.Model.BmiModel;
import com.example.MoveOn.Model.UserModel;
import com.example.MoveOn.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BMI extends AppCompatActivity {

    EditText weight, height;
    TextView resulttext, result;
    String calculation;
    String BMIresult;
    float BMIValue;

    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_bmi);
        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        resulttext = findViewById(R.id.result);
    }

    public void calculateBMI(View view) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving...");
        progressDialog.show();

        String S1 = weight.getText().toString();
        String S2 = height.getText().toString();

        float weightValue = Float.parseFloat(S1);
        float heightValue = Float.parseFloat(S2) / 100;
        float bmi = weightValue / (heightValue * heightValue);

        if (bmi < 16) {
            BMIresult = "กินข้าวบ้างนะ";
        } else if (bmi < 18.5) {
            BMIresult = "ยังต่ำกว่าเกณฑ์นะ";
        } else if (bmi <= 24.9) {
            BMIresult = "ยินด้วยคุณปกติดี";
        } else if (bmi <= 29.9) {
            BMIresult = "อีกนิดจะเป็นหมูแล้วน้าา";
        } else {
            BMIresult = "ลดบ้างนะอ้วนเกินไปแล้ว";
        }
        calculation = "ค่า BMI คือ :" + bmi + "\n" + BMIresult;
        resulttext.setText(calculation);
        BMIValue = bmi;

        Intent i = getIntent();
        UserModel userbmi = (UserModel) i.getSerializableExtra("user_head");

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ApiResponse<BmiModel>> call = apiInterface.bmiuser(BMIValue, BMIresult, userbmi.getUser_id());
        call.enqueue(new Callback<ApiResponse<BmiModel>>() {
            @Override
            public void onResponse(Call<ApiResponse<BmiModel>> call, Response<ApiResponse<BmiModel>> response) {
                progressDialog.dismiss();

                Log.i(DatapersonalActivity.class.getSimpleName(), response.toString());

                ApiResponse<BmiModel> body = response.body();
                String value = body != null ? body.getValue() : "0";
                String message = body != null ? body.getMassage() : "Save failed";

                if (!"1".equals(value)) {
                    Toast.makeText(BMI.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<BmiModel>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(BMI.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        Dialog dialog = new Dialog(BMI.this);
        dialog.setContentView(R.layout.item_dialog_box_bmi);
        resulttext = dialog.findViewById(R.id.result);
        resulttext.setText(calculation);
        Button ok_m = dialog.findViewById(R.id.ok_m);
        ok_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        dialog.show();
    }
}
