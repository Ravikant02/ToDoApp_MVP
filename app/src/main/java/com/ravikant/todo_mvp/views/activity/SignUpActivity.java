package com.ravikant.todo_mvp.views.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ravikant.todo_mvp.R;
import com.ravikant.todo_mvp.config.AppConfig;
import com.ravikant.todo_mvp.core.AndroidPermissions;
import com.ravikant.todo_mvp.core.AppCore;
import com.ravikant.todo_mvp.interfaces.SignUpView;
import com.ravikant.todo_mvp.presenters.SignUpPresenter;
import com.ravikant.todo_mvp.views.ToDoApplication;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity implements SignUpView {

    /** BIND ALL VIEW OBJECTS TO THIS ACTIVITY */
    @BindView(R.id.edtName) EditText edtName;
    @BindView(R.id.edtEmail) EditText edtEmail;
    @BindView(R.id.edtPassword) EditText edtPassword;
    @BindView(R.id.btnSignUp) Button btnSignUp;
    @BindView(R.id.txtSetPicture) TextView txtSetPicture;

    private File finalFileName;
    private SignUpPresenter signUpPresenter;
    private String photoUrl="";
    private static final int SELECT_IMAGE = 3;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        /** BIND BUTTER KNIFE TO THIS SCREEN*/
        ButterKnife.bind(this);
        /** HIDE TOOL BAR*/
        if (getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setTitle("");
            getSupportActionBar().setElevation(0);
            // getSupportActionBar().setHomeAsUpIndicator(null);
        }

        initView();
        initPresenter();
    }

    private void initView(){
        /** INITIALISE ALL VIEW OBJECTS HERE*/
        Typeface font = AppCore.getAppDefaultFont(this);
        edtEmail.setTypeface(font);
        edtPassword.setTypeface(font);
        edtName.setTypeface(font);
        btnSignUp.setTypeface(font);
        txtSetPicture.setTypeface(font);

        edtName.setText("Ravikant Verma");
        edtEmail.setText("ravikant@blazeautomation.com");
        edtPassword.setText("qwerty");
    }

    private void initPresenter(){
        /** INITIALISE PRESENTER OF THIS LOGIN CLASS*/
        signUpPresenter = new SignUpPresenter(this);
    }

    @OnClick(R.id.btnSignUp)
    public void onSignUpClick(){
        signUpPresenter.doSingUp(edtName.getText().toString().trim(),
                edtEmail.getText().toString().trim(), edtPassword.getText().toString().trim(), photoUrl);
    }

    @OnClick({R.id.txtSetPicture, R.id.view})
    public void onImageClick(){
        ImagePickerDialog();
    }
    @Override
    public void onSignUpSuccess() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onSignUpFailed(String errorMessage) {

    }

    @Override
    public void onErrorShow(String errorMessage) {

    }

    private void openCameraIntent(){
        /*String path = Environment.getExternalStorageDirectory().toString() + File.separator + getResources().getString(R.string.app_name) + File.separator;
        File file = new File(path);
        finalFileName = new File(file, "temp.jpg");
        Uri uriSavedImage=Uri.fromFile(finalFileName);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
        startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);*/

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getApplicationContext().getPackageManager()) != null) {
            // intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, Integer.parseInt(videoLength.split(":")[1])*60);
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void openGalleryIntent(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select a Video "), SELECT_IMAGE);
    }

    private void ImagePickerDialog(){
        // METHOD TO SHOW ALERT DIALOG FOR IMAGE SELECTION
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.video_picker_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        TextView fromGallery = (TextView) dialog.findViewById(R.id.lblChooseVideo);
        TextView recordVideo = (TextView) dialog.findViewById(R.id.lblRecordVideo);
        dialog.setCancelable(true);
        dialog.show();
        fromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (AndroidPermissions.isReadStoragePermitted(getApplicationContext(), ToDoApplication.getInstance().getCurrentActivity())){
                    openGalleryIntent();
                }
            }
        });

        recordVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (AndroidPermissions.isCameraPermitted(getApplicationContext(), ToDoApplication.getInstance().getCurrentActivity())){
                    openCameraIntent();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case AppConfig.MY_PERMISSIONS_REQUEST_CAMERA:
                openCameraIntent();
                /*if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (intent.resolveActivity(this.getPackageManager()) != null) {
                        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                    }
                }*/
                break;
            case AppConfig.MY_PERMISSIONS_REQUEST_READ_STORAGE:
                openGalleryIntent();
                /*if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image*//*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select an Image "), SELECT_IMAGE);
                }*/
                break;
            case AppConfig.MY_PERMISSIONS_REQUEST_WRITE_STORAGE:
                break;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data==null) return;
        if (requestCode == SELECT_IMAGE) {
            Uri selectedImageUri = data.getData();
            // selectedPath = getFileNameFromUri(selectedImageUri, context);
            photoUrl = AppCore.getFilePath(this, selectedImageUri);
        }
        else if (requestCode == REQUEST_IMAGE_CAPTURE) {

            // if (finalFileName!=null) photoUrl = finalFileName.getAbsolutePath();
            Uri selectedImageUri = data.getData();
            photoUrl = AppCore.getFilePath(this, selectedImageUri);
        }
        Bitmap bitmap = AppCore.getImageBitmap(photoUrl);
        if (bitmap!=null) {
            // imgProfile.setImageBitmap(bitmap);
        }
    }
}
