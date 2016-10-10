package com.example.andy.dangjian;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentPicInfoActivity extends AppCompatActivity {

    @BindView(R.id.credential_text)
    TextView credentialTextTextView;
    @BindView(R.id.selected_image_imageview)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_pic_info);

        ButterKnife.bind(this);

        String credentialText = getIntent().getStringExtra("Credential");

        credentialTextTextView.setText(credentialText);

        String imageUrl = getIntent().getStringExtra("imageUrl");

        final ProgressDialog dialog = ProgressDialog.show(this, null, "正在加载，请稍候。。。", true, false);

        Glide.with(this)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(new GlideDrawableImageViewTarget(imageView) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);

                        dialog.dismiss();
                    }
                });

    }
}
