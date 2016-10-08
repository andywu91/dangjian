package com.example.andy.dangjian;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andy.dangjian.interfaces.UserInterface;
import com.example.andy.dangjian.model.CustomResponse;
import com.example.andy.dangjian.network.HttpUtils;
import com.example.andy.dangjian.utils.Utils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = LoginActivity.class.getSimpleName();

    @BindView(R.id.user_phone_number_edittext)
    EditText userPhoneNumberEditText;
    @BindView(R.id.firstname_edittext)
    EditText firstNameEditText;
    @BindView(R.id.lastname_edittext)
    EditText lastNameEditText;
    @BindView(R.id.emial_edittext)
    EditText emailEditText;
    @BindView(R.id.login)
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utils utils = Utils.INSTANCE;
                utils.hideSoftInput(LoginActivity.this, userPhoneNumberEditText);

                final String userPhoneNumber = userPhoneNumberEditText.getText().toString();

                if (userPhoneNumber.length() != 11) {
                    Toast.makeText(LoginActivity.this, "请输入11位手机号", Toast.LENGTH_SHORT).show();
                } else {

                    final ProgressDialog dialog = ProgressDialog.show(LoginActivity.this, "请稍后", "正在执行", true, false);

                    String firstName = firstNameEditText.getText().toString();
                    String lastName = lastNameEditText.getText().toString();
                    String email = emailEditText.getText().toString();

                    HttpUtils httpUtils = HttpUtils.INSTANCE;
                    Retrofit retrofit = httpUtils.getRetrofitInstance();

                    final Map<String, String> params = new HashMap<>();
                    params.put("mode", "SYS");
                    params.put("action", "addSysUser");
                    params.put("userid", userPhoneNumber);
                    params.put("userpass", userPhoneNumber);
                    params.put("firstName", firstName);
                    params.put("lastName", lastName);
                    params.put("email", email);

                    final UserInterface userInterface = retrofit.create(UserInterface.class);

                    Call<CustomResponse> responseCall = userInterface.registerUser(params);

                    responseCall.enqueue(new Callback<CustomResponse>() {
                        @Override
                        public void onResponse(Call<CustomResponse> call, retrofit2.Response<CustomResponse> response) {

                            CustomResponse registerResponse = response.body();

                            String success = registerResponse.getSuccess();
                            String res = registerResponse.getRes();

                            boolean registerSuccess = false;
                            if (success.equals("1") || (success.equals("0") && res.equals("ID HAS BEEN USED!"))) {
                                registerSuccess = true;
                            }

                            if (registerSuccess) {
                                Map<String, String> loginParams = new HashMap<>();
                                loginParams.put("mode", "SYS");
                                loginParams.put("action", "checkSysUser");
                                loginParams.put("userid", userPhoneNumber);
                                loginParams.put("userpass", userPhoneNumber);

                                Call<CustomResponse> loginCall = userInterface.loginUser(loginParams);
                                loginCall.enqueue(new Callback<CustomResponse>() {
                                    @Override
                                    public void onResponse(Call<CustomResponse> call, retrofit2.Response<CustomResponse> response) {
                                        Log.i(TAG, "onResponse: ");

                                        CustomResponse loginResponse = response.body();

                                        if (loginResponse.getSuccess().equals("1")) {
                                            Utils utils = Utils.INSTANCE;
                                            utils.saveUserPhoneNumber(LoginActivity.this, userPhoneNumber);

                                            Log.i(TAG, "onResponse: login success");

                                            dialog.dismiss();
                                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

                                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            dialog.dismiss();
                                            Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                                        }

                                    }

                                    @Override
                                    public void onFailure(Call<CustomResponse> call, Throwable t) {
                                        Log.i(TAG, "onFailure: login");

                                        dialog.dismiss();
                                        Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            } else {
                                dialog.dismiss();
                                Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<CustomResponse> call, Throwable t) {
                            Log.i(TAG, "onFailure: register");

                            dialog.dismiss();
                            Toast.makeText(LoginActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
    }

}
