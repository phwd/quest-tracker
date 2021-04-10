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
import com.facebook.debug.log.LoggingUtil;
import com.facebook.secure.logger.Reporter;
import com.facebook.secure.pendingintent.SecurePendingIntent;
import com.facebook.secure.trustedapp.exception.CannotAttachCallerInfoException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public class CallerInfoHelper {
    static final int BASE64_FLAG = 11;
    static final String CALLER_DOMAIN = "d";
    static final String CALLER_INFO = "_ci_";
    static final int CALLER_INFO_LONG_TIMEOUT_UNSAFE = 86400000;
    static final int CALLER_INFO_TIMEOUT = 60000;
    static final String CREATION_TIME_EPOCH = "t";
    static final String CREATION_TIME_SYSTEM_REALTIME = "r";
    static final String ENCODING = "UTF-8";
    static final String ERROR_BELOW_API_17 = "Skipping caller identity metadata check on API <= 17.";
    static final String ERROR_EXPIRED_CALLER_IDENTITY = "Caller identity has expired.";
    static final String ERROR_EXTRACTING_METADATA = "Error extracting metadata from caller identity.";
    static final String ERROR_MISSING_CALLER_IDENTITY = "Missing caller identity intent extra.";
    static final String ERROR_MISSING_SIGNATURE = "Failed to get signature.";
    static final String ERROR_NULL_CALLER_IDENTITY = "Null caller identity intent extra.";
    static final String ERROR_NULL_METADATA = String.format("Null metadata in caller identity, API=%d", Integer.valueOf(Build.VERSION.SDK_INT));
    static final String ERROR_NULL_METADATA_PREFIX = "Null metadata in caller identity, API=";
    static final String ERROR_PARSING_METADATA = "Error parsing metadata from caller identity.";
    static final String INVALID_CLASS = "com.facebook.invalid_class.f4c3b00c";
    static final int PENDING_INTENT_DEFAULT_FLAGS = 1140850688;
    static final int PENDING_INTENT_DEFAULT_REQUEST_CODE = 0;
    static final String TAG = "CallerInfoHelper";
    static final String VERSION_NAME = "v";

    @SuppressLint({"CatchGeneralException", "PendingIntentWithoutSetPackage"})
    public static Intent attachCallerInfo(Intent intent, Context context, int i, int i2, @Nullable String str) throws CannotAttachCallerInfoException {
        try {
            intent.setExtrasClassLoader(context.getClassLoader());
            Bundle extras = intent.getExtras();
            if (extras == null) {
                extras = new Bundle();
            }
            extras.setClassLoader(context.getClassLoader());
            extras.putParcelable(CALLER_INFO, generateCallerInfo(context, i, i2, str));
            intent.putExtras(extras);
            return intent;
        } catch (Exception e) {
            throw new CannotAttachCallerInfoException(e);
        }
    }

    public static Intent attachCallerInfo(Intent intent, Context context, @Nullable String str) throws CannotAttachCallerInfoException {
        return attachCallerInfo(intent, context, 0, PENDING_INTENT_DEFAULT_FLAGS, str);
    }

    public static Intent attachCallerInfoWithErrorReporting(Intent intent, Context context, @Nullable String str, Reporter reporter) {
        try {
            return attachCallerInfo(intent, context, str);
        } catch (CannotAttachCallerInfoException e) {
            reporter.report(TAG, "Error attaching caller info to Intent.", e);
            return intent;
        }
    }

    public static Intent attachCallerInfoCatchException(Intent intent, Context context, @Nullable String str) {
        try {
            return attachCallerInfo(intent, context, str);
        } catch (CannotAttachCallerInfoException unused) {
            return intent;
        }
    }

    public static Intent removeCallerInfo(Intent intent) {
        if (intent.hasExtra(CALLER_INFO)) {
            intent.removeExtra(CALLER_INFO);
        }
        return intent;
    }

    @Nullable
    public static AppIdentity getCallerInfo(Context context, Intent intent, @Nullable Reporter reporter) {
        return getCallerInfo(context, intent, false, reporter);
    }

    /* JADX WARNING: Removed duplicated region for block: B:57:0x0102  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0109  */
    @javax.annotation.Nullable
    @android.annotation.SuppressLint({"DeprecatedMethod"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.facebook.secure.trustedapp.AppIdentity getCallerInfo(android.content.Context r16, android.content.Intent r17, boolean r18, @javax.annotation.Nullable com.facebook.secure.logger.Reporter r19) {
        /*
        // Method dump skipped, instructions count: 267
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.secure.trustedapp.CallerInfoHelper.getCallerInfo(android.content.Context, android.content.Intent, boolean, com.facebook.secure.logger.Reporter):com.facebook.secure.trustedapp.AppIdentity");
    }

    @Nullable
    public static AppIdentity getCallerInfo(Context context, Intent intent) {
        return getCallerInfo(context, intent, null);
    }

    public static void enforceValidCallerInfo(Context context, Intent intent, Reporter reporter) {
        if (getCallerInfo(context, intent, reporter) == null) {
            throw new SecurityException("Invalid caller info in incoming intent!" + intent);
        }
    }

    public static void enforceValidCallerInfo(Context context, Intent intent) {
        if (getCallerInfo(context, intent) == null) {
            throw new SecurityException("Invalid caller info in incoming intent!" + intent);
        }
    }

    static boolean isValidTimestamp(long j, boolean z, boolean z2) {
        return (z2 ? System.currentTimeMillis() : SystemClock.elapsedRealtime()) - j < ((long) (z ? CALLER_INFO_LONG_TIMEOUT_UNSAFE : CALLER_INFO_TIMEOUT));
    }

    static PendingIntent generateCallerInfo(Context context, int i, int i2, @Nullable String str) {
        String str2;
        try {
            str2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException | RuntimeException unused) {
            str2 = null;
        }
        return SecurePendingIntent.builder().setAction(encodeCallerInfoMetaData(System.currentTimeMillis(), SystemClock.elapsedRealtime(), str2, str)).setComponentName(new ComponentName(context, INVALID_CLASS)).buildForActivity(context, i, i2);
    }

    @Nullable
    static String encodeCallerInfoMetaData(long j, long j2, @Nullable String str, @Nullable String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(CREATION_TIME_EPOCH, Long.toString(j));
            jSONObject.put(CREATION_TIME_SYSTEM_REALTIME, Long.toString(j2));
            if (str2 != null) {
                jSONObject.put(CALLER_DOMAIN, str2);
            }
            if (str != null) {
                jSONObject.put(VERSION_NAME, str);
            }
            return Base64.encodeToString(jSONObject.toString().getBytes("UTF-8"), 11);
        } catch (JSONException unused) {
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

    private static void logToReporter(@Nullable Reporter reporter, String str, @Nullable Throwable th) {
        if (reporter != null) {
            reporter.report(TAG, str, th);
        }
    }

    static boolean isOnOrAboveApi17() {
        return Build.VERSION.SDK_INT >= 17;
    }

    /* access modifiers changed from: package-private */
    @TargetApi(17)
    public static class Api17Utils {
        Api17Utils() {
        }

        public static int getCreatorUid(PendingIntent pendingIntent) {
            return pendingIntent.getCreatorUid();
        }

        public static String getCreatorPackage(PendingIntent pendingIntent) {
            return pendingIntent.getCreatorPackage();
        }
    }

    public static class CallerInfo {
        @Nullable
        public final String callerDomain;
        @Nullable
        public final String callerPackageName;
        public final int callerUid;
        @Nullable
        public final String callerVersionName;

        public CallerInfo(int i, @Nullable String str, @Nullable String str2, @Nullable String str3) {
            this.callerUid = i;
            this.callerPackageName = str;
            this.callerVersionName = str2;
            this.callerDomain = str3;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("CallerInfo={ uid=");
            sb.append(this.callerUid);
            sb.append(",callerPackageName=");
            String str = this.callerPackageName;
            if (str == null) {
                str = LoggingUtil.NO_HASHCODE;
            }
            sb.append(str);
            sb.append(",callerVersionName=");
            String str2 = this.callerVersionName;
            if (str2 == null) {
                str2 = LoggingUtil.NO_HASHCODE;
            }
            sb.append(str2);
            sb.append(",callerDomain=");
            String str3 = this.callerDomain;
            if (str3 == null) {
                str3 = LoggingUtil.NO_HASHCODE;
            }
            sb.append(str3);
            sb.append("}");
            return sb.toString();
        }

        public JSONObject toJson() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("caller_uid", this.callerUid);
            String str = this.callerPackageName;
            if (str != null) {
                jSONObject.put("caller_package_name", str);
            }
            String str2 = this.callerVersionName;
            if (str2 != null) {
                jSONObject.put("caller_version_name", str2);
            }
            String str3 = this.callerDomain;
            if (str3 != null) {
                jSONObject.put("caller_domain", str3);
            }
            return jSONObject;
        }

        public static String getPackageName(@Nullable CallerInfo callerInfo) {
            if (callerInfo == null) {
                return LoggingUtil.NO_HASHCODE;
            }
            String str = callerInfo.callerPackageName;
            return str == null ? LoggingUtil.NO_HASHCODE : str;
        }
    }
}
