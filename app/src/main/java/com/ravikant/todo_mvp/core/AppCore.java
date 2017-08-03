package com.ravikant.todo_mvp.core;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by ravikant on 28/7/17.
 **/

public class AppCore {

    public static Typeface getAppDefaultFont(Context context){
        return Typeface.createFromAsset(context.getAssets(), "fonts/bariol_bold.ttf");
    }

    public static String getFilePath(Context context, Uri uri){
        if (uri==null) return "";
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":")+1);
        cursor.close();

        cursor = context.getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }

    public static Bitmap getImageBitmap(String name){
        try{
            FileInputStream fis = new FileInputStream (new File(name));
            Bitmap b = BitmapFactory.decodeStream(fis);
            // Bitmap originalBitmap = BitmapFactory.decodeStream(fis);
            // Bitmap resizedBitmap = Bitmap.createScaledBitmap(originalBitmap, bgImageWidth, bgImageHeight, false);
            fis.close();
            return b;
        }
        catch(Exception e){
            // Loggers.error(e.getMessage());
        }
        return null;
    }
}
