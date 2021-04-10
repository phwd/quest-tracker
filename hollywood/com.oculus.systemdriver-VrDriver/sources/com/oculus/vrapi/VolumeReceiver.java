package com.oculus.vrapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

class VolumeReceiver extends BroadcastReceiver {
    private static String STREAM_TYPE = "android.media.EXTRA_VOLUME_STREAM_TYPE";
    private static String STREAM_VALUE = "android.media.EXTRA_VOLUME_STREAM_VALUE";
    private static final String TAG = "VolumeReceiver";
    private static String VOLUME_CHANGED_ACTION = "android.media.VOLUME_CHANGED_ACTION";
    private static IntentFilter filter = null;
    private static VolumeReceiver receiver = null;

    private static native void volumeChanged(int i);

    VolumeReceiver() {
    }

    private static void startReceiver(Context context) {
        synchronized (VolumeReceiver.class) {
            if (filter == null) {
                filter = new IntentFilter();
                filter.addAction(VOLUME_CHANGED_ACTION);
            }
            if (receiver == null) {
                receiver = new VolumeReceiver();
                context.registerReceiver(receiver, filter);
            }
        }
    }

    private static void stopReceiver(Context context) {
        synchronized (VolumeReceiver.class) {
            if (receiver != null) {
                context.unregisterReceiver(receiver);
                receiver = null;
            }
        }
    }

    public void onReceive(Context context, Intent intent) {
        int stream = ((Integer) intent.getExtras().get(STREAM_TYPE)).intValue();
        int volume = ((Integer) intent.getExtras().get(STREAM_VALUE)).intValue();
        if (stream == 3) {
            volumeChanged(volume);
        }
    }
}
