package org.chromium.device.bluetooth;

import J.N;
import android.bluetooth.BluetoothGattDescriptor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ChromeBluetoothRemoteGattCharacteristic {

    /* renamed from: a  reason: collision with root package name */
    public long f10944a;
    public final Wrappers$BluetoothGattCharacteristicWrapper b;
    public final String c;
    public final ChromeBluetoothDevice d;

    public ChromeBluetoothRemoteGattCharacteristic(long j, Wrappers$BluetoothGattCharacteristicWrapper wrappers$BluetoothGattCharacteristicWrapper, String str, ChromeBluetoothDevice chromeBluetoothDevice) {
        this.f10944a = j;
        this.b = wrappers$BluetoothGattCharacteristicWrapper;
        this.c = str;
        this.d = chromeBluetoothDevice;
        chromeBluetoothDevice.e.put(wrappers$BluetoothGattCharacteristicWrapper, this);
    }

    public static ChromeBluetoothRemoteGattCharacteristic create(long j, Wrappers$BluetoothGattCharacteristicWrapper wrappers$BluetoothGattCharacteristicWrapper, String str, ChromeBluetoothDevice chromeBluetoothDevice) {
        return new ChromeBluetoothRemoteGattCharacteristic(j, wrappers$BluetoothGattCharacteristicWrapper, str, chromeBluetoothDevice);
    }

    public final void createDescriptors() {
        Wrappers$BluetoothGattCharacteristicWrapper wrappers$BluetoothGattCharacteristicWrapper = this.b;
        List<BluetoothGattDescriptor> descriptors = wrappers$BluetoothGattCharacteristicWrapper.f10951a.getDescriptors();
        ArrayList arrayList = new ArrayList(descriptors.size());
        for (BluetoothGattDescriptor bluetoothGattDescriptor : descriptors) {
            Wrappers$BluetoothGattDescriptorWrapper wrappers$BluetoothGattDescriptorWrapper = (Wrappers$BluetoothGattDescriptorWrapper) wrappers$BluetoothGattCharacteristicWrapper.b.c.get(bluetoothGattDescriptor);
            if (wrappers$BluetoothGattDescriptorWrapper == null) {
                Wrappers$BluetoothDeviceWrapper wrappers$BluetoothDeviceWrapper = wrappers$BluetoothGattCharacteristicWrapper.b;
                wrappers$BluetoothGattDescriptorWrapper = new Wrappers$BluetoothGattDescriptorWrapper(bluetoothGattDescriptor, wrappers$BluetoothDeviceWrapper);
                wrappers$BluetoothDeviceWrapper.c.put(bluetoothGattDescriptor, wrappers$BluetoothGattDescriptorWrapper);
            }
            arrayList.add(wrappers$BluetoothGattDescriptorWrapper);
        }
        int i = 0;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Wrappers$BluetoothGattDescriptorWrapper wrappers$BluetoothGattDescriptorWrapper2 = (Wrappers$BluetoothGattDescriptorWrapper) it.next();
            N.MkqvrLoV(this.f10944a, this, this.c + "/" + wrappers$BluetoothGattDescriptorWrapper2.f10952a.getUuid().toString() + ";" + i, wrappers$BluetoothGattDescriptorWrapper2, this.d);
            i++;
        }
    }

    public final int getProperties() {
        return this.b.f10951a.getProperties();
    }

    public final String getUUID() {
        return this.b.f10951a.getUuid().toString();
    }

    public final void onBluetoothRemoteGattCharacteristicAndroidDestruction() {
        C4668rz1 rz1 = this.d.c;
        if (rz1 != null) {
            rz1.f11241a.setCharacteristicNotification(this.b.f10951a, false);
        }
        this.f10944a = 0;
        this.d.e.remove(this.b);
    }

    public final boolean readRemoteCharacteristic() {
        if (this.d.c.f11241a.readCharacteristic(this.b.f10951a)) {
            return true;
        }
        AbstractC1220Ua0.d("Bluetooth", "readRemoteCharacteristic readCharacteristic failed.", new Object[0]);
        return false;
    }

    public final boolean setCharacteristicNotification(boolean z) {
        return this.d.c.f11241a.setCharacteristicNotification(this.b.f10951a, z);
    }

    public final boolean writeRemoteCharacteristic(byte[] bArr, int i) {
        if (!this.b.f10951a.setValue(bArr)) {
            AbstractC1220Ua0.d("Bluetooth", "writeRemoteCharacteristic setValue failed.", new Object[0]);
            return false;
        }
        if (i != 0) {
            this.b.f10951a.setWriteType(i);
        }
        if (this.d.c.f11241a.writeCharacteristic(this.b.f10951a)) {
            return true;
        }
        AbstractC1220Ua0.d("Bluetooth", "writeRemoteCharacteristic writeCharacteristic failed.", new Object[0]);
        return false;
    }
}
