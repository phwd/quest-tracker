package com.oculus.deviceauthserver;

import android.os.Build;

public class Constants {
    public static final String CALIBRATION_SYNC_PACKAGE = "com.oculus.calibrationsync";
    public static final String DEVICE_AUTH_SERVICE_ACCESS_TOKEN = "HW|430457441064115|a7332e35cdd419007366bcf2703befa6";
    public static final String DEVICE_AUTH_SERVICE_APP_ID = "430457441064115";
    public static final String DEVICE_AUTH_SERVICE_CLIENT_TOKEN = "a7332e35cdd419007366bcf2703befa6";
    public static final String ENTERPRISE_SERVER_PACKAGE = "com.oculus.alpenglow";
    public static final String HORIZON_PACKAGE = "com.oculus.horizon";
    public static final boolean IS_DEBUG_BUILD = (!Build.TYPE.equals("user"));
    public static final String OCMS_PACKAGE = "com.oculus.ocms";
    public static final String UPDATER_PACKAGE = "com.oculus.updater";
}
