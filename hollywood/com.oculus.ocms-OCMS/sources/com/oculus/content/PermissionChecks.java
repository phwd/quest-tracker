package com.oculus.content;

import android.content.Context;
import android.os.Binder;
import com.oculus.common.build.BuildConstants;
import com.oculus.util.vr.VRUtils;

public class PermissionChecks {
    private static final String ACCESS_NOT_ALLOWED_MESSAGE = "Component access not allowed.";
    private static final String PERMISSION_SYSTEM_PLATFORM_SDK = "com.oculus.permission.SYSTEM_PLATFORM_SDK";
    private static final String[] PERMISSION_SYSTEM_PLATFORM_SDK_PACKAGE_WHITELIST = {"com.oculus.updater", BuildConstants.UID_NAME_SYSTEM_APPS};

    public static boolean callingAppHasSameSignature(Context context) {
        int callingUid = Binder.getCallingUid();
        int i = context.getApplicationInfo().uid;
        return callingUid == i || context.getPackageManager().checkSignatures(i, callingUid) == 0;
    }

    public static final boolean callingAppHasSystemPlatformSDKPermission(Context context) {
        String nameForUid = context.getPackageManager().getNameForUid(Binder.getCallingUid());
        if (!(new VRUtils(context).isStandaloneDevice() && context.checkCallingPermission(PERMISSION_SYSTEM_PLATFORM_SDK) == 0)) {
            return false;
        }
        for (String str : PERMISSION_SYSTEM_PLATFORM_SDK_PACKAGE_WHITELIST) {
            if (str.equals(nameForUid)) {
                return true;
            }
        }
        return false;
    }

    public static final void throwAccessException() {
        throw new SecurityException(ACCESS_NOT_ALLOWED_MESSAGE);
    }
}
