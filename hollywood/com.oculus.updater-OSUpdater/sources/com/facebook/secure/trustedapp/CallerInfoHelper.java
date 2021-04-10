package com.facebook.secure.trustedapp;

import android.annotation.SuppressLint;
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
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public class CallerInfoHelper {
    static final String ERROR_NULL_METADATA = String.format("Null metadata in caller identity, API=%d", Integer.valueOf(Build.VERSION.SDK_INT));

    @SuppressLint({"CatchGeneralException", "PendingIntentWithoutSetPackage"})
    public static Intent attachCallerInfo(Intent intent, Context context, int i, int i2, @Nullable String str) throws CannotAttachCallerInfoException {
        try {
            intent.setExtrasClassLoader(context.getClassLoader());
            Bundle extras = intent.getExtras();
            if (extras == null) {
                extras = new Bundle();
            }
            extras.setClassLoader(context.getClassLoader());
            extras.putParcelable("_ci_", generateCallerInfo(context, i, i2, str));
            intent.putExtras(extras);
            return intent;
        } catch (Exception e) {
            throw new CannotAttachCallerInfoException(e);
        }
    }

    public static Intent attachCallerInfo(Intent intent, Context context, @Nullable String str) throws CannotAttachCallerInfoException {
        return attachCallerInfo(intent, context, 0, 1140850688, str);
    }

    public static Intent attachCallerInfoWithErrorReporting(Intent intent, Context context, @Nullable String str, Reporter reporter) {
        try {
            return attachCallerInfo(intent, context, str);
        } catch (CannotAttachCallerInfoException e) {
            reporter.report("CallerInfoHelper", "Error attaching caller info to Intent.", e);
            return intent;
        }
    }

    static PendingIntent generateCallerInfo(Context context, int i, int i2, @Nullable String str) {
        String str2;
        try {
            str2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException | RuntimeException unused) {
            str2 = null;
        }
        return SecurePendingIntent.builder().setAction(encodeCallerInfoMetaData(System.currentTimeMillis(), SystemClock.elapsedRealtime(), str2, str)).setComponentName(new ComponentName(context, "com.facebook.invalid_class.f4c3b00c")).buildForActivity(context, i, i2);
    }

    @Nullable
    static String encodeCallerInfoMetaData(long j, long j2, @Nullable String str, @Nullable String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("t", Long.toString(j));
            jSONObject.put("r", Long.toString(j2));
            if (str2 != null) {
                jSONObject.put("d", str2);
            }
            if (str != null) {
                jSONObject.put("v", str);
            }
            return Base64.encodeToString(jSONObject.toString().getBytes("UTF-8"), 11);
        } catch (JSONException unused) {
            return null;
        }
    }
}
