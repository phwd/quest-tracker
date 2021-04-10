package com.oculus.notifications;

import android.app.Notification;
import android.service.notification.StatusBarNotification;
import com.oculus.horizon.notifications.core.NotificationBuilder;

public class NotificationConstants {
    public static final String EXTRA_FBID = "oculus_notification_fbid";
    public static final String EXTRA_NDID = "oculus_notification_ndid";
    public static final String EXTRA_TYPE = "oculus_notification_type";
    public static final String KEY_ACTION_TEXT = "action_text";
    public static final String KEY_AUI_NOTIF_DURATION = "aui_notif_duration";
    public static final String KEY_AUI_PERSIST = "vrshell_aui_persist";
    public static final String KEY_CUSTOM_SOUND_RESOURCE_ID = "custom_sound_resource_id";
    public static final String KEY_ID = "id";
    public static final String KEY_LARGE_IMAGE_TYPE = "large_image_type";
    public static final String KEY_OCULUS_CATEGORY = "oculus_category";
    public static final String KEY_PREVENT_SOUND = "prevent_sound";
    public static final String KEY_PRIORITY = "priority";
    public static final String KEY_TEXT = "text";
    public static final String KEY_TITLE = "title";
    public static final int NOTIFICATION_ID_INCREMENT = -1;
    public static final int NOTIFICATION_ID_LAST = 1;

    public enum LargeImageType {
        INVALID(null),
        ICON("icon"),
        PROFILE("profile"),
        DETAIL(NotificationBuilder.LARGE_IMAGE_TYPE_DETAIL);
        
        public final String mValue;

        public static LargeImageType fromNotification(Notification notification) {
            String string = notification.extras.getString("large_image_type", null);
            if (!(string == null || notification.getSmallIcon() == null)) {
                LargeImageType[] values = values();
                for (LargeImageType largeImageType : values) {
                    if (string.equalsIgnoreCase(largeImageType.mValue)) {
                        return largeImageType;
                    }
                }
            }
            return INVALID;
        }

        public String toValue() {
            return this.mValue;
        }

        /* access modifiers changed from: public */
        LargeImageType(String str) {
            this.mValue = str;
        }
    }

    public static boolean notificationHasClickAction(StatusBarNotification statusBarNotification) {
        Notification notification = statusBarNotification.getNotification();
        if (notification == null) {
            return false;
        }
        Notification.Action[] actionArr = notification.actions;
        if ((actionArr == null || actionArr.length <= 0) && notification.contentIntent == null) {
            return false;
        }
        return true;
    }
}
