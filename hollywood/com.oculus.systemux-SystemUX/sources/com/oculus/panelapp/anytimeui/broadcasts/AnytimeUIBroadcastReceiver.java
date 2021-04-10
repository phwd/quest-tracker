package com.oculus.panelapp.anytimeui.broadcasts;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.LibraryFilter;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.CaptureStateUtil;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.HmdInactivityUtil;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryLogger;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryStateHelper;
import com.oculus.vrshell.util.SystemUXScreenshotUtil;
import java.util.Locale;
import java.util.Optional;

public class AnytimeUIBroadcastReceiver extends BroadcastReceiver {
    private static final String ACTION_CAPTURE_STATE_UPDATE = "com.oculus.systemactivities.CAPTURE_STATE_UPDATE";
    private static final String ACTION_GET_CAPTURE_STATE = "com.oculus.horizon.GET_CAPTURE_STATE_ACTION";
    private static final String ACTION_LOCAL_STREAM_STATE_UPDATE = "com.oculus.systemactivities.LOCAL_STREAM_STATE_UPDATE";
    private static final String ACTION_MOUNT_STATE_CHANGED = "com.oculus.intent.action.MOUNT_STATE_CHANGED";
    private static final String ACTION_UPDATE_APP_SCREENSHOT = "com.oculus.vrshell.intent.action.UPDATE_APP_SCREENSHOT";
    private static final String HORIZON_ABUSE_RECORDING_ELAPSED_TIME = "abuse_recording_elapsed_time";
    private static final String HORIZON_ABUSE_RECORDING_UUID = "abuse_recording_uuid";
    private static final String HORIZON_CAPTURE_MODE_KEY = "capture_mode";
    private static final String HORIZON_COMPONENT_NAME = "com.oculus.horizon.service_media.OVRMediaService";
    private static final String HORIZON_IS_CAPTURE_RUNNING_KEY = "is_capture_running";
    private static final String HORIZON_MESSAGE_TYPE_KEY = "message_type";
    private static final String TAG = LoggingUtil.tag(AnytimeUIBroadcastReceiver.class);

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action == null) {
            Log.w(TAG, "Received an intent with no action.");
            return;
        }
        Log.d(TAG, "action: " + action);
        char c = 65535;
        switch (action.hashCode()) {
            case -1542846910:
                if (action.equals(ACTION_MOUNT_STATE_CHANGED)) {
                    c = 2;
                    break;
                }
                break;
            case -570800286:
                if (action.equals(ACTION_UPDATE_APP_SCREENSHOT)) {
                    c = 1;
                    break;
                }
                break;
            case -218510198:
                if (action.equals(ACTION_CAPTURE_STATE_UPDATE)) {
                    c = 0;
                    break;
                }
                break;
            case 1627679816:
                if (action.equals(ACTION_LOCAL_STREAM_STATE_UPDATE)) {
                    c = 3;
                    break;
                }
                break;
        }
        if (c == 0) {
            handleCaptureStateUpdate(context, intent);
        } else if (c == 1) {
            handleUpdateAppScreenshot(context, intent);
        } else if (c == 2) {
            handleMountStateChanged(context, intent);
        } else if (c != 3) {
            Log.w(TAG, "Received unknown action: " + action);
        } else {
            handleLocalStreamStateUpdate(context, intent);
        }
    }

    private static void handleCaptureStateUpdate(Context context, Intent intent) {
        Optional optional;
        Bundle extras = intent.getExtras();
        if (extras != null) {
            boolean z = extras.getInt(HORIZON_CAPTURE_MODE_KEY) == 0;
            boolean z2 = extras.getBoolean(HORIZON_IS_CAPTURE_RUNNING_KEY);
            boolean z3 = extras.getInt(HORIZON_CAPTURE_MODE_KEY) == 1;
            String string = extras.containsKey(HORIZON_ABUSE_RECORDING_UUID) ? extras.getString(HORIZON_ABUSE_RECORDING_UUID) : null;
            if (extras.containsKey(HORIZON_ABUSE_RECORDING_ELAPSED_TIME)) {
                optional = Optional.of(Long.valueOf(extras.getLong(HORIZON_ABUSE_RECORDING_ELAPSED_TIME)));
            } else {
                optional = Optional.empty();
            }
            Log.i(TAG, String.format(Locale.ROOT, "handleCaptureStateUpdate: captureToDisk %b, captureEnabled %b, isCapturingAbuseReport %b, abuseRecordingUUID %s", Boolean.valueOf(z), Boolean.valueOf(z2), Boolean.valueOf(z3), string));
            CaptureStateUtil.onCaptureStateChanged(z, z2, z3, string, optional);
        }
    }

    private static void handleMountStateChanged(Context context, Intent intent) {
        if (intent.getBooleanExtra("state", true)) {
            long currentTimeMillis = System.currentTimeMillis() - LibraryStateHelper.loadHMDOffTimestamp(context);
            if (LibraryStateHelper.loadLastFilterState(context) != LibraryFilter.UNKNOWN_SOURCES) {
                if (currentTimeMillis > LibraryStateHelper.INACTIVITY_LIBRARY_STATE_RESET_THRESHOLD) {
                    LibraryStateHelper.saveDefaultDropdownsState(context);
                    LibraryStateHelper.saveDefaultScrollPositionState(context);
                    new LibraryLogger(context, null).logLibraryStateReset(LibraryLogger.STATE_RESET_REASON_HMD_INACTIVITY);
                }
                HmdInactivityUtil.onHmdInactivityEnd(currentTimeMillis);
                return;
            }
            return;
        }
        LibraryStateHelper.saveHMDOffTimestamp(context, System.currentTimeMillis());
    }

    private static void handleUpdateAppScreenshot(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null && extras.containsKey("intent_pkg") && extras.containsKey("jpg_data")) {
            SystemUXScreenshotUtil.setAppScreenshot(extras.getString("intent_pkg"), extras.getByteArray("jpg_data"));
        }
    }

    private static void handleLocalStreamStateUpdate(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null || !extras.containsKey(HORIZON_IS_CAPTURE_RUNNING_KEY)) {
            Log.w(TAG, "Malformed extras payload in broadcast com.oculus.systemactivities.LOCAL_STREAM_STATE_UPDATE");
            return;
        }
        boolean z = true;
        if (extras.getInt(HORIZON_IS_CAPTURE_RUNNING_KEY) != 1) {
            z = false;
        }
        CaptureStateUtil.onLocalStreamStateUpdate(z);
    }

    public static void requestCaptureState(Context context) {
        Log.d(TAG, "requestCaptureState being sent");
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.oculus.horizon", HORIZON_COMPONENT_NAME));
        intent.putExtra(HORIZON_MESSAGE_TYPE_KEY, ACTION_GET_CAPTURE_STATE);
        context.startService(intent);
    }
}
