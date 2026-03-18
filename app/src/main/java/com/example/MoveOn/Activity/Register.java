package com.example.MoveOn.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.MoveOn.ApiClient;
import com.example.MoveOn.Interface.ApiInterface;
import com.example.MoveOn.Model.ApiResponse;
import com.example.MoveOn.Model.UserModel;
import com.example.MoveOn.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register<user_password> extends AppCompatActivity {

    EditText name, lastname, username, email, password;
    ImageView img;

    private CircleImageView mPicture;
    private FloatingActionButton mFabChoosePic;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        mPicture = findViewById(R.id.profile_user_et);
        mFabChoosePic = findViewById(R.id.fabChoosePic);

        mFabChoosePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseFile();
            }
        });

        setDataFromIntentExtra();
    }

    private void chooseFile() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                mPicture.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setDataFromIntentExtra() {
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
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    public void clicksingup(View view) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving...");
        progressDialog.show();

        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        String edemail = email.getText().toString();
        String edusername = username.getText().toString();
        String edpassword = password.getText().toString();

        String picture = bitmap == null ? "" : getStringImage(bitmap);

        if (edemail.equals("")) {
            progressDialog.dismiss();
            Toast.makeText(Register.this, "กรุณาระบุอีเมล์", Toast.LENGTH_SHORT).show();
        } else if (edusername.equals("")) {
            progressDialog.dismiss();
            Toast.makeText(Register.this, "กรุณาระบุชื่อ", Toast.LENGTH_SHORT).show();
        } else if (edpassword.equals("")) {
            progressDialog.dismiss();
            Toast.makeText(Register.this, "กรุณาระบุรหัส", Toast.LENGTH_SHORT).show();
        } else {
            ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
            Call<ApiResponse<UserModel>> call = apiInterface.regis(edemail, edusername, edpassword, picture);
            call.enqueue(new Callback<ApiResponse<UserModel>>() {

                @Override
                public void onResponse(Call<ApiResponse<UserModel>> call, Response<ApiResponse<UserModel>> response) {
                    progressDialog.dismiss();
                    ApiResponse<UserModel> body = response.body();
                    String value = body != null ? body.getValue() : "0";
                    String message = body != null ? body.getMassage() : "Register failed";

                    if ("1".equals(value)) {
                        finish();
                    } else {
                        Toast.makeText(Register.this, message, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse<UserModel>> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(Register.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }
}
