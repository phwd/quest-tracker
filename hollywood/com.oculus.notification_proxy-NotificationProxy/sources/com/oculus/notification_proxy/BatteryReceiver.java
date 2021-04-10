package com.oculus.notification_proxy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Handler;
import android.os.Message;
import com.oculus.notification_proxy.VrPowerManagerClient;
import java.util.HashMap;
import java.util.Map;

public class BatteryReceiver extends BroadcastReceiver implements VrPowerManagerClient.Callback {
    static final int BATTERY_CRITICAL_5 = 5;
    static final int BATTERY_LOW_15 = 15;
    private static final Map<Integer, Boolean> sCheckpoints = new HashMap<Integer, Boolean>() {
        /* class com.oculus.notification_proxy.BatteryReceiver.AnonymousClass1 */

        {
            put(Integer.valueOf((int) BatteryReceiver.BATTERY_CRITICAL_5), true);
            put(Integer.valueOf((int) BatteryReceiver.BATTERY_LOW_15), true);
        }
    };
    private BatteryManager mBatteryManager;
    private final Context mContext;
    private int mCurrentBatteryLevel = 100;
    private Handler mHandler = new Handler() {
        /* class com.oculus.notification_proxy.BatteryReceiver.AnonymousClass2 */

        public void handleMessage(Message message) {
            if (!hasMessages(message.what)) {
                int i = message.what;
                if (i == BatteryReceiver.BATTERY_CRITICAL_5) {
                    BatteryReceiver.this.showBatteryCriticalWarning();
                } else if (i == BatteryReceiver.BATTERY_LOW_15) {
                    BatteryReceiver.this.showBatteryLowWarning();
                }
            }
        }
    };
    private boolean mIsCharging = false;

    @Override // com.oculus.notification_proxy.VrPowerManagerClient.Callback
    public void onHeadsetUnmounted() {
    }

    public BatteryReceiver(Context context) {
        this.mContext = context;
        this.mBatteryManager = new BatteryManager();
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getAction() == "android.intent.action.BATTERY_CHANGED") {
            this.mCurrentBatteryLevel = intent.getIntExtra("level", 100);
            int intExtra = intent.getIntExtra("status", -1);
            this.mIsCharging = (intExtra == 2 || intExtra == BATTERY_CRITICAL_5) && this.mBatteryManager.isCharging();
            if (!sCheckpoints.containsKey(Integer.valueOf(this.mCurrentBatteryLevel))) {
                return;
            }
            if (this.mIsCharging) {
                for (Integer num : sCheckpoints.keySet()) {
                    sCheckpoints.put(num, Boolean.valueOf(this.mCurrentBatteryLevel >= num.intValue()));
                }
            } else if (sCheckpoints.get(Integer.valueOf(this.mCurrentBatteryLevel)).booleanValue()) {
                Message obtain = Message.obtain();
                obtain.what = this.mCurrentBatteryLevel;
                this.mHandler.sendMessage(obtain);
                sCheckpoints.put(Integer.valueOf(this.mCurrentBatteryLevel), false);
            }
        }
    }

    @Override // com.oculus.notification_proxy.VrPowerManagerClient.Callback
    public void onHeadsetMounted() {
        if (!this.mIsCharging) {
            int i = this.mCurrentBatteryLevel;
            int i2 = BATTERY_LOW_15;
            if (i <= BATTERY_CRITICAL_5 || i <= BATTERY_LOW_15) {
                Message obtain = Message.obtain();
                if (this.mCurrentBatteryLevel <= BATTERY_CRITICAL_5) {
                    i2 = BATTERY_CRITICAL_5;
                }
                obtain.what = i2;
                this.mHandler.sendMessage(obtain);
            }
        }
    }

    public void register() {
        this.mContext.registerReceiver(this, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void showBatteryLowWarning() {
        NotificationBuildingHelper.notify(this.mContext, R.string.battery_low_title, R.string.battery_low_text, NotificationIds.BATTERY_LOW);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void showBatteryCriticalWarning() {
        NotificationBuildingHelper.notify(this.mContext, R.string.battery_critical_title, R.string.battery_critical_text, NotificationIds.BATTERY_CRITICAL);
    }
}
