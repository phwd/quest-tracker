package com.oculus.panelapp.dogfood;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.ResultReceiver;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;

public final class OTAUpdateHelper {
    private static final String ACTION_CHECK_OTA_AVAILABILITY = "check_ota_availability";
    private static final String ACTION_EXT_CHECK_UPDATES = "ext_check_updates";
    public static final String ACTION_UPDATER_STATE_NOTIFICATION = "com.oculus.updater.STATE_NOTIFICATION";
    private static final String CHECK_OTA_AVAILABILITY_EXTRA_FULL_UPDATE = "full_update";
    private static final String CHECK_OTA_AVAILABILITY_EXTRA_RESULT_RECEIVER = "result_receiver";
    private static final String CHECK_OTA_AVAILABILITY_RESULT_EXTRA_ARE_UPDATES_AVAILABLE = "ota_are_updates_available";
    private static final ComponentName OTA_COMPONENT_NAME = new ComponentName("com.oculus.updater", OTA_SERVICE_NAME);
    private static final String OTA_PACKAGE_NAME = "com.oculus.updater";
    private static final String OTA_SERVICE_NAME = "com.oculus.updater.core.os.OSUpdateService";
    public static final String STATE_NOTIFICATION_CHECKING_FOR_UDPATES = "checking_for_updates";
    public static final String STATE_NOTIFICATION_EXTRA_PROGRESS = "progress";
    public static final String STATE_NOTIFICATION_EXTRA_STATE = "state";
    public static final String STATE_NOTIFICATION_NO_UPDATES_AVAILABLE = "no_updates_available";
    public static final String STATE_NOTIFICATION_WAITING_FOR_REBOOT = "waiting_for_reboot";
    private static String TAG = LoggingUtil.tag(OTAUpdateHelper.class);

    @FunctionalInterface
    public interface OTACheckResult {
        void onOtaResult(int i, boolean z, Bundle bundle);
    }

    public static boolean tryOTAUpdate(Context context, boolean z) {
        Intent intent = new Intent(ACTION_EXT_CHECK_UPDATES);
        intent.setComponent(OTA_COMPONENT_NAME);
        try {
            context.startService(intent);
            Log.d(TAG, "OTA Update Service launched");
            return true;
        } catch (SecurityException e) {
            Log.e(TAG, "Unable to launch OTA service. Send 'cancel' response.", e);
            return false;
        }
    }

    public static void tryOTACheck(Context context, boolean z, final OTACheckResult oTACheckResult) {
        AnonymousClass1 r0 = new ResultReceiver(null) {
            /* class com.oculus.panelapp.dogfood.OTAUpdateHelper.AnonymousClass1 */

            /* access modifiers changed from: protected */
            public void onReceiveResult(int i, Bundle bundle) {
                oTACheckResult.onOtaResult(i, bundle != null ? bundle.getBoolean(OTAUpdateHelper.CHECK_OTA_AVAILABILITY_RESULT_EXTRA_ARE_UPDATES_AVAILABLE) : false, bundle);
            }
        };
        Parcel obtain = Parcel.obtain();
        r0.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        obtain.recycle();
        Intent intent = new Intent(ACTION_CHECK_OTA_AVAILABILITY);
        intent.setComponent(OTA_COMPONENT_NAME);
        intent.putExtra("result_receiver", (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(obtain));
        intent.putExtra(CHECK_OTA_AVAILABILITY_EXTRA_FULL_UPDATE, z);
        try {
            context.startService(intent);
        } catch (SecurityException e) {
            oTACheckResult.onOtaResult(-1, false, new Bundle());
            Log.d(TAG, "Security exception, unable to launch OTA service.", e);
        }
    }
}
