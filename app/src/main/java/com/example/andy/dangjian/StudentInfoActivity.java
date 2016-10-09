package com.example.andy.dangjian;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentInfoActivity extends AppCompatActivity {

    @BindView(R.id.student_name_textview)
    TextView studentNameTextView;
    @BindView(R.id.student_education_textview)
    TextView studentEducationTextView;
    @BindView(R.id.student_address_textview)
    TextView studentAddressTextView;
    @BindView(R.id.student_id_textview)
    TextView studentIdTextView;
    @BindView(R.id.back)
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        ButterKnife.bind(this);

        String studentName = getIntent().getStringExtra("studentName");
        String studentEducation = getIntent().getStringExtra("studentEducation");
        String studentAddress = getIntent().getStringExtra("studentAddress");
        String studentId = getIntent().getStringExtra("studentId");

        studentNameTextView.setText(studentName);
        studentEducationTextView.setText(studentEducation);
        studentAddressTextView.setText(studentAddress);
        studentIdTextView.setText(studentId);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
