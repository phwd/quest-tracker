package com.facebook.acra;

import android.content.Context;
import com.facebook.debug.log.BLog;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class PermissionsReporter {
    private static final String[] ALL_PERMISSIONS_SAMPLES = {"android.permission.READ_CALENDAR", "android.permission.CAMERA", "android.permission.READ_CONTACTS", "android.permission.ACCESS_FINE_LOCATION", "android.permission.RECORD_AUDIO", "android.permission.READ_PHONE_STATE", "android.permission.BODY_SENSORS", "android.permission.SEND_SMS", "android.permission.READ_EXTERNAL_STORAGE"};
    public static final String TAG = "PermissionsReporter";

    @Nullable
    public static String getGroupPermission(String permission) {
        char c = 65535;
        switch (permission.hashCode()) {
            case -2062386608:
                if (permission.equals("android.permission.READ_SMS")) {
                    c = 19;
                    break;
                }
                break;
            case -1928411001:
                if (permission.equals("android.permission.READ_CALENDAR")) {
                    c = 0;
                    break;
                }
                break;
            case -1921431796:
                if (permission.equals("android.permission.READ_CALL_LOG")) {
                    c = 11;
                    break;
                }
                break;
            case -1888586689:
                if (permission.equals("android.permission.ACCESS_FINE_LOCATION")) {
                    c = 6;
                    break;
                }
                break;
            case -1479758289:
                if (permission.equals("android.permission.RECEIVE_WAP_PUSH")) {
                    c = 20;
                    break;
                }
                break;
            case -1238066820:
                if (permission.equals("android.permission.BODY_SENSORS")) {
                    c = 16;
                    break;
                }
                break;
            case -895673731:
                if (permission.equals("android.permission.RECEIVE_SMS")) {
                    c = 18;
                    break;
                }
                break;
            case -406040016:
                if (permission.equals("android.permission.READ_EXTERNAL_STORAGE")) {
                    c = 21;
                    break;
                }
                break;
            case -63024214:
                if (permission.equals("android.permission.ACCESS_COARSE_LOCATION")) {
                    c = 7;
                    break;
                }
                break;
            case -5573545:
                if (permission.equals("android.permission.READ_PHONE_STATE")) {
                    c = '\t';
                    break;
                }
                break;
            case 52602690:
                if (permission.equals("android.permission.SEND_SMS")) {
                    c = 17;
                    break;
                }
                break;
            case 112197485:
                if (permission.equals("android.permission.CALL_PHONE")) {
                    c = '\n';
                    break;
                }
                break;
            case 214526995:
                if (permission.equals("android.permission.WRITE_CONTACTS")) {
                    c = 4;
                    break;
                }
                break;
            case 463403621:
                if (permission.equals("android.permission.CAMERA")) {
                    c = 2;
                    break;
                }
                break;
            case 603653886:
                if (permission.equals("android.permission.WRITE_CALENDAR")) {
                    c = 1;
                    break;
                }
                break;
            case 610633091:
                if (permission.equals("android.permission.WRITE_CALL_LOG")) {
                    c = '\f';
                    break;
                }
                break;
            case 784519842:
                if (permission.equals("android.permission.USE_SIP")) {
                    c = 14;
                    break;
                }
                break;
            case 952819282:
                if (permission.equals("android.permission.PROCESS_OUTGOING_CALLS")) {
                    c = 15;
                    break;
                }
                break;
            case 1271781903:
                if (permission.equals("android.permission.GET_ACCOUNTS")) {
                    c = 5;
                    break;
                }
                break;
            case 1365911975:
                if (permission.equals("android.permission.WRITE_EXTERNAL_STORAGE")) {
                    c = 22;
                    break;
                }
                break;
            case 1831139720:
                if (permission.equals("android.permission.RECORD_AUDIO")) {
                    c = '\b';
                    break;
                }
                break;
            case 1977429404:
                if (permission.equals("android.permission.READ_CONTACTS")) {
                    c = 3;
                    break;
                }
                break;
            case 2133799037:
                if (permission.equals("com.android.voicemail.permission.ADD_VOICEMAIL")) {
                    c = '\r';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
                return "android.permission-group.CALENDAR";
            case 2:
                return "android.permission-group.CAMERA";
            case 3:
            case 4:
            case 5:
                return "android.permission-group.CONTACTS";
            case 6:
            case 7:
                return "android.permission-group.LOCATION";
            case '\b':
                return "android.permission-group.MICROPHONE";
            case '\t':
            case '\n':
            case 11:
            case '\f':
            case '\r':
            case 14:
            case 15:
                return "android.permission-group.PHONE";
            case 16:
                return "android.permission-group.SENSORS";
            case 17:
            case 18:
            case 19:
            case 20:
                return "android.permission-group.SMS";
            case 21:
            case 22:
                return "android.permission-group.STORAGE";
            default:
                return null;
        }
    }

    private static boolean isPermissionGranted(Context context, String permissionName) {
        try {
            return context.checkCallingOrSelfPermission(permissionName) == 0;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public static String getAppGrantedPermissions(Context context) {
        JSONObject perms = new JSONObject();
        for (int i = 0; i < ALL_PERMISSIONS_SAMPLES.length; i++) {
            String permString = getGroupPermission(ALL_PERMISSIONS_SAMPLES[i]);
            int tailIndex = permString.lastIndexOf(46);
            if (tailIndex >= 0) {
                permString = permString.substring(tailIndex + 1);
            }
            try {
                perms.put(permString, isPermissionGranted(context, ALL_PERMISSIONS_SAMPLES[i]));
            } catch (JSONException e) {
                BLog.e(TAG, e, "Caught JSONException");
            }
        }
        return perms.toString();
    }
}
