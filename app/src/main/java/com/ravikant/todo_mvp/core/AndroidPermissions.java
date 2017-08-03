package com.ravikant.todo_mvp.core;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.ravikant.todo_mvp.config.AppConfig;

/**
 * Created by Ravikant on 3/8/17.
 **/

public class AndroidPermissions {

    public static boolean isPermitted(final Context context, final Activity activity){
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.CAMERA},
                    AppConfig.MY_PERMISSIONS_REQUEST_CAMERA);
        }
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    AppConfig.MY_PERMISSIONS_REQUEST_WRITE_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    AppConfig.MY_PERMISSIONS_REQUEST_READ_STORAGE);
            return false;
        }
        return true;
    }

    public static boolean isCameraPermitted(final Context context, final Activity activity){
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.CAMERA},
                    AppConfig.MY_PERMISSIONS_REQUEST_CAMERA);
            return false;
        }
        return true;
    }

    public static boolean isWriteStoragePermitted(final Context context, final Activity activity){
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    AppConfig.MY_PERMISSIONS_REQUEST_WRITE_STORAGE);
            return false;
        }
        return true;
    }

    public static boolean isReadStoragePermitted(final Context context, final Activity activity){
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    AppConfig.MY_PERMISSIONS_REQUEST_READ_STORAGE);
            return false;
        }
        return true;
    }
}
