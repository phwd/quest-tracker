package com.oculus.vrshell;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.navigation.NavigationRouter;
import com.oculus.common.navigation.PrimaryPackage;
import com.oculus.vrshell.ShellApplication;
import com.oculus.vrshell.notifications.VrNotificationToastGenerator;
import com.oculus.vrshell.util.DeviceHelper;

public class ShellControlProtectedReceiver extends ShellBaseBroadcastReceiver {
    private static final String TAG = LoggingUtil.tag(ShellControlProtectedReceiver.class);

    public void onReceive(Context context, Intent intent) {
        ShellApplication shellApplication = (ShellApplication) context.getApplicationContext();
        NavigationRouter navigationRouter = shellApplication.getNavigationRouter();
        PrimaryPackage primaryPackage = navigationRouter.getPrimaryPackage();
        ShellApplication.IVrClient vrClient = shellApplication.getVrClient();
        long nativeAppPtr = vrClient != null ? vrClient.getNativeAppPtr() : 0;
        Log.d(TAG, String.format("onReceive with MainActivity native pointer %d, action %s", Long.valueOf(nativeAppPtr), intent.getAction()));
        if (!navigationRouter.isIntentForReceiver(context, intent, this) || shouldIgnoreIntent(context, intent) || HandsController.handleNotification(context, intent)) {
            return;
        }
        if (nativeAppPtr != 0) {
            ShellApplication.nativeBroadcastIntent(nativeAppPtr, IntentParser.ToEnvironment(intent));
        } else if (primaryPackage.isShellEnv()) {
            broadcastIntentToShellEnvOverlayService(context, intent);
        }
    }

    private static boolean shouldIgnoreIntent(Context context, Intent intent) {
        if (DeviceHelper.isScreenOn(context)) {
            return false;
        }
        String action = intent.getAction();
        char c = 65535;
        if (action.hashCode() == -1487793573 && action.equals(VrNotificationToastGenerator.ACTION_TOAST_NOTIFICATION)) {
            c = 0;
        }
        if (c != 0) {
            return false;
        }
        Log.d(TAG, "Ignoring while screen is off: " + action);
        return true;
    }
}
