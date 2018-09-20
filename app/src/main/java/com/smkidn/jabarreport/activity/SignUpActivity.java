package com.smkidn.jabarreport.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.smkidn.jabarreport.R;
import com.smkidn.jabarreport.api.ApiClient;
import com.smkidn.jabarreport.api.ApiInterface;
import com.smkidn.jabarreport.model.register.ResponseRegister;
import com.smkidn.jabarreport.util.CheckConnection;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    AlertDialog waitDialog;

    String nUser, nPass, nGender, nAlamat, nFull, nEmail;

    @BindView(R.id.etSFullname)
    EditText etFullName;
    @BindView(R.id.etSEmail)
    EditText etEmail;
    @BindView(R.id.etSUsername)
    EditText etUser;
    @BindView(R.id.etSPassword)
    EditText etPass;
    @BindView(R.id.radioGroupNb)
    RadioGroup radioGender;
    @BindView(R.id.etSAlamat)
    EditText etAlamat;
    @BindView(R.id.btnSSignup)
    Button btnSignUp;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        waitDialog = new SpotsDialog(this);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btnSSignup)
    public void onViewClicked() {
        nUser = etUser.getText().toString();
        nAlamat = etAlamat.getText().toString();
        nPass = etPass.getText().toString();
        nFull = etFullName.getText().toString();
        nEmail = etEmail.getText().toString();

        int selectedGender = radioGender.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedGender);
        if (radioButton.getText().equals("Laki Laki")) {
            nGender = "L";
        } else if (radioButton.getText().equals("Perempuan")) {
            nGender = "P";
        }

        if (TextUtils.isEmpty(nUser)) {
            etUser.setError("Username tidak boleh kosong");
            etUser.requestFocus();
        }

        if (TextUtils.isEmpty(nPass)) {
            etPass.setError("Password tidak boleh kosong");
            etPass.requestFocus();
        }

        if (TextUtils.isEmpty(nAlamat)) {
            etAlamat.setError("Alamat tidak boleh kosong");
            etAlamat.requestFocus();
        }

        if (TextUtils.isEmpty(nEmail)) {
            etEmail.setError("Email tidak boleh kosong");
            etEmail.requestFocus();
        }

        if (TextUtils.isEmpty(nFull)) {
            etFullName.setError("Fullname tidak boleh kosong");
            etFullName.requestFocus();
        }

        if (nPass.length() <= 6) {
            etPass.setError("Password minimal 6 karakter");
            etPass.requestFocus();
        }

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (!nEmail.matches(emailPattern)) {
            etEmail.setError("Email tidak valid");
            etEmail.requestFocus();
        }
        if (nEmail.matches(emailPattern) && !TextUtils.isEmpty(nUser) && !TextUtils.isEmpty(nPass) && !TextUtils.isEmpty(nAlamat) && !TextUtils.isEmpty(nEmail) && !TextUtils.isEmpty(nFull) && CheckConnection.checkInternet(this)) {
            register();
            hideInputMethod();
            waitDialog.show();
        } else if (!CheckConnection.checkInternet(this)) {
            Toast.makeText(this, getString(R.string.connection), Toast.LENGTH_SHORT).show();
        }
    }

    private void register() {
        ApiInterface apiInterface = ApiClient.getInstance();
        Call<ResponseRegister> call = apiInterface.responseRegister(nFull, nEmail, nUser, nPass, nAlamat, "", nGender);
        call.enqueue(new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                waitDialog.dismiss();
                Log.i("response", response.body().getMsg() + " " + response.body().getLog());
                if (response.body().getResult().equals("true")) {
                    Toast.makeText(SignUpActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                    etEmail.setText("");
                    etAlamat.setText("");
                    etFullName.setText("");
                    etPass.setText("");
                    etUser.setText("");
                } else if (response.body().getResult().equals("false")) {
                    if (response.body().getMsg().equals("Email already taken")) {
                        etEmail.requestFocus();
                        etEmail.setError(response.body().getMsg());
                    } else {
                        Toast.makeText(SignUpActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                waitDialog.dismiss();
                Log.d("Error Cause; ", t.getMessage());
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (!TextUtils.isEmpty(etAlamat.getText().toString()) || !TextUtils.isEmpty(etEmail.getText().toString()) || !TextUtils.isEmpty(etPass.getText().toString()) || !TextUtils.isEmpty(etFullName.getText().toString()) || !TextUtils.isEmpty(etUser.getText().toString())) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("Yakin ingin keluar?")
                    .setMessage("Perubahan yang anda lakukan tidak akan disimpan")
                    .setPositiveButton("Yakin", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                        }
                    })
                    .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
            builder.show();
        } else {
            super.onBackPressed();
        }
    }

    public void hideInputMethod() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
