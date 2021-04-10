package com.oculus.headlesshorizon.notifications.receivers;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;

@Dependencies({"_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
public class NotificationsLogger {
    public static final String EVENT_TYPE = "event_type";
    public static final String NOTIFICATION_APP_GROUP_ID = "notification_app_group_id";
    public static final String NOTIFICATION_ENABLED = "notification_enabled";
    public static final String NOTIFICATION_ID = "notification_id";
    public static final String NOTIFICATION_NDID = "notification_ndid";
    public static final String NOTIFICATION_NOTIF_REQUEST_ID = "notification_request_id";
    public static final String NOTIFICATION_RECEIVED = "oculus_notification_received";
    public static final String NOTIFICATION_TYPE = "notification_type";
    public static final String NOTIFICATION_UUID = "notification_uuid";
    public static final String REFERRER = "referrer";
    public static final String SDK_NOTIFICATION_RECEIVED = "oculus_notification_received";
    public AnonymousClass0QC _UL_mInjectionContext;

    public static class REFERRERS {
        public static final String NOTIF_EVENT_START = "notif_event_start";
    }

    @Inject
    public NotificationsLogger(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(2, r3);
    }
}
