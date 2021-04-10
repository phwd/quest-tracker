package oculus.internal;

import android.content.pm.PermissionInfo;
import android.util.ArrayMap;

public class PermissionGroupAdapter implements PermissionGroupAdapterInterface {
    private static final ArrayMap<String, String> PLATFORM_PERMISSIONS = new ArrayMap<>();

    static {
        PLATFORM_PERMISSIONS.put("android.permission.READ_CONTACTS", "android.permission-group.CONTACTS");
        PLATFORM_PERMISSIONS.put("android.permission.WRITE_CONTACTS", "android.permission-group.CONTACTS");
        PLATFORM_PERMISSIONS.put("android.permission.GET_ACCOUNTS", "android.permission-group.CONTACTS");
        PLATFORM_PERMISSIONS.put("android.permission.READ_CALENDAR", "android.permission-group.CALENDAR");
        PLATFORM_PERMISSIONS.put("android.permission.WRITE_CALENDAR", "android.permission-group.CALENDAR");
        PLATFORM_PERMISSIONS.put("android.permission.SEND_SMS", "android.permission-group.SMS");
        PLATFORM_PERMISSIONS.put("android.permission.RECEIVE_SMS", "android.permission-group.SMS");
        PLATFORM_PERMISSIONS.put("android.permission.READ_SMS", "android.permission-group.SMS");
        PLATFORM_PERMISSIONS.put("android.permission.RECEIVE_MMS", "android.permission-group.SMS");
        PLATFORM_PERMISSIONS.put("android.permission.RECEIVE_WAP_PUSH", "android.permission-group.SMS");
        PLATFORM_PERMISSIONS.put("android.permission.READ_CELL_BROADCASTS", "android.permission-group.SMS");
        PLATFORM_PERMISSIONS.put("android.permission.READ_EXTERNAL_STORAGE", "android.permission-group.STORAGE");
        PLATFORM_PERMISSIONS.put("android.permission.WRITE_EXTERNAL_STORAGE", "android.permission-group.STORAGE");
        PLATFORM_PERMISSIONS.put("android.permission.ACCESS_MEDIA_LOCATION", "android.permission-group.STORAGE");
        PLATFORM_PERMISSIONS.put("android.permission.ACCESS_FINE_LOCATION", "android.permission-group.LOCATION");
        PLATFORM_PERMISSIONS.put("android.permission.ACCESS_COARSE_LOCATION", "android.permission-group.LOCATION");
        PLATFORM_PERMISSIONS.put("android.permission.ACCESS_BACKGROUND_LOCATION", "android.permission-group.LOCATION");
        PLATFORM_PERMISSIONS.put("android.permission.READ_CALL_LOG", "android.permission-group.CALL_LOG");
        PLATFORM_PERMISSIONS.put("android.permission.WRITE_CALL_LOG", "android.permission-group.CALL_LOG");
        PLATFORM_PERMISSIONS.put("android.permission.PROCESS_OUTGOING_CALLS", "android.permission-group.CALL_LOG");
        PLATFORM_PERMISSIONS.put("android.permission.READ_PHONE_STATE", "android.permission-group.PHONE");
        PLATFORM_PERMISSIONS.put("android.permission.READ_PHONE_NUMBERS", "android.permission-group.PHONE");
        PLATFORM_PERMISSIONS.put("android.permission.CALL_PHONE", "android.permission-group.PHONE");
        PLATFORM_PERMISSIONS.put("com.android.voicemail.permission.ADD_VOICEMAIL", "android.permission-group.PHONE");
        PLATFORM_PERMISSIONS.put("android.permission.USE_SIP", "android.permission-group.PHONE");
        PLATFORM_PERMISSIONS.put("android.permission.ANSWER_PHONE_CALLS", "android.permission-group.PHONE");
        PLATFORM_PERMISSIONS.put("android.permission.RECORD_AUDIO", "android.permission-group.MICROPHONE");
        PLATFORM_PERMISSIONS.put("android.permission.ACTIVITY_RECOGNITION", "android.permission-group.ACTIVITY_RECOGNITION");
        PLATFORM_PERMISSIONS.put("android.permission.CAMERA", "android.permission-group.CAMERA");
        PLATFORM_PERMISSIONS.put("android.permission.BODY_SENSORS", "android.permission-group.SENSORS");
    }

    @Override // oculus.internal.PermissionGroupAdapterInterface
    public String getGroupOfPermission(PermissionInfo permission) {
        String groupName = PLATFORM_PERMISSIONS.get(permission.name);
        if (groupName == null) {
            return permission.group;
        }
        return groupName;
    }
}
