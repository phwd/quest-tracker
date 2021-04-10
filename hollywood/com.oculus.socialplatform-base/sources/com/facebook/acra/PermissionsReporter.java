package com.facebook.acra;

import X.AnonymousClass0MD;
import android.content.Context;
import com.oculus.modules.AppLaunchModuleImpl;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class PermissionsReporter {
    public static final String[] ALL_PERMISSIONS_SAMPLES = {"android.permission.READ_CALENDAR", "android.permission.CAMERA", "android.permission.READ_CONTACTS", "android.permission.ACCESS_FINE_LOCATION", AppLaunchModuleImpl.ANDROID_MICROPHONE_PERMISSION, "android.permission.READ_PHONE_STATE", "android.permission.BODY_SENSORS", "android.permission.SEND_SMS", "android.permission.READ_EXTERNAL_STORAGE"};
    public static final String TAG = "PermissionsReporter";

    public static boolean isPermissionGranted(Context context, String str) {
        try {
            return context.checkCallingOrSelfPermission(str) == 0;
        } catch (RuntimeException unused) {
            return false;
        }
    }

    public static String getAppGrantedPermissions(Context context) {
        JSONObject jSONObject = new JSONObject();
        int i = 0;
        while (true) {
            String[] strArr = ALL_PERMISSIONS_SAMPLES;
            if (i >= strArr.length) {
                return jSONObject.toString();
            }
            String groupPermission = getGroupPermission(strArr[i]);
            int lastIndexOf = groupPermission.lastIndexOf(46);
            if (lastIndexOf >= 0) {
                groupPermission = groupPermission.substring(lastIndexOf + 1);
            }
            try {
                jSONObject.put(groupPermission, isPermissionGranted(context, ALL_PERMISSIONS_SAMPLES[i]));
            } catch (JSONException e) {
                AnonymousClass0MD.A0C(TAG, e, "Caught JSONException");
            }
            i++;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0031 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x003f A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x005b A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0072 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0088 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0093 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:51:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:52:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:54:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:55:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:57:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:58:? A[RETURN, SYNTHETIC] */
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getGroupPermission(java.lang.String r1) {
        /*
        // Method dump skipped, instructions count: 244
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.PermissionsReporter.getGroupPermission(java.lang.String):java.lang.String");
    }
}
