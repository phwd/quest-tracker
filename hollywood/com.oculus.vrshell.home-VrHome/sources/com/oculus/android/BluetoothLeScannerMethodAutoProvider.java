package com.oculus.android;

import android.bluetooth.le.BluetoothLeScanner;
import com.facebook.inject.AbstractProvider;

public class BluetoothLeScannerMethodAutoProvider extends AbstractProvider<BluetoothLeScanner> {
    @Override // javax.inject.Provider
    public BluetoothLeScanner get() {
        return AndroidModule.provideBluetoothLeScanner(AndroidModule.$ul_$xXXandroid_bluetooth_BluetoothAdapter$xXXACCESS_METHOD(this));
    }
}
