package com.oculus.panelapp.anytimeui.v2.tablet.notifications;

import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.AbstractVRNotification;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.NativeNotification;
import org.json.JSONException;
import org.json.JSONObject;

public class NotificationsHelper {
    public static final String NOTIF_FEED_CLICK = "oculus_hmd_notification_feed_item_clicked";
    public static final String NOTIF_FEED_DISMISS = "oculus_hmd_notification_feed_item_dismissed";
    public static final String NOTIF_FEED_IMPRESSION = "oculus_hmd_notification_feed_item_impression";
    private static final String TAG = "NotificationsHelper";

    public static JSONObject formLogEvent(IBaseVRNotification iBaseVRNotification) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("oculus_notification_fbid", iBaseVRNotification.getFBID());
            jSONObject.put("oculus_notification_ndid", iBaseVRNotification.getNDID());
            jSONObject.put("oculus_notification_type", iBaseVRNotification.getNotificationType());
        } catch (JSONException e) {
            Log.e(TAG, "Error putting notification ids into logs", e);
        }
        return jSONObject;
    }

    public static boolean isMessagingVRNotification(IBaseVRNotification iBaseVRNotification) {
        return (iBaseVRNotification instanceof NativeNotification) && ((NativeNotification) iBaseVRNotification).isAnyMessagingNotification();
    }

    public static boolean isAnyMessengerNotification(StatusBarNotification statusBarNotification) {
        String notificationType = getNotificationType(statusBarNotification);
        if (TextUtils.isEmpty(notificationType)) {
            return false;
        }
        if (NativeNotification.MESSENGER_NOTIFICATION_TYPE.equals(notificationType) || NativeNotification.OCULUS_CHATS_NOTIFICATION_TYPE.equals(notificationType)) {
            return true;
        }
        return false;
    }

    public static boolean isFBMessengerNotification(StatusBarNotification statusBarNotification) {
        String notificationType = getNotificationType(statusBarNotification);
        if (!TextUtils.isEmpty(notificationType)) {
            return NativeNotification.MESSENGER_NOTIFICATION_TYPE.equals(notificationType);
        }
        return false;
    }

    public static String getNotificationType(StatusBarNotification statusBarNotification) {
        String string = statusBarNotification.getNotification().extras.getString("oculus_notification_type", "");
        String string2 = statusBarNotification.getNotification().extras.getString(AbstractVRNotification.NOTIF_XTYPE_FIELD, "");
        return TextUtils.isEmpty(string2) ? string : string2;
    }

    public enum NotificationLogEvents {
        NOTIF_FEED_IMPRESSION(NotificationsHelper.NOTIF_FEED_IMPRESSION),
        NOTIF_FEED_CLICK(NotificationsHelper.NOTIF_FEED_CLICK),
        NOTIF_FEED_DISMISS(NotificationsHelper.NOTIF_FEED_DISMISS);
        
        private String mEventName;

        private NotificationLogEvents(String str) {
            this.mEventName = str;
        }

        public String getLogEventName() {
            return this.mEventName;
        }

        public static NotificationLogEvents fromSeenState(AbstractVRNotification.NotificationSeenState notificationSeenState) {
            int i = AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$notifications$core$AbstractVRNotification$NotificationSeenState[notificationSeenState.ordinal()];
            if (i == 1) {
                return NOTIF_FEED_CLICK;
            }
            if (i == 2) {
                return NOTIF_FEED_IMPRESSION;
            }
            if (i != 3) {
                return NOTIF_FEED_IMPRESSION;
            }
            return NOTIF_FEED_DISMISS;
        }
    }

    /* renamed from: com.oculus.panelapp.anytimeui.v2.tablet.notifications.NotificationsHelper$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$notifications$core$AbstractVRNotification$NotificationSeenState = new int[AbstractVRNotification.NotificationSeenState.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.AbstractVRNotification$NotificationSeenState[] r0 = com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.AbstractVRNotification.NotificationSeenState.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.anytimeui.v2.tablet.notifications.NotificationsHelper.AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$notifications$core$AbstractVRNotification$NotificationSeenState = r0
                int[] r0 = com.oculus.panelapp.anytimeui.v2.tablet.notifications.NotificationsHelper.AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$notifications$core$AbstractVRNotification$NotificationSeenState     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.AbstractVRNotification$NotificationSeenState r1 = com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.AbstractVRNotification.NotificationSeenState.CLICKED     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.panelapp.anytimeui.v2.tablet.notifications.NotificationsHelper.AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$notifications$core$AbstractVRNotification$NotificationSeenState     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.AbstractVRNotification$NotificationSeenState r1 = com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.AbstractVRNotification.NotificationSeenState.SEEN_AND_READ     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.panelapp.anytimeui.v2.tablet.notifications.NotificationsHelper.AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$notifications$core$AbstractVRNotification$NotificationSeenState     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.AbstractVRNotification$NotificationSeenState r1 = com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.AbstractVRNotification.NotificationSeenState.DISMISSED     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.v2.tablet.notifications.NotificationsHelper.AnonymousClass1.<clinit>():void");
        }
    }
}
