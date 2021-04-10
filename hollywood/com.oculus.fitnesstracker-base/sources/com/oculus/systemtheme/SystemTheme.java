package com.oculus.systemtheme;

import android.app.Application;
import android.content.Context;
import com.facebook.jni.annotations.DoNotStrip;

@DoNotStrip
public class SystemTheme {
    private static final String TAG = "SystemTheme";

    private SystemTheme() {
    }

    private static Context getApplicationContext() {
        try {
            return (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke(null, null);
        } catch (Exception unused) {
            return null;
        }
    }

    @DoNotStrip
    public static boolean getIsLightModeEnabled() {
        Context applicationContext = getApplicationContext();
        if (applicationContext != null && (applicationContext.getResources().getConfiguration().uiMode & 48) == 16) {
            return true;
        }
        return false;
    }
}
