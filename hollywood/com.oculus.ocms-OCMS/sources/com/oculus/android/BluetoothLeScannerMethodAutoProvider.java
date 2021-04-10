package com.oculus.android;

import android.bluetooth.le.BluetoothLeScanner;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class BluetoothLeScannerMethodAutoProvider extends AbstractProvider<BluetoothLeScanner> {
    @Override // javax.inject.Provider
    public BluetoothLeScanner get() {
        return AndroidModule.provideBluetoothLeScanner(AndroidModule._UL__ULSEP_android_bluetooth_BluetoothAdapter_ULSEP_ACCESS_METHOD(this));
    }
}
