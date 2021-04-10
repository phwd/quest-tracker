package com.oculus.android;

import X.AbstractC0097Hv;
import X.C00208d;
import android.bluetooth.BluetoothManager;
import com.facebook.annotations.Generated;

@Generated({"By: InjectorProcessor"})
public class BluetoothManagerMethodAutoProvider extends AbstractC0097Hv<BluetoothManager> {
    public final Object get() {
        return C00208d.A00(this).getSystemService("bluetooth");
    }
}
