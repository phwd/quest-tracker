package com.oculus.security;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import com.oculus.security.SecurePendingIntent;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class CallerInfoHelper {
    @SuppressLint({"CatchGeneralException", "PendingIntentWithoutSetPackage"})
    public static Intent attachCallerInfo(Intent intent, Context context, int i, int i2, String str) {
        try {
            intent.setExtrasClassLoader(context.getClassLoader());
            Bundle extras = intent.getExtras();
            if (extras == null) {
                extras = new Bundle();
            }
            extras.setClassLoader(context.getClassLoader());
            extras.putParcelable("_ci_", generateCallerInfo(context, i, i2, str));
            intent.putExtras(extras);
        } catch (Exception e) {
            Log.d("CallerInfoHelper", e.getMessage());
        }
        return intent;
    }

    public static Intent attachCallerInfo(Intent intent, Context context, String str) {
        attachCallerInfo(intent, context, 0, 1140850688, str);
        return intent;
    }

    static PendingIntent generateCallerInfo(Context context, int i, int i2, String str) {
        String encodeCallerInfoMetaData = encodeCallerInfoMetaData(str, System.currentTimeMillis());
        SecurePendingIntent.Builder builder = SecurePendingIntent.builder();
        builder.setAction(encodeCallerInfoMetaData);
        builder.setComponentName(getRandomizedComponentName(context));
        return builder.buildCallerInfoForActivity(context, i, i2);
    }

    static String encodeCallerInfoMetaData(String str, long j) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("t", Long.toString(j));
            if (str != null) {
                jSONObject.put("d", str);
            }
            return Base64.encodeToString(jSONObject.toString().getBytes("UTF-8"), 11);
        } catch (JSONException unused) {
            return null;
        }
    }

    private static ComponentName getRandomizedComponentName(Context context) {
        return new ComponentName(context, "com.facebook.class" + UUID.randomUUID().toString().replace('-', '_'));
    }
}
