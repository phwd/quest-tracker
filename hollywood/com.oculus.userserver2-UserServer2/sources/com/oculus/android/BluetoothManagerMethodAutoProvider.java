package com.oculus.android;

import X.AbstractC0029Ba;
import X.IX;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import com.facebook.annotations.Generated;

@Generated({"By: InjectorProcessor"})
public class BluetoothManagerMethodAutoProvider extends AbstractC0029Ba<BluetoothManager> {
    public final Object get() {
        return ((Context) IX.A00(1, this)).getSystemService("bluetooth");
    }
}
