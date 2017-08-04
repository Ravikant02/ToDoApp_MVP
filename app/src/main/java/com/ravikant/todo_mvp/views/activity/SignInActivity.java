package com.ravikant.todo_mvp.views.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.ravikant.todo_mvp.R;
import com.ravikant.todo_mvp.components.MessageProgressDialog;
import com.ravikant.todo_mvp.core.AppCore;
import com.ravikant.todo_mvp.interfaces.LoginView;
import com.ravikant.todo_mvp.presenters.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class SignInActivity extends AppCompatActivity implements LoginView {

    /** BIND ALL VIEW OBJECTS TO THIS ACTIVITY */
    @BindView(R.id.edtEmail) EditText edtEmail;
    @BindView(R.id.edtPassword) EditText edtPassword;
    @BindView(R.id.btnLogin) Button btnLogin;
    @BindView(R.id.btnForgot) Button btnForgot;

    private LoginPresenter loginPresenter;
    private MessageProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
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
        btnForgot.setTypeface(font);
        btnLogin.setTypeface(font);
        progressDialog = new MessageProgressDialog(this);

        edtEmail.setText("ravikant@blazeautomation.com");
        edtPassword.setText("qwerty");
    }

    private void initPresenter(){
        /** INITIALISE PRESENTER OF THIS LOGIN CLASS*/
        loginPresenter = new LoginPresenter(this);
    }

    @OnClick(R.id.btnLogin)
    public void onBntLoginClick(){
        loginPresenter.getLogin(edtEmail.getText().toString().trim(), edtPassword.getText().toString().trim());
    }

    @OnClick(R.id.btnForgot)
    public void onBtnForgotClick(){

    }

    @Override
    public void onLoginSuccess() {
        progressDialog.dismissProgress();
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onLoginFailed(String errorMessage) {
        progressDialog.dismissProgress();
        showErrorDialog(errorMessage);
    }

    @Override
    public void onErrorShow(String errorMessage) {
        progressDialog.dismissProgress();
        showErrorDialog(errorMessage);
    }

    @Override
    public void onShowProgress(String message) {
        progressDialog.showProgress(message);
    }

    private void showErrorDialog(final String errorMessage){
        /** METHOD TO SHOW ERROR MESSAGE IN TOP ERROR DIALOG IN THE SCREEN*/
        Crouton.makeText(SignInActivity.this, errorMessage, Style.ALERT).show();
    }
}
