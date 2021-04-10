package com.oculus.vrshell;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.util.SystemUXScreenshotUtil;

public class SystemUXBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = LoggingUtil.tag(SystemUXBroadcastReceiver.class);

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String str = TAG;
        Log.d(str, "action: " + action);
        if (action == null) {
            Log.w(TAG, "Received an intent with no action.");
            return;
        }
        char c = 65535;
        if (action.hashCode() == -1777923849 && action.equals("com.oculus.systemux.action.SET_SCREENSHOT")) {
            c = 0;
        }
        if (c != 0) {
            String str2 = TAG;
            Log.w(str2, "Received unknown action: " + action);
            return;
        }
        SystemUXScreenshotUtil.setHomeScreenshot(intent.getByteArrayExtra("image_data"));
    }
}
