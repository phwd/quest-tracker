package com.oculus.vrshell.home;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.adobe.xmp.XMPError;

public class NewOverlayToRenderReceiver extends BroadcastReceiver {
    private static final String ABUSE_RECORDING_OVERLAYS_TYPE = "abuse_recording";
    private static final String EMPTY_TYPE = "empty";
    private static final String EXTRA_DISTANCE = "distance";
    private static final String EXTRA_DURATION_MS = "duration_ms";
    private static final String EXTRA_HEIGHT = "height";
    private static final String EXTRA_LAYER_FLAGS = "layer_flags";
    private static final String EXTRA_NEEDS_INPUTS = "needs_inputs";
    private static final String EXTRA_PITCH = "pitch";
    private static final String EXTRA_POSITION = "position";
    private static final int EXTRA_POSITION_FIXED_TO_VIEW = 4;
    private static final String EXTRA_POSITION_USER_YAW = "user_yaw";
    private static final String EXTRA_WIDTH = "width";
    private static final String EXTRA_YAW = "yaw";
    private static final String LIVESTREAMING_OVERLAYS_TYPE = "livestreaming_comments";
    private static String OVERLAYS_TYPE_KEY = "overlays_type";
    private static final String TAG = NewOverlayToRenderReceiver.class.getSimpleName();

    public void onReceive(Context context, Intent incomingIntent) {
        String overlaysType = incomingIntent.getStringExtra(OVERLAYS_TYPE_KEY);
        if (ABUSE_RECORDING_OVERLAYS_TYPE.equals(overlaysType)) {
            startAbuseRecordingOverlay(context, incomingIntent);
        } else if (EMPTY_TYPE.equals(overlaysType)) {
            startEmptyOverlay(context, incomingIntent);
        } else if (LIVESTREAMING_OVERLAYS_TYPE.equals(overlaysType)) {
            startLivestreamingOverlay(context, incomingIntent);
        } else {
            Log.e(TAG, "Unknown overlaysType: " + overlaysType);
        }
    }

    private void startLivestreamingOverlay(Context context, Intent incomingIntent) {
        Intent intent = getDefaultIntent(context, incomingIntent);
        intent.putExtra(EXTRA_YAW, 35.0f);
        context.sendBroadcast(intent);
    }

    private void startAbuseRecordingOverlay(Context context, Intent incomingIntent) {
        boolean shouldHeadlockOverlay = false;
        Intent intent = getDefaultIntent(context, incomingIntent);
        intent.putExtra(EXTRA_PITCH, -20.0f);
        intent.putExtra(EXTRA_YAW, 2.0f);
        intent.putExtra(EXTRA_WIDTH, 322);
        intent.putExtra(EXTRA_HEIGHT, XMPError.BADRDF);
        if (!intent.getBooleanExtra(EXTRA_NEEDS_INPUTS, false)) {
            shouldHeadlockOverlay = true;
        }
        if (shouldHeadlockOverlay) {
            intent.putExtra(EXTRA_LAYER_FLAGS, 4);
        }
        context.sendBroadcast(intent);
    }

    private void startEmptyOverlay(Context context, Intent incomingIntent) {
        context.sendBroadcast(getDefaultIntent(context, incomingIntent));
    }

    private Intent getDefaultIntent(Context context, Intent incomingIntent) {
        Intent intent = new Intent("com.oculus.vrdriver.new_notification_to_show");
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, 0);
        intent.putExtras(incomingIntent.getExtras());
        intent.putExtra("_ci_", pendingIntent);
        intent.putExtra(EXTRA_DURATION_MS, -1);
        intent.putExtra(EXTRA_YAW, 0.0f);
        intent.putExtra(EXTRA_PITCH, -45.0f);
        intent.putExtra(EXTRA_DISTANCE, 3.0f);
        intent.putExtra(EXTRA_WIDTH, 420);
        intent.putExtra(EXTRA_HEIGHT, 252);
        return intent;
    }
}
