package com.oculus.panelapp.debug;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.oculus.vrcast.VrCastController;
import java.util.List;

public class VrCastManager implements VrCastController.VrShellCastCallback {
    private static final boolean DEBUG = true;
    private static final String TAG = "VrCastManager";
    private final VrCastController mCastController;
    private VrCastConnectView mConnectView;
    private VrCastDevicesAdapter mDeviceAdapter;
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    public VrCastManager(Context context) {
        this.mCastController = new VrCastController(context, this);
    }

    public void setDeviceAdapter(VrCastDevicesAdapter vrCastDevicesAdapter) {
        this.mDeviceAdapter = vrCastDevicesAdapter;
    }

    public void onDevicesFound(final List<VrCastController.VrShellCastDevice> list) {
        String str = TAG;
        Log.d(str, "onDevicesFound() called with: devices = " + list.toString());
        this.mHandler.post(new Runnable() {
            /* class com.oculus.panelapp.debug.VrCastManager.AnonymousClass1 */

            public void run() {
                VrCastManager.this.mDeviceAdapter.onDevicesFound(list);
            }
        });
    }

    public void onDeviceStateUpdated(final VrCastController.VrShellCastDevice vrShellCastDevice) {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("onDeviceStateUpdated() called with: device = [");
        sb.append(vrShellCastDevice != null ? vrShellCastDevice.toString() : null);
        sb.append("]");
        Log.d(str, sb.toString());
        this.mHandler.post(new Runnable() {
            /* class com.oculus.panelapp.debug.VrCastManager.AnonymousClass2 */

            public void run() {
                VrCastManager.this.mConnectView.onDeviceStateUpdated(vrShellCastDevice);
            }
        });
    }

    public void onBindServiceSucceeded() {
        Log.d(TAG, "onBindServiceSucceeded() called");
    }

    public void onBindServiceFailed() {
        Log.d(TAG, "onBindServiceFailed() called");
    }

    public void onRemoteServiceDied() {
        Log.d(TAG, "onRemoteServiceDied() called");
    }

    public void unbindVrCastService() {
        this.mCastController.unbindVrCastService();
    }

    public void startDiscovery() {
        this.mCastController.startDiscovery();
    }

    public void stopDiscovery() {
        this.mCastController.stopDiscovery();
    }

    public void startCast(VrCastController.VrShellCastDevice vrShellCastDevice) {
        this.mCastController.startCast(vrShellCastDevice.id);
    }

    public void stopCast(VrCastController.VrShellCastDevice vrShellCastDevice) {
        this.mCastController.stopCast(vrShellCastDevice.id);
    }

    public void setConnectView(VrCastConnectView vrCastConnectView) {
        this.mConnectView = vrCastConnectView;
    }
}
