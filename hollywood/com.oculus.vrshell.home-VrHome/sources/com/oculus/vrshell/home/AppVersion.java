package com.oculus.vrshell.home;

import android.content.Context;
import com.oculus.panellib.AppDetails;

public class AppVersion {
    public static String getAppVersionName(Context context) {
        try {
            return AppDetails.get(context, "com.oculus.vrshell.home").versionName;
        } catch (Exception e) {
            return "99999.99999.99999.99999.99999";
        }
    }
}
