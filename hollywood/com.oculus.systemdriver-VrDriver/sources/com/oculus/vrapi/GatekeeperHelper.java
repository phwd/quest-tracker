package com.oculus.vrapi;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class GatekeeperHelper {
    public static final String COLUMN = "gk_enabled";
    private static final String GK_ENABLE_GUARDIAN_ON_SCREEN_CAPTURE = "oculus_enable_guardian_on_screen_capture";
    private static final String GK_ENABLE_HAND_TRACKING_FREQUENCY_INTERNAL = "oculus_mobile_enable_hand_tracking_frequency_inter";
    private static final String GK_ENABLE_MOBILE_SCREENSHOT_SHORTCUT = "oculus_mobile_screenshot_shortcut";
    private static final String GK_ENABLE_SMALL_FINGER_OPENING_PINCH_THRESHOLD = "oculus_mobile_enable_small_finger_opening_pinch";
    private static final String GK_HOMEBUTTON_EATTRIGGER_SPOTFIX = "oculus_mobile_homebutton_eattrigger_spotfix";
    private static final String GK_HOMEBUTTON_FREEZEINPUT = "oculus_mobile_homebutton_freezeinput";
    private static final String GK_HOMEBUTTON_FREEZEINPUT_APICHK = "oculus_mobile_homebutton_freezeinput_apichk";
    private static final String GK_HOME_SHORTPRESS_LOGGING = "oculus_home_shortpress_logging";
    private static final String GK_MOBILE_SPACEWARP = "oculus_mobile_spacewarp";
    private static final String GK_POINTER_POSE_FILTER_TUNING_EXPERIMENT = "oculus_mobile_pointer_pose_filter_tuning";
    private static final String GK_VRSHELL_FOCUS_AWARENESS_PUSH_ROLLOUT = "oculus_vrshell_focus_awareness_push_rollout";
    private static final String TAG = "GatekeeperHelper";
    private static final String TREX_GK_PREFIX = "oculus_trex_";
    private static final String TREX_KILLSWITCH_POSTFIX = "_killsw";

    private static Uri getContentUri(String gkName) {
        return Uri.parse("content://com.oculus.horizon.gatekeeper/fetch?name=" + gkName);
    }

    public static boolean checkGatekeeperHelper(Context context, String gatekeeper) {
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(getContentUri(gatekeeper), null, null, null, null);
            boolean z = false;
            if (cursor != null) {
                if (cursor.moveToNext()) {
                    if (cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN)) == 1) {
                        z = true;
                    }
                    cursor.close();
                    return z;
                }
            }
            return false;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public static boolean checkGuardianOnCaptureEnabledGatekeeper(Context context) {
        return checkGatekeeperHelper(context, GK_ENABLE_GUARDIAN_ON_SCREEN_CAPTURE);
    }

    public static boolean checkHomeShortpressLogging(Context context) {
        return checkGatekeeperHelper(context, GK_HOME_SHORTPRESS_LOGGING);
    }

    public static boolean checkIsMobileSpaceWarpEnabled(Context context) {
        return checkGatekeeperHelper(context, GK_MOBILE_SPACEWARP);
    }

    public static boolean checkIsMobileScreenshotShortcutEnabled(Context context) {
        return checkGatekeeperHelper(context, GK_ENABLE_MOBILE_SCREENSHOT_SHORTCUT);
    }

    public static boolean checkVrShellFocusAwarenessPushRollout(Context context) {
        return checkGatekeeperHelper(context, GK_VRSHELL_FOCUS_AWARENESS_PUSH_ROLLOUT);
    }

    public static boolean checkPointerPoseFilterTuningExperiment(Context context) {
        return checkGatekeeperHelper(context, GK_POINTER_POSE_FILTER_TUNING_EXPERIMENT);
    }

    public static boolean checkHomeButtonFreezeInput(Context context) {
        return checkGatekeeperHelper(context, GK_HOMEBUTTON_FREEZEINPUT);
    }

    public static boolean checkHomeButtonFreezeInputAPIChk(Context context) {
        return checkGatekeeperHelper(context, GK_HOMEBUTTON_FREEZEINPUT_APICHK);
    }

    public static boolean checkHomeButtonEatTriggerSpotFix(Context context) {
        return checkGatekeeperHelper(context, GK_HOMEBUTTON_EATTRIGGER_SPOTFIX);
    }

    public static boolean checkTREXKillswitchGK(Context context, String killswitchGK) {
        return checkGatekeeperHelper(context, TREX_GK_PREFIX + killswitchGK + TREX_KILLSWITCH_POSTFIX);
    }

    public static boolean checkHandTrackingFrequencyInternalGK(Context context) {
        return checkGatekeeperHelper(context, GK_ENABLE_HAND_TRACKING_FREQUENCY_INTERNAL);
    }

    public static boolean checkSmallFingerOpeningThresholdGK(Context context) {
        return checkGatekeeperHelper(context, GK_ENABLE_SMALL_FINGER_OPENING_PINCH_THRESHOLD);
    }

    public static int getHorizonCaptureCapability(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo("com.oculus.horizon", 128).metaData.getInt("com.oculus.horizon.screencapture.capability", 0);
        } catch (PackageManager.NameNotFoundException e) {
            Log.w(TAG, "Failed to load horizon metadata: " + e.getMessage());
            return -1;
        }
    }
}
