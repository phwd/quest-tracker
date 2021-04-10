package com.oculus.android;

import X.AbstractC0029Ba;
import X.IX;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import com.facebook.annotations.Generated;

@Generated({"By: InjectorProcessor"})
public class BluetoothLeScannerMethodAutoProvider extends AbstractC0029Ba<BluetoothLeScanner> {
    public final Object get() {
        return ((BluetoothAdapter) IX.A00(14, this)).getBluetoothLeScanner();
    }
}
