package com.oculus.vrshell;

import X.AnonymousClass006;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.util.SystemUXScreenshotUtil;

public class SystemUXBroadcastReceiver extends BroadcastReceiver {
    public static final String TAG = LoggingUtil.tag(SystemUXBroadcastReceiver.class);

    public void onReceive(Context context, Intent intent) {
        String str;
        String A07;
        String action = intent.getAction();
        if (action == null) {
            str = TAG;
            A07 = "Received an intent with no action.";
        } else if (action.hashCode() != -1777923849 || !action.equals("com.oculus.systemux.action.SET_SCREENSHOT")) {
            str = TAG;
            A07 = AnonymousClass006.A07("Received unknown action: ", action);
        } else {
            SystemUXScreenshotUtil.setHomeScreenshot(intent.getByteArrayExtra("image_data"));
            return;
        }
        Log.w(str, A07);
    }
}
