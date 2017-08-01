package com.ravikant.todo_mvp.core;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by ravikant on 28/7/17.
 **/

public class ToDoCore {

    public static Typeface getAppDefaultFont(Context context){
        return Typeface.createFromAsset(context.getAssets(), "fonts/bariol_bold.ttf");
    }
}
