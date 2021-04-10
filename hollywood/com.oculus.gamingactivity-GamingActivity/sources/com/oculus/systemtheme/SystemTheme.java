package com.oculus.systemtheme;

import android.app.Application;
import android.content.Context;
import com.facebook.jni.annotations.DoNotStrip;

@DoNotStrip
public class SystemTheme {
    private static final String TAG = SystemTheme.class.getSimpleName();

    private SystemTheme() {
    }

    private static Context getApplicationContext() {
        try {
            return (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke(null, null);
        } catch (Exception e) {
            return null;
        }
    }

    @DoNotStrip
    public static boolean getIsLightModeEnabled() {
        Context appContext = getApplicationContext();
        if (appContext == null) {
            return false;
        }
        switch (appContext.getResources().getConfiguration().uiMode & 48) {
            case 16:
                return true;
            default:
                return false;
        }
    }
}
