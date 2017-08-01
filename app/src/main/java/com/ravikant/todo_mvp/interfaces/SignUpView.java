package com.ravikant.todo_mvp.interfaces;

/**
 * Created by ravikant on 1/8/17.
 **/

public interface SignUpView {
    void onSignUpSuccess();
    void onSignUpFailed(String errorMessage);
    void onEmptyData(String errorMessage);
    void onEmptyName(String errorMessage);
    void onEmptyEmail(String errorMessage);
    void onEmptyPassword(String errorMessage);
}
