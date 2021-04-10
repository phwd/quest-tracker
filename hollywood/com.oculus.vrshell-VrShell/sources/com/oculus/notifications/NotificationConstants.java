package com.oculus.notifications;

import android.app.Notification;
import com.oculus.systemdialog.DialogInformationBox;

public class NotificationConstants {
    public static final String EXTRA_FBID = "oculus_notification_fbid";
    public static final String EXTRA_NDID = "oculus_notification_ndid";
    public static final String EXTRA_SERVER_CATEGORY = "oculus_notification_category";
    public static final String EXTRA_TYPE = "oculus_notification_type";
    public static final String KEY_ACTION_ON_CLICK = "action_on_click";
    public static final String KEY_ACTION_TEXT = "action_text";
    public static final String KEY_ACTION_URI = "action_uri";
    public static final String KEY_AUI_NOTIF_DURATION = "aui_notif_duration";
    public static final String KEY_AUI_PERSIST = "vrshell_aui_persist";
    public static final String KEY_CUSTOM_SOUND_RESOURCE_ID = "custom_sound_resource_id";
    public static final String KEY_ID = "id";
    public static final String KEY_LARGE_IMAGE_TYPE = "large_image_type";
    public static final String KEY_OCULUS_BUTTON_OVERRIDE_URI = "oculus_button_override_uri";
    public static final String KEY_OCULUS_CATEGORY = "oculus_category";
    public static final String KEY_PREVENT_SOUND = "prevent_sound";
    public static final String KEY_PRIORITY = "priority";
    public static final String KEY_TEXT = "text";
    public static final String KEY_TITLE = "title";
    public static final int NOTIFICATION_ID_FOREGROUND_SERVICE = 3;
    public static final int NOTIFICATION_ID_GUARDIAN = 2;
    public static final int NOTIFICATION_ID_HANDS_GUARDIAN = 6;
    public static final int NOTIFICATION_ID_INCREMENT = -1;
    public static final int NOTIFICATION_ID_INSUFFICIENT_HANDS_LIGHTING = 5;
    public static final int NOTIFICATION_ID_LAST = 8;
    public static final int NOTIFICATION_ID_PASSTHROUGH = 4;
    public static final int NOTIFICATION_ID_STORAGE = 1;
    public static final String SOCIAL_CATEGORY = "social";

    public enum LargeImageType {
        INVALID(null),
        ICON(DialogInformationBox.DIALOG_INFORMATION_BOX_ICON_KEY),
        PROFILE("profile"),
        DETAIL("detail");
        
        final String mValue;

        private LargeImageType(String str) {
            this.mValue = str;
        }

        public String toValue() {
            return this.mValue;
        }

        public static LargeImageType fromNotification(Notification notification) {
            String string = notification.extras.getString(NotificationConstants.KEY_LARGE_IMAGE_TYPE, "");
            if (string == null || notification.getSmallIcon() == null) {
                return INVALID;
            }
            LargeImageType[] values = values();
            for (LargeImageType largeImageType : values) {
                if (string.equalsIgnoreCase(largeImageType.mValue)) {
                    return largeImageType;
                }
            }
            return INVALID;
        }
    }
}
