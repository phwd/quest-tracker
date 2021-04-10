package com.oculus.companion.server;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.UserHandle;

public class ScreenshotActionsReceiver extends BroadcastReceiver {
    public static void addScreenshotActionFilter(Context context) {
        IntentFilter intentFilter = new IntentFilter("com.oculus.systemactivities.SCREENSHOT");
        intentFilter.addAction("com.oculus.systemactivities.BEGIN_VIDEO_CAPTURE");
        intentFilter.addAction("com.oculus.systemactivities.BEGIN_VIDEO_CAPTURE_WITH_SURFACE");
        intentFilter.addAction("com.oculus.systemactivities.PAUSE_VIDEO_CAPTURE");
        intentFilter.addAction("com.oculus.systemactivities.RESUME_VIDEO_CAPTURE");
        context.registerReceiverAsUser(new ScreenshotActionsReceiver(), UserHandle.ALL, intentFilter, null, null);
    }

    public void onReceive(Context context, Intent intent) {
        intent.setPackage("com.oculus.systemdriver");
        context.sendBroadcastAsUser(intent, UserHandle.SYSTEM);
    }
}
