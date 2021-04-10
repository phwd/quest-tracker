package com.oculus.vrshell;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.navigation.NavigationRouter;
import com.oculus.common.navigation.PrimaryPackage;
import com.oculus.vrshell.ShellApplication;

public final class AssistantBroadcastReceiver extends ShellBaseBroadcastReceiver {
    private static final String TAG = LoggingUtil.tag(AssistantBroadcastReceiver.class);

    public void onReceive(Context context, Intent intent) {
        ShellApplication shellApplication = (ShellApplication) context.getApplicationContext();
        NavigationRouter navigationRouter = shellApplication.getNavigationRouter();
        PrimaryPackage primaryPackage = navigationRouter.getPrimaryPackage();
        ShellApplication.IVrClient vrClient = shellApplication.getVrClient();
        long nativeAppPtr = vrClient != null ? vrClient.getNativeAppPtr() : 0;
        Log.d(TAG, String.format("onReceive with MainActivity native pointer %d, action %s", Long.valueOf(nativeAppPtr), intent.getAction()));
        if (navigationRouter.isIntentForReceiver(context, intent, this)) {
            boolean isLaunchAction = navigationRouter.isLaunchAction(intent);
            boolean z = shellApplication.getBootConfig().isDisableIapInOverlayEnabled;
            if (isLaunchAction && navigationRouter.needsUpgradeToLaunchIntent(nativeAppPtr)) {
                navigationRouter.submitAsLaunchIntent(context, intent, z);
            } else if (isLaunchAction && navigationRouter.shouldLaunchAsOverlay(intent, z)) {
                Log.d(TAG, "Launch intent that should start overlay.");
                navigationRouter.startOverlayIntent(context, intent);
            } else if (primaryPackage.isShellEnv()) {
                broadcastIntentToShellEnvOverlayService(context, intent);
            } else if (!primaryPackage.isShell()) {
                intent.setComponent(new ComponentName(context, ShellOverlayBroadcastReceiver.class));
                context.sendBroadcast(intent);
            } else if (nativeAppPtr != 0) {
                ShellApplication.nativeBroadcastIntent(nativeAppPtr, IntentParser.ToEnvironment(intent));
            }
        }
    }
}
