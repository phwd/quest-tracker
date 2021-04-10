package com.oculus.vrapilayers;

import android.util.Log;

class AndroidSystemProperties {
    private static final String TAG = "VrApiLayers";
    private static Class<?> sSystemPropertiesClass;

    AndroidSystemProperties() {
    }

    static {
        try {
            sSystemPropertiesClass = Class.forName("android.os.SystemProperties");
        } catch (ClassNotFoundException ex) {
            Log.e(TAG, "Unable to find android.os.SystemProperties class.", ex);
        }
    }

    public static String getSystemPropertyString(String key, String defaultValue) {
        try {
            return (String) sSystemPropertiesClass.getMethod("get", String.class, String.class).invoke(null, key, defaultValue);
        } catch (Exception ex) {
            Log.e(TAG, "SystemProperties.getString() returning default " + defaultValue, ex);
            return defaultValue;
        }
    }
}
