package com.example.andy.dangjian;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andy.dangjian.interfaces.StudentInterface;
import com.example.andy.dangjian.model.StudentResponse;
import com.example.andy.dangjian.model.Student;
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

public class UserInfoQueryActivity extends AppCompatActivity {

    @BindView(R.id.student_picId_edittext)
    EditText studentPicIdEditText;
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.review_state)
    TextView reviewStateTextview;
    @BindView(R.id.back)
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_query);

        ButterKnife.bind(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utils utils = Utils.INSTANCE;
                utils.hideSoftInput(UserInfoQueryActivity.this, studentPicIdEditText);

                final String studentPicId = studentPicIdEditText.getText().toString();
                if (studentPicId.equals("")) {
                    Toast.makeText(UserInfoQueryActivity.this, "请输入身份证号", Toast.LENGTH_SHORT).show();
                    return;
                }

                final ProgressDialog dialog = ProgressDialog.show(UserInfoQueryActivity.this, "请稍后", "正在执行", true, false);

                HttpUtils httpUtils = HttpUtils.INSTANCE;
                Retrofit retrofit = httpUtils.getRetrofitInstance();
                StudentInterface studentInterface = retrofit.create(StudentInterface.class);

                Map<String, String> params = new HashMap<>();
                params.put("mode", "SP");
                params.put("action", "checkStdUser");
                params.put("pid", studentPicId);
                Call<StudentResponse> getStudentInfoCall = studentInterface.getStudentInfo(params);
                getStudentInfoCall.enqueue(new Callback<StudentResponse>() {
                    @Override
                    public void onResponse(Call<StudentResponse> call, Response<StudentResponse> response) {

                        dialog.dismiss();

                        StudentResponse getStudentInfoResponse = response.body();
                        String status = getStudentInfoResponse.getStatus();
                        if (status.equals("002")) {
                            reviewStateTextview.setVisibility(View.VISIBLE);
                            reviewStateTextview.setText("对不起，所查学员不存在");
                        } else if (status.equals("003")) {
                            reviewStateTextview.setVisibility(View.VISIBLE);
                            reviewStateTextview.setText("对不起，审核未通过");
                        } else if (status.equals("004")) {
                            reviewStateTextview.setVisibility(View.VISIBLE);
                            reviewStateTextview.setText("正在审核中，请稍候");
                        } else if (status.equals("200")) {

                            reviewStateTextview.setVisibility(View.GONE);

                            Toast.makeText(UserInfoQueryActivity.this, "恭喜，审核已通过", Toast.LENGTH_SHORT).show();

                            Student student = getStudentInfoResponse.getStudentRpcJson().getStudent().get(0);
                            Intent intent = new Intent(UserInfoQueryActivity.this, StudentInfoActivity.class);
                            intent.putExtra("studentName", student.getName());
                            intent.putExtra("studentEducation", student.getEducation());
                            intent.putExtra("studentAddress", student.getAddress());
                            intent.putExtra("studentId", student.getStudentId());
                            intent.putExtra("studentBirthday",student.getBirthday());
                            intent.putExtra("studentNation",student.getNation());
                            intent.putExtra("studentPoliticalStatus",student.getPoliticalStatus());
                            intent.putExtra("studentTelephone",student.getTelephone());
                            intent.putExtra("studentSex",student.getSex());
                            intent.putExtra("studentSFZUrl", student.getSfzUrl());
                            intent.putExtra("studentXLZMUrl", student.getXlzmUrl());
                            startActivity(intent);
                        }

                    }

                    @Override
                    public void onFailure(Call<StudentResponse> call, Throwable t) {
                        dialog.dismiss();
                        Toast.makeText(UserInfoQueryActivity.this, "查询失败", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }

    private void showAlertDialog(String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(UserInfoQueryActivity.this);
        builder.setMessage(message);
        builder.setTitle("提示");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }
}
