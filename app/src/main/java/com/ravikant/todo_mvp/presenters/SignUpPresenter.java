package com.ravikant.todo_mvp.presenters;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ravikant.todo_mvp.R;
import com.ravikant.todo_mvp.interfaces.SignUpView;
import com.ravikant.todo_mvp.views.ToDoApplication;

/**
 * Created by ravikant on 1/8/17.
 **/

public class SignUpPresenter {
    private SignUpView signUpView;
    private FirebaseAuth firebaseAuth;

    public SignUpPresenter(SignUpView signUpView){
        this.signUpView = signUpView;
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void doSingUp(final String name, final String email, final String password){
        if (name.isEmpty() && email.isEmpty() && password.isEmpty()){
            signUpView.onEmptyEmail(ToDoApplication.getContext().getString(R.string.please_provide_required_details));
            return;
        }
        if (name.isEmpty()){
            signUpView.onEmptyEmail(ToDoApplication.getContext().getString(R.string.name_required));
            return;
        }
        if (email.isEmpty()){
            signUpView.onEmptyEmail(ToDoApplication.getContext().getString(R.string.email_required));
            return;
        }
        if (password.isEmpty()){
            signUpView.onEmptyEmail(ToDoApplication.getContext().getString(R.string.password_required));
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new ToDoApplication().getCurrentActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) signUpView.onSignUpSuccess();
                        else signUpView.onSignUpFailed(ToDoApplication.getContext().getString(R.string.error_while_sign_up));
                    }
                });
    }
}
