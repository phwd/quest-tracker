package com.oculus.vrshell.util;

import X.AnonymousClass006;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.util.SecurePendingIntent;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class CallerInfoHelper {
    public static final int BASE64_FLAG = 11;
    public static final String CALLER_DOMAIN = "d";
    public static final String CALLER_INFO = "_ci_";
    public static final int CALLER_INFO_TIMEOUT = 10000;
    public static final String CREATION_TIME = "t";
    public static final String ENCODING = "UTF-8";
    public static final String INVALID_CLASS_NAME = "com.facebook.class";
    public static final int PENDING_INTENT_DEFAULT_FLAGS = 1140850688;
    public static final int PENDING_INTENT_DEFAULT_REQUEST_CODE = 0;
    public static final String TAG = LoggingUtil.tag(CallerInfoHelper.class);

    public static class CallerInfo {
        @Nullable
        public final String callerDomain;
        @Nullable
        public final String callerPackageName;
        public final int callerUid;
        public final boolean isValid;

        public static CallerInfo createInvalid(int i, @Nullable String str, @Nullable String str2) {
            return new CallerInfo(false, i, str, str2);
        }

        public static CallerInfo createValid(int i, @Nullable String str, @Nullable String str2) {
            return new CallerInfo(true, i, str, str2);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("CallerInfo={ isValid=");
            sb.append(this.isValid);
            sb.append(",uid=");
            sb.append(this.callerUid);
            sb.append(",callerPackageName=");
            String str = this.callerPackageName;
            if (str == null) {
                str = "null";
            }
            sb.append(str);
            sb.append(",callerDomain=");
            sb.append(this.callerDomain);
            sb.append("}");
            return sb.toString();
        }

        public CallerInfo(boolean z, int i, @Nullable String str, @Nullable String str2) {
            this.isValid = z;
            this.callerUid = i;
            this.callerPackageName = str;
            this.callerDomain = str2;
        }
    }

    public static boolean isValidTimestamp(long j, long j2) {
        return j - j2 < 10000;
    }

    @Nullable
    public static String encodeCallerInfoMetaData(@Nullable String str, long j) {
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

    @Nullable
    public static String extractMetaDataFromPendingIntent(PendingIntent pendingIntent) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return (String) PendingIntent.class.getMethod("getTag", String.class).invoke(pendingIntent, "");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:? A[ExcHandler: IllegalAccessException | NoSuchMethodException | InvocationTargetException (unused java.lang.Throwable), SYNTHETIC, Splitter:B:7:0x002b] */
    @android.annotation.SuppressLint({"DeprecatedMethod"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.oculus.vrshell.util.CallerInfoHelper.CallerInfo getCallerInfo(android.content.Intent r11) {
        /*
            java.lang.String r9 = "d"
            java.lang.String r1 = "_ci_"
            boolean r0 = r11.hasExtra(r1)
            r7 = -1
            r10 = 0
            r6 = 0
            if (r0 == 0) goto L_0x005a
            android.os.Parcelable r0 = r11.getParcelableExtra(r1)
            android.app.PendingIntent r0 = (android.app.PendingIntent) r0
            if (r0 == 0) goto L_0x005a
            java.lang.String r8 = r0.getCreatorPackage()
            int r5 = r0.getCreatorUid()
            java.lang.String r1 = extractMetaDataFromPendingIntent(r0)
            if (r1 == 0) goto L_0x005a
            r0 = 11
            byte[] r2 = android.util.Base64.decode(r1, r0)
            java.lang.String r1 = "UTF-8"
            java.lang.String r0 = new java.lang.String     // Catch:{ IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x005a, IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x005a }
            r0.<init>(r2, r1)     // Catch:{ IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x005a, IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x005a }
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x005a, IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x005a }
            r1.<init>(r0)     // Catch:{ IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x005a, IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x005a }
            java.lang.String r0 = "t"
            java.lang.String r0 = r1.getString(r0)     // Catch:{ IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x005a, IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x005a }
            long r3 = java.lang.Long.parseLong(r0)     // Catch:{ IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x005a, IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x005a }
            boolean r0 = r1.has(r9)     // Catch:{ IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x005a, IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x005a }
            if (r0 == 0) goto L_0x004a
            java.lang.String r2 = r1.getString(r9)     // Catch:{ IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x005a, IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x005a }
            goto L_0x004b
        L_0x004a:
            r2 = r6
        L_0x004b:
            long r0 = java.lang.System.currentTimeMillis()     // Catch:{ UnsupportedEncodingException | NumberFormatException | JSONException -> 0x0053 }
            boolean r10 = isValidTimestamp(r0, r3)     // Catch:{ UnsupportedEncodingException | NumberFormatException | JSONException -> 0x0053 }
        L_0x0053:
            if (r10 == 0) goto L_0x005a
            com.oculus.vrshell.util.CallerInfoHelper$CallerInfo r0 = com.oculus.vrshell.util.CallerInfoHelper.CallerInfo.createValid(r5, r8, r2)
            return r0
        L_0x005a:
            com.oculus.vrshell.util.CallerInfoHelper$CallerInfo r0 = com.oculus.vrshell.util.CallerInfoHelper.CallerInfo.createInvalid(r7, r6, r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrshell.util.CallerInfoHelper.getCallerInfo(android.content.Intent):com.oculus.vrshell.util.CallerInfoHelper$CallerInfo");
    }

    public static ComponentName getRandomizedComponentName(Context context) {
        return new ComponentName(context, AnonymousClass006.A07("com.facebook.class", UUID.randomUUID().toString().replace('-', '_')));
    }

    public static Intent removeCallerInfo(Intent intent) {
        if (intent.hasExtra("_ci_")) {
            intent.removeExtra("_ci_");
        }
        return intent;
    }

    public static void enforceValidCallerInfo(Intent intent) {
        if (!getCallerInfo(intent).isValid) {
            StringBuilder sb = new StringBuilder("Invalid caller info in incoming intent!");
            sb.append(intent);
            throw new SecurityException(sb.toString());
        }
    }

    public static PendingIntent generateCallerInfo(Context context, int i, int i2, @Nullable String str) {
        String encodeCallerInfoMetaData = encodeCallerInfoMetaData(str, System.currentTimeMillis());
        SecurePendingIntent.Builder builder = new SecurePendingIntent.Builder();
        builder.mAction = encodeCallerInfoMetaData;
        builder.mComponentName = getRandomizedComponentName(context);
        return builder.buildCallerInfoForActivity(context, i, i2);
    }

    public static Intent attachCallerInfoCatchException(Intent intent, Context context, @Nullable String str) {
        try {
            attachCallerInfo(intent, context, str);
        } catch (Exception unused) {
        }
        return intent;
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
            extras.putParcelable("_ci_", generateCallerInfo(context, i, i2, str));
            intent.putExtras(extras);
            return intent;
        } catch (Exception e) {
            e.getMessage();
            return intent;
        }
    }

    public static Intent attachCallerInfo(Intent intent, Context context, @Nullable String str) {
        attachCallerInfo(intent, context, 0, 1140850688, str);
        return intent;
    }
}
