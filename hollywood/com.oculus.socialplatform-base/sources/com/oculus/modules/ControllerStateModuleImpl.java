package com.oculus.modules;

import X.AnonymousClass006;
import android.util.Log;
import com.oculus.modules.codegen.ControllerStateModule;
import com.oculus.modules.codegen.Resolver;
import com.oculus.os.Controllers;

public class ControllerStateModuleImpl extends ControllerStateModule {
    public static final String PRIMARY_DEVICE_TYPE = "primary";
    public static final String SECONDARY_DEVICE_TYPE = "secondary";
    public static final String TAG = "ControllerStateModuleImpl";
    public Controllers mControllers;
    public Observer mObserver;

    public class Observer implements Controllers.ControllerStatusObserver {
        public void updateControllerStatus(int i, Controllers.ControllerInfo controllerInfo) {
            String str;
            controllerInfo.status.toString();
            ControllerStateModule.ControllerStatusUpdate controllerStatusUpdate = new ControllerStateModule.ControllerStatusUpdate();
            if (i == 0) {
                str = ControllerStateModuleImpl.PRIMARY_DEVICE_TYPE;
            } else {
                str = ControllerStateModuleImpl.SECONDARY_DEVICE_TYPE;
            }
            controllerStatusUpdate.type = str;
            controllerStatusUpdate.status = controllerInfo.status.toString();
            ControllerStateModuleImpl.this.emitOnControllerStatusChanged(controllerStatusUpdate);
        }

        public Observer() {
        }
    }

    @Override // com.oculus.modules.codegen.ControllerStateModule
    public void getControllerStatusesImpl(Resolver<ControllerStateModule.ControllerStatuses> resolver) {
        String str;
        String format;
        Controllers.ControllerStatus controllerStatus;
        Controllers controllers = this.mControllers;
        if (controllers == null) {
            controllers = new Controllers((Controllers.ControllerStatusObserver) null);
        }
        Controllers.ControllerStatus controllerStatus2 = Controllers.ControllerStatus.UNKNOWN_ERROR;
        Controllers.ControllerStatus controllerStatus3 = Controllers.ControllerStatus.UNKNOWN_ERROR;
        try {
            int[] deviceTypes = controllers.getDeviceTypes();
            if (deviceTypes == null) {
                str = TAG;
                format = "getDeviceTypes returned null";
            } else {
                int length = deviceTypes.length;
                if (length == 0 || 2 < length) {
                    str = TAG;
                    format = String.format("getDeviceTypes returned an invalid number of devices: %d", Integer.valueOf(length));
                } else {
                    try {
                        Controllers.ControllerStatus pairedDeviceStatus = controllers.getPairedDeviceStatus(0);
                        if (pairedDeviceStatus != null) {
                            controllerStatus2 = pairedDeviceStatus;
                        }
                        if (2 != length || (controllerStatus = controllers.getPairedDeviceStatus(1)) == null) {
                            controllerStatus = controllerStatus3;
                        }
                        controllerStatus3 = controllerStatus;
                    } catch (InterruptedException e) {
                        str = TAG;
                        format = AnonymousClass006.A07("getPairedDeviceStatus Error: ", e.getMessage());
                    }
                    ControllerStateModule.ControllerStatuses controllerStatuses = new ControllerStateModule.ControllerStatuses();
                    controllerStatuses.primary = controllerStatus2.toString();
                    controllerStatuses.f3secondary = controllerStatus3.toString();
                    resolver.resolve(controllerStatuses);
                }
            }
            Log.e(str, format);
        } catch (InterruptedException e2) {
            Log.e(TAG, AnonymousClass006.A07("getDeviceTypes Error: ", e2.getMessage()));
            resolver.resolve(null);
        }
        ControllerStateModule.ControllerStatuses controllerStatuses2 = new ControllerStateModule.ControllerStatuses();
        controllerStatuses2.primary = controllerStatus2.toString();
        controllerStatuses2.f3secondary = controllerStatus3.toString();
        resolver.resolve(controllerStatuses2);
    }

    @Override // com.oculus.modules.codegen.ControllerStateModule
    public void startListeningForStatusChangesImpl(Resolver<Boolean> resolver) {
        boolean z;
        if (this.mObserver != null) {
            z = false;
        } else {
            Observer observer = new Observer();
            this.mObserver = observer;
            this.mControllers = new Controllers(observer);
            z = true;
        }
        resolver.resolve(Boolean.valueOf(z));
    }

    @Override // com.oculus.modules.codegen.ControllerStateModule
    public void stopListeningForStatusChangesImpl(Resolver<Boolean> resolver) {
        boolean z = false;
        if (this.mObserver != null) {
            this.mObserver = null;
            this.mControllers = null;
            z = true;
        }
        resolver.resolve(Boolean.valueOf(z));
    }

    public static /* synthetic */ String access$000() {
        return TAG;
    }
}
