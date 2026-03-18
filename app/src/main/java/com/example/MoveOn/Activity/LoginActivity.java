package com.example.MoveOn.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.MoveOn.ApiClient;
import com.example.MoveOn.Interface.ApiInterface;
import com.example.MoveOn.Model.ApiResponse;
import com.example.MoveOn.Model.UserModel;
import com.example.MoveOn.R;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "MoveOnLogin";

    final String PREF_NAME = "LoginPreferences";
    final String KEY_USERNAME = "Username";
    final String KEY_REMEMBER = "RememberUsername";

    SharedPreferences sp;
    SharedPreferences.Editor editor;
    CheckBox chkremem;
    EditText etusername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();

        etusername = findViewById(R.id.user_username);
        etusername.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            public void afterTextChanged(Editable s) {
                editor = sp.edit();
                editor.putString(KEY_USERNAME, s.toString());
                editor.apply();
            }
        });

        chkremem = findViewById(R.id.chkremem);
        chkremem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean(KEY_REMEMBER, isChecked);
                editor.apply();
            }
        });

        boolean isRemember = sp.getBoolean(KEY_REMEMBER, false);
        chkremem.setChecked(isRemember);

        if (isRemember) {
            String username = sp.getString(KEY_USERNAME, "");
            etusername.setText(username);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    public void click(View view) {
        EditText etemail = findViewById(R.id.user_username);
        EditText etpassword = findViewById(R.id.user_password);
        String email = etemail.getText().toString().trim();
        String userPassword = etpassword.getText().toString().trim();

        if (validaLogin(email, userPassword)) {
            doLogin(email, userPassword);
        }
    }

    private boolean validaLogin(String userUsername, String userPassword) {
        if (userUsername == null || userUsername.trim().length() == 0) {
            Toast.makeText(this, "กรุณากรอกชื่อผู้ใช้หรืออีเมล", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (userPassword == null || userPassword.trim().length() == 0) {
            Toast.makeText(this, "กรุณากรอกรหัสผ่าน", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void doLogin(final String email, final String userPassword) {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ApiResponse<UserModel>> call = apiInterface.login(email, userPassword);
        Log.d(TAG, "Login request url: " + call.request().url());

        call.enqueue(new Callback<ApiResponse<UserModel>>() {
            @Override
            public void onResponse(Call<ApiResponse<UserModel>> call, Response<ApiResponse<UserModel>> response) {
                ApiResponse<UserModel> body = response.body();
                UserModel user = body != null ? body.getData() : null;
                String errorBody = readErrorBody(response);

                Log.d(TAG, "Login response code: " + response.code());
                Log.d(TAG, "Login response successful: " + response.isSuccessful());
                Log.d(TAG, "Login response body value: " + (body != null ? body.getValue() : "null"));
                Log.d(TAG, "Login response body message: " + (body != null ? body.getMessage() : "null"));
                if (!errorBody.isEmpty()) {
                    Log.e(TAG, "Login error body: " + errorBody);
                }

                if (response.isSuccessful() && body != null && body.isSuccess() && user != null) {
                    Intent intent = new Intent(LoginActivity.this, home.class);
                    intent.putExtra("user_head", user);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, user.getUser_username(), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (body != null && body.getMessage() != null && !body.getMessage().trim().isEmpty()) {
                    Toast.makeText(LoginActivity.this, body.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Login failed: HTTP " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(LoginActivity.this, "Login failed: empty or invalid response", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ApiResponse<UserModel>> call, Throwable t) {
                Log.e(TAG, "Login onFailure for url: " + call.request().url(), t);
                Toast.makeText(LoginActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String readErrorBody(Response<?> response) {
        if (response.errorBody() == null) {
            return "";
        }

        try {
            return response.errorBody().string();
        } catch (IOException e) {
            Log.e(TAG, "Cannot read error body", e);
            return "";
        }
    }

    public void Register(View view) {
        Intent intent = new Intent(LoginActivity.this, Register.class);
        startActivity(intent);
    }
}
