package com.oculus.panelapp.anytimeui.v2.tablet.settings.modules;

import android.util.Log;
import android.util.Pair;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.os.Controllers;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class ControllerStateModule {
    public static final String PRIMARY_DEVICE_TYPE = "primary";
    public static final String SECONDARY_DEVICE_TYPE = "secondary";
    private static final String TAG = LoggingUtil.tag(ControllerStateModule.class);
    private Controllers mControllers = new Controllers(this.mObserver);
    private Observer mObserver = new Observer();
    private final Map<ControllerStateUpdateListener, WeakReference<ControllerStateUpdateListener>> mUpdateListener = new HashMap();

    @FunctionalInterface
    public interface ControllerStateUpdateListener {
        void onUpdate(String str, String str2);
    }

    private class Observer implements Controllers.ControllerStatusObserver {
        private Observer() {
        }

        @Override // com.oculus.os.Controllers.ControllerStatusObserver
        public void updateControllerStatus(int i, Controllers.ControllerInfo controllerInfo) {
            ControllerStateModule.this.notifyUpdateListener(i == 0 ? "primary" : "secondary", controllerInfo.status.toString());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void notifyUpdateListener(String str, String str2) {
        for (WeakReference<ControllerStateUpdateListener> weakReference : this.mUpdateListener.values()) {
            weakReference.get().onUpdate(str, str2);
        }
    }

    public void addControllerStatusListener(ControllerStateUpdateListener controllerStateUpdateListener) {
        this.mUpdateListener.put(controllerStateUpdateListener, new WeakReference<>(controllerStateUpdateListener));
    }

    public void removeControllerStatusListener(ControllerStateUpdateListener controllerStateUpdateListener) {
        this.mUpdateListener.remove(controllerStateUpdateListener);
    }

    public Pair<String, String> getControllerStatuses() {
        Controllers.ControllerStatus pairedDeviceStatus;
        Controllers.ControllerStatus controllerStatus = Controllers.ControllerStatus.UNKNOWN_ERROR;
        Controllers.ControllerStatus controllerStatus2 = Controllers.ControllerStatus.UNKNOWN_ERROR;
        try {
            int[] deviceTypes = this.mControllers.getDeviceTypes();
            if (deviceTypes == null) {
                Log.e(TAG, "getDeviceTypes returned null");
                return null;
            }
            if (deviceTypes.length == 0 || 2 < deviceTypes.length) {
                Log.e(TAG, String.format("getDeviceTypes returned an invalid number of devices: %d", Integer.valueOf(deviceTypes.length)));
            } else {
                try {
                    Controllers.ControllerStatus pairedDeviceStatus2 = this.mControllers.getPairedDeviceStatus(0);
                    if (pairedDeviceStatus2 != null) {
                        controllerStatus = pairedDeviceStatus2;
                    }
                    if (2 == deviceTypes.length && (pairedDeviceStatus = this.mControllers.getPairedDeviceStatus(1)) != null) {
                        controllerStatus2 = pairedDeviceStatus;
                    }
                } catch (InterruptedException e) {
                    String str = TAG;
                    Log.e(str, "Error getting paired devices: " + e.getMessage());
                }
            }
            return new Pair<>(controllerStatus.toString(), controllerStatus2.toString());
        } catch (InterruptedException e2) {
            String str2 = TAG;
            Log.e(str2, "Error getting devices: " + e2.getMessage());
            return null;
        }
    }
}
