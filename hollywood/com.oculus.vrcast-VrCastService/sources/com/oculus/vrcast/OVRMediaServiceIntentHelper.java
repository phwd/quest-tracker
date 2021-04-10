package com.oculus.vrcast;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.Surface;
import com.oculus.security.CallerInfoHelper;

public class OVRMediaServiceIntentHelper {
    private static final String CAST_CLASS = "com.oculus.horizon.service_media.OVRMediaService";
    private static final String EXTRA_SURFACE = "surface";
    private static final String MSG_KEY = "message_type";
    private static final String MSG_START_CAPTURE = "com.oculus.horizon.START_CAPTURE_TO_SURFACE";
    private static final String MSG_START_CAST = "com.oculus.horizon.START_CAST_NATIVE_RECEIVER";
    private static final String MSG_STOP_CAPTURE = "com.oculus.horizon.STOP_CAPTURE_TO_SURFACE";
    private static final String MSG_STOP_CAST = "com.oculus.horizon.STOP_CAST_NATIVE_RECEIVER";

    public static void sendStartSurfaceCaptureIntent(Context context, Surface surface, String str) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.oculus.horizon", CAST_CLASS));
        intent.putExtra(MSG_KEY, MSG_START_CAPTURE);
        CallerInfoHelper.attachCallerInfo(intent, context, str + ":sendStartSurfaceCaptureIntent");
        intent.putExtra(EXTRA_SURFACE, surface);
        context.startService(intent);
    }

    public static void sendStopSurfaceCaptureIntent(Context context, String str) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.oculus.horizon", CAST_CLASS));
        CallerInfoHelper.attachCallerInfo(intent, context, str + ":sendStopSurfaceCaptureIntent");
        intent.putExtra(MSG_KEY, MSG_STOP_CAPTURE);
        context.startService(intent);
    }

    public static void sendStartCastMwsIntent(Context context, String str, String str2, int i, int i2, int i3) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.oculus.horizon", CAST_CLASS));
        CallerInfoHelper.attachCallerInfo(intent, context, str + ":sendStartCastMwsIntent");
        intent.putExtra(MSG_KEY, MSG_START_CAST);
        intent.putExtra("endpoint", str2);
        intent.putExtra("video_width", i);
        intent.putExtra("video_height", i2);
        intent.putExtra("video_fps", i3);
        context.startService(intent);
    }

    public static void sendStopCastMwsIntent(Context context, String str) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.oculus.horizon", CAST_CLASS));
        CallerInfoHelper.attachCallerInfo(intent, context, str + ":sendStopCastMwsIntent");
        intent.putExtra(MSG_KEY, MSG_STOP_CAST);
        context.startService(intent);
    }
}
