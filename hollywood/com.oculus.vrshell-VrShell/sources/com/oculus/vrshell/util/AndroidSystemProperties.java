package com.oculus.vrshell.util;

import android.util.Log;
import com.facebook.mobileconfigservice.serviceconstants.MobileConfigServiceConstants;
import com.oculus.common.logutilities.LoggingUtil;

public class AndroidSystemProperties {
    private static final String TAG = LoggingUtil.tag(AndroidSystemProperties.class);
    private static Class<?> sSystemPropertiesClass;

    static {
        try {
            sSystemPropertiesClass = Class.forName("android.os.SystemProperties");
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "Unable to find android.os.SystemProperties class.", e);
        }
    }

    public static String getSystemPropertyString(String str, String str2) {
        try {
            return (String) sSystemPropertiesClass.getMethod(MobileConfigServiceConstants.PATH_GET, String.class, String.class).invoke(null, str, str2);
        } catch (Exception e) {
            String str3 = TAG;
            Log.e(str3, "SystemProperties.getString() returning default " + str2, e);
            return str2;
        }
    }
}
