package com.oculus.logging;

import X.BQ;
import com.google.common.collect.ImmutableSet;

public class ExtraKeys {
    public static final String BUILD_MANUFACTURER = "build_manufacturer";
    public static final String BUILD_PRODUCT = "build_product";
    public static final String BUILD_RELEASE_VERSION = "build_release_version";
    public static final String BUILD_TYPE = "build_type";
    public static final String BUILD_VERSION = "build_version";
    public static final String CORE_COUNT = "core_count";
    public static final String CORRUPTED = "corrupted";
    public static final String DEVICE_SERIAL = "device_serial";
    public static final String DEVICE_TYPE = "device_type";
    public static final String ENDPOINT_HOST = "endpoint_host";
    public static final String HW_DEVICE_ID = "hw_device_id";
    public static final String INCOMPLETE = "incomplete";
    public static final String MANAGED_MODE = "managed_mode";
    public static final String OS_TYPE = "os_type";
    public static final String OS_VERSION = "os_ver";
    public static final ImmutableSet RESTRICTED_KEY_MAP;
    public static final String SDK_APP_ID = "platform_app_id";
    public static final String SDK_APP_PACKAGE = "sdk_app_package";
    public static final String SDK_APP_PACKAGE_VERSION_CODE = "sdk_app_package_version_code";
    public static final String SDK_APP_PACKAGE_VERSION_NAME = "sdk_app_package_version_name";
    public static final String SESSIONS = "__sessions";
    public static final String TRUSTED_USER = "trusted_user";
    public static final String USER_INTERNAL_ID = "id";
    public static final String UTL_LOGGING_PACKAGE = "utl_logging_package";
    public static final String UTL_OVERRIDE_EVENT_TIME = "utl_override_event_time";
    public static final String UTL_PRIMARY_USER = "utl_primary_user";
    public static final String XRS_SESSION_ID = "xrs_session_id";

    static {
        BQ bq = new BQ();
        bq.A00(BUILD_MANUFACTURER);
        bq.A00(BUILD_PRODUCT);
        bq.A00("build_type");
        bq.A00(BUILD_VERSION);
        bq.A00(BUILD_RELEASE_VERSION);
        bq.A00("id");
        bq.A00(TRUSTED_USER);
        bq.A00(ENDPOINT_HOST);
        bq.A00(MANAGED_MODE);
        bq.A00(INCOMPLETE);
        bq.A00(CORRUPTED);
        bq.A00(XRS_SESSION_ID);
        bq.A00(SDK_APP_PACKAGE_VERSION_NAME);
        bq.A00(SDK_APP_PACKAGE_VERSION_CODE);
        bq.A00(UTL_PRIMARY_USER);
        bq.A00(HW_DEVICE_ID);
        bq.A00(DEVICE_SERIAL);
        bq.A00(SESSIONS);
        RESTRICTED_KEY_MAP = bq.build();
    }
}
