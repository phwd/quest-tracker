package com.oculus.platform.receiver;

import X.AnonymousClass006;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.oculus.platform.OVR;

public class IAPBroadcastReceiver extends BroadcastReceiver {
    public static final String IAP_BROADCAST_PERMISSION = "com.oculus.iap.IAPResultPermission";
    public static final String IAP_SHELL_RESULT = "com.oculus.iap_shell_result";
    public static final String TAG = "IAPBroadcastReceiver";

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("com.oculus.iap_shell_result".equals(action)) {
            OVR.nativeIAPResultFromShell(intent.getLongExtra("platform_request_id", 0), intent.getStringExtra(OVR.IAP_SHELL_RESULT), intent.getBooleanExtra("is_successful", false));
            return;
        }
        Log.e(TAG, AnonymousClass006.A05("Error logging unhandled broadcast action:", action));
    }
}
