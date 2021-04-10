package com.oculus.vrruntimeservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.oculus.vrapi.Appshot;
import com.oculus.vrapi.ContextHelper;

public class VrRuntimeInputManager {
    private static final String TAG = "VrRuntimeInputManager";

    public static void sendChordedSystembuttonShort(Context context) {
        Log.d(TAG, "CHORDED sendChordedSystembuttonShort");
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.oculus.horizon", "com.oculus.horizon.service_media.OVRMediaService"));
        intent.putExtra("message_type", "com.oculus.horizon.CHORDED_SYSTEMBUTTONS_SHORT");
        intent.putExtra("target_package", Appshot.SHELL_PACKAGE_NAME);
        CallerInfoHelper.attachCallerInfo(intent, context, "VrRuntimeService:chordedSystembuttonShort()");
        ContextHelper.startServiceAsCurrentUser(context, intent);
        Log.d(TAG, "CHORDED end sendChordedSystembuttonShort");
    }

    public static void sendChordedSystembuttonLongStart(Context context) {
        Log.d(TAG, "CHORDED sendChordedSystembuttonLongStart");
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.oculus.horizon", "com.oculus.horizon.service_media.OVRMediaService"));
        intent.putExtra("message_type", "com.oculus.horizon.CHORDED_SYSTEMBUTTONS_LONG_START");
        intent.putExtra("target_package", Appshot.SHELL_PACKAGE_NAME);
        CallerInfoHelper.attachCallerInfo(intent, context, "VrRuntimeService:chordedSystembuttonLongStart()");
        ContextHelper.startServiceAsCurrentUser(context, intent);
        Log.d(TAG, "CHORDED end sendChordedSystembuttonLongStart");
    }

    public static void sendChordedSystembuttonLong(Context context) {
        Log.d(TAG, "CHORDED sendChordedSystembuttonLong");
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.oculus.horizon", "com.oculus.horizon.service_media.OVRMediaService"));
        intent.putExtra("message_type", "com.oculus.horizon.CHORDED_SYSTEMBUTTONS_LONG");
        intent.putExtra("target_package", Appshot.SHELL_PACKAGE_NAME);
        CallerInfoHelper.attachCallerInfo(intent, context, "VrRuntimeService:chordedSystembuttonLong()");
        ContextHelper.startServiceAsCurrentUser(context, intent);
        Log.d(TAG, "CHORDED end sendChordedSystembuttonLong");
    }

    public static void sendHandsBrightnessWarning(Context context, boolean tooDark) {
        if (tooDark) {
            Log.d(TAG, "HANDTRACKING Should fire too dark for hands Toast");
        } else {
            Log.d(TAG, "HANDTRACKING Should fire too bright for hands Toast");
        }
        Intent intent = new Intent("com.oculus.vrshell.intent.action.HANDS");
        intent.putExtra("intent_data", "systemux://hands/insufficient_hands_lighting");
        intent.setPackage(Appshot.SHELL_PACKAGE_NAME);
        context.sendBroadcast(intent);
    }

    public static void wakeupHandsAffordance(Context context) {
        Log.d(TAG, "HANDTRACKING wakeupHandsAffordance");
        Intent intent = new Intent("com.oculus.vrshell.intent.action.OVERLAY_HANDS_AFFORDANCE");
        intent.setPackage(Appshot.SHELL_PACKAGE_NAME);
        context.sendBroadcast(intent);
    }

    public static void sendShellLaunchIntent(Context context, String dataStr, String packageName) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(Appshot.SHELL_PACKAGE_NAME);
        intent.putExtra("intent_data", dataStr);
        intent.putExtra("intent_pkg", packageName);
        context.startActivity(intent);
    }
}
