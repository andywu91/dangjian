package com.example.andy.dangjian;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andy.dangjian.interfaces.StudentInterface;
import com.example.andy.dangjian.model.CustomResponse;
import com.example.andy.dangjian.network.HttpUtils;
import com.example.andy.dangjian.utils.Utils;

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
    @BindView(R.id.submit)
    ImageView submitTextView;

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

        submitTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utils utils = Utils.INSTANCE;
                utils.hideSoftInput(UserInfoImproveActivity.this, studentNameEdittext);
                utils.hideSoftInput(UserInfoImproveActivity.this, studentEducationEdittext);
                utils.hideSoftInput(UserInfoImproveActivity.this, studentAddressEdittext);
                utils.hideSoftInput(UserInfoImproveActivity.this, studentPicIdEdittext);

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

                String studentPicId = studentPicIdEdittext.getText().toString();
                if (studentPicId.equals("")) {
                    Toast.makeText(UserInfoImproveActivity.this, "请输入身份证后六位", Toast.LENGTH_SHORT).show();
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
                params.put("pic_id", "12");
                if (maleButton.getCurrentTextColor() == ContextCompat.getColor(context, R.color.white)) {
                    params.put("sex", "男");
                } else {
                    params.put("sex", "女");
                }
                params.put("education", studentEducation);
                params.put("address", studentAddress);

                HttpUtils httpUtils = HttpUtils.INSTANCE;
                Retrofit retrofit = httpUtils.getRetrofitInstance();
                StudentInterface studentInterface = retrofit.create(StudentInterface.class);
                Call<CustomResponse> registerStudent = studentInterface.registerStudent(params);

                registerStudent.enqueue(new Callback<CustomResponse>() {
                    @Override
                    public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {

                        CustomResponse registerStudentResponse = response.body();
                        if (registerStudentResponse != null && registerStudentResponse.getSuccess().equals("1")) {

                            dialog.dismiss();

                            String status = registerStudentResponse.getStatus();
                            if (status.equals("001")) {
                                Toast.makeText(UserInfoImproveActivity.this, "登陆账号不合法，不可提交", Toast.LENGTH_SHORT).show();
                            } else if (status.equals("100")) {
                                Toast.makeText(UserInfoImproveActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                            } else if (status.equals("101")) {
                                Toast.makeText(UserInfoImproveActivity.this, "该学员已经存在", Toast.LENGTH_SHORT).show();
                            }

                            finish();

                        } else {
                            dialog.dismiss();
                            Toast.makeText(UserInfoImproveActivity.this, "提交失败", Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<CustomResponse> call, Throwable t) {
                        dialog.dismiss();
                        Toast.makeText(UserInfoImproveActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}
