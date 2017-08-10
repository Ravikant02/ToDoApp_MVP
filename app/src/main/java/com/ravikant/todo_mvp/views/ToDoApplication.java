package com.ravikant.todo_mvp.views;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.multidex.MultiDexApplication;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ravikant.todo_mvp.config.AppConfig;

/**
 * Created by ravikant on 28/7/17.
 **/

public class ToDoApplication extends Application implements Application.ActivityLifecycleCallbacks {

    private static Context applicationContext = null;
    private Activity currentActivity=null;
    private static FirebaseDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(this);
        ToDoApplication.applicationContext = getApplicationContext();
        // FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);
    }

    public static FirebaseDatabase getFirebaseDataBaseInstance(){
        return database;
    }

    public static DatabaseReference getBoardsTableDbReference(){
        DatabaseReference dbReference = database.getReference(AppConfig.TABLE_BOARDS);
        dbReference.keepSynced(true);
        return dbReference;
    }

    public static ToDoApplication getInstance() {
        return (ToDoApplication) applicationContext;
    }

    public static Context getContext() {
        return ToDoApplication.applicationContext;
    }

    public Activity getCurrentActivity() {
        return ToDoApplication.this.currentActivity;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        ToDoApplication.this.currentActivity = activity;
    }

    @Override
    public void onActivityStarted(Activity activity) {
        ToDoApplication.this.currentActivity = activity;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        ToDoApplication.this.currentActivity = activity;
    }

    @Override
    public void onActivityPaused(Activity activity) {
        ToDoApplication.this.currentActivity = null;
    }

    @Override
    public void onActivityStopped(Activity activity) {
        ToDoApplication.this.currentActivity = null;
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        ToDoApplication.this.currentActivity = null;
    }
}
