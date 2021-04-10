package com.oculus.vrshell;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.notifications.NotificationConstants;
import com.oculus.notifications.NotificationSender;
import com.oculus.notifications.NotificationsDisplayDuration;
import com.oculus.os.SettingsManager;

public final class GuardianController {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int GUARDIAN_DIALOG = 0;
    public static final String GUARDIAN_INTENT_ACTION_NAME = "com.oculus.vrshell.intent.action.GUARDIAN";
    private static final int GUARDIAN_NOTIF = 1;
    private static final String GUARDIAN_NOTIF_3DOF_ENABLED = "systemux://guardian/notif_3dof_enabled";
    private static final int GUARDIAN_NOTIF_CANCEL = 2;
    public static final String GUARDIAN_NOTIF_HAND_TRACKING = "systemux://guardian/notif_hand_tracking";
    private static final String GUARDIAN_NOTIF_ID_3DOF_ENABLED = "oculus_mobile_guardian_3dof_enabled";
    private static final String GUARDIAN_NOTIF_ID_HAND_TRACKING = "oculus_mobile_guardian_hand_tracking";
    private static final String GUARDIAN_NOTIF_ID_PT_ONDEMAND = "oculus_mobile_guardian_pt_ondemand";
    private static final String GUARDIAN_NOTIF_PT_ONDEMAND = "systemux://guardian/notif_ptondemand";
    private static final String GUARDIAN_NOTIF_TAG = "guardian";
    private static final String KEY_GUARDIAN_ACTION_TYPE = "guardian_action_type";
    private static final String TAG = LoggingUtil.tag(GuardianController.class);

    private static boolean isGuardianAction(Intent intent) {
        return "com.oculus.vrshell.intent.action.GUARDIAN".equals(intent.getAction());
    }

    public static boolean handleNotification(Context context, Intent intent) {
        if (!isGuardianAction(intent)) {
            return false;
        }
        int intExtra = intent.getIntExtra(KEY_GUARDIAN_ACTION_TYPE, 0);
        if (intExtra == 1) {
            sendGuardianNotification(context, intent);
            return true;
        } else if (intExtra != 2) {
            return false;
        } else {
            cancelGuardianNotification(context, intent);
            return true;
        }
    }

    public static boolean isLaunchAction(Context context, Intent intent) {
        if (isGuardianAction(intent) && intent.getIntExtra(KEY_GUARDIAN_ACTION_TYPE, 0) == 0) {
            if (new SettingsManager().getBoolean("first_time_nux_allow_guardian", false)) {
                return true;
            }
            Log.w(TAG, "Ignoring GUARDIAN_DIALOG due to pre-NUX activation.");
        }
        return false;
    }

    private static void sendGuardianNotification(Context context, Intent intent) {
        int i;
        int i2;
        String str;
        String str2;
        String str3;
        Bundle extras = intent.getExtras();
        String string = extras.getString("intent_data");
        NotificationsDisplayDuration notificationsDisplayDuration = NotificationsDisplayDuration.DEFAULT;
        char c = 65535;
        int hashCode = string.hashCode();
        boolean z = false;
        if (hashCode != -728383025) {
            if (hashCode != 1902233555) {
                if (hashCode == 1955499394 && string.equals(GUARDIAN_NOTIF_HAND_TRACKING)) {
                    c = 2;
                }
            } else if (string.equals(GUARDIAN_NOTIF_PT_ONDEMAND)) {
                c = 1;
            }
        } else if (string.equals(GUARDIAN_NOTIF_3DOF_ENABLED)) {
            c = 0;
        }
        if (c != 0) {
            if (c == 1) {
                String string2 = context.getResources().getString(R.string.toast_passthrough_ondemand_intro);
                i2 = extras.getInt("id", 4);
                str = string2;
                str2 = GUARDIAN_NOTIF_ID_PT_ONDEMAND;
                i = R.drawable.ic_notif_passthrough;
                z = true;
            } else if (c != 2) {
                str2 = "";
                str = str2;
                i2 = 0;
                i = 0;
            } else {
                String string3 = context.getResources().getString(R.string.toast_hands_guardian_intro);
                int i3 = extras.getInt("id", 6);
                NotificationsDisplayDuration notificationsDisplayDuration2 = NotificationsDisplayDuration.LONG;
                str3 = GUARDIAN_NOTIF_ID_HAND_TRACKING;
                i2 = i3;
                str = string3;
                i = R.drawable.ic_notif_hand_tracking;
                notificationsDisplayDuration = notificationsDisplayDuration2;
            }
            NotificationSender.build(str2, str, "", GUARDIAN_NOTIF_TAG, i2, i).setDisplayDuration(notificationsDisplayDuration).setIconImageType(NotificationConstants.LargeImageType.ICON).setSuppressSound(z).send(context);
        }
        String string4 = context.getResources().getString(R.string.toast_3dof_activated);
        int i4 = extras.getInt("id", 2);
        str3 = GUARDIAN_NOTIF_ID_3DOF_ENABLED;
        i2 = i4;
        str = string4;
        i = R.drawable.ic_notif_alert;
        str2 = str3;
        NotificationSender.build(str2, str, "", GUARDIAN_NOTIF_TAG, i2, i).setDisplayDuration(notificationsDisplayDuration).setIconImageType(NotificationConstants.LargeImageType.ICON).setSuppressSound(z).send(context);
    }

    private static void cancelGuardianNotification(Context context, Intent intent) {
        String string = intent.getExtras().getString("intent_data");
        char c = 65535;
        int hashCode = string.hashCode();
        int i = 0;
        if (hashCode != -728383025) {
            if (hashCode != 1902233555) {
                if (hashCode == 1955499394 && string.equals(GUARDIAN_NOTIF_HAND_TRACKING)) {
                    c = 2;
                }
            } else if (string.equals(GUARDIAN_NOTIF_PT_ONDEMAND)) {
                c = 1;
            }
        } else if (string.equals(GUARDIAN_NOTIF_3DOF_ENABLED)) {
            c = 0;
        }
        if (c == 0) {
            i = 2;
        } else if (c == 1) {
            i = 4;
        } else if (c == 2) {
            i = 6;
        }
        ((NotificationManager) context.getSystemService("notification")).cancel(GUARDIAN_NOTIF_TAG, i);
    }
}
