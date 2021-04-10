package com.oculus.android;

import X.AnonymousClass0Li;
import X.C01340Gg;
import android.bluetooth.BluetoothManager;
import com.facebook.annotations.Generated;

@Generated({"By: InjectorProcessor"})
public class BluetoothManagerMethodAutoProvider extends AnonymousClass0Li<BluetoothManager> {
    public final Object get() {
        return C01340Gg.A00(this).getSystemService("bluetooth");
    }
}
