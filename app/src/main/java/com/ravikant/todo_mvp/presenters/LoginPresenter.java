package com.ravikant.todo_mvp.presenters;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ravikant.todo_mvp.R;
import com.ravikant.todo_mvp.interfaces.LoginView;
import com.ravikant.todo_mvp.views.ToDoApplication;

/**
 * Created by ravikant on 31/7/17.
 **/

public class LoginPresenter {

    private LoginView loginView;
    private FirebaseAuth firebaseAuth;

    public LoginPresenter(LoginView loginView){
        this.loginView = loginView;
        firebaseAuth = FirebaseAuth.getInstance();
    }


    public void getLogin(final String email, final String password){
        if (email.isEmpty() && password.isEmpty()){
            loginView.onErrorShow(ToDoApplication.getContext().getString(R.string.please_provide_required_details));
            return;
        }
        if (email.isEmpty()){
            loginView.onErrorShow(ToDoApplication.getContext().getString(R.string.email_required));
            return;
        }
        if (password.isEmpty()){
            loginView.onErrorShow(ToDoApplication.getContext().getString(R.string.password_required));
            return;
        }
        loginView.onShowProgress(ToDoApplication.getContext().getResources().getString(R.string.hold_on_we_are_loading));
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(ToDoApplication.getInstance().getCurrentActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            loginView.onLoginSuccess();
                        }else{
                            loginView.onLoginFailed(ToDoApplication.getContext().getString(R.string.error_while_login));
                        }
                    }
                });
    }


}
