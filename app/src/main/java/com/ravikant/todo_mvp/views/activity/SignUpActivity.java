package com.ravikant.todo_mvp.views.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.ravikant.todo_mvp.R;
import com.ravikant.todo_mvp.core.ToDoCore;
import com.ravikant.todo_mvp.interfaces.SignUpView;
import com.ravikant.todo_mvp.presenters.SignUpPresenter;
import com.ravikant.todo_mvp.views.ToDoApplication;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity implements SignUpView {

    /** BIND ALL VIEW OBJECTS TO THIS ACTIVITY */
    @BindView(R.id.edtName) EditText edtName;
    @BindView(R.id.edtEmail) EditText edtEmail;
    @BindView(R.id.edtPassword) EditText edtPassword;
    @BindView(R.id.btnSignUp) Button btnSignUp;
    private SignUpPresenter signUpPresenter;
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
        Typeface font = ToDoCore.getAppDefaultFont(this);
        edtEmail.setTypeface(font);
        edtPassword.setTypeface(font);
        edtName.setTypeface(font);
        btnSignUp.setTypeface(font);

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
        ToDoApplication application = new ToDoApplication();
        Activity activity = application.getCurrentActivity();
        finish();
        /*signUpPresenter.doSingUp(edtName.getText().toString().trim(),
                edtEmail.getText().toString().trim(), edtPassword.getText().toString().trim());*/
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
    public void onEmptyData(String errorMessage) {

    }

    @Override
    public void onEmptyName(String errorMessage) {

    }

    @Override
    public void onEmptyEmail(String errorMessage) {

    }

    @Override
    public void onEmptyPassword(String errorMessage) {

    }
}
