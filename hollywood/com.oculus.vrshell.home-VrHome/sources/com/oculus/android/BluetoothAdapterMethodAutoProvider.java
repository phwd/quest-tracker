package com.oculus.android;

import android.bluetooth.BluetoothAdapter;
import com.facebook.inject.AbstractProvider;

public class BluetoothAdapterMethodAutoProvider extends AbstractProvider<BluetoothAdapter> {
    @Override // javax.inject.Provider
    public BluetoothAdapter get() {
        return AndroidModule.provideBluetoothAdapter();
    }
}
