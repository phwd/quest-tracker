package com.oculus.horizon.remotelaunchlogger;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID"})
public class RemoteLaunchLogger {
    public static final String ERROR_REASON_DUPLICATE_LAUNCH = "DUPLICATE_LAUNCH";
    public static final String ERROR_REASON_EMPTY_SYSTEM_ROUTE = "EMPTY_SYSTEM_ROUTE";
    public static final String ERROR_REASON_GRAPHQL_ERROR = "GRAPHQL_ERROR";
    public static final String ERROR_REASON_LAUNCH_FAILED = "LAUNCH_FAILED";
    public static final String ERROR_REASON_POSSIBLY_EXPIRED_LAUNCH = "POSSIBLY_EXPIRED_LAUNCH";
    public static final String ERROR_REASON_TWILIGHT_LAUNCH_INTENT_ERROR = "TWILIGHT_LAUNCH_INTENT_ERROR";
    public static final String EVENT_LAUNCH_FAILURE = "oculus_metaport_headset_launch_failure";
    public static final String EVENT_LAUNCH_SUCCESS = "oculus_metaport_headset_launch_success";
    public static final String KEY_APP_ID_LAUNCHED = "app_id_launched";
    public static final String KEY_DEEPLINK_MESSAGE = "deeplink_message";
    public static final String KEY_DEVICE_SERIAL = "device_serial";
    public static final String KEY_ERROR_MESSAGE = "error_message";
    public static final String KEY_ERROR_REASON = "error_reason";
    public static final String KEY_HAS_DEEPLINK_MESSAGE = "has_deeplink_message";
    public static final String KEY_PARTY_ID = "party_id";
    public static final String KEY_PROCESSED_QUEUED_REMOTE_LAUNCH_ID = "processed_queued_remote_launch_id";
    public static final String KEY_QUEUED_REMOTE_LAUNCH_ID_FROM_NOTIF = "queued_remote_launch_id_from_notif";
    public static final String KEY_SOURCE = "source";
    public AnonymousClass0QC _UL_mInjectionContext;

    public static class Builder {
        @Nullable
        public String appId;
        @Nullable
        public String deeplinkMessage;
        @Nullable
        public String deviceSerial;
        @Nullable
        public String errorMessage;
        @Nullable
        public String errorReason;
        @Nullable
        public final String partyId;
        @Nullable
        public String processedQueuedRemoteLaunchId;
        @Nullable
        public String queuedRemoteLaunchIdFromNotif;
        @Nullable
        public String source;
    }

    public static Event A00(RemoteLaunchLogger remoteLaunchLogger, String str, Builder builder) {
        Event A22 = ((EventManager) AnonymousClass0J2.A03(0, 242, remoteLaunchLogger._UL_mInjectionContext)).A22(str);
        String str2 = builder.source;
        if (str2 == null) {
            str2 = "";
        }
        A22.A15("source", str2);
        String str3 = builder.deviceSerial;
        if (str3 == null) {
            str3 = "";
        }
        A22.A15(KEY_DEVICE_SERIAL, str3);
        String str4 = builder.appId;
        if (str4 == null) {
            str4 = "";
        }
        A22.A15(KEY_APP_ID_LAUNCHED, str4);
        String str5 = builder.deeplinkMessage;
        if (str5 == null) {
            str5 = "";
        }
        A22.A15("deeplink_message", str5);
        String str6 = builder.queuedRemoteLaunchIdFromNotif;
        if (str6 == null) {
            str6 = "";
        }
        A22.A15(KEY_QUEUED_REMOTE_LAUNCH_ID_FROM_NOTIF, str6);
        String str7 = builder.processedQueuedRemoteLaunchId;
        if (str7 == null) {
            str7 = "";
        }
        A22.A15(KEY_PROCESSED_QUEUED_REMOTE_LAUNCH_ID, str7);
        String str8 = builder.partyId;
        if (str8 == null) {
            str8 = "";
        }
        A22.A15("party_id", str8);
        return A22;
    }

    public final void A01(Builder builder) {
        Event A00 = A00(this, EVENT_LAUNCH_FAILURE, builder);
        String str = builder.errorReason;
        if (str == null) {
            str = "";
        }
        A00.A15("error_reason", str);
        String str2 = builder.errorMessage;
        if (str2 == null) {
            str2 = "";
        }
        A00.A15("error_message", str2);
        A00.A5L();
    }

    public final void A02(String str, String str2, boolean z, String str3) {
        Event A22 = ((EventManager) AnonymousClass0J2.A03(0, 242, this._UL_mInjectionContext)).A22(EVENT_LAUNCH_FAILURE);
        A22.A15("source", str);
        A22.A15(KEY_APP_ID_LAUNCHED, str2);
        A22.A16(KEY_HAS_DEEPLINK_MESSAGE, z);
        A22.A15("error_message", str3);
        A22.A5L();
    }

    @Inject
    public RemoteLaunchLogger(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
    }
}
