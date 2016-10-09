package com.example.andy.dangjian;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.andy.dangjian.interfaces.UserInterface;
import com.example.andy.dangjian.model.StudentResponse;
import com.example.andy.dangjian.model.UserResponse;
import com.example.andy.dangjian.network.HttpUtils;
import com.example.andy.dangjian.utils.Utils;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class SplashActivity extends AppCompatActivity {

    public static final String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Utils utils = Utils.INSTANCE;
        final String userPhoneNumber = utils.getUserPhoneNumber(this);

        HttpUtils httpUtils = HttpUtils.INSTANCE;
        Retrofit retrofit = httpUtils.getRetrofitInstance();

        final Map<String, String> params = new HashMap<>();
        params.put("mode", "SYS");
        params.put("action", "addSysUser");
        params.put("userid", userPhoneNumber);
        params.put("userpass", userPhoneNumber);
        params.put("firstName", "");
        params.put("lastName", "");
        params.put("email", "");

        final UserInterface userInterface = retrofit.create(UserInterface.class);

        Call<UserResponse> responseCall = userInterface.registerUser(params);

        responseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, retrofit2.Response<UserResponse> response) {

                UserResponse registerResponse = response.body();

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

                    Call<UserResponse> loginCall = userInterface.loginUser(loginParams);
                    loginCall.enqueue(new Callback<UserResponse>() {
                        @Override
                        public void onResponse(Call<UserResponse> call, retrofit2.Response<UserResponse> response) {
                            Log.i(TAG, "onResponse: ");

                            UserResponse loginResponse = response.body();

                            if (loginResponse.getSuccess().equals("1")) {

                                Log.i(TAG, "onResponse: login success");
                                Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }

                        }

                        @Override
                        public void onFailure(Call<UserResponse> call, Throwable t) {
                            Log.i(TAG, "onFailure: login");
                        }
                    });

                }

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: register");
            }
        });

    }

}
