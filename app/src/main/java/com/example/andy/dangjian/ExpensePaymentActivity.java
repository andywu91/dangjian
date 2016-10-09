package com.example.andy.dangjian;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExpensePaymentActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.alipay_layout)
    RelativeLayout alipayLayout;
    @BindView(R.id.wechat_layout)
    RelativeLayout wechatLayout;
    @BindView(R.id.icbc_imageview)
    ImageView icbcImageView;
    @BindView(R.id.boc_imageview)
    ImageView bocImageView;
    @BindView(R.id.cmb_imageview)
    ImageView cmbImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_payment);

        ButterKnife.bind(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        alipayLayout.setOnClickListener(this);
        wechatLayout.setOnClickListener(this);
        icbcImageView.setOnClickListener(this);
        bocImageView.setOnClickListener(this);
        cmbImageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "敬请期待", Toast.LENGTH_SHORT).show();
    }
}
