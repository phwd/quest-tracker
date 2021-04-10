package com.oculus.android;

import android.bluetooth.BluetoothManager;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class BluetoothManagerMethodAutoProvider extends AbstractProvider<BluetoothManager> {
    @Override // javax.inject.Provider
    public BluetoothManager get() {
        return AndroidModule.provideBluetoothManager(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_ACCESS_METHOD(this));
    }
}
