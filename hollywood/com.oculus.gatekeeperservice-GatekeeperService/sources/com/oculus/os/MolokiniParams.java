package com.oculus.os;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import oculus.internal.ExternalBattery;

public final class MolokiniParams {
    private static final String TAG = "MolokiniParams";
    private ExternalBattery mBattery = new ExternalBattery();
    private Context mContext;

    public enum ChargingStatus {
        UNKNOWN,
        CHARGING,
        NOT_CHARGING
    }

    public MolokiniParams(Context context) {
        this.mContext = context;
    }

    public boolean isChargerPlugged() {
        return this.mBattery.isChargerPlugged();
    }

    public int getBatteryLevel() {
        if (getConnectionStatus()) {
            return this.mBattery.getBatteryLevel();
        }
        Log.d(TAG, "Molokini not connected, cannot get battery level");
        return -1;
    }

    public int getCombinedBatteryLevel() {
        Intent intent = this.mContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        int internalBatteryLevel = (int) (100.0f * (((float) intent.getIntExtra("level", -1)) / ((float) intent.getIntExtra("scale", -1))));
        if (internalBatteryLevel <= 0) {
            Log.e(TAG, "Failed to get HMD battery level");
            return -1;
        }
        int externalBatteryLevel = getBatteryLevel();
        if (externalBatteryLevel == -1) {
            return internalBatteryLevel;
        }
        return (internalBatteryLevel + externalBatteryLevel) / 2;
    }

    public ChargingStatus getChargingStatus() {
        if (!getConnectionStatus()) {
            Log.d(TAG, "Molokini not connected, cannot get charging status");
            return ChargingStatus.UNKNOWN;
        }
        int i = AnonymousClass1.$SwitchMap$oculus$internal$ExternalBattery$ChargingStatus[this.mBattery.getChargingStatus().ordinal()];
        if (i == 1) {
            return ChargingStatus.CHARGING;
        }
        if (i != 2) {
            return ChargingStatus.UNKNOWN;
        }
        return ChargingStatus.NOT_CHARGING;
    }

    /* renamed from: com.oculus.os.MolokiniParams$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$oculus$internal$ExternalBattery$ChargingStatus = new int[ExternalBattery.ChargingStatus.values().length];

        static {
            try {
                $SwitchMap$oculus$internal$ExternalBattery$ChargingStatus[ExternalBattery.ChargingStatus.CHARGING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$oculus$internal$ExternalBattery$ChargingStatus[ExternalBattery.ChargingStatus.NOT_CHARGING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$oculus$internal$ExternalBattery$ChargingStatus[ExternalBattery.ChargingStatus.UNKNOWN.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public boolean getConnectionStatus() {
        return this.mBattery.getConnectionStatus();
    }
}
