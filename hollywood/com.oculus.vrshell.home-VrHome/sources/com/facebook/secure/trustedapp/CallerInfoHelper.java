package com.facebook.secure.trustedapp;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Base64;
import com.facebook.secure.logger.Reporter;
import com.facebook.secure.pendingintent.SecurePendingIntent;
import com.facebook.secure.trustedapp.exception.CannotAttachCallerInfoException;
import com.oculus.os.Version;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public class CallerInfoHelper {
    static final String ERROR_NULL_METADATA = String.format("Null metadata in caller identity, API=%d", Integer.valueOf(Build.VERSION.SDK_INT));

    @SuppressLint({"CatchGeneralException", "PendingIntentWithoutSetPackage"})
    public static Intent attachCallerInfo(Intent intent, Context context, int requestCode, int flags, @Nullable String callerDomain) throws CannotAttachCallerInfoException {
        try {
            intent.setExtrasClassLoader(context.getClassLoader());
            Bundle extras = intent.getExtras();
            if (extras == null) {
                extras = new Bundle();
            }
            extras.setClassLoader(context.getClassLoader());
            extras.putParcelable("_ci_", generateCallerInfo(context, requestCode, flags, callerDomain));
            intent.putExtras(extras);
            return intent;
        } catch (Exception exception) {
            throw new CannotAttachCallerInfoException(exception);
        }
    }

    public static Intent attachCallerInfo(Intent intent, Context context, @Nullable String callerDomain) throws CannotAttachCallerInfoException {
        return attachCallerInfo(intent, context, 0, 1140850688, callerDomain);
    }

    @Nullable
    public static AppIdentity getCallerInfo(Context context, Intent launchingIntent, @Nullable Reporter reporter) {
        return getCallerInfo(context, launchingIntent, false, reporter);
    }

    @Nullable
    @SuppressLint({"DeprecatedMethod"})
    public static AppIdentity getCallerInfo(Context context, Intent launchingIntent, boolean useLongTimeout_UNSAFE, @Nullable Reporter reporter) {
        boolean isValid;
        if (isOnOrAboveApi17()) {
            isValid = false;
        } else {
            isValid = true;
            logToReporter(reporter, "Skipping caller identity metadata check on API <= 17.", null);
        }
        int callerUid = -1;
        String callerPackageName = null;
        AppSignatureHash callerAppSignatureHash = null;
        String callerVersionName = null;
        String callerDomain = null;
        if (launchingIntent.hasExtra("_ci_")) {
            PendingIntent pendingIntent = (PendingIntent) launchingIntent.getParcelableExtra("_ci_");
            if (pendingIntent != null) {
                if (isOnOrAboveApi17()) {
                    callerPackageName = Api17Utils.getCreatorPackage(pendingIntent);
                    callerUid = Api17Utils.getCreatorUid(pendingIntent);
                } else {
                    callerPackageName = pendingIntent.getTargetPackage();
                    callerUid = AppVerifier.getUidFromPackageName(context, callerPackageName);
                }
                try {
                    callerAppSignatureHash = AppVerifier.getSignatureFromPackageName(context, callerPackageName);
                    String metaData = null;
                    try {
                        metaData = extractMetaDataFromPendingIntent(pendingIntent);
                    } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException exception) {
                        isValid = false;
                        logToReporter(reporter, "Error extracting metadata from caller identity.", exception);
                    }
                    if (metaData != null) {
                        try {
                            JSONObject metaDataJson = new JSONObject(new String(Base64.decode(metaData, 11), "UTF-8"));
                            if (metaDataJson.has("d")) {
                                callerDomain = metaDataJson.getString("d");
                            }
                            if (metaDataJson.has("v")) {
                                callerVersionName = metaDataJson.getString("v");
                            }
                            if (isValidTimestamp(Long.parseLong(metaDataJson.getString("t")), useLongTimeout_UNSAFE, true)) {
                                isValid = true;
                            } else if (!metaDataJson.has("r") || !isValidTimestamp(Long.parseLong(metaDataJson.getString("r")), useLongTimeout_UNSAFE, false)) {
                                logToReporter(reporter, "Caller identity has expired.", null);
                            } else {
                                isValid = true;
                            }
                        } catch (UnsupportedEncodingException | IllegalArgumentException | JSONException exception2) {
                            isValid = false;
                            logToReporter(reporter, "Error parsing metadata from caller identity.", exception2);
                        }
                    } else {
                        logToReporter(reporter, ERROR_NULL_METADATA, null);
                    }
                } catch (SecurityException e) {
                    logToReporter(reporter, "Failed to get signature.", e);
                    return null;
                }
            } else {
                logToReporter(reporter, "Null caller identity intent extra.", null);
            }
        } else {
            logToReporter(reporter, "Missing caller identity intent extra.", null);
        }
        if (isValid) {
            return new AppIdentity(callerUid, callerPackageName, callerAppSignatureHash, callerVersionName, callerDomain);
        }
        return null;
    }

    @Nullable
    public static AppIdentity getCallerInfo(Context context, Intent launchingIntent) {
        return getCallerInfo(context, launchingIntent, null);
    }

    static boolean isValidTimestamp(long sentTime, boolean useLongTimeout_UNSAFE, boolean useEpochTime) {
        return (useEpochTime ? System.currentTimeMillis() : SystemClock.elapsedRealtime()) - sentTime < ((long) (useLongTimeout_UNSAFE ? 86400000 : 60000));
    }

    static PendingIntent generateCallerInfo(Context context, int requestCode, int flags, @Nullable String callerDomain) {
        String versionName;
        try {
            versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException | RuntimeException e) {
            versionName = null;
        }
        return SecurePendingIntent.builder().setAction(encodeCallerInfoMetaData(System.currentTimeMillis(), SystemClock.elapsedRealtime(), versionName, callerDomain)).setComponentName(new ComponentName(context, "com.facebook.invalid_class.f4c3b00c")).buildForActivity(context, requestCode, flags);
    }

    @Nullable
    static String encodeCallerInfoMetaData(long creationTime, long creationRealTime, @Nullable String versionName, @Nullable String callerDomain) {
        JSONObject json = new JSONObject();
        try {
            json.put("t", Long.toString(creationTime));
            json.put("r", Long.toString(creationRealTime));
            if (callerDomain != null) {
                json.put("d", callerDomain);
            }
            if (versionName != null) {
                json.put("v", versionName);
            }
            try {
                return Base64.encodeToString(json.toString().getBytes("UTF-8"), 11);
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        } catch (JSONException e2) {
            return null;
        }
    }

    @Nullable
    private static String extractMetaDataFromPendingIntent(PendingIntent pendingIntent) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (Build.VERSION.SDK_INT >= 24) {
            return (String) PendingIntent.class.getMethod("getTag", String.class).invoke(pendingIntent, "");
        } else if (Build.VERSION.SDK_INT >= 18) {
            return ((Intent) PendingIntent.class.getMethod("getIntent", (Class[]) Collections.emptyList().toArray(new Class[0])).invoke(pendingIntent, new Object[0])).getAction();
        } else {
            return null;
        }
    }

    private static void logToReporter(@Nullable Reporter reporter, String message, @Nullable Throwable exception) {
        if (reporter != null) {
            reporter.report("CallerInfoHelper", message, exception);
        }
    }

    static boolean isOnOrAboveApi17() {
        return Build.VERSION.SDK_INT >= 17;
    }

    /* access modifiers changed from: package-private */
    @TargetApi(Version.VERSION_17)
    public static class Api17Utils {
        public static int getCreatorUid(PendingIntent pendingIntent) {
            return pendingIntent.getCreatorUid();
        }

        public static String getCreatorPackage(PendingIntent pendingIntent) {
            return pendingIntent.getCreatorPackage();
        }
    }
}
