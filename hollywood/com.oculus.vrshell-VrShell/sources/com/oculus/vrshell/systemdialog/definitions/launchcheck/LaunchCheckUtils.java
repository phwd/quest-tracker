package com.oculus.vrshell.systemdialog.definitions.launchcheck;

import android.content.Context;
import android.net.UrlQuerySanitizer;
import com.oculus.vrshell.util.HorizonUtil;

public class LaunchCheckUtils {
    private static final String DATA_KEY_PACKAGE_NAME = "package_name";

    public static String getAppPackageName(String str) {
        return new UrlQuerySanitizer(str).getValue("package_name");
    }

    public static String getAppDisplayName(Context context, String str) {
        return HorizonUtil.getSafeApplicationName(context, getAppPackageName(str));
    }
}
