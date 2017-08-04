package com.ravikant.todo_mvp.components;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.view.View;

import com.ravikant.todo_mvp.core.AppCore;
import com.ravikant.todo_mvp.core.CustomTypefaceSpan;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Raviknat on 4/8/17.
 **/
public class MessageProgressDialog {

    private final ProgressDialog pd;
    private Timer timer;

    private Context context;
    private Typeface font;
    private SpannableString spannableString;
    public MessageProgressDialog(Context context)
    {
        this.context = context;
        pd = new ProgressDialog(context);
        pd.setCancelable(false);
//        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        font = AppCore.getAppDefaultFont(context);

    }


    public void showProgress(String message) {
        spannableString = new SpannableString(message);
        spannableString.setSpan(new CustomTypefaceSpan("", font), 0, spannableString.length(), 0);
        pd.setMessage(spannableString);
        handler.sendEmptyMessage(0);
    }

    public void showProgress(String message, int autoDismissDuration, final String failureMessage) {
        spannableString = new SpannableString(message);
        spannableString.setSpan(new CustomTypefaceSpan("", font), 0, spannableString.length(), 0);
        pd.setMessage(spannableString);
        timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Message message1 = new Message();
                message1.what = 2;
                Bundle bundle = new Bundle();
                bundle.putString("failureMessage", failureMessage);
                message1.setData(bundle);
                handler.sendMessage(message1);
                dismissProgress();
            }
        };

        timer.schedule(timerTask,autoDismissDuration);
        handler.sendEmptyMessage(0);

    }

    public void  dismissProgress()
    {
        handler.sendEmptyMessage(1);
    }
    public boolean isShowing() {
        return pd.isShowing();
    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            switch (msg.what)
            {
                case 0:
                    pd.show();
                    break;

                case 1:
                    if(timer!= null)
                    {
                        timer.cancel();
                    }
                    pd.dismiss();
                    break;

                case 2:
                    /*MessageAlertDialog messageAlertDialog = new MessageAlertDialog(context);
                    if(!msg.getData().getString("failureMessage").equalsIgnoreCase("")) {
                        messageAlertDialog.showAlertMessage(context.getResources().getString(R.string.app_name), msg.getData().getString("failureMessage"));
                        messageAlertDialog.setCancelButtonVisibility(View.GONE);
                    }*/
                    break;

            }
            return false;
        }
    });
}
