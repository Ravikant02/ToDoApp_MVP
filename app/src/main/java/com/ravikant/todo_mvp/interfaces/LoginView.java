package com.ravikant.todo_mvp.interfaces;

/**
 * Created by ravikant on 31/7/17.
 **/

public interface LoginView {
    void onLoginSuccess();
    void onLoginFailed(String errorMessage);
    void onEmptyData(String errorMessage);
    void onEmptyEmail(String errorMessage);
    void onEmptyPassword(String errorMessage);
}
