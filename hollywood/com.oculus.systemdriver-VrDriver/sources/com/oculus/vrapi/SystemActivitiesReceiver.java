package com.oculus.vrapi;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import java.util.Arrays;

public class SystemActivitiesReceiver extends BroadcastReceiver {
    private static SystemActivitiesReceiver Receiver = new SystemActivitiesReceiver();
    private static boolean RegisteredReceiver = false;
    private static String SYSTEM_ACTIVITY_COMMAND_EXIT_TO_HOME = "exitToHome";
    private static String SYSTEM_ACTIVITY_COMMAND_REORIENT = "reorient";
    private static String SYSTEM_ACTIVITY_INTENT = "com.oculus.system_activity";
    private static final String TAG = "SystemActivitiesReceiver";
    private static Context contextObject;

    public static native boolean nativeSystemActivityIntent(Context context, String str, String str2, String str3);

    public static void startReceiver(Context context) {
        synchronized (SystemActivitiesReceiver.class) {
            if (!RegisteredReceiver) {
                Log.d(TAG, "Registering Oculus System Activity receiver");
                IntentFilter filter = new IntentFilter();
                filter.addAction(SYSTEM_ACTIVITY_INTENT);
                context.registerReceiver(Receiver, filter);
                RegisteredReceiver = true;
                contextObject = context;
                TrustedSignatureVerifier.GenerateSignatureMap();
            } else {
                Log.d(TAG, "Already registered Oculus System Activty receiver!");
            }
        }
    }

    public static void stopReceiver(Context context) {
        synchronized (SystemActivitiesReceiver.class) {
            if (RegisteredReceiver) {
                Log.d(TAG, "Unregistering Oculus System Activity receiver");
                context.unregisterReceiver(Receiver);
                RegisteredReceiver = false;
                contextObject = null;
            }
        }
    }

    private boolean isTrustedApp(Context context, String callerPackageName) {
        if (Arrays.asList(new String[0]).contains(callerPackageName)) {
            return isUserDevBuild();
        }
        if (!Arrays.asList(TrustedSignatureVerifier.TrustedSignedApps).contains(callerPackageName) || !TrustedSignatureVerifier.isSignatureValid(context, callerPackageName)) {
            return false;
        }
        return true;
    }

    private static boolean isUserDevBuild() {
        Log.d(TAG, "Checking build type");
        String propValue = SystemProps.getString("ro.build.fingerprint", null);
        if (propValue == null) {
            return false;
        }
        return !propValue.endsWith("release-keys");
    }

    public void onReceive(Context context, Intent intent) {
        Context context2;
        Log.d(TAG, "SystemActivityReceiver.onReceive intent:" + intent);
        if (intent.getAction().equals(SYSTEM_ACTIVITY_INTENT)) {
            String fromPackageName = SystemUtilities.getPackageStringFromIntent(intent);
            String command = SystemUtilities.getCommandStringFromIntent(intent);
            String uri = SystemUtilities.getUriStringFromIntent(intent);
            int platformUIVersion = SystemUtilities.getPlatformUIVersion(command);
            Log.d(TAG, "fromPackageName: '" + fromPackageName + "'");
            Log.d(TAG, "command: '" + command + "'");
            Log.d(TAG, "uri: '" + uri + "'");
            if ((command.contains(SYSTEM_ACTIVITY_COMMAND_EXIT_TO_HOME) || command.contains(SYSTEM_ACTIVITY_COMMAND_REORIENT)) && platformUIVersion >= 4) {
                if (intent.hasExtra("_ci_")) {
                    PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra("_ci_");
                    if (pendingIntent == null) {
                        Log.w(TAG, "PendingIntent is null");
                        return;
                    }
                    String callerPackageName = pendingIntent.getCreatorPackage();
                    if (!isTrustedApp(context, callerPackageName)) {
                        Log.w(TAG, "Intent sent from untrustworthy app - " + callerPackageName);
                        return;
                    }
                    Log.d(TAG, "App is trusted");
                } else {
                    Log.w(TAG, "No PendingIntent attached");
                    return;
                }
            }
            if (nativeSystemActivityIntent(context, fromPackageName, command, uri) && (context2 = contextObject) != null) {
                ContextHelper.finishActivityWithoutTransitionAnimation(context2, false);
            }
        }
    }
}
