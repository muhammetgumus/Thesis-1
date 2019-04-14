package com.project.thesis.util;

import android.app.Activity;
import android.app.ProgressDialog;

public final class CommonUtil {

    public static ProgressDialog progressDialog(Activity activity) {
        ProgressDialog pd = new ProgressDialog(activity);
        pd.setMessage("Loading");
        return pd;
    }
}
