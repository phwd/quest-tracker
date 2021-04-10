package com.facebook.acra;

import android.content.Context;
import com.facebook.breakpad.BreakpadManager;
import com.facebook.debug.log.BLog;
import com.facebook.reliability.anr.AnrState;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class PermissionsReporter {
    private static final String[] ALL_PERMISSIONS_SAMPLES = {"android.permission.READ_CALENDAR", "android.permission.CAMERA", "android.permission.READ_CONTACTS", "android.permission.ACCESS_FINE_LOCATION", "android.permission.RECORD_AUDIO", "android.permission.READ_PHONE_STATE", "android.permission.BODY_SENSORS", "android.permission.SEND_SMS", "android.permission.READ_EXTERNAL_STORAGE"};

    private static boolean isPermissionGranted(Context context, String str) {
        try {
            return context.checkCallingOrSelfPermission(str) == 0;
        } catch (RuntimeException unused) {
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public static String getAppGrantedPermissions(Context context) {
        char c;
        String str;
        JSONObject jSONObject = new JSONObject();
        int i = 0;
        while (true) {
            String[] strArr = ALL_PERMISSIONS_SAMPLES;
            if (i >= strArr.length) {
                return jSONObject.toString();
            }
            String str2 = strArr[i];
            switch (str2.hashCode()) {
                case -2062386608:
                    if (str2.equals("android.permission.READ_SMS")) {
                        c = 19;
                        break;
                    }
                    c = 65535;
                    break;
                case -1928411001:
                    if (str2.equals("android.permission.READ_CALENDAR")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -1921431796:
                    if (str2.equals("android.permission.READ_CALL_LOG")) {
                        c = 11;
                        break;
                    }
                    c = 65535;
                    break;
                case -1888586689:
                    if (str2.equals("android.permission.ACCESS_FINE_LOCATION")) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case -1479758289:
                    if (str2.equals("android.permission.RECEIVE_WAP_PUSH")) {
                        c = 20;
                        break;
                    }
                    c = 65535;
                    break;
                case -1238066820:
                    if (str2.equals("android.permission.BODY_SENSORS")) {
                        c = 16;
                        break;
                    }
                    c = 65535;
                    break;
                case -895673731:
                    if (str2.equals("android.permission.RECEIVE_SMS")) {
                        c = 18;
                        break;
                    }
                    c = 65535;
                    break;
                case -406040016:
                    if (str2.equals("android.permission.READ_EXTERNAL_STORAGE")) {
                        c = 21;
                        break;
                    }
                    c = 65535;
                    break;
                case -63024214:
                    if (str2.equals("android.permission.ACCESS_COARSE_LOCATION")) {
                        c = 7;
                        break;
                    }
                    c = 65535;
                    break;
                case -5573545:
                    if (str2.equals("android.permission.READ_PHONE_STATE")) {
                        c = '\t';
                        break;
                    }
                    c = 65535;
                    break;
                case 52602690:
                    if (str2.equals("android.permission.SEND_SMS")) {
                        c = 17;
                        break;
                    }
                    c = 65535;
                    break;
                case 112197485:
                    if (str2.equals("android.permission.CALL_PHONE")) {
                        c = '\n';
                        break;
                    }
                    c = 65535;
                    break;
                case 214526995:
                    if (str2.equals("android.permission.WRITE_CONTACTS")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 463403621:
                    if (str2.equals("android.permission.CAMERA")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 603653886:
                    if (str2.equals("android.permission.WRITE_CALENDAR")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 610633091:
                    if (str2.equals("android.permission.WRITE_CALL_LOG")) {
                        c = '\f';
                        break;
                    }
                    c = 65535;
                    break;
                case 784519842:
                    if (str2.equals("android.permission.USE_SIP")) {
                        c = 14;
                        break;
                    }
                    c = 65535;
                    break;
                case 952819282:
                    if (str2.equals("android.permission.PROCESS_OUTGOING_CALLS")) {
                        c = 15;
                        break;
                    }
                    c = 65535;
                    break;
                case 1271781903:
                    if (str2.equals("android.permission.GET_ACCOUNTS")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case 1365911975:
                    if (str2.equals("android.permission.WRITE_EXTERNAL_STORAGE")) {
                        c = 22;
                        break;
                    }
                    c = 65535;
                    break;
                case 1831139720:
                    if (str2.equals("android.permission.RECORD_AUDIO")) {
                        c = '\b';
                        break;
                    }
                    c = 65535;
                    break;
                case 1977429404:
                    if (str2.equals("android.permission.READ_CONTACTS")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 2133799037:
                    if (str2.equals("com.android.voicemail.permission.ADD_VOICEMAIL")) {
                        c = '\r';
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                case 1:
                    str = "android.permission-group.CALENDAR";
                    break;
                case 2:
                    str = "android.permission-group.CAMERA";
                    break;
                case 3:
                case 4:
                case 5:
                    str = "android.permission-group.CONTACTS";
                    break;
                case 6:
                case 7:
                    str = "android.permission-group.LOCATION";
                    break;
                case '\b':
                    str = "android.permission-group.MICROPHONE";
                    break;
                case AnrState.NO_SIGQUIT_AM_CONFIRMED_MT_BLOCKED$65befc1 /*{ENCODED_INT: 9}*/:
                case AnrState.NO_SIGQUIT_AM_CONFIRMED_MT_UNBLOCKED$65befc1 /*{ENCODED_INT: 10}*/:
                case BreakpadManager.SIGSEGV /*{ENCODED_INT: 11}*/:
                case '\f':
                case '\r':
                case 14:
                case 15:
                    str = "android.permission-group.PHONE";
                    break;
                case BreakpadManager.SIGSTKFLT /*{ENCODED_INT: 16}*/:
                    str = "android.permission-group.SENSORS";
                    break;
                case 17:
                case 18:
                case 19:
                case 20:
                    str = "android.permission-group.SMS";
                    break;
                case 21:
                case 22:
                    str = "android.permission-group.STORAGE";
                    break;
                default:
                    str = null;
                    break;
            }
            int lastIndexOf = str.lastIndexOf(46);
            if (lastIndexOf >= 0) {
                str = str.substring(lastIndexOf + 1);
            }
            try {
                jSONObject.put(str, isPermissionGranted(context, ALL_PERMISSIONS_SAMPLES[i]));
            } catch (JSONException e) {
                BLog.e("PermissionsReporter", e, "Caught JSONException");
            }
            i++;
        }
    }
}
