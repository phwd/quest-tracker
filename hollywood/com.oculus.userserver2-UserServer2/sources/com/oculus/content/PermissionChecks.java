package com.oculus.content;

import com.oculus.common.build.BuildConstants;

public class PermissionChecks {
    public static final String ACCESS_NOT_ALLOWED_MESSAGE = "Component access not allowed.";
    public static final String PERMISSION_SYSTEM_PLATFORM_SDK = "com.oculus.permission.SYSTEM_PLATFORM_SDK";
    public static final String[] PERMISSION_SYSTEM_PLATFORM_SDK_PACKAGE_WHITELIST = {"com.oculus.updater", BuildConstants.UID_NAME_SYSTEM_APPS};
}
