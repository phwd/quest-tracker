package com.oculus.vrshell;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.navigation.NavigationRouter;
import com.oculus.vrshell.notifications.VrNotificationToastGenerator;
import com.oculus.vrshell.util.DeviceHelper;
import java.util.HashMap;

public final class ShellOverlayBroadcastReceiver extends BroadcastReceiver {
    private static final String INTENT_ASSISTANT_COMMAND = "com.oculus.assistant.COMMAND";
    private static final String INTENT_ASSISTANT_LAUNCH = "com.oculus.assistant.LAUNCH";
    public static final String INTENT_GUARDIAN_ACTION = "com.oculus.vrshell.intent.action.GUARDIAN";
    private static final String INTENT_LOCAL_STREAM_STATE_UPDATE = "com.oculus.systemactivities.LOCAL_STREAM_STATE_UPDATE";
    private static final String INTENT_OVERLAY_COMMAND = "com.oculus.vrshell.OVERLAY_COMMAND";
    private static final String INTENT_OVERLAY_HANDS_AFFORDANCE = "com.oculus.vrshell.intent.action.OVERLAY_HANDS_AFFORDANCE";
    private static final String INTENT_QUEUE_COMMAND = "com.oculus.vrshell.intent.action.QUEUE_COMMAND";
    private static final String INTENT_WIDGET = "com.oculus.vrshell.intent.action.WIDGET";
    private static final String TAG = LoggingUtil.tag(ShellOverlayBroadcastReceiver.class);

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public void onReceive(Context context, Intent intent) {
        char c;
        char c2;
        char c3;
        String action = intent.getAction();
        if (action != null) {
            ShellApplication shellApplication = (ShellApplication) context.getApplicationContext();
            NavigationRouter navigationRouter = shellApplication.getNavigationRouter();
            if (navigationRouter.isIntentForReceiver(context, intent, this)) {
                if (!DeviceHelper.isScreenOn(context)) {
                    switch (action.hashCode()) {
                        case -1487793573:
                            if (action.equals(VrNotificationToastGenerator.ACTION_TOAST_NOTIFICATION)) {
                                c3 = 3;
                                break;
                            }
                            c3 = 65535;
                            break;
                        case -526681743:
                            if (action.equals(INTENT_OVERLAY_HANDS_AFFORDANCE)) {
                                c3 = 2;
                                break;
                            }
                            c3 = 65535;
                            break;
                        case 5382275:
                            if (action.equals(INTENT_ASSISTANT_COMMAND)) {
                                c3 = 0;
                                break;
                            }
                            c3 = 65535;
                            break;
                        case 1769166683:
                            if (action.equals(INTENT_ASSISTANT_LAUNCH)) {
                                c3 = 1;
                                break;
                            }
                            c3 = 65535;
                            break;
                        default:
                            c3 = 65535;
                            break;
                    }
                    if (c3 == 0 || c3 == 1 || c3 == 2 || c3 == 3) {
                        Log.d(TAG, "Ignoring while screen is off: " + action);
                        return;
                    }
                }
                ShellOverlayService shellOverlayService = (ShellOverlayService) shellApplication.getVrClient();
                if (shellOverlayService == null) {
                    switch (action.hashCode()) {
                        case -2025369718:
                            if (action.equals(INTENT_OVERLAY_COMMAND)) {
                                c2 = 2;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -1487793573:
                            if (action.equals(VrNotificationToastGenerator.ACTION_TOAST_NOTIFICATION)) {
                                c2 = 4;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -526681743:
                            if (action.equals(INTENT_OVERLAY_HANDS_AFFORDANCE)) {
                                c2 = 3;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 5382275:
                            if (action.equals(INTENT_ASSISTANT_COMMAND)) {
                                c2 = 0;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 14054524:
                            if (action.equals(INTENT_WIDGET)) {
                                c2 = 6;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 1769166683:
                            if (action.equals(INTENT_ASSISTANT_LAUNCH)) {
                                c2 = 1;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 1813617829:
                            if (action.equals(INTENT_QUEUE_COMMAND)) {
                                c2 = 5;
                                break;
                            }
                            c2 = 65535;
                            break;
                        default:
                            c2 = 65535;
                            break;
                    }
                    switch (c2) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                            Log.d(TAG, "onReceive with null service. Restarting for " + action);
                            navigationRouter.startOverlayIntent(context, intent);
                            return;
                        default:
                            Log.d(TAG, "onReceive with null service. Ignoring " + action + " ...");
                            return;
                    }
                } else {
                    long nativeAppPtr = shellOverlayService.getNativeAppPtr();
                    Log.d(TAG, String.format("onReceive with ShellOverlayService native pointer %d, action %s", Long.valueOf(nativeAppPtr), action));
                    switch (action.hashCode()) {
                        case -2025369718:
                            if (action.equals(INTENT_OVERLAY_COMMAND)) {
                                c = '\t';
                                break;
                            }
                            c = 65535;
                            break;
                        case -1814774568:
                            if (action.equals(VrNotificationToastGenerator.ACTION_DISMISS_NOTIFICATION)) {
                                c = 3;
                                break;
                            }
                            c = 65535;
                            break;
                        case -1487793573:
                            if (action.equals(VrNotificationToastGenerator.ACTION_TOAST_NOTIFICATION)) {
                                c = 5;
                                break;
                            }
                            c = 65535;
                            break;
                        case -526681743:
                            if (action.equals(INTENT_OVERLAY_HANDS_AFFORDANCE)) {
                                c = 4;
                                break;
                            }
                            c = 65535;
                            break;
                        case 5382275:
                            if (action.equals(INTENT_ASSISTANT_COMMAND)) {
                                c = 1;
                                break;
                            }
                            c = 65535;
                            break;
                        case 14054524:
                            if (action.equals(INTENT_WIDGET)) {
                                c = 7;
                                break;
                            }
                            c = 65535;
                            break;
                        case 490676681:
                            if (action.equals("com.oculus.vrshell.intent.action.GUARDIAN")) {
                                c = '\b';
                                break;
                            }
                            c = 65535;
                            break;
                        case 1627679816:
                            if (action.equals("com.oculus.systemactivities.LOCAL_STREAM_STATE_UPDATE")) {
                                c = 0;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1769166683:
                            if (action.equals(INTENT_ASSISTANT_LAUNCH)) {
                                c = 2;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1813617829:
                            if (action.equals(INTENT_QUEUE_COMMAND)) {
                                c = 6;
                                break;
                            }
                            c = 65535;
                            break;
                        default:
                            c = 65535;
                            break;
                    }
                    switch (c) {
                        case 0:
                            shellOverlayService.sendOverlayCommand("localStreamStateUpdate", IntentParser.ToEnvironment(intent, new HashMap()));
                            return;
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                            if (!navigationRouter.getPrimaryPackage().isShell()) {
                                ShellApplication.nativeBroadcastIntent(nativeAppPtr, IntentParser.ToEnvironment(intent));
                                shellOverlayService.wakeNative();
                                return;
                            }
                            return;
                        case 7:
                            ShellApplication.nativeBroadcastIntent(nativeAppPtr, IntentParser.ToEnvironment(intent));
                            shellOverlayService.wakeNative();
                            return;
                        case '\b':
                            Log.d(TAG, "Waking for guardian state check");
                            shellOverlayService.wakeNative();
                            return;
                        case '\t':
                            shellOverlayService.sendOverlayCommand(null, IntentParser.ToEnvironment(intent, new HashMap()));
                            return;
                        default:
                            return;
                    }
                }
            }
        }
    }
}
