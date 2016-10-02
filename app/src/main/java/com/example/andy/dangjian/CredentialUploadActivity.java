package com.example.andy.dangjian;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CredentialUploadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credential_upload);

        String credentialText = getIntent().getStringExtra("Credential");

        TextView textView = (TextView) findViewById(R.id.credential_text);
        textView.setText(credentialText);

    }
}
