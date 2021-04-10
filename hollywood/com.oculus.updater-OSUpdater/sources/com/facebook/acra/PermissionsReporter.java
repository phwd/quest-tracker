package com.facebook.acra;

import android.content.Context;
import com.facebook.debug.log.BLog;
import com.facebook.ultralight.UL;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class PermissionsReporter {
    private static final String[] ALL_PERMISSIONS_SAMPLES = {"android.permission.READ_CALENDAR", "android.permission.CAMERA", "android.permission.READ_CONTACTS", "android.permission.ACCESS_FINE_LOCATION", "android.permission.RECORD_AUDIO", "android.permission.READ_PHONE_STATE", "android.permission.BODY_SENSORS", "android.permission.SEND_SMS", "android.permission.READ_EXTERNAL_STORAGE"};
    public static final String TAG = "PermissionsReporter";

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    @Nullable
    public static String getGroupPermission(String str) {
        char c;
        switch (str.hashCode()) {
            case -2062386608:
                if (str.equals("android.permission.READ_SMS")) {
                    c = 19;
                    break;
                }
                c = 65535;
                break;
            case -1928411001:
                if (str.equals("android.permission.READ_CALENDAR")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1921431796:
                if (str.equals("android.permission.READ_CALL_LOG")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case -1888586689:
                if (str.equals("android.permission.ACCESS_FINE_LOCATION")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -1479758289:
                if (str.equals("android.permission.RECEIVE_WAP_PUSH")) {
                    c = 20;
                    break;
                }
                c = 65535;
                break;
            case -1238066820:
                if (str.equals("android.permission.BODY_SENSORS")) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case -895673731:
                if (str.equals("android.permission.RECEIVE_SMS")) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case -406040016:
                if (str.equals("android.permission.READ_EXTERNAL_STORAGE")) {
                    c = 21;
                    break;
                }
                c = 65535;
                break;
            case -63024214:
                if (str.equals("android.permission.ACCESS_COARSE_LOCATION")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -5573545:
                if (str.equals("android.permission.READ_PHONE_STATE")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 52602690:
                if (str.equals("android.permission.SEND_SMS")) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case 112197485:
                if (str.equals("android.permission.CALL_PHONE")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 214526995:
                if (str.equals("android.permission.WRITE_CONTACTS")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 463403621:
                if (str.equals("android.permission.CAMERA")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 603653886:
                if (str.equals("android.permission.WRITE_CALENDAR")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 610633091:
                if (str.equals("android.permission.WRITE_CALL_LOG")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case 784519842:
                if (str.equals("android.permission.USE_SIP")) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case 952819282:
                if (str.equals("android.permission.PROCESS_OUTGOING_CALLS")) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case 1271781903:
                if (str.equals("android.permission.GET_ACCOUNTS")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1365911975:
                if (str.equals("android.permission.WRITE_EXTERNAL_STORAGE")) {
                    c = 22;
                    break;
                }
                c = 65535;
                break;
            case 1831139720:
                if (str.equals("android.permission.RECORD_AUDIO")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1977429404:
                if (str.equals("android.permission.READ_CONTACTS")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 2133799037:
                if (str.equals("com.android.voicemail.permission.ADD_VOICEMAIL")) {
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
            case UL.id._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreImpl_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID /*{ENCODED_INT: 12}*/:
            case UL.id._UL__ULSEP_com_oculus_util_device_DeviceUtils_ULSEP_BINDING_ID /*{ENCODED_INT: 13}*/:
            case UL.id._UL__ULSEP_android_os_PowerManager_ULSEP_BINDING_ID /*{ENCODED_INT: 14}*/:
            case UL.id._UL__ULSEP_com_oculus_multiuser_UserClassifier_ULSEP_BINDING_ID /*{ENCODED_INT: 15}*/:
                return "android.permission-group.PHONE";
            case UL.id._UL__ULSEP_com_oculus_auth_storage_AuthDatastore_ULSEP_BINDING_ID /*{ENCODED_INT: 16}*/:
                return "android.permission-group.SENSORS";
            case UL.id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_AppNameInUserAgent_ULSEP_BINDING_ID /*{ENCODED_INT: 17}*/:
            case UL.id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_UserAgentAppVersionMap_ULSEP_BINDING_ID /*{ENCODED_INT: 18}*/:
            case UL.id._UL__ULSEP_com_oculus_updater_core_monitors_SystemUpdatePolicyMonitor_ULSEP_BINDING_ID /*{ENCODED_INT: 19}*/:
            case UL.id._UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_BINDING_ID /*{ENCODED_INT: 20}*/:
                return "android.permission-group.SMS";
            case UL.id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphEndpoint_ULSEP_BINDING_ID /*{ENCODED_INT: 21}*/:
            case UL.id._UL__ULSEP_java_lang_Boolean_ULSEP_com_oculus_http_core_annotations_ReportGraphBackendException_ULSEP_BINDING_ID /*{ENCODED_INT: 22}*/:
                return "android.permission-group.STORAGE";
            default:
                return null;
        }
    }

    private static boolean isPermissionGranted(Context context, String str) {
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
                BLog.e(TAG, e, "Caught JSONException");
            }
            i++;
        }
    }
}
