package com.oculus.notification_proxy;

import android.os.RemoteException;
import android.util.Log;
import java.util.HashSet;
import java.util.Set;
import oculus.internal.BinderClient;
import oculus.internal.power.IVrPowerManager;
import oculus.internal.power.IVrPowerManagerClient;

public class VrPowerManagerClient extends IVrPowerManagerClient.Stub {
    private static final String TAG = "VrPowerManagerClient";
    private static final String VR_POWER_SERVICE = "vrpowermanager";
    private BinderClient<IVrPowerManager> mBinderClient = new BinderClient<IVrPowerManager>(VR_POWER_SERVICE, $$Lambda$VrPowerManagerClient$VudFZFrKlcrr04OAzU2LW7D33xQ.INSTANCE) {
        /* class com.oculus.notification_proxy.VrPowerManagerClient.AnonymousClass1 */

        /* access modifiers changed from: protected */
        public void onServiceConnected(IVrPowerManager iVrPowerManager) {
            try {
                iVrPowerManager.registerClient(VrPowerManagerClient.this);
            } catch (RemoteException e) {
                Log.e(VrPowerManagerClient.TAG, "Failed to register with VrPowerManager", e);
            }
        }
    };
    private Set<Callback> mCallbacks = new HashSet();
    Boolean mIsHeadsetMounted = null;

    public interface Callback {
        void onHeadsetMounted();

        void onHeadsetUnmounted();
    }

    public void addCallback(Callback callback) {
        this.mCallbacks.add(callback);
    }

    public void removeCallback(Callback callback) {
        this.mCallbacks.remove(callback);
    }

    public void onStateChange(int i) {
        if (i == 1) {
            String str = TAG;
            Log.d(str, "Headset state: " + i);
            Boolean bool = this.mIsHeadsetMounted;
            if (bool == null || !bool.booleanValue()) {
                this.mIsHeadsetMounted = true;
                for (Callback callback : this.mCallbacks) {
                    callback.onHeadsetMounted();
                }
            }
        } else if (i == 2 || i == 3) {
            String str2 = TAG;
            Log.d(str2, "Headset state: " + i);
            Boolean bool2 = this.mIsHeadsetMounted;
            if (bool2 == null || bool2.booleanValue()) {
                this.mIsHeadsetMounted = false;
                for (Callback callback2 : this.mCallbacks) {
                    callback2.onHeadsetUnmounted();
                }
            }
        }
    }
}
