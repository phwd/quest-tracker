package com.oculus.vrapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.util.Log;

class HeadsetReceiver extends BroadcastReceiver {
    private static final boolean DEBUG = false;
    private static final String TAG = "HeadsetReceiver";
    public static IntentFilter filter = null;
    public static HeadsetReceiver receiver = null;

    private static native void stateChanged(int i);

    HeadsetReceiver() {
    }

    private static void startReceiver(Context context) {
        stateChanged(getCurrentHeadsetState(context));
        if (filter == null) {
            filter = new IntentFilter("android.intent.action.HEADSET_PLUG");
        }
        if (receiver == null) {
            receiver = new HeadsetReceiver();
        }
        context.registerReceiver(receiver, filter);
    }

    private static void stopReceiver(Context context) {
        context.unregisterReceiver(receiver);
    }

    private static int getCurrentHeadsetState(Context context) {
        AudioManager manager = (AudioManager) context.getSystemService("audio");
        boolean state = manager != null && manager.isWiredHeadsetOn();
        Log.d(TAG, "getCurrentHeadsetState: " + state);
        if (state) {
            return 1;
        }
        return 0;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.hasExtra("state")) {
            stateChanged(intent.getIntExtra("state", 0));
        }
    }
}
