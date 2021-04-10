package com.oculus.vrapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.util.Log;

class BatteryReceiver extends BroadcastReceiver {
    private static final boolean DEBUG = false;
    private static final String TAG = "BatteryReceiver";
    public static IntentFilter filter = null;
    private static BatteryManager mBatteryManager = null;
    public static BatteryReceiver receiver = null;
    private int batteryCurrentNow = 0;
    private int batteryLevel = 0;
    private int batteryStatus = 0;
    private int batteryTemperature = 0;

    private static native void dispatchEvent(int i, int i2, int i3, int i4);

    BatteryReceiver() {
    }

    private void processIntent(Intent intent, Context context) {
        if (intent.getBooleanExtra("present", false)) {
            int status = intent.getIntExtra("status", 0);
            int rawlevel = intent.getIntExtra("level", -1);
            int scale = intent.getIntExtra("scale", -1);
            int temperature = intent.getIntExtra("temperature", 0);
            int level = 0;
            if (rawlevel >= 0 && scale > 0) {
                level = (rawlevel * 100) / scale;
            }
            int currentNow = 0;
            BatteryManager batteryManager = mBatteryManager;
            if (batteryManager != null) {
                currentNow = batteryManager.getIntProperty(2);
            }
            if (status != this.batteryStatus || level != this.batteryLevel || temperature != this.batteryTemperature || currentNow != this.batteryCurrentNow) {
                this.batteryStatus = status;
                this.batteryLevel = level;
                this.batteryTemperature = temperature;
                this.batteryCurrentNow = currentNow;
                dispatchEvent(status, level, temperature, currentNow);
            }
        }
    }

    private static void startReceiver(Context context) {
        Log.d(TAG, "Registering battery receiver");
        if (filter == null) {
            filter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
        }
        if (receiver == null) {
            receiver = new BatteryReceiver();
        }
        if (mBatteryManager == null) {
            mBatteryManager = (BatteryManager) context.getSystemService("batterymanager");
        }
        context.registerReceiver(receiver, filter);
    }

    private static void stopReceiver(Context context) {
        Log.d(TAG, "Unregistering battery receiver");
        context.unregisterReceiver(receiver);
        mBatteryManager = null;
    }

    public void onReceive(Context context, Intent intent) {
        processIntent(intent, context);
    }
}
