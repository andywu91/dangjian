package com.example.andy.dangjian;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.andy.dangjian.utils.Utils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Utils utils = Utils.INSTANCE;
        String userPhoneNumber = utils.getUserPhoneNumber(this);
        if(userPhoneNumber.isEmpty()){
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(this,SplashActivity.class);
            startActivity(intent);
        }

        finish();
    }
}
