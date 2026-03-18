package com.example.MoveOn.Activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.MoveOn.ApiClient;
import com.example.MoveOn.Interface.ApiInterface;
import com.example.MoveOn.Model.ApiResponse;
import com.example.MoveOn.Model.Datapersonal;
import com.example.MoveOn.Model.UserModel;
import com.example.MoveOn.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditDatapersonal extends AppCompatActivity {

    private Spinner mtitlernameSpinner;
    private Spinner mGenderSpinner;
    private Spinner mstatusSpinner;
    private EditText mName;
    private EditText mLastName;
    private EditText mWeight;
    private EditText mHeight;
    private EditText mBirth;
    private EditText mAddress;
    private Calendar myCalendar = Calendar.getInstance();

    private int mtitelnameId = 0;
    private int msexId = 0;
    private int mstatLevId = 0;

    public static final int title_nine = 1;
    public static final int title_nang = 2;
    public static final int title_nangsaw = 3;

    public static final int GENDER_MALE = 1;
    public static final int GENDER_FEMALE = 2;
    public static final int GENDER_UNKNOWN = 3;

    public static final int status_student = 1;
    public static final int status_Workingage = 2;
    public static final int status_Theelderly = 3;

    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_datapersonal);

        mName = findViewById(R.id.edit_name);
        mLastName = findViewById(R.id.edit_lastname);
        mWeight = findViewById(R.id.edit_weight1);
        mHeight = findViewById(R.id.edit_height1);
        mBirth = findViewById(R.id.edit_birth);
        mAddress = findViewById(R.id.edit_address);

        mtitlernameSpinner = findViewById(R.id.fname_edit);
        mGenderSpinner = findViewById(R.id.edit_gender);
        mstatusSpinner = findViewById(R.id.edit_status_level);

        mBirth.setFocusableInTouchMode(false);
        mBirth.setFocusable(false);
        mBirth.setOnClickListener(v -> new DatePickerDialog(
                EditDatapersonal.this,
                date,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show());

        setupSpinner1();
        setupSpinner2();
        setupSpinner3();
        editdatapn();
    }

    private void setupSpinner1() {
        ArrayAdapter<?> titelSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.array_title_options, android.R.layout.simple_spinner_item);
        titelSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mtitlernameSpinner.setAdapter(titelSpinnerAdapter);

        mtitlernameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.title_nine))) {
                        mtitelnameId = title_nine;
                    } else if (selection.equals(getString(R.string.title_nang))) {
                        mtitelnameId = title_nang;
                    } else {
                        mtitelnameId = title_nangsaw;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mtitelnameId = 0;
            }
        });
    }

    private void setupSpinner2() {
        ArrayAdapter<?> genderSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.array_gender_options, android.R.layout.simple_spinner_item);
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mGenderSpinner.setAdapter(genderSpinnerAdapter);

        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_male))) {
                        msexId = GENDER_MALE;
                    } else if (selection.equals(getString(R.string.gender_female))) {
                        msexId = GENDER_FEMALE;
                    } else {
                        msexId = GENDER_UNKNOWN;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                msexId = 0;
            }
        });
    }

    private void setupSpinner3() {
        ArrayAdapter<?> statSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.array_stat_options, android.R.layout.simple_spinner_item);
        statSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mstatusSpinner.setAdapter(statSpinnerAdapter);

        mstatusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.status_student))) {
                        mstatLevId = status_student;
                    } else if (selection.equals(getString(R.string.status_Workingage))) {
                        mstatLevId = status_Workingage;
                    } else {
                        mstatLevId = status_Theelderly;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mstatLevId = 0;
            }
        });
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setBirth();
        }

        private void setBirth() {
            String myFormat = "dd MMMM yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            mBirth.setText(sdf.format(myCalendar.getTime()));
        }
    };

    private void editdatapn() {
        Button btnDatapnEdit = findViewById(R.id.btn_datapn_edit);
        btnDatapnEdit.setOnClickListener(v -> {
            ProgressDialog progressDialog = new ProgressDialog(EditDatapersonal.this);
            progressDialog.setMessage("EditSave...");
            progressDialog.show();

            String name = mName.getText().toString().trim();
            String lastname = mLastName.getText().toString().trim();
            String weight = mWeight.getText().toString().trim();
            String height = mHeight.getText().toString().trim();
            String birth = mBirth.getText().toString().trim();
            String address = mAddress.getText().toString().trim();

            UserModel userDatapn = (UserModel) getIntent().getSerializableExtra("user_head");
            if (userDatapn == null) {
                progressDialog.dismiss();
                return;
            }

            apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
            Call<ApiResponse<Datapersonal>> call = apiInterface.Editdatapn(
                    mtitelnameId,
                    name,
                    lastname,
                    msexId,
                    mstatLevId,
                    weight,
                    height,
                    birth,
                    address,
                    userDatapn.getUser_id());

            call.enqueue(new Callback<ApiResponse<Datapersonal>>() {
                @Override
                public void onResponse(Call<ApiResponse<Datapersonal>> call, Response<ApiResponse<Datapersonal>> response) {
                    progressDialog.dismiss();
                    Log.i(DatapersonalActivity.class.getSimpleName(), response.toString());

                    ApiResponse<Datapersonal> body = response.body();
                    if (body != null && body.isSuccess()) {
                        finish();
                    } else {
                        Toast.makeText(EditDatapersonal.this, body != null ? body.getMessage() : "Edit failed", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse<Datapersonal>> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(EditDatapersonal.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
