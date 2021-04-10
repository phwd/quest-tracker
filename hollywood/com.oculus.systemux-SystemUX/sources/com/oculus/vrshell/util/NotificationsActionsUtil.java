package com.oculus.vrshell.util;

import android.app.Notification;
import android.app.PendingIntent;
import android.graphics.drawable.Icon;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.facebook.secure.logger.IntentLogger;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.anytimeui.v2.tablet.profile.ProfileDialogs;
import com.oculus.panelapp.social.SocialBundleConstants;
import com.oculus.socialdialogs.SocialPartyDialogs;

public class NotificationsActionsUtil {
    public static final String[] ACCEPT_ACTION_TITLES = {"accept", "ok", IntentLogger.Status.ALLOW};
    public static final String[] ACCEPT_PARTY_INVITE_ACTION_TITLES = {SocialBundleConstants.FB_UPSELL_ACCEPT_PARTY_INVITE};
    public static final String[] CANCEL_DOWNLOAD_ACTION_TITLES = {"cancel download"};
    public static final String[] DENY_ACTION_TITLES = {IntentLogger.Status.DENY, "ignore", "dismiss"};
    private static final String EXTRA_ANDROID_BIGTEXT = "android.bigText";
    private static final String EXTRA_ANDROID_TEXT = "android.text";
    private static final String EXTRA_ANDROID_TEXTLINES = "android.textLines";
    private static final String EXTRA_ANDROID_TITLE = "android.title";
    private static final String EXTRA_DOWNLOAD_IN_PROGRESS = "download_in_progress";
    private static final String EXTRA_PROGRESS_BAR_RATIO = "progress_bar_ratio";
    private static final String EXTRA_USE_TITLE_AS_DESCRIPTION = "title_as_description_in_toast";
    public static final String[] INVITE_CALL_ACTION_TITLES = {SocialPartyDialogs.INVITE_ACTION, NotificationCompat.CATEGORY_CALL};
    public static final String[] LAUNCH_APP_ACTION_TITLES = {"launch"};
    public static final String[] LAUNCH_CAMERA_ROLL_ACTION_TITLES = {"accept_cameraroll"};
    public static final String[] RETRY_DOWNLOAD_ACTION_TITLES = {ProfileDialogs.RETRY_ACTION};
    public static final String[] SEE_MORE_ACTION_TITLES = {"see more"};
    public static final String TAG = LoggingUtil.tag(NotificationsActionsUtil.class);

    public static boolean isAcceptActionItem(Notification.Action action) {
        return isActionTitle(ACCEPT_ACTION_TITLES, action);
    }

    public static boolean isDenyActionItem(Notification.Action action) {
        return isActionTitle(DENY_ACTION_TITLES, action);
    }

    public static boolean isInviteActionItem(Notification.Action action) {
        return isActionTitle(INVITE_CALL_ACTION_TITLES, action);
    }

    public static boolean isSeeMoreActionItem(Notification.Action action) {
        return isActionTitle(SEE_MORE_ACTION_TITLES, action);
    }

    public static boolean isCancelDownloadActionItem(Notification.Action action) {
        return isActionTitle(CANCEL_DOWNLOAD_ACTION_TITLES, action);
    }

    public static boolean isLaunchContentActionItem(Notification.Action action) {
        return isActionTitle(LAUNCH_APP_ACTION_TITLES, action);
    }

    public static boolean isRetryDownloadActionItem(Notification.Action action) {
        return isActionTitle(RETRY_DOWNLOAD_ACTION_TITLES, action);
    }

    public static boolean shouldHideDismiss(Notification notification) {
        return notification.extras.getBoolean("hide_dismiss", false);
    }

    public static boolean isAcceptPartyInviteActionItem(Notification.Action action) {
        return isActionTitle(ACCEPT_PARTY_INVITE_ACTION_TITLES, action);
    }

    public static boolean isLaunchCamerarollActionItem(Notification.Action action) {
        return isActionTitle(LAUNCH_CAMERA_ROLL_ACTION_TITLES, action);
    }

    private static boolean isActionTitle(String[] strArr, Notification.Action action) {
        for (String str : strArr) {
            if (action.title != null && str.equalsIgnoreCase(action.title.toString())) {
                return true;
            }
        }
        return false;
    }

    public static boolean dismissAfterClick(Notification.Action action) {
        return !isRetryDownloadActionItem(action) && !isCancelDownloadActionItem(action) && !isSeeMoreActionItem(action) && !isLaunchCamerarollActionItem(action);
    }

    public static void invokeNotificationAction(PendingIntent pendingIntent) {
        if (pendingIntent != null) {
            try {
                Log.i(TAG, "Launching CTA PendingIntent");
                pendingIntent.send();
            } catch (PendingIntent.CanceledException e) {
                Log.e(TAG, "Could not perform CTA:  ", e);
            }
        }
    }

    public static Icon getNotificationIcon(Notification notification) {
        Icon largeIcon = notification.getLargeIcon();
        return largeIcon == null ? notification.getSmallIcon() : largeIcon;
    }

    public static String getNotificationTitle(Notification notification) {
        return notification.extras.getString("android.title", "");
    }

    public static String getNotificationDescription(Notification notification) {
        return getNotificationDescription(notification, false);
    }

    public static String getNotificationDescription(Notification notification, boolean z) {
        String str;
        CharSequence[] charSequenceArray;
        if (!z || !notification.extras.getBoolean(EXTRA_USE_TITLE_AS_DESCRIPTION, false)) {
            str = notification.extras.getString("android.text", "");
        } else {
            str = notification.extras.getString("android.title", "");
        }
        if (str.isEmpty()) {
            str = notification.extras.getString("android.bigText", "");
        }
        if (!str.isEmpty() || (charSequenceArray = notification.extras.getCharSequenceArray("android.textLines")) == null) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < charSequenceArray.length; i++) {
            sb.append(charSequenceArray[i]);
            if (i < charSequenceArray.length - 1) {
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public static boolean getNotificationIsDownloadInProgress(Notification notification) {
        return notification.extras.getBoolean(EXTRA_DOWNLOAD_IN_PROGRESS, false);
    }

    public static float getNotificationProgress(Notification notification) {
        return notification.extras.getFloat(EXTRA_PROGRESS_BAR_RATIO, 0.0f);
    }
}
