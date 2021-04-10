package com.oculus.modules;

import android.content.Context;
import android.util.Log;
import com.oculus.modules.codegen.DeviceUnlockStatusModule;
import com.oculus.secure.unlockulus.UnlockulusHelper;

public class DeviceUnlockStatusModuleImpl extends DeviceUnlockStatusModule {
    private static final String TAG = DeviceUnlockStatusModuleImpl.class.getSimpleName();
    private final Context mContext;

    public DeviceUnlockStatusModuleImpl(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.DeviceUnlockStatusModule
    public boolean marshallJSConstantIsDeviceUnlocked() {
        Log.d(TAG, "marshallJSConstantIsDeviceUnlocked()");
        return UnlockulusHelper.isEmployeeWithWhitelistedDevice(this.mContext);
    }
}
