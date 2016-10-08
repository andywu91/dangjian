package com.example.andy.dangjian;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExaminationPlaceQueryActivity extends AppCompatActivity {

    @BindView(R.id.submit)
    Button sumbit;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examination_place_query);

        ButterKnife.bind(this);
        
        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ExaminationPlaceQueryActivity.this, "敬请期待！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
