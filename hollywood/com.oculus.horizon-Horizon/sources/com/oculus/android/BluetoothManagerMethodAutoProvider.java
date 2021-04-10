package com.oculus.android;

import X.AnonymousClass0J3;
import X.C003108z;
import android.bluetooth.BluetoothManager;
import com.facebook.annotations.Generated;

@Generated({"By: InjectorProcessor"})
public class BluetoothManagerMethodAutoProvider extends AnonymousClass0J3<BluetoothManager> {
    public final Object get() {
        return C003108z.A00(this).getSystemService("bluetooth");
    }
}
