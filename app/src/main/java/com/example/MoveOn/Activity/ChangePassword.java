package com.example.MoveOn.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
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

public class ChangePassword extends AppCompatActivity {

    EditText name, lastname, email_new, user_new, pass_new;
    ImageView img;

    private CircleImageView mPicture;
    private FloatingActionButton mFabChoosePic;
    private Bitmap bitmap;
    private int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_change_password);

        Intent intent = getIntent();
        user_id = intent.getIntExtra("user_id", 0);

        mPicture = findViewById(R.id.profile_user_new);
        mFabChoosePic = findViewById(R.id.fabChoosePic);

        mFabChoosePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseFile();
            }
        });
    }

    public void Save(View view) {
        updateuser();
    }

    private void updateuser() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();

        email_new = findViewById(R.id.email_new);
        user_new = findViewById(R.id.user_new);
        pass_new = findViewById(R.id.pass_new);

        String edemail = email_new.getText().toString();
        String edusername = user_new.getText().toString();
        String edpassword = pass_new.getText().toString();

        String picture = bitmap == null ? "" : getStringImage(bitmap);

        if (edemail.equals("")) {
            progressDialog.dismiss();
            Toast.makeText(ChangePassword.this, "กรุณาระบุอีเมล์", Toast.LENGTH_SHORT).show();
        } else if (edusername.equals("")) {
            progressDialog.dismiss();
            Toast.makeText(ChangePassword.this, "กรุณาระบุชื่อ", Toast.LENGTH_SHORT).show();
        } else if (edpassword.equals("")) {
            progressDialog.dismiss();
            Toast.makeText(ChangePassword.this, "กรุณาระบุรหัส", Toast.LENGTH_SHORT).show();
        } else {
            Intent i = getIntent();
            UserModel user_head = (UserModel) i.getSerializableExtra("user_head");
            ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
            Call<ApiResponse<UserModel>> call = apiInterface.updateUser(user_head.getUser_id(), edemail, edusername, edpassword, picture);
            call.enqueue(new Callback<ApiResponse<UserModel>>() {

                @Override
                public void onResponse(Call<ApiResponse<UserModel>> call, Response<ApiResponse<UserModel>> response) {
                    progressDialog.dismiss();
                    Log.i(ChangePassword.class.getSimpleName(), response.toString());
                    ApiResponse<UserModel> body = response.body();
                    String value = body != null ? body.getValue() : "0";
                    String message = body != null ? body.getMassage() : "Update failed";

                    Toast.makeText(ChangePassword.this, message, Toast.LENGTH_SHORT).show();
                    if ("1".equals(value)) {
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse<UserModel>> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(ChangePassword.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
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

    private String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }
}
