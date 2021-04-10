package com.facebook.secure.trustedapp;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import com.facebook.secure.logger.Reporter;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

public class CallerInfoHelper {
    static final String ERROR_NULL_METADATA = String.format("Null metadata in caller identity, API=%d", Integer.valueOf(Build.VERSION.SDK_INT));

    public static AppIdentity getCallerInfo(Context context, Intent intent, Reporter reporter) {
        return getCallerInfo(context, intent, false, reporter);
    }

    /* JADX WARNING: Removed duplicated region for block: B:54:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00ed  */
    @android.annotation.SuppressLint({"DeprecatedMethod"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.facebook.secure.trustedapp.AppIdentity getCallerInfo(android.content.Context r16, android.content.Intent r17, boolean r18, com.facebook.secure.logger.Reporter r19) {
        /*
        // Method dump skipped, instructions count: 239
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.secure.trustedapp.CallerInfoHelper.getCallerInfo(android.content.Context, android.content.Intent, boolean, com.facebook.secure.logger.Reporter):com.facebook.secure.trustedapp.AppIdentity");
    }

    public static AppIdentity getCallerInfo(Context context, Intent intent) {
        return getCallerInfo(context, intent, null);
    }

    static boolean isValidTimestamp(long j, boolean z, boolean z2) {
        return (z2 ? System.currentTimeMillis() : SystemClock.elapsedRealtime()) - j < ((long) (z ? 86400000 : 60000));
    }

    private static String extractMetaDataFromPendingIntent(PendingIntent pendingIntent) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int i = Build.VERSION.SDK_INT;
        if (i >= 24) {
            return (String) PendingIntent.class.getMethod("getTag", String.class).invoke(pendingIntent, "");
        } else if (i >= 18) {
            return ((Intent) PendingIntent.class.getMethod("getIntent", (Class[]) Collections.emptyList().toArray(new Class[0])).invoke(pendingIntent, new Object[0])).getAction();
        } else {
            return null;
        }
    }

    private static void logToReporter(Reporter reporter, String str, Throwable th) {
        if (reporter != null) {
            reporter.report("CallerInfoHelper", str, th);
        }
    }

    static boolean isOnOrAboveApi17() {
        return Build.VERSION.SDK_INT >= 17;
    }

    /* access modifiers changed from: package-private */
    @TargetApi(17)
    public static class Api17Utils {
        public static int getCreatorUid(PendingIntent pendingIntent) {
            return pendingIntent.getCreatorUid();
        }

        public static String getCreatorPackage(PendingIntent pendingIntent) {
            return pendingIntent.getCreatorPackage();
        }
    }
}
