package org.chromium.device.bluetooth;

import android.bluetooth.BluetoothDevice;
import java.util.HashMap;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ChromeBluetoothDevice {

    /* renamed from: a  reason: collision with root package name */
    public long f10943a;
    public final Wrappers$BluetoothDeviceWrapper b;
    public C4668rz1 c;
    public final C0407Gq d = new C0407Gq(this, null);
    public final HashMap e = new HashMap();
    public final HashMap f = new HashMap();

    public ChromeBluetoothDevice(long j, Wrappers$BluetoothDeviceWrapper wrappers$BluetoothDeviceWrapper) {
        this.f10943a = j;
        this.b = wrappers$BluetoothDeviceWrapper;
    }

    public static ChromeBluetoothDevice create(long j, Wrappers$BluetoothDeviceWrapper wrappers$BluetoothDeviceWrapper) {
        return new ChromeBluetoothDevice(j, wrappers$BluetoothDeviceWrapper);
    }

    public final void createGattConnectionImpl() {
        AbstractC1220Ua0.d("Bluetooth", "connectGatt", new Object[0]);
        C4668rz1 rz1 = this.c;
        if (rz1 != null) {
            rz1.f11241a.close();
        }
        Wrappers$BluetoothDeviceWrapper wrappers$BluetoothDeviceWrapper = this.b;
        this.c = new C4668rz1(wrappers$BluetoothDeviceWrapper.f10950a.connectGatt(ContextUtils.getApplicationContext(), false, new C5008tz1(this.d, wrappers$BluetoothDeviceWrapper), 2), wrappers$BluetoothDeviceWrapper);
    }

    public final void disconnectGatt() {
        AbstractC1220Ua0.d("Bluetooth", "BluetoothGatt.disconnect", new Object[0]);
        C4668rz1 rz1 = this.c;
        if (rz1 != null) {
            rz1.f11241a.disconnect();
        }
    }

    public final String getAddress() {
        return this.b.a();
    }

    public final int getBluetoothClass() {
        Wrappers$BluetoothDeviceWrapper wrappers$BluetoothDeviceWrapper = this.b;
        BluetoothDevice bluetoothDevice = wrappers$BluetoothDeviceWrapper.f10950a;
        if (bluetoothDevice == null || bluetoothDevice.getBluetoothClass() == null) {
            return 7936;
        }
        return wrappers$BluetoothDeviceWrapper.f10950a.getBluetoothClass().getDeviceClass();
    }

    public final String getName() {
        return this.b.f10950a.getName();
    }

    public final boolean isPaired() {
        return this.b.f10950a.getBondState() == 12;
    }

    public final void onBluetoothDeviceAndroidDestruction() {
        C4668rz1 rz1 = this.c;
        if (rz1 != null) {
            rz1.f11241a.close();
            this.c = null;
        }
        this.f10943a = 0;
    }
}
