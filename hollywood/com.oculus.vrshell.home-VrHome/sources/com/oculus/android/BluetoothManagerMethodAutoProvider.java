package com.oculus.android;

import android.bluetooth.BluetoothManager;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

public class BluetoothManagerMethodAutoProvider extends AbstractProvider<BluetoothManager> {
    @Override // javax.inject.Provider
    public BluetoothManager get() {
        return AndroidModule.provideBluetoothManager(BundledAndroidModule.$ul_$xXXandroid_content_Context$xXXcom_facebook_inject_ForAppContext$xXXACCESS_METHOD(this));
    }
}
