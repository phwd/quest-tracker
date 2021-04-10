package com.oculus.vrguardianservice;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class GuardianGatekeeperHelper {
    public static final String COLUMN = "gk_enabled";
    private static final String GK_DEVICECONFIG_TEST_73 = "oculus_deviceconfig_test_73";
    private static final String GK_DEVICECONFIG_TEST_ENABLED = "oculus_deviceconfig_test_enabled";
    private static final String GK_GUARDIAN_REALITY_TUNER = "oculus_guardian_reality_tuner";
    private static final String GK_INTERNAL_ANCHOR = "oculus_guardian_internal_anchor";
    private static final String GK_INTRUSION_DETECTION = "oculus_intrusion_detection";
    private static final String GK_INTRUSION_DETECTION_HEADLESS = "oculus_intrusion_detection_headless";
    private static final String GK_LOW_MEM_KILL = "oculus_guardian_low_mem_kill";
    private static final String GK_MRSERVICE_INSIGHT_ENABLE = "oculus_mrservice_insightsdk";
    private static final String GK_MR_DESK_CREATION = "oculus_mobile_guardian_mr_desk_gk";
    private static final String GK_QUEST_PASSTHROUGH_QUICKBOOT = "oculus_quest_passthrough_background_quickboot";
    private static final String GK_QUEST_PLAYSPACE_SCAN = "oculus_quest_playspace_scan";
    private static final String GK_QUEST_PLAYSPACE_SCAN_HEADLESS = "oculus_quest_playspace_scan_headless";
    private static final String GK_SPATIAL_ANCHOR = "oculus_mobile_guardian_spatial_anchor";
    private static final String GK_STATIONARY_GUARDIAN_V2 = "oculus_stationary_guardian_v2";
    private static final String TAG = "GuardianGatekeeperHelper";

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

    public static boolean checkMrServiceInsightSdkGatekeeper(Context context) {
        return checkGatekeeperHelper(context, GK_MRSERVICE_INSIGHT_ENABLE);
    }

    public static boolean checkQuestPlayspaceScanGatekeeper(Context context) {
        return checkGatekeeperHelper(context, GK_QUEST_PLAYSPACE_SCAN);
    }

    public static boolean checkQuestPlayspaceScanHeadlessGatekeeper(Context context) {
        return checkGatekeeperHelper(context, GK_QUEST_PLAYSPACE_SCAN_HEADLESS);
    }

    public static boolean checkEnableDeviceConfigTestGatekeeper(Context context) {
        return checkGatekeeperHelper(context, GK_DEVICECONFIG_TEST_ENABLED);
    }

    public static boolean checkDeviceConfigTest73Gatekeeper(Context context) {
        return checkGatekeeperHelper(context, GK_DEVICECONFIG_TEST_73);
    }

    public static boolean checkQuestPassthroughQuickbootGatekeeper(Context context) {
        return checkGatekeeperHelper(context, GK_QUEST_PASSTHROUGH_QUICKBOOT);
    }

    public static boolean checkRealityTunerGatekeeper(Context context) {
        return checkGatekeeperHelper(context, GK_GUARDIAN_REALITY_TUNER);
    }

    public static boolean checkStationaryGuardianV2Gatekeeper(Context context) {
        return checkGatekeeperHelper(context, GK_STATIONARY_GUARDIAN_V2);
    }

    public static boolean checkIntrusionDetectionGatekeeper(Context context) {
        return checkGatekeeperHelper(context, GK_INTRUSION_DETECTION);
    }

    public static boolean checkIntrusionDetectionHeadlessGatekeeper(Context context) {
        return checkGatekeeperHelper(context, GK_INTRUSION_DETECTION_HEADLESS);
    }

    public static boolean checkInternalAnchorGatekeeper(Context context) {
        return checkGatekeeperHelper(context, GK_INTERNAL_ANCHOR);
    }

    public static boolean checkMrDeskCreationGatekeeper(Context context) {
        return checkGatekeeperHelper(context, GK_MR_DESK_CREATION);
    }

    public static boolean checkSpatialAnchorGatekeeper(Context context) {
        return checkGatekeeperHelper(context, GK_SPATIAL_ANCHOR);
    }

    public static boolean checkLowMemKillGatekeeper(Context context) {
        return checkGatekeeperHelper(context, GK_LOW_MEM_KILL);
    }
}
