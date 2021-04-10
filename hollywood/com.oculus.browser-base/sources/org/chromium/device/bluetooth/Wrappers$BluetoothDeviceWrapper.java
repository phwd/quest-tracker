package org.chromium.device.bluetooth;

import android.bluetooth.BluetoothDevice;
import java.util.HashMap;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Wrappers$BluetoothDeviceWrapper {

    /* renamed from: a  reason: collision with root package name */
    public final BluetoothDevice f10950a;
    public final HashMap b = new HashMap();
    public final HashMap c = new HashMap();

    public Wrappers$BluetoothDeviceWrapper(BluetoothDevice bluetoothDevice) {
        this.f10950a = bluetoothDevice;
    }

    public String a() {
        return this.f10950a.getAddress();
    }
}
