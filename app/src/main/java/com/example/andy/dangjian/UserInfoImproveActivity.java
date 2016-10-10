package com.example.andy.dangjian;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andy.dangjian.interfaces.StudentInterface;
import com.example.andy.dangjian.model.StudentResponse;
import com.example.andy.dangjian.network.HttpUtils;
import com.example.andy.dangjian.utils.Utils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserInfoImproveActivity extends AppCompatActivity {

    @BindView(R.id.student_name_edittext)
    EditText studentNameEdittext;
    @BindView(R.id.male_button)
    TextView maleButton;
    @BindView(R.id.female_button)
    TextView femaleButton;
    @BindView(R.id.student_education_edittext)
    EditText studentEducationEdittext;
    @BindView(R.id.student_address_edittext)
    EditText studentAddressEdittext;
    @BindView(R.id.student_picId_edittext)
    EditText studentPicIdEdittext;
    @BindView(R.id.student_birthday_textview)
    TextView studentBirthdayTextView;
    @BindView(R.id.student_nation_edittext)
    EditText studentNationEdittext;
    @BindView(R.id.student_political_status_edittext)
    EditText studentPoliticalStatusEdittext;
    @BindView(R.id.student_telephone_edittext)
    EditText studentTelephoneEdittext;
    @BindView(R.id.submit)
    ImageView submitTextView;
    @BindView(R.id.back)
    ImageView back;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_improve);

        ButterKnife.bind(this);

        context = this;

        maleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                femaleButton.setTextColor(ContextCompat.getColor(context, R.color.blue));
                femaleButton.setBackgroundResource(0);
                maleButton.setTextColor(ContextCompat.getColor(context, R.color.white));
                maleButton.setBackgroundResource(R.drawable.oval_bg_blue);
            }
        });

        femaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maleButton.setTextColor(ContextCompat.getColor(context, R.color.blue));
                maleButton.setBackgroundResource(0);
                femaleButton.setTextColor(ContextCompat.getColor(context, R.color.white));
                femaleButton.setBackgroundResource(R.drawable.oval_bg_blue);
            }
        });


        studentBirthdayTextView.setText("请点击选择日期");

        studentBirthdayTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DatePickerDialog mDialog = new DatePickerDialog(UserInfoImproveActivity.this,AlertDialog.THEME_HOLO_LIGHT,null,
                        1980, 1, 1);

                //手动设置按钮
                mDialog.setButton(DialogInterface.BUTTON_POSITIVE, "完成", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //通过mDialog.getDatePicker()获得dialog上的DatePicker组件，然后可以获取日期信息
                        DatePicker datePicker = mDialog.getDatePicker();
                        int year = datePicker.getYear();
                        int month = datePicker.getMonth();
                        int day = datePicker.getDayOfMonth();

                        studentBirthdayTextView.setText(year+"-"+month+"-"+day);
                    }
                });
                //取消按钮，如果不需要直接不设置即可
                mDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                mDialog.show();

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        submitTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Utils utils = Utils.INSTANCE;
                utils.hideSoftInput(UserInfoImproveActivity.this, studentNameEdittext);
                utils.hideSoftInput(UserInfoImproveActivity.this, studentEducationEdittext);
                utils.hideSoftInput(UserInfoImproveActivity.this, studentAddressEdittext);
                utils.hideSoftInput(UserInfoImproveActivity.this, studentPicIdEdittext);
                utils.hideSoftInput(UserInfoImproveActivity.this, studentNationEdittext);
                utils.hideSoftInput(UserInfoImproveActivity.this, studentPoliticalStatusEdittext);
                utils.hideSoftInput(UserInfoImproveActivity.this, studentTelephoneEdittext);

                String studentName = studentNameEdittext.getText().toString();
                if (studentName.equals("")) {
                    Toast.makeText(UserInfoImproveActivity.this, "请输入姓名", Toast.LENGTH_SHORT).show();
                    return;
                }

                String studentEducation = studentEducationEdittext.getText().toString();
                if (studentEducation.equals("")) {
                    Toast.makeText(UserInfoImproveActivity.this, "请输入学历", Toast.LENGTH_SHORT).show();
                    return;
                }

                String studentAddress = studentAddressEdittext.getText().toString();
                if (studentAddress.equals("")) {
                    Toast.makeText(UserInfoImproveActivity.this, "请输入地址", Toast.LENGTH_SHORT).show();
                    return;
                }

                final String studentPicId = studentPicIdEdittext.getText().toString();
                if (studentPicId.equals("")) {
                    Toast.makeText(UserInfoImproveActivity.this, "请输入身份证后六位", Toast.LENGTH_SHORT).show();
                    return;
                }

                final String studentBirthday = studentBirthdayTextView.getText().toString();
                if (studentBirthday.equals("")) {
                    Toast.makeText(UserInfoImproveActivity.this, "请输入出生日期", Toast.LENGTH_SHORT).show();
                    return;
                }

                final String studentNation = studentNationEdittext.getText().toString();
                if (studentBirthday.equals("")) {
                    Toast.makeText(UserInfoImproveActivity.this, "请输入民族", Toast.LENGTH_SHORT).show();
                    return;
                }

                final String studentPoliticalStatus = studentPoliticalStatusEdittext.getText().toString();
                if (studentBirthday.equals("")) {
                    Toast.makeText(UserInfoImproveActivity.this, "请输入政治面貌", Toast.LENGTH_SHORT).show();
                    return;
                }

                final String studentTelephone = studentTelephoneEdittext.getText().toString();
                if (studentBirthday.equals("")) {
                    Toast.makeText(UserInfoImproveActivity.this, "请输入联系电话", Toast.LENGTH_SHORT).show();
                    return;
                }


                final ProgressDialog dialog = ProgressDialog.show(UserInfoImproveActivity.this, "请稍后", "正在执行", true, false);

                Map<String, String> params = new HashMap<>();
                params.put("mode", "SP");
                params.put("action", "addStdUser");

                String userPhoneNumber = utils.getUserPhoneNumber(UserInfoImproveActivity.this);
                params.put("userid", userPhoneNumber);

                params.put("stdname", studentName);
                params.put("pid", studentPicId);

                if (maleButton.getCurrentTextColor() == ContextCompat.getColor(context, R.color.white)) {
                    params.put("sex", "男");
                } else {
                    params.put("sex", "女");
                }
                params.put("education", studentEducation);
                params.put("address", studentAddress);
                params.put("birthday",studentBirthday);
                params.put("nation",studentNation);
                params.put("political_status",studentPoliticalStatus);
                params.put("tel",studentTelephone);

                HttpUtils httpUtils = HttpUtils.INSTANCE;
                Retrofit retrofit = httpUtils.getRetrofitInstance();
                StudentInterface studentInterface = retrofit.create(StudentInterface.class);
                Call<StudentResponse> registerStudent = studentInterface.registerStudent(params);

                registerStudent.enqueue(new Callback<StudentResponse>() {
                    @Override
                    public void onResponse(Call<StudentResponse> call, Response<StudentResponse> response) {

                        StudentResponse registerStudentResponse = response.body();
                        if (registerStudentResponse != null && registerStudentResponse.getSuccess().equals("1")) {

                            dialog.dismiss();

                            String status = registerStudentResponse.getStatus();
                            if (status.equals("001")) {
                                Toast.makeText(UserInfoImproveActivity.this, "对不起，登陆账号不合法，不可提交", Toast.LENGTH_SHORT).show();
                            } else if (status.equals("100")) {
                                Toast.makeText(UserInfoImproveActivity.this, "提交成功！", Toast.LENGTH_SHORT).show();

                                utils.saveUserPidNumber(UserInfoImproveActivity.this, studentPicId);

                            } else if (status.equals("101")) {
                                Toast.makeText(UserInfoImproveActivity.this, "对不起，该学员已经存在", Toast.LENGTH_SHORT).show();
                            }

                            finish();

                        } else {
                            dialog.dismiss();
                            Toast.makeText(UserInfoImproveActivity.this, "对不起，提交失败", Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<StudentResponse> call, Throwable t) {
                        dialog.dismiss();
                        Toast.makeText(UserInfoImproveActivity.this, "对不起，提交失败", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}
