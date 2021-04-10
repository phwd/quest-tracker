package com.oculus.platform.receiver;

import X.AnonymousClass006;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;
import android.view.Surface;
import com.oculus.platform.OVR;
import com.oculus.platform.OVRServiceManager;

public class OVRPlatformBroadcastReceiver extends BroadcastReceiver {
    public static final String ASSET_FILE_DOWNLOAD_UPDATE = "com.oculus.asset_file_download_update";
    public static final String DUMP_THREADS = "com.oculus.dump_threads";
    public static final String GENERIC_DEEPLINK_RESULT = "com.oculus.generic_deeplink_result";
    public static final String HORIZON_PERMISSION_STATUS_CHANGED = "com.oculus.horizon_permission_change";
    public static final String INVITE_ACTION = "com.oculus.invite_accepted";
    public static final String LAUNCH_INTENT_CHANGED = "com.oculus.launch_intent_changed";
    public static final String LIVESTREAMING_STATUS_CHANGE = "com.oculus.livestreaming_status_change";
    public static final String SHARE_TO_FACEBOOK_RESULT = "com.oculus.share_to_facebook_result";
    public static final String SYSTEM_VOIP_STATE_ACTION = "com.oculus.system_voip_state";
    public static final String TAG = "OVRPlatformBroadcastReceiver";
    public static final String VRCAMERA_SURFACE_CHANGE = "com.oculus.vrcamera_surface_change";

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (INVITE_ACTION.equals(action)) {
            if (intent.hasExtra("ovr_room_id")) {
                OVR.nativeJoinRoomNotification(intent.getStringExtra("ovr_room_id"));
            }
        } else if ("com.oculus.system_voip_state".equals(action)) {
            if (intent.hasExtra("system_voip_state")) {
                OVR.nativeSystemVoipStateNotification(intent.getStringExtra("system_voip_state"));
            }
        } else if ("com.oculus.livestreaming_status_change".equals(action)) {
            OVR.nativeLivestreamingStatusUpdate(OVRServiceManager.getJsonStringFromBundle(intent.getExtras()));
        } else if (HORIZON_PERMISSION_STATUS_CHANGED.equals(action)) {
            OVR.nativeHorizonPermissionStatusUpdate(intent.getLongExtra("platform_request_id", 0), OVR.getPermissionHelper(intent.getStringExtra("permission_status")).toString());
        } else if (GENERIC_DEEPLINK_RESULT.equals(action)) {
            int intExtra = intent.getIntExtra("platform_message_type", 0);
            long longExtra = intent.getLongExtra("platform_request_id", 0);
            boolean booleanExtra = intent.getBooleanExtra("is_successful", false);
            String stringExtra = intent.getStringExtra(OVR.GENERIC_RESULT_KEY);
            if (booleanExtra) {
                long j = (long) intExtra;
                if (stringExtra == null) {
                    stringExtra = "";
                }
                OVR.nativeGenericDeeplinkResult(j, longExtra, stringExtra);
                return;
            }
            OVR.nativeGenericDeeplinkErrorResult((long) intExtra, longExtra, 0, "Unexpected error");
        } else if (SHARE_TO_FACEBOOK_RESULT.equals(action)) {
            OVR.nativeShareToFacebookResultFromShell(intent.getLongExtra("platform_request_id", 0), intent.getBooleanExtra(OVR.SHARE_SUCCESS_KEY, false), intent.getStringExtra(OVR.SHARE_RESULT_KEY));
        } else if (DUMP_THREADS.equals(action)) {
            Process.sendSignal(Process.myPid(), 3);
        } else if ("com.oculus.asset_file_download_update".equals(action)) {
            OVR.nativeAssetFileDownloadUpdate(OVRServiceManager.getJsonStringFromBundle(intent.getExtras()));
        } else if (LAUNCH_INTENT_CHANGED.equals(action)) {
            if (intent.hasExtra("intent_cmd")) {
                OVR.setLatestIntentIfNotNull(intent);
                OVR.nativeOnLaunchIntentChanged();
            }
        } else if ("com.oculus.vrcamera_surface_change".equals(action)) {
            OVR.mLatestVrcameraSurface = (Surface) intent.getParcelableExtra("surface");
            OVR.nativeVrcameraSurfaceUpdate(OVRServiceManager.getJsonStringFromBundle(intent.getExtras()));
        } else {
            Log.e(TAG, AnonymousClass006.A05("Error logging unhandled broadcast action:", action));
        }
    }
}
