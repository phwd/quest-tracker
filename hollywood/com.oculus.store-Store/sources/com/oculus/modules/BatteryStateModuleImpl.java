package com.oculus.modules;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.oculus.modules.codegen.BatteryStateModule;
import org.json.JSONObject;

public class BatteryStateModuleImpl extends BatteryStateModule {
    private static final String TAG = BatteryStateModule.class.getSimpleName();
    private JSONObject batteryDataObject = null;
    private int mBatteryLevel = -1;
    private int mBatteryStatus = -1;
    private int mPlugged = -1;

    public BatteryStateModuleImpl(Context context) {
        super(context);
    }

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public IntentFilter getIntentFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.BATTERY_CHANGED");
        return filter;
    }

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public void processIntent(Intent intent, boolean forceNativeUpdate) {
        Log.d(TAG, "processIntent()");
        if (intent.getAction() == "android.intent.action.BATTERY_CHANGED" && intent.getBooleanExtra("present", false)) {
            Log.d(TAG, "isPresent");
            int status = intent.getIntExtra("status", 0);
            int rawlevel = intent.getIntExtra("level", -1);
            int scale = intent.getIntExtra("scale", -1);
            int plugged = intent.getIntExtra("plugged", -1);
            int level = 0;
            if (rawlevel >= 0 && scale > 0) {
                level = (rawlevel * 100) / scale;
            }
            if (forceNativeUpdate || level != this.mBatteryLevel || status != this.mBatteryStatus || plugged != this.mPlugged) {
                this.mBatteryLevel = level;
                this.mBatteryStatus = status;
                this.mPlugged = plugged;
                BatteryStateModule.BatteryUpdate update = new BatteryStateModule.BatteryUpdate();
                update.level = (double) level;
                update.status = (double) status;
                update.plugged = (double) plugged;
                emitOnBatteryUpdated(update);
            }
        }
    }
}
