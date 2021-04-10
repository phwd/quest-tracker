package com.oculus.vrshell.util;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class CallerInfoHelper {
    private static final int BASE64_FLAG = 11;
    private static final String CALLER_DOMAIN = "d";
    private static final String CALLER_INFO = "_ci_";
    private static final int CALLER_INFO_TIMEOUT = 10000;
    private static final String CREATION_TIME = "t";
    private static final String ENCODING = "UTF-8";
    private static final String INVALID_CLASS_NAME = "com.facebook.class";
    private static final int PENDING_INTENT_DEFAULT_FLAGS = 1140850688;
    private static final int PENDING_INTENT_DEFAULT_REQUEST_CODE = 0;
    private static final String TAG = LoggingUtil.tag(CallerInfoHelper.class);

    static boolean isValidTimestamp(long j, long j2) {
        return j - j2 < 10000;
    }

    @SuppressLint({"CatchGeneralException", "PendingIntentWithoutSetPackage"})
    public static Intent attachCallerInfo(Intent intent, Context context, int i, int i2, @Nullable String str) {
        try {
            intent.setExtrasClassLoader(context.getClassLoader());
            Bundle extras = intent.getExtras();
            if (extras == null) {
                extras = new Bundle();
            }
            extras.setClassLoader(context.getClassLoader());
            extras.putParcelable(CALLER_INFO, generateCallerInfo(context, i, i2, str));
            intent.putExtras(extras);
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }
        return intent;
    }

    public static Intent attachCallerInfo(Intent intent, Context context, @Nullable String str) {
        return attachCallerInfo(intent, context, 0, PENDING_INTENT_DEFAULT_FLAGS, str);
    }

    public static Intent attachCallerInfoCatchException(Intent intent, Context context, @Nullable String str) {
        try {
            return attachCallerInfo(intent, context, str);
        } catch (Exception unused) {
            return intent;
        }
    }

    public static Intent removeCallerInfo(Intent intent) {
        if (intent.hasExtra(CALLER_INFO)) {
            intent.removeExtra(CALLER_INFO);
        }
        return intent;
    }

    @SuppressLint({"DeprecatedMethod"})
    public static CallerInfo getCallerInfo(Intent intent) {
        String str;
        int i;
        String str2;
        PendingIntent pendingIntent;
        String str3;
        boolean z = false;
        if (!intent.hasExtra(CALLER_INFO) || (pendingIntent = (PendingIntent) intent.getParcelableExtra(CALLER_INFO)) == null) {
            i = -1;
            str = null;
            str2 = null;
        } else {
            str2 = pendingIntent.getCreatorPackage();
            i = pendingIntent.getCreatorUid();
            try {
                str3 = extractMetaDataFromPendingIntent(pendingIntent);
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
                str3 = null;
            }
            if (str3 != null) {
                try {
                    JSONObject jSONObject = new JSONObject(new String(Base64.decode(str3, 11), "UTF-8"));
                    long parseLong = Long.parseLong(jSONObject.getString(CREATION_TIME));
                    str = jSONObject.has(CALLER_DOMAIN) ? jSONObject.getString(CALLER_DOMAIN) : null;
                    try {
                        z = isValidTimestamp(System.currentTimeMillis(), parseLong);
                    } catch (UnsupportedEncodingException | NumberFormatException | JSONException unused2) {
                    }
                } catch (UnsupportedEncodingException | NumberFormatException | JSONException unused3) {
                }
            }
            str = null;
        }
        if (z) {
            return CallerInfo.createValid(i, str2, str);
        }
        return CallerInfo.createInvalid(-1, null, null);
    }

    public static void enforceValidCallerInfo(Intent intent) {
        if (!getCallerInfo(intent).isValid) {
            throw new SecurityException("Invalid caller info in incoming intent!" + intent);
        }
    }

    static PendingIntent generateCallerInfo(Context context, int i, int i2, @Nullable String str) {
        return SecurePendingIntent.builder().setAction(encodeCallerInfoMetaData(str, System.currentTimeMillis())).setComponentName(getRandomizedComponentName(context)).buildCallerInfoForActivity(context, i, i2);
    }

    @Nullable
    static String encodeCallerInfoMetaData(@Nullable String str, long j) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(CREATION_TIME, Long.toString(j));
            if (str != null) {
                jSONObject.put(CALLER_DOMAIN, str);
            }
            return Base64.encodeToString(jSONObject.toString().getBytes("UTF-8"), 11);
        } catch (JSONException unused) {
            return null;
        }
    }

    private static ComponentName getRandomizedComponentName(Context context) {
        return new ComponentName(context, INVALID_CLASS_NAME + UUID.randomUUID().toString().replace('-', '_'));
    }

    @Nullable
    private static String extractMetaDataFromPendingIntent(PendingIntent pendingIntent) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return (String) PendingIntent.class.getMethod("getTag", String.class).invoke(pendingIntent, "");
    }

    public static class CallerInfo {
        @Nullable
        public final String callerDomain;
        @Nullable
        public final String callerPackageName;
        public final int callerUid;
        public final boolean isValid;

        private CallerInfo(boolean z, int i, @Nullable String str, @Nullable String str2) {
            this.isValid = z;
            this.callerUid = i;
            this.callerPackageName = str;
            this.callerDomain = str2;
        }

        public static CallerInfo createValid(int i, @Nullable String str, @Nullable String str2) {
            return new CallerInfo(true, i, str, str2);
        }

        public static CallerInfo createInvalid(int i, @Nullable String str, @Nullable String str2) {
            return new CallerInfo(false, i, str, str2);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("CallerInfo={ isValid=");
            sb.append(this.isValid);
            sb.append(",uid=");
            sb.append(this.callerUid);
            sb.append(",callerPackageName=");
            String str = this.callerPackageName;
            if (str == null) {
                str = com.facebook.debug.log.LoggingUtil.NO_HASHCODE;
            }
            sb.append(str);
            sb.append(",callerDomain=");
            sb.append(this.callerDomain);
            sb.append("}");
            return sb.toString();
        }
    }
}
