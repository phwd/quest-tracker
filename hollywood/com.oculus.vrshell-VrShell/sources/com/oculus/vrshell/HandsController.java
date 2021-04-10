package com.oculus.vrshell;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.notifications.NotificationSender;

public final class HandsController {
    private static final String HANDS_NOTIF_ID = "oculus_mobile_hands_insufficient_lighting";
    private static final String HANDS_NOTIF_INSUFFICIENT_HANDS_LIGHTING = "systemux://hands/insufficient_hands_lighting";
    private static final String HANDS_NOTIF_TAG = "hands";
    private static final String TAG = LoggingUtil.tag(HandsController.class);

    private static boolean isHandsAction(Intent intent) {
        return "com.oculus.vrshell.intent.action.HANDS".equals(intent.getAction());
    }

    public static boolean handleNotification(Context context, Intent intent) {
        if (!isHandsAction(intent)) {
            return false;
        }
        Bundle extras = intent.getExtras();
        if (!HANDS_NOTIF_INSUFFICIENT_HANDS_LIGHTING.equals(extras.getString("intent_data"))) {
            return false;
        }
        sendNotification(context, HANDS_NOTIF_ID, context.getResources().getString(R.string.toast_insufficient_hands_lighting_title), context.getResources().getString(R.string.toast_insufficient_hands_lighting_description), R.drawable.ic_notif_alert, extras.getInt("id", 5));
        return true;
    }

    private static void sendNotification(Context context, String str, String str2, String str3, int i, int i2) {
        NotificationSender.build(str, str2, str3, HANDS_NOTIF_TAG, i2, i).send(context);
    }
}
