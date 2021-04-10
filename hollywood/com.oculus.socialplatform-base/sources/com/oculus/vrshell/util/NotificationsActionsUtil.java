package com.oculus.vrshell.util;

import android.app.Notification;
import android.app.PendingIntent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizon.notifications.core.NotificationBuilder;
import com.oculus.horizon.notifications.core.NotificationsIntentCreationUtils;
import com.oculus.socialdialogs.SocialPartyDialogs;
import com.oculus.vrshell.panels.AndroidPanelLayer;

public class NotificationsActionsUtil {
    public static final String[] ACCEPT_ACTION_TITLES = {"accept", "ok", "allow"};
    public static final String[] ACCEPT_PARTY_INVITE_ACTION_TITLES = {"accept_party_invite"};
    public static final String[] CANCEL_DOWNLOAD_ACTION_TITLES = {"cancel download"};
    public static final String[] DENY_ACTION_TITLES = {"deny", "ignore", "dismiss"};
    public static final String EXTRA_ANDROID_BIGTEXT = "android.bigText";
    public static final String EXTRA_ANDROID_TEXT = "android.text";
    public static final String EXTRA_ANDROID_TEXTLINES = "android.textLines";
    public static final String EXTRA_ANDROID_TITLE = "android.title";
    public static final String EXTRA_DOWNLOAD_IN_PROGRESS = "download_in_progress";
    public static final String EXTRA_PROGRESS_BAR_RATIO = "progress_bar_ratio";
    public static final String EXTRA_USE_TITLE_AS_DESCRIPTION = "title_as_description_in_toast";
    public static final String[] INVITE_CALL_ACTION_TITLES = {SocialPartyDialogs.INVITE_ACTION, "call"};
    public static final String[] LAUNCH_APP_ACTION_TITLES = {NotificationsIntentCreationUtils.LAUNCH_CTA};
    public static final String[] LAUNCH_CAMERA_ROLL_ACTION_TITLES = {NotificationBuilder.ACCEPT_CAMERAROLL};
    public static final String[] RETRY_DOWNLOAD_ACTION_TITLES = {"retry"};
    public static final String[] SEE_MORE_ACTION_TITLES = {"see more"};
    public static final String TAG = LoggingUtil.tag(NotificationsActionsUtil.class);

    public static boolean isActionTitle(String[] strArr, Notification.Action action) {
        for (String str : strArr) {
            CharSequence charSequence = action.title;
            if (charSequence != null && str.equalsIgnoreCase(charSequence.toString())) {
                return true;
            }
        }
        return false;
    }

    public static boolean dismissAfterClick(Notification.Action action) {
        if (isActionTitle(RETRY_DOWNLOAD_ACTION_TITLES, action) || isActionTitle(CANCEL_DOWNLOAD_ACTION_TITLES, action) || isActionTitle(SEE_MORE_ACTION_TITLES, action) || isActionTitle(LAUNCH_CAMERA_ROLL_ACTION_TITLES, action)) {
            return false;
        }
        return true;
    }

    public static boolean getNotificationIsDownloadInProgress(Notification notification) {
        return notification.extras.getBoolean(EXTRA_DOWNLOAD_IN_PROGRESS, false);
    }

    public static float getNotificationProgress(Notification notification) {
        return notification.extras.getFloat(EXTRA_PROGRESS_BAR_RATIO, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
    }

    public static String getNotificationTitle(Notification notification) {
        return notification.extras.getString(EXTRA_ANDROID_TITLE, "");
    }

    public static void invokeNotificationAction(PendingIntent pendingIntent) {
        if (pendingIntent != null) {
            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException e) {
                Log.e(TAG, "Could not perform CTA:  ", e);
            }
        }
    }

    public static boolean isAcceptActionItem(Notification.Action action) {
        return isActionTitle(ACCEPT_ACTION_TITLES, action);
    }

    public static boolean isAcceptPartyInviteActionItem(Notification.Action action) {
        return isActionTitle(ACCEPT_PARTY_INVITE_ACTION_TITLES, action);
    }

    public static boolean isCancelDownloadActionItem(Notification.Action action) {
        return isActionTitle(CANCEL_DOWNLOAD_ACTION_TITLES, action);
    }

    public static boolean isDenyActionItem(Notification.Action action) {
        return isActionTitle(DENY_ACTION_TITLES, action);
    }

    public static boolean isInviteActionItem(Notification.Action action) {
        return isActionTitle(INVITE_CALL_ACTION_TITLES, action);
    }

    public static boolean isLaunchCamerarollActionItem(Notification.Action action) {
        return isActionTitle(LAUNCH_CAMERA_ROLL_ACTION_TITLES, action);
    }

    public static boolean isLaunchContentActionItem(Notification.Action action) {
        return isActionTitle(LAUNCH_APP_ACTION_TITLES, action);
    }

    public static boolean isRetryDownloadActionItem(Notification.Action action) {
        return isActionTitle(RETRY_DOWNLOAD_ACTION_TITLES, action);
    }

    public static boolean isSeeMoreActionItem(Notification.Action action) {
        return isActionTitle(SEE_MORE_ACTION_TITLES, action);
    }

    public static boolean shouldHideDismiss(Notification notification) {
        return notification.extras.getBoolean("hide_dismiss", false);
    }

    public static Icon getNotificationIcon(Notification notification) {
        Icon largeIcon = notification.getLargeIcon();
        if (largeIcon == null) {
            return notification.getSmallIcon();
        }
        return largeIcon;
    }

    public static String getNotificationDescription(Notification notification) {
        return getNotificationDescription(notification, false);
    }

    public static String getNotificationDescription(Notification notification, boolean z) {
        Bundle bundle;
        String str;
        CharSequence[] charSequenceArray;
        int i = 0;
        if (!z || !notification.extras.getBoolean(EXTRA_USE_TITLE_AS_DESCRIPTION, false)) {
            bundle = notification.extras;
            str = EXTRA_ANDROID_TEXT;
        } else {
            bundle = notification.extras;
            str = EXTRA_ANDROID_TITLE;
        }
        String string = bundle.getString(str, "");
        if (string.isEmpty()) {
            string = notification.extras.getString(EXTRA_ANDROID_BIGTEXT, "");
        }
        if (!string.isEmpty() || (charSequenceArray = notification.extras.getCharSequenceArray(EXTRA_ANDROID_TEXTLINES)) == null) {
            return string;
        }
        StringBuilder sb = new StringBuilder();
        while (true) {
            int length = charSequenceArray.length;
            if (i >= length) {
                return sb.toString();
            }
            sb.append(charSequenceArray[i]);
            if (i < length - 1) {
                sb.append(System.lineSeparator());
            }
            i++;
        }
    }
}
