package com.example.andy.dangjian;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CredentialUploadActivity extends AppCompatActivity {

    @BindView(R.id.credential_text)
    TextView credentialTextTextView;
    @BindView(R.id.selected_image_imageview)
    ImageView selectedImageImageView;
    @BindView(R.id.submit)
    TextView submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credential_upload);

        ButterKnife.bind(this);

        String credentialText = getIntent().getStringExtra("Credential");
        Uri uri = getIntent().getParcelableExtra("uri");

        credentialTextTextView.setText(credentialText);

        ContentResolver cr = this.getContentResolver();
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                /* 将Bitmap设定到ImageView */
            selectedImageImageView.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            Log.e("Exception", e.getMessage(), e);
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CredentialUploadActivity.this, "提交完成", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}
