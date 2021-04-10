package com.oculus.userserver.managerservice;

import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;

public class OumsTelemetry {
    public static final String EVENT_FRAMEWORK_FAILURE = "oculus_mobile_userserver_framework_failure";
    public static final String EVENT_FRAMEWORK_SUCCESS = "oculus_mobile_userserver_framework_success";
    public static final String EVENT_PREFIX = "oculus_mobile_userserver_";
    public static final String EVENT_SYNC = "oculus_mobile_userserver_sync";
    public static final int NULL_ID = -1000;
    public static final String TAG = "OumsTelemetry";

    public interface Extras {
        public static final String DID_SYNC_WITH_BACKEND = "did_sync_with_backend";
        public static final String EVENT_TYPE = "event_type";
        public static final String FULL_USER_REMOVALS_FAILED = "full_user_removals_failed";
        public static final String FULL_USER_REMOVALS_SUCCEEDED = "full_user_removals_succeeded";
        public static final String OCULUS_USER_REMOVALS = "oculus_user_removals";
        public static final String OS_USER_REMOVALS_FAILED = "os_user_removals_failed";
        public static final String OS_USER_REMOVALS_SUCCEEDED = "os_user_removals_succeeded";
        public static final String TARGET_USER = "target_user";
    }

    public enum FrameworkCallType {
        CREATE_USER,
        SWITCH_NEW_USER,
        REMOVE_TARGET,
        REMOVE_SELF
    }

    public static void A00(String str, FrameworkCallType frameworkCallType, int i) {
        UnifiedTelemetryLogger.getInstance().reportEvent(new AnalyticsEvent(str).setExtra(Extras.EVENT_TYPE, frameworkCallType.name()).setExtra(Extras.TARGET_USER, Integer.valueOf(i)), false);
    }

    public static void A01(boolean z, int i, int i2, int i3, int i4, int i5) {
        UnifiedTelemetryLogger.getInstance().reportEvent(new AnalyticsEvent(EVENT_SYNC).setExtra(Extras.DID_SYNC_WITH_BACKEND, Boolean.valueOf(z)).setExtra(Extras.FULL_USER_REMOVALS_SUCCEEDED, Integer.valueOf(i)).setExtra(Extras.FULL_USER_REMOVALS_FAILED, Integer.valueOf(i2)).setExtra(Extras.OS_USER_REMOVALS_SUCCEEDED, Integer.valueOf(i3)).setExtra(Extras.OS_USER_REMOVALS_FAILED, Integer.valueOf(i4)).setExtra(Extras.OCULUS_USER_REMOVALS, Integer.valueOf(i5)), false);
    }
}
