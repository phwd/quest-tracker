package com.oculus.vrshell;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.navigation.NavigationRouter;
import com.oculus.vrshell.ShellApplication;

public final class DeviceServerBroadcastReceiver extends ShellBaseBroadcastReceiver {
    private static final String TAG = LoggingUtil.tag(DeviceServerBroadcastReceiver.class);

    public void onReceive(Context context, Intent intent) {
        ShellApplication shellApplication = (ShellApplication) context.getApplicationContext();
        NavigationRouter navigationRouter = shellApplication.getNavigationRouter();
        ShellApplication.IVrClient vrClient = shellApplication.getVrClient();
        long nativeAppPtr = vrClient != null ? vrClient.getNativeAppPtr() : 0;
        Log.d(TAG, String.format("onReceive with MainActivity native pointer %d, action %s", Long.valueOf(nativeAppPtr), intent.getAction()));
        if (navigationRouter.isIntentForReceiver(context, intent, this)) {
            if (shellApplication.getBootConfig().isShellEnvEnabled) {
                broadcastIntentToShellEnvOverlayService(context, intent);
            } else if (nativeAppPtr != 0) {
                ShellApplication.nativeBroadcastIntent(nativeAppPtr, IntentParser.ToEnvironment(intent));
            }
        }
    }
}
