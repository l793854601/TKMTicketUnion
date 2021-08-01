package com.example.tkmticketunion.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

    private static Toast sToast;

    public static void showToast(Context context, String msg, int duration) {
        Toast toast = getToast(context, msg, duration);
        toast.show();
    }

    private static Toast getToast(Context context, String msg, int duration) {
        if (sToast == null) {
            sToast = Toast.makeText(context, msg, duration);
        } else {
            sToast.setText(msg);
        }
        return sToast;
    }
}
