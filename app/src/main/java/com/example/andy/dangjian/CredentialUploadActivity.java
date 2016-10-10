package com.example.andy.dangjian;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andy.dangjian.interfaces.StudentInterface;
import com.example.andy.dangjian.model.StudentResponse;
import com.example.andy.dangjian.network.HttpUtils;
import com.example.andy.dangjian.utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CredentialUploadActivity extends AppCompatActivity {

    public static final String TAG = CredentialUploadActivity.class.getSimpleName();

    @BindView(R.id.credential_text)
    TextView credentialTextTextView;
    @BindView(R.id.selected_image_imageview)
    ImageView selectedImageImageView;
    @BindView(R.id.submit)
    ImageView submit;
    @BindView(R.id.back)
    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credential_upload);

        ButterKnife.bind(this);

        final String credentialText = getIntent().getStringExtra("Credential");
        final Uri uri = getIntent().getParcelableExtra("uri");

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

                ContentResolver cr = CredentialUploadActivity.this.getContentResolver();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = cr.query(uri, filePathColumn, null, null, null);
                if (cursor == null) {
                    return;
                }
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();

                File file = new File(picturePath);

                Utils utils = Utils.INSTANCE;
                String userPidNumber = utils.getUserPidNumber(CredentialUploadActivity.this);

                String imageType = "";
                if (credentialText.equals("身份证信息")) {
                    imageType = "1";
                } else if (credentialText.equals("学历证明")) {
                    imageType = "2";
                }

                HttpUtils httpUtils = HttpUtils.INSTANCE;
                Retrofit retrofit = httpUtils.getRetrofitInstance();
                StudentInterface studentInterface = retrofit.create(StudentInterface.class);

                MultipartBody.Builder builder = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("pid", userPidNumber)
                        .addFormDataPart("img_type", imageType);
                RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                builder.addFormDataPart("image", file.getName(), imageBody);

                List<MultipartBody.Part> parts = builder.build().parts();

                Map<String, String> uploadParams = new HashMap<>();
                uploadParams.put("mode", "SP");
                uploadParams.put("action", "uploadImg");

                Call<StudentResponse> uploadImageResponse = studentInterface.uploadImage(uploadParams, parts);
                uploadImageResponse.enqueue(new Callback<StudentResponse>() {
                    @Override
                    public void onResponse(Call<StudentResponse> call, Response<StudentResponse> response) {

                        StudentResponse studentResponse = response.body();
                        String success = studentResponse.getSuccess();
                        if (success.equals("1")) {

                            String status = studentResponse.getStatus();
                            if(status.equals("001")){
                                Toast.makeText(CredentialUploadActivity.this, "对不起，上传出错", Toast.LENGTH_SHORT).show();
                            }else if(status.equals("002")){
                                Toast.makeText(CredentialUploadActivity.this, "身份证信息不存在，请先填写信息", Toast.LENGTH_SHORT).show();
                            }else if(status.equals("003")){
                                Toast.makeText(CredentialUploadActivity.this, "对不起，图片类型不正确", Toast.LENGTH_SHORT).show();
                            }else if(status.equals("200")){
                                Toast.makeText(CredentialUploadActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onResponse: " + studentResponse.getStudentRpcJson().getStudent().get(0).getImgUrl());
                                finish();
                            }

                        } else {
                            Toast.makeText(CredentialUploadActivity.this, "对不起，上传失败", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<StudentResponse> call, Throwable t) {

                        t.printStackTrace();

                        Toast.makeText(CredentialUploadActivity.this, "对不起，上传失败", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
