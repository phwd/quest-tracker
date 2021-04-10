package oculus.internal;

import android.hardware.thermal.V1_0.ThermalStatus;
import android.hardware.thermal.V2_0.CoolingDevice;
import android.hardware.thermal.V2_0.IThermal;
import android.os.IHwBinder;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import oculus.internal.ThermalFanStatusInterface;
import oculus.internal.ThermalFanStatusProvider;

public class ThermalFanStatusProvider implements ThermalFanStatusInterface {
    private static final String TAG = "ThermalFanStatusProvider";
    private static final int THERMAL_HAL_DEATH_COOKIE = 5612;
    private ScheduledExecutorService fanPollingExecutor = Executors.newSingleThreadScheduledExecutor();
    private ThermalFanStatusInterface.ThermalFanStatusUpdateCallback mFanStatusCallback;
    private IThermal mThermalHal20 = null;
    private Runnable runnable = new Runnable() {
        /* class oculus.internal.ThermalFanStatusProvider.AnonymousClass1 */

        public void run() {
            try {
                ThermalFanStatusProvider.this.mThermalHal20.getCurrentCoolingDevices(true, 0, new IThermal.getCurrentCoolingDevicesCallback() {
                    /* class oculus.internal.$$Lambda$ThermalFanStatusProvider$1$83iAOlGYiZuoVPxbq_mtZqfFf5o */

                    public final void onValues(ThermalStatus thermalStatus, ArrayList arrayList) {
                        ThermalFanStatusProvider.AnonymousClass1.this.lambda$run$0$ThermalFanStatusProvider$1(thermalStatus, arrayList);
                    }
                });
            } catch (RemoteException e) {
                Log.e(ThermalFanStatusProvider.TAG, "Couldn't getCurrentCoolingDevices from Thermal-Hal:", e);
                ThermalFanStatusProvider.this.fanPollingExecutor.shutdown();
            }
        }

        public /* synthetic */ void lambda$run$0$ThermalFanStatusProvider$1(ThermalStatus status, ArrayList coolingDevices) {
            if (status.code == 0) {
                Iterator it = coolingDevices.iterator();
                while (it.hasNext()) {
                    ThermalFanStatusProvider.this.thermalEngineFanHandle(((CoolingDevice) it.next()).value < 0);
                }
                return;
            }
            Log.e(ThermalFanStatusProvider.TAG, "Couldn't get cooling device because of HAL error: " + status.debugMessage);
        }
    };

    public ThermalFanStatusProvider() {
        connectToThermalHal();
        this.fanPollingExecutor.scheduleAtFixedRate(this.runnable, 0, 5, TimeUnit.SECONDS);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void thermalEngineFanHandle(boolean malfunction) {
        ThermalFanStatusInterface.ThermalFanStatusUpdateCallback thermalFanStatusUpdateCallback = this.mFanStatusCallback;
        if (thermalFanStatusUpdateCallback != null) {
            thermalFanStatusUpdateCallback.fanStatusUpdate(malfunction);
        }
    }

    /* access modifiers changed from: package-private */
    public final class DeathRecipient implements IHwBinder.DeathRecipient {
        DeathRecipient() {
        }

        public void serviceDied(long cookie) {
            if (cookie == 5612) {
                Log.e(ThermalFanStatusProvider.TAG, "Thermal HAL service died cookie: " + cookie);
                ThermalFanStatusProvider.this.connectToThermalHal();
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void connectToThermalHal() {
        try {
            this.mThermalHal20 = IThermal.getService();
            this.mThermalHal20.linkToDeath(new DeathRecipient(), 5612);
        } catch (RemoteException | NoSuchElementException e) {
            Log.e(TAG, "Thermal HAL 2.0 service not connected");
            this.mThermalHal20 = null;
        }
    }

    @Override // oculus.internal.ThermalFanStatusInterface
    public void registerCallback(ThermalFanStatusInterface.ThermalFanStatusUpdateCallback callback) {
        this.mFanStatusCallback = callback;
    }

    @Override // oculus.internal.ThermalFanStatusInterface
    public void onDestroy() {
        this.fanPollingExecutor.shutdown();
    }
}
