package com.example.andy.dangjian;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andy.dangjian.interfaces.StudentInterface;
import com.example.andy.dangjian.model.CustomResponse;
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
    TextView submit;
    @BindView(R.id.query_layout)
    LinearLayout queryLayout;
    @BindView(R.id.result_layout)
    LinearLayout resultLayout;
    @BindView(R.id.student_name_textview)
    TextView studentNameTextView;
    @BindView(R.id.student_education_textview)
    TextView studentEducationTextView;
    @BindView(R.id.student_id_textview)
    TextView studentIdTextView;
    @BindView(R.id.student_address_textview)
    TextView studentAddressTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_query);

        ButterKnife.bind(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utils utils= Utils.INSTANCE;
                utils.hideSoftInput(UserInfoQueryActivity.this,studentPicIdEditText);

                String studentPicId = studentPicIdEditText.getText().toString();
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
                Call<CustomResponse> getStudentInfoCall = studentInterface.getStudentInfo(params);
                getStudentInfoCall.enqueue(new Callback<CustomResponse>() {
                    @Override
                    public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {

                        dialog.dismiss();

                        CustomResponse getStudentInfoResponse = response.body();
                        String status = getStudentInfoResponse.getStatus();
                        if (status.equals("002")) {
                            showAlertDialog("所查学员不存在");
                        } else if (status.equals("003")) {
                            showAlertDialog("审核未通过");
                        } else if (status.equals("004")) {
                            showAlertDialog("正在审核中");
                        } else if (status.equals("200")) {

                            queryLayout.setVisibility(View.GONE);
                            resultLayout.setVisibility(View.VISIBLE);

                            Student student = getStudentInfoResponse.getRpcJson().getStudent();
                            studentNameTextView.setText(student.getName());
                            studentEducationTextView.setText(student.getEducation());
                            studentIdTextView.setText(student.getStudentId());
                            studentAddressTextView.setText(student.getAddress());
                        }

                    }

                    @Override
                    public void onFailure(Call<CustomResponse> call, Throwable t) {
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
