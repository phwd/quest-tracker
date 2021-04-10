package com.oculus.vrapi;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

public class SystemUtilities {
    public static final String DIALOG_KEY = "dialog";
    public static final String INTENT_KEY_CMD = "intent_cmd";
    public static final String INTENT_KEY_COMPONENT = "intent_component_name";
    public static final String INTENT_KEY_DATA = "intent_data";
    public static final String INTENT_KEY_FROM_PKG = "intent_pkg";
    public static final String INTENT_KEY_JPEG = "jpg_data";
    private static final String TAG = "SystemUtils";
    public static final String URI_KEY = "uri";

    public static native void nativeSetScreenshotPublishFlag(boolean z);

    public static String getCommandStringFromIntent(Intent intent) {
        String commandStr;
        if (intent == null || (commandStr = intent.getStringExtra(INTENT_KEY_CMD)) == null) {
            return "";
        }
        return commandStr;
    }

    public static String getPackageStringFromIntent(Intent intent) {
        String packageStr;
        if (intent == null || (packageStr = intent.getStringExtra("intent_pkg")) == null) {
            return "";
        }
        return packageStr;
    }

    public static String getUriStringFromIntent(Intent intent) {
        Uri uri;
        String uriString;
        if (intent == null || (uri = intent.getData()) == null || (uriString = uri.toString()) == null) {
            return "";
        }
        return uriString;
    }

    public static int getPlatformUIVersion(String command) {
        if (command == null) {
            return 0;
        }
        try {
            return new JSONObject(command).getInt("PlatformUIVersion");
        } catch (JSONException e) {
            Log.d(TAG, "JSONException: " + e);
            return 0;
        }
    }

    public static void finishAffinityOnUiThread(Context context) {
        if (!(context instanceof Activity)) {
            Log.w(TAG, "Can't call finishAffinity() without an Activity");
            return;
        }
        final Activity act = (Activity) context;
        act.runOnUiThread(new Runnable() {
            /* class com.oculus.vrapi.SystemUtilities.AnonymousClass1 */

            public void run() {
                Log.d(SystemUtilities.TAG, "finishAffinityOnUiThread calling finish()");
                act.finishAffinity();
                act.overridePendingTransition(0, 0);
            }
        });
    }

    public static void sendLaunchIntent(Context context, String packageName, String commandStr, String uriStr, String actionString) {
        Log.d(TAG, "sendLaunchIntent: '" + packageName + "' command: '" + commandStr + "' uri: '" + uriStr + "'action: '" + actionString + "'");
        Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        if (launchIntent == null) {
            Log.d(TAG, "sendLaunchIntent: null destination activity");
            return;
        }
        if (actionString != null && !actionString.isEmpty()) {
            launchIntent.setAction(actionString);
        }
        launchIntent.putExtra(INTENT_KEY_CMD, commandStr);
        launchIntent.putExtra("intent_pkg", context.getApplicationContext().getPackageName());
        if (uriStr.length() > 0) {
            launchIntent.setData(Uri.parse(uriStr));
        }
        sendIntent(context, launchIntent);
    }

    public static void sendIntent(Context context, Intent intent) {
        try {
            Log.d(TAG, "startActivity " + intent);
            intent.addFlags(65536);
            ContextHelper.startActivityWithoutTransitionAnimation(context, intent);
        } catch (ActivityNotFoundException e) {
            Log.d(TAG, "startActivity " + intent + " not found!");
        } catch (Exception e2) {
            Log.e(TAG, "sendIntent threw exception " + e2);
        }
    }

    public static void sendIntentFromNative(Context context, String actionName, String toPackageName, String toClassName, String commandStr, String uriStr) {
        Log.d(TAG, "SendIntentFromNative: action: '" + actionName + "' toPackage: '" + toPackageName + "/" + toClassName + "' command: '" + commandStr + "' uri: '" + uriStr + "'");
        Intent intent = new Intent(actionName);
        if (toPackageName != null && !toPackageName.isEmpty()) {
            intent.setPackage(toPackageName);
        }
        if (toPackageName != null && !toPackageName.isEmpty() && toClassName != null && !toClassName.isEmpty()) {
            ComponentName cname = new ComponentName(toPackageName, toClassName);
            intent.setComponent(cname);
            Log.d(TAG, "Sending explicit intent: " + cname.flattenToString());
        }
        if (uriStr.length() > 0) {
            intent.setData(Uri.parse(uriStr));
        }
        intent.putExtra(INTENT_KEY_CMD, commandStr);
        intent.putExtra("intent_pkg", context.getApplicationContext().getPackageName());
        sendIntent(context, intent);
    }

    public static boolean sendBroadcastIntent(Context context, String actionName, String dataStr, String currentPkgStr, String cmdStr) {
        Log.d(TAG, "sendBroadcastIntent - action: '" + actionName + "' data: '" + dataStr + "'intent_pkg: " + currentPkgStr);
        Intent intent = new Intent(actionName);
        if (dataStr != null && !dataStr.isEmpty()) {
            intent.putExtra("intent_data", dataStr);
        }
        if (currentPkgStr != null && !currentPkgStr.isEmpty()) {
            intent.putExtra("intent_pkg", currentPkgStr);
        }
        if (cmdStr != null && !cmdStr.isEmpty()) {
            intent.putExtra(INTENT_KEY_CMD, cmdStr);
        }
        addComponentNameToIntent(context, intent);
        intent.addFlags(65536);
        intent.addFlags(32);
        boolean isVrShellForeground = isVrShellForegrounded(context);
        if (!isVrShellForeground) {
            intent.addFlags(268435456);
        }
        if (isLaunchAction(actionName)) {
            intent.setPackage(Appshot.SHELL_PACKAGE_NAME);
        }
        ContextHelper.sendBroadcastAsCurrentUser(context, intent);
        return !isVrShellForeground;
    }

    private static boolean isLaunchAction(String actionName) {
        return "com.oculus.vrshell.intent.action.LAUNCH".equals(actionName);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0042 A[EDGE_INSN: B:19:0x0042->B:12:0x0042 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:3:0x0019  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean isVrShellForegrounded(android.content.Context r6) {
        /*
        // Method dump skipped, instructions count: 106
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrapi.SystemUtilities.isVrShellForegrounded(android.content.Context):boolean");
    }

    private static void addComponentNameToIntent(Context context, Intent intent) {
        if (context instanceof Activity) {
            intent.putExtra(INTENT_KEY_COMPONENT, ((Activity) context).getComponentName().flattenToString());
        }
    }
}
