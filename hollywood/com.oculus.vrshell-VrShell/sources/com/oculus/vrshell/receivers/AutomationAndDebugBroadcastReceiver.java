package com.oculus.vrshell.receivers;

import android.content.Context;
import android.content.Intent;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.ShellApplication;
import com.oculus.vrshell.ShellBaseBroadcastReceiver;
import com.oculus.vrshell.config.AutomationAndDebugCommandHandlers;

public final class AutomationAndDebugBroadcastReceiver extends ShellBaseBroadcastReceiver {
    private static final String TAG = LoggingUtil.tag(AutomationAndDebugBroadcastReceiver.class);

    public void onReceive(Context context, Intent intent) {
        if (((ShellApplication) context.getApplicationContext()).getNavigationRouter().isIntentForReceiver(context, intent, this)) {
            AutomationAndDebugCommandHandlers.handleNotification(context, intent);
        }
    }
}
