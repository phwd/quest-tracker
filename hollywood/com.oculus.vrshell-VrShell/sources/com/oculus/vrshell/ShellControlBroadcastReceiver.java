package com.oculus.vrshell;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.navigation.NavigationRouter;
import com.oculus.common.navigation.PrimaryPackage;
import com.oculus.notifications.NotificationSender;
import com.oculus.vrshell.ShellApplication;
import com.oculus.vrshell.config.AutomationAndDebugCommandHandlers;

public final class ShellControlBroadcastReceiver extends ShellBaseBroadcastReceiver {
    private static final String ACTION_ENTITLEMENT_SHARING_WARNING = "com.oculus.vrshell.intent.action.ENTITLEMENT_SHARING_WARNING";
    private static final String ACTION_SEND_TEST_NOTIF = "com.oculus.vrshell.intent.action.SEND_TEST_NOTIF";
    private static final String TAG = LoggingUtil.tag(ShellControlBroadcastReceiver.class);

    public void onReceive(Context context, Intent intent) {
        String activeThirdPartyApplication;
        ShellApplication shellApplication = (ShellApplication) context.getApplicationContext();
        NavigationRouter navigationRouter = shellApplication.getNavigationRouter();
        PrimaryPackage primaryPackage = navigationRouter.getPrimaryPackage();
        ShellApplication.IVrClient vrClient = shellApplication.getVrClient();
        long nativeAppPtr = vrClient != null ? vrClient.getNativeAppPtr() : 0;
        Log.d(TAG, String.format("onReceive with MainActivity native pointer %d, action %s", Long.valueOf(nativeAppPtr), intent.getAction()));
        if (navigationRouter.isIntentForReceiver(context, intent, this)) {
            if (isAccessTokenChangedAction(intent)) {
                shellApplication.handleUserAccessTokenChanged();
            }
            boolean z = shellApplication.getBootConfig().isDisableIapInOverlayEnabled;
            boolean z2 = shellApplication.getBootConfig().isShellEnvEnabled;
            boolean isLaunchAction = navigationRouter.isLaunchAction(intent);
            String str = TAG;
            Log.d(str, "isLaunchAction = " + isLaunchAction);
            if (!GuardianController.handleNotification(context, intent) && !AutomationAndDebugCommandHandlers.handleNotification(shellApplication, intent)) {
                if (isOverlayAction(intent)) {
                    Log.d(TAG, "OverlayAction");
                    intent.setAction("com.oculus.vrshell.intent.action.LAUNCH");
                    navigationRouter.startOverlayIntent(context, intent);
                } else if (ACTION_SEND_TEST_NOTIF.equals(intent.getAction())) {
                    Log.d(TAG, "Test Notification");
                    NotificationSender.sendTestNotif(context, intent);
                } else if (ACTION_ENTITLEMENT_SHARING_WARNING.equals(intent.getAction())) {
                    handleEntitlementSharingWarning(shellApplication);
                } else if (isLocalStreamPrivacyCheckAction(intent)) {
                    Log.d(TAG, "IsLocalStreamPrivacyCheckAction");
                    Uri.Builder buildUpon = Uri.parse(SystemUXRoute.LOCAL_STREAM_PRIVACY_CHECK_DIALOG.path()).buildUpon();
                    buildUpon.appendQueryParameter("timestamp", Long.valueOf(System.currentTimeMillis()).toString());
                    intent.putExtra("intent_data", buildUpon.toString());
                    if (!primaryPackage.isShell() && (activeThirdPartyApplication = getActiveThirdPartyApplication(context)) != null) {
                        intent.putExtra(ShellApplication.INTENT_KEY_FROM_PKG, activeThirdPartyApplication);
                    }
                    navigationRouter.submitAsLaunchIntent(context, intent, z);
                } else if ((isLaunchAction || GuardianController.isLaunchAction(context, intent)) && navigationRouter.needsUpgradeToLaunchIntent(nativeAppPtr)) {
                    navigationRouter.submitAsLaunchIntent(context, intent, z);
                } else if (isLaunchAction && navigationRouter.shouldLaunchAsOverlay(intent, z)) {
                    Log.d(TAG, "Launch intent that should start overlay.");
                    navigationRouter.startOverlayIntent(context, intent);
                } else if (nativeAppPtr != 0) {
                    Log.d(TAG, "nativeBroadcastIntent");
                    ShellApplication.nativeBroadcastIntent(nativeAppPtr, IntentParser.ToEnvironment(intent, prepareInitialEnvironmentFor(context, intent)));
                } else if (z2) {
                    Log.d(TAG, "broadcastIntentToShellEnvOverlayService isShellEnvEnabled");
                    broadcastIntentToShellEnvOverlayService(context, intent);
                } else if (isGatekeeperAction(intent)) {
                    Log.d(TAG, "broadcastIntentToShellEnvOverlayService gateKeeperAction");
                    broadcastIntentToShellEnvOverlayService(context, intent);
                }
            }
        }
    }

    private static boolean isAccessTokenChangedAction(Intent intent) {
        return "com.oculus.horizon.ACCESS_TOKEN_CHANGED".equals(intent.getAction());
    }

    private static boolean isLocalStreamPrivacyCheckAction(Intent intent) {
        return ShellEnvOverlayService.LOCAL_STREAM_PRIVACY_CHECK.equals(intent.getAction());
    }

    private static boolean isOverlayAction(Intent intent) {
        return "com.oculus.vrshell.intent.action.OVERLAY".equals(intent.getAction());
    }

    private static boolean isGatekeeperAction(Intent intent) {
        return "com.oculus.vrshell.intent.action.GATEKEEPER".equals(intent.getAction());
    }

    private static String getActiveThirdPartyApplication(Context context) {
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
            if (runningAppProcessInfo.importance == 100 && !runningAppProcessInfo.processName.startsWith("com.oculus")) {
                return runningAppProcessInfo.processName;
            }
        }
        return null;
    }

    private static void handleEntitlementSharingWarning(ShellApplication shellApplication) {
        Intent intent = new Intent("com.oculus.vrshell.intent.action.LAUNCH");
        intent.setPackage("com.oculus.vrshell");
        intent.putExtra("intent_data", Uri.parse(SystemUXRoute.LAUNCH_CHECK_ENTITLEMENT_SHARING.path()));
        intent.putExtra(ShellApplication.INTENT_KEY_FROM_PKG, shellApplication.getNavigationRouter().getPrimaryPackage().getPackageName());
        shellApplication.sendBroadcast(intent);
    }
}
