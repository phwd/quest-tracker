package com.oculus.modules;

import android.util.Log;
import com.oculus.modules.codegen.ControllerStateModule;
import com.oculus.modules.codegen.Resolver;
import com.oculus.os.Controllers;

public class ControllerStateModuleImpl extends ControllerStateModule {
    private static final String PRIMARY_DEVICE_TYPE = "primary";
    private static final String SECONDARY_DEVICE_TYPE = "secondary";
    private static final String TAG = ControllerStateModuleImpl.class.getSimpleName();
    private Controllers mControllers;
    private Observer mObserver;

    private class Observer implements Controllers.ControllerStatusObserver {
        private Observer() {
        }

        public void updateControllerStatus(int deviceType, Controllers.ControllerInfo controllerInfo) {
            Log.i(ControllerStateModuleImpl.TAG, String.format("Updating controller device: %d with status: %s", Integer.valueOf(deviceType), controllerInfo.status.toString()));
            ControllerStateModule.ControllerStatusUpdate update = new ControllerStateModule.ControllerStatusUpdate();
            update.type = deviceType == 0 ? ControllerStateModuleImpl.PRIMARY_DEVICE_TYPE : ControllerStateModuleImpl.SECONDARY_DEVICE_TYPE;
            update.status = controllerInfo.status.toString();
            ControllerStateModuleImpl.this.emitOnControllerStatusChanged(update);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.ControllerStateModule
    public void startListeningForStatusChangesImpl(Resolver<Boolean> resolver) {
        if (this.mObserver != null) {
            resolver.resolve(false);
            return;
        }
        this.mObserver = new Observer();
        this.mControllers = new Controllers(this.mObserver);
        resolver.resolve(true);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.ControllerStateModule
    public void stopListeningForStatusChangesImpl(Resolver<Boolean> resolver) {
        if (this.mObserver == null) {
            resolver.resolve(false);
            return;
        }
        this.mObserver = null;
        this.mControllers = null;
        resolver.resolve(true);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.ControllerStateModule
    public void getControllerStatusesImpl(Resolver<ControllerStateModule.ControllerStatuses> resolver) {
        Controllers.ControllerStatus secondaryStatus;
        Controllers controllers = this.mControllers != null ? this.mControllers : new Controllers((Controllers.ControllerStatusObserver) null);
        Controllers.ControllerStatus primary = Controllers.ControllerStatus.UNKNOWN_ERROR;
        Controllers.ControllerStatus secondary = Controllers.ControllerStatus.UNKNOWN_ERROR;
        try {
            int[] deviceTypes = controllers.getDeviceTypes();
            if (deviceTypes == null) {
                Log.e(TAG, "getDeviceTypes returned null");
            } else if (deviceTypes.length == 0 || 2 < deviceTypes.length) {
                Log.e(TAG, String.format("getDeviceTypes returned an invalid number of devices: %d", Integer.valueOf(deviceTypes.length)));
            } else {
                Log.d(TAG, String.format("getDeviceTypes returned %d devices", Integer.valueOf(deviceTypes.length)));
                try {
                    Controllers.ControllerStatus primaryStatus = controllers.getPairedDeviceStatus(0);
                    if (primaryStatus != null) {
                        primary = primaryStatus;
                    }
                    if (2 == deviceTypes.length && (secondaryStatus = controllers.getPairedDeviceStatus(1)) != null) {
                        secondary = secondaryStatus;
                    }
                } catch (InterruptedException e) {
                    Log.e(TAG, "getPairedDeviceStatus Error: " + e.getMessage());
                }
            }
        } catch (InterruptedException e2) {
            Log.e(TAG, "getDeviceTypes Error: " + e2.getMessage());
            resolver.resolve(null);
        }
        ControllerStateModule.ControllerStatuses statuses = new ControllerStateModule.ControllerStatuses();
        statuses.primary = primary.toString();
        statuses.secondary = secondary.toString();
        resolver.resolve(statuses);
    }
}
