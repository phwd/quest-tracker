package com.oculus.secure.trustedapp;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import com.oculus.secure.pendingintent.SecurePendingIntent;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
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

    @SuppressLint({"DeprecatedMethod"})
    public static CallerInfo getCallerInfo(Intent launchingIntent, boolean enforceTimeout) {
        PendingIntent pendingIntent;
        boolean isValid = !isOnOrAboveApi17();
        int callerUid = -1;
        String callerPackageName = null;
        String callerDomain = null;
        if (launchingIntent.hasExtra("_ci_") && (pendingIntent = (PendingIntent) launchingIntent.getParcelableExtra("_ci_")) != null) {
            if (isOnOrAboveApi17()) {
                callerPackageName = Api17Utils.getCreatorPackage(pendingIntent);
            } else {
                callerPackageName = pendingIntent.getTargetPackage();
            }
            if (isOnOrAboveApi17()) {
                callerUid = Api17Utils.getCreatorUid(pendingIntent);
            }
            String metaData = null;
            try {
                metaData = extractMetaDataFromPendingIntent(pendingIntent);
            } catch (NoSuchMethodException e) {
                isValid = false;
            } catch (InvocationTargetException e2) {
                isValid = false;
            } catch (IllegalAccessException e3) {
                isValid = false;
            }
            if (metaData != null) {
                try {
                    JSONObject metaDataJson = new JSONObject(new String(Base64.decode(metaData, 11), "UTF-8"));
                    long creationTime = Long.parseLong(metaDataJson.getString("t"));
                    if (metaDataJson.has("d")) {
                        callerDomain = metaDataJson.getString("d");
                    }
                    if (isValidTimestamp(System.currentTimeMillis(), creationTime) || !enforceTimeout) {
                        isValid = true;
                    }
                } catch (UnsupportedEncodingException e4) {
                    isValid = false;
                } catch (JSONException e5) {
                    isValid = false;
                } catch (NumberFormatException e6) {
                    isValid = false;
                }
            }
        }
        if (isValid) {
            return CallerInfo.createValid(callerUid, callerPackageName, callerDomain);
        }
        return CallerInfo.createInvalid(-1, null, null);
    }

    static boolean isValidTimestamp(long currentTime, long sentTime) {
        return currentTime - sentTime < 10000;
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

    private static String extractMetaDataFromPendingIntent(PendingIntent pendingIntent) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (Build.VERSION.SDK_INT >= 24) {
            return (String) PendingIntent.class.getMethod("getTag", String.class).invoke(pendingIntent, "");
        } else if (Build.VERSION.SDK_INT >= 18) {
            return ((Intent) PendingIntent.class.getMethod("getIntent", new Class[0]).invoke(pendingIntent, new Object[0])).getAction();
        } else {
            return null;
        }
    }

    static boolean isOnOrAboveApi17() {
        return Build.VERSION.SDK_INT >= 17;
    }

    @TargetApi(17)
    static class Api17Utils {
        public static int getCreatorUid(PendingIntent pendingIntent) {
            return pendingIntent.getCreatorUid();
        }

        public static String getCreatorPackage(PendingIntent pendingIntent) {
            return pendingIntent.getCreatorPackage();
        }
    }

    public static class CallerInfo {
        public final String callerDomain;
        public final String callerPackageName;
        public final int callerUid;
        public final boolean isValid;

        private CallerInfo(boolean isValid2, int callerUid2, String callerPackageName2, String callerDomain2) {
            this.isValid = isValid2;
            this.callerUid = callerUid2;
            this.callerPackageName = callerPackageName2;
            this.callerDomain = callerDomain2;
        }

        public static CallerInfo createValid(int uid, String packageName, String domain) {
            return new CallerInfo(true, uid, packageName, domain);
        }

        public static CallerInfo createInvalid(int uid, String packageName, String domain) {
            return new CallerInfo(false, uid, packageName, domain);
        }

        public String toString() {
            return "CallerInfo={ isValid=" + this.isValid + ",uid=" + this.callerUid + ",callerPackageName=" + (this.callerPackageName == null ? "null" : this.callerPackageName) + ",callerDomain=" + this.callerDomain + "}";
        }
    }
}
