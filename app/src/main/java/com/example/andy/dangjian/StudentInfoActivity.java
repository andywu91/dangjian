package com.example.andy.dangjian;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
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
    @BindView(R.id.student_sfz_query)
    TextView studentSFZQueryTextView;
    @BindView(R.id.student_xlzm_query)
    TextView studentXLZMQueryTextView;
    @BindView(R.id.student_birthday_textview)
    TextView studentBirthdayTextView;
    @BindView(R.id.student_nation_textview)
    TextView studentNationTextView;
    @BindView(R.id.male_button)
    TextView studentMaleTextView;
    @BindView(R.id.female_button)
    TextView studentFemaleTextView;
    @BindView(R.id.student_political_status_textview)
    TextView studentPoliticalStatusTextView;
    @BindView(R.id.student_telephone_textview)
    TextView studentTelephoneTextView;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        ButterKnife.bind(this);

        context = this;

        String studentName = getIntent().getStringExtra("studentName");
        String studentEducation = getIntent().getStringExtra("studentEducation");
        String studentAddress = getIntent().getStringExtra("studentAddress");
        String studentId = getIntent().getStringExtra("studentId");
        String studentBirthday = getIntent().getStringExtra("studentBirthday");
        String studentNation = getIntent().getStringExtra("studentNation");
        String studentSex = getIntent().getStringExtra("studentSex");
        String studentPoliticalStatus = getIntent().getStringExtra("studentPoliticalStatus");
        String studentTelephone = getIntent().getStringExtra("studentTelephone");
        final String studentSFZUrl = getIntent().getStringExtra("studentSFZUrl");
        final String studentXLZMUrl = getIntent().getStringExtra("studentXLZMUrl");

        studentNameTextView.setText(studentName);
        studentEducationTextView.setText(studentEducation);
        studentAddressTextView.setText(studentAddress);
        studentIdTextView.setText(studentId);
        studentBirthdayTextView.setText(studentBirthday);
        studentPoliticalStatusTextView.setText(studentPoliticalStatus);
        studentTelephoneTextView.setText(studentTelephone);
        studentNationTextView.setText(studentNation);
        if(studentSex.equals("0")){
            studentFemaleTextView.setTextColor(ContextCompat.getColor(context, R.color.blue));
            studentFemaleTextView.setBackgroundResource(0);
            studentMaleTextView.setTextColor(ContextCompat.getColor(context, R.color.white));
            studentMaleTextView.setBackgroundResource(R.drawable.oval_bg_blue);
        }else {
            studentMaleTextView.setTextColor(ContextCompat.getColor(context, R.color.blue));
            studentMaleTextView.setBackgroundResource(0);
            studentFemaleTextView.setTextColor(ContextCompat.getColor(context, R.color.white));
            studentFemaleTextView.setBackgroundResource(R.drawable.oval_bg_blue);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (studentSFZUrl == null || studentSFZUrl.equals("")) {
            studentSFZQueryTextView.setVisibility(View.GONE);
        } else {

            studentSFZQueryTextView.setVisibility(View.VISIBLE);
            studentSFZQueryTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(StudentInfoActivity.this, StudentPicInfoActivity.class);
                    intent.putExtra("Credential", "身份证信息图片");
                    intent.putExtra("imageUrl", studentSFZUrl);
                    startActivity(intent);
                }
            });

        }

        if (studentXLZMUrl == null || studentXLZMUrl.equals("")) {
            studentXLZMQueryTextView.setVisibility(View.GONE);
        } else {

            studentXLZMQueryTextView.setVisibility(View.VISIBLE);
            studentXLZMQueryTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(StudentInfoActivity.this, StudentPicInfoActivity.class);
                    intent.putExtra("Credential", "学历证明信息图片");
                    intent.putExtra("imageUrl", studentXLZMUrl);
                    startActivity(intent);
                }
            });


        }

    }
}
