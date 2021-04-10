package oculus.internal;

import android.os.IHwBinder;
import android.os.RemoteException;
import android.util.Log;
import java.util.NoSuchElementException;
import vendor.oculus.hardware.battery.V1_0.IBattery;

public class ExternalBattery {
    private static final boolean DEBUG = false;
    public static final String TAG = "External Battery";
    private final BattService mBattService = new BattService();

    public enum ChargingStatus {
        UNKNOWN,
        CHARGING,
        NOT_CHARGING
    }

    private class BattService implements IHwBinder.DeathRecipient {
        public IBattery mService;

        public BattService() {
            try {
                this.mService = IBattery.getService();
                if (this.mService != null) {
                    this.mService.asBinder().linkToDeath(this, 0);
                }
            } catch (RemoteException | NoSuchElementException e) {
                Log.d(ExternalBattery.TAG, "Battery Service not available");
            }
        }

        public boolean isConnected() {
            if (this.mService != null) {
                return true;
            }
            return ExternalBattery.DEBUG;
        }

        public synchronized void serviceDied(long cookie) {
            Log.w(ExternalBattery.TAG, "Battery Service died, attempting to reconnect");
            try {
                this.mService = IBattery.getService();
                if (this.mService != null) {
                    this.mService.asBinder().linkToDeath(this, 0);
                }
            } catch (RemoteException | NoSuchElementException e) {
                Log.d(ExternalBattery.TAG, "Battery Service not available");
            }
        }
    }

    public boolean isChargerPlugged() {
        if (!this.mBattService.isConnected()) {
            return DEBUG;
        }
        try {
            return this.mBattService.mService.isChargerPlugged();
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to get charger plugged status");
            return DEBUG;
        }
    }

    public int getBatteryLevel() {
        if (!this.mBattService.isConnected()) {
            return -1;
        }
        try {
            return this.mBattService.mService.getBatteryLevel();
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to get battery level");
            return -1;
        }
    }

    public ChargingStatus getChargingStatus() {
        if (!this.mBattService.isConnected()) {
            return ChargingStatus.UNKNOWN;
        }
        try {
            int status = this.mBattService.mService.getStatus();
            if (status == 2) {
                return ChargingStatus.CHARGING;
            }
            if (status != 3) {
                return ChargingStatus.UNKNOWN;
            }
            return ChargingStatus.NOT_CHARGING;
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to get battery status");
            return ChargingStatus.UNKNOWN;
        }
    }

    public boolean getConnectionStatus() {
        if (!this.mBattService.isConnected()) {
            return DEBUG;
        }
        try {
            int status = this.mBattService.mService.getStatus();
            if (status == 0) {
                Log.e(TAG, "Current Molokini status is unknown");
            }
            if (status != 1) {
                return true;
            }
            return DEBUG;
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to get battery status");
            return DEBUG;
        }
    }
}
