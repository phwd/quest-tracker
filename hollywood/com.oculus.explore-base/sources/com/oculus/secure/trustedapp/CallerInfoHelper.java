package com.oculus.secure.trustedapp;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import com.oculus.secure.pendingintent.SecurePendingIntent;
import java.io.UnsupportedEncodingException;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class CallerInfoHelper {
    private static final String TAG = CallerInfoHelper.class.getSimpleName();

    @SuppressLint({"CatchGeneralException", "PendingIntentWithoutSetPackage"})
    public static Intent attachCallerInfo(Intent intent, Context context, int requestCode, int flags, String callerDomain) {
        try {
            intent.setExtrasClassLoader(context.getClassLoader());
            Bundle extras = intent.getExtras();
            if (extras == null) {
                extras = new Bundle();
            }
            extras.setClassLoader(context.getClassLoader());
            extras.putParcelable("_ci_", generateCallerInfo(context, requestCode, flags, callerDomain));
            intent.putExtras(extras);
        } catch (Exception exception) {
            Log.d(TAG, exception.getMessage());
        }
        return intent;
    }

    public static Intent attachCallerInfo(Intent intent, Context context, String callerDomain) {
        return attachCallerInfo(intent, context, 0, 1140850688, callerDomain);
    }

    static PendingIntent generateCallerInfo(Context context, int requestCode, int flags, String callerDomain) {
        return SecurePendingIntent.builder().setAction(encodeCallerInfoMetaData(callerDomain, System.currentTimeMillis())).setComponentName(getRandomizedComponentName(context)).buildCallerInfoForActivity(context, requestCode, flags);
    }

    static String encodeCallerInfoMetaData(String callerDomain, long creationTime) {
        JSONObject json = new JSONObject();
        try {
            json.put("t", Long.toString(creationTime));
            if (callerDomain != null) {
                json.put("d", callerDomain);
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

    private static ComponentName getRandomizedComponentName(Context context) {
        return new ComponentName(context, "com.facebook.class" + UUID.randomUUID().toString().replace('-', '_'));
    }
}
