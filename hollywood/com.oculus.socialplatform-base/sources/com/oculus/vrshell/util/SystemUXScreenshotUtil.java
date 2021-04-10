package com.oculus.vrshell.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import com.oculus.common.logutilities.LoggingUtil;
import java.util.HashSet;
import java.util.Set;

public class SystemUXScreenshotUtil {
    public static final String TAG = LoggingUtil.tag(SystemUXScreenshotUtil.class);
    public static Bitmap sAppScreenshot;
    public static Set<AppScreenshotObserver> sAppScreenshotObservers = new HashSet();
    public static Bitmap sHomeScreenshot;
    public static String sPackageName;

    public interface AppScreenshotObserver {
        void onAppScreenshotChanged();
    }

    public static void clearAppScreenshot() {
        sAppScreenshot = null;
        sPackageName = null;
    }

    public static void setHomeScreenshot(byte[] bArr) {
        sHomeScreenshot = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    public static void addAppScreenshotObserver(AppScreenshotObserver appScreenshotObserver) {
        sAppScreenshotObservers.add(appScreenshotObserver);
    }

    public static Bitmap getAppScreenshot() {
        if (TextUtils.isEmpty(sPackageName) || PackageUtil.isShellApp(sPackageName)) {
            return null;
        }
        return sAppScreenshot;
    }

    public static String getAppScreenshotPackageName() {
        return sPackageName;
    }

    public static Bitmap getHomeScreenshot() {
        return sHomeScreenshot;
    }

    public static void removeAppScreenshotObserver(AppScreenshotObserver appScreenshotObserver) {
        sAppScreenshotObservers.remove(appScreenshotObserver);
    }

    public static void setAppScreenshot(String str, byte[] bArr) {
        sPackageName = str;
        sAppScreenshot = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        for (AppScreenshotObserver appScreenshotObserver : sAppScreenshotObservers) {
            appScreenshotObserver.onAppScreenshotChanged();
        }
    }
}
