package com.oculus.systemdialog;

import com.oculus.modules.ControllerStateModuleImpl;

public enum DialogPrimaryButtonStyle {
    PRIMARY(ControllerStateModuleImpl.PRIMARY_DEVICE_TYPE),
    DANGER("danger"),
    SECONDARY(ControllerStateModuleImpl.SECONDARY_DEVICE_TYPE);
    
    public final String mIPCString;

    public String getIPCString() {
        return this.mIPCString;
    }

    /* access modifiers changed from: public */
    DialogPrimaryButtonStyle(String str) {
        this.mIPCString = str;
    }
}
