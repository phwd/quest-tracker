package com.oculus.appsafety;

import android.app.Application;
import android.content.SharedPreferences;

public final class AppSafetyApplication extends Application {
    public static final String APPSAFETY_HW_CLIENT_TOKEN = "OC|347785309477760|d68cd89f218a9e63ec6dccb70cfeaf57";
    public static final int BINARY_CHECK_JOB_ID = 123;
    public static final int MISSING_PACKAGE_INFO_JOB_ID = 125;
    public static final int PACKAGE_PARTS_UPLOAD_JOB_ID = 124;
    public static final int SAFETY_SIGNAL_COLLECTOR_JOB_ID = 126;
    public static final String SHARED_PREFERENCES_FILE = "app_safety_preferences";
    private static AppSafetyApplication sInstance;

    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static SharedPreferences getSharedPreferences() {
        return sInstance.createDeviceProtectedStorageContext().getSharedPreferences(SHARED_PREFERENCES_FILE, 0);
    }
}
