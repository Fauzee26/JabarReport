package com.smkidn.jabarreport.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.smkidn.jabarreport.R;
import com.smkidn.jabarreport.api.ApiClient;
import com.smkidn.jabarreport.api.ApiInterface;
import com.smkidn.jabarreport.model.login.ResponseLogin;
import com.smkidn.jabarreport.util.CheckConnection;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    @BindView(R.id.etUsername)
    EditText etUsername;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.btnSignIn)
    Button btnSignIn;
    @BindView(R.id.txtSignup)
    TextView txtSignup;
    @BindView(R.id.txtLupapass)
    TextView txtLupapass;
    @BindView(R.id.btnSignGoogle)
    Button btnSignGoogle;

    String user, pass;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        dialog = new SpotsDialog(this);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnSignIn, R.id.txtSignup, R.id.txtLupapass, R.id.btnSignGoogle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSignIn:
                user = etUsername.getText().toString();
                pass = etPassword.getText().toString();

                if (TextUtils.isEmpty(user)) {
                    etUsername.setError("Username tidak boleh kosong");
                    etUsername.requestFocus();
                }

                if (TextUtils.isEmpty(pass)) {
                    etPassword.setError("Password tidak boleh kosong");
                    etPassword.requestFocus();
                }

                if (CheckConnection.checkInternet(this)) {
                    hideInputMethod();
                    login();
                    dialog.show();
                } else
                    Toast.makeText(this, getString(R.string.connection), Toast.LENGTH_SHORT).show();
                break;
            case R.id.txtSignup:
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
                break;
            case R.id.txtLupapass:
                break;
            case R.id.btnSignGoogle:
                break;
        }
    }

    private void login() {
        ApiInterface apiInterface = ApiClient.getInstance();
        Call<ResponseLogin> call = apiInterface.response_login(user, pass);
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                dialog.dismiss();
                if (response.body().getResult().equals("true")) {
                    Toast.makeText(SignInActivity.this, response.body().getLog(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignInActivity.this, MainActivity.class));
                } else if (response.body().getResult().equals("false")) {
                    etPassword.setError(response.body().getLog());
                    etPassword.requestFocus();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(SignInActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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
