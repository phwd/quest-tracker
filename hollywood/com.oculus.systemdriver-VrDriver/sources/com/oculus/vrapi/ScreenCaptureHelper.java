package com.oculus.vrapi;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaActionSound;
import android.util.Log;

class ScreenCaptureHelper {
    private static final String TAG = "ScreenCaptureHelper";
    private static MediaActionSound sMediaActionSound = null;

    ScreenCaptureHelper() {
    }

    public static void PlayShutterSound(Context context) {
        if (sMediaActionSound == null) {
            sMediaActionSound = new MediaActionSound();
        }
        sMediaActionSound.play(0);
    }

    private static Intent getMediaServiceIntent() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.oculus.horizon", "com.oculus.horizon.service_media.OVRMediaService"));
        return intent;
    }

    public static void advertiseStreamablePackage(Context cachedContext) {
        if (cachedContext != null) {
            Intent intent = getMediaServiceIntent();
            intent.putExtra("package_name", cachedContext.getApplicationContext().getPackageName());
            intent.putExtra("message_type", "set_package_name");
            cachedContext.startService(intent);
            Log.i(TAG, "Advertising " + cachedContext.getApplicationContext().getPackageName() + " as streamable");
        }
    }
}
