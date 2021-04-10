package com.oculus.vrshell;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.navigation.NavigationRouter;

public final class DeviceServerOverlayBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = LoggingUtil.tag(DeviceServerOverlayBroadcastReceiver.class);

    public DeviceServerOverlayBroadcastReceiver() {
        System.loadLibrary("overlay");
    }

    public void onReceive(Context context, Intent intent) {
        ShellApplication shellApplication = (ShellApplication) context.getApplicationContext();
        NavigationRouter navigationRouter = shellApplication.getNavigationRouter();
        ShellOverlayService shellOverlayService = (ShellOverlayService) shellApplication.getVrClient();
        long nativeAppPtr = shellOverlayService != null ? shellOverlayService.getNativeAppPtr() : 0;
        Log.d(TAG, String.format("onReceive with MainActivity native pointer %d, action %s", Long.valueOf(nativeAppPtr), intent.getAction()));
        if (navigationRouter.isIntentForReceiver(context, intent, this) && nativeAppPtr != 0) {
            ShellApplication.nativeBroadcastIntent(nativeAppPtr, IntentParser.ToEnvironment(intent));
        }
    }
}
