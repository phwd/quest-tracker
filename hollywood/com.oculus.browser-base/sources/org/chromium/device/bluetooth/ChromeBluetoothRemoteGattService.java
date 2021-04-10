package org.chromium.device.bluetooth;

import J.N;
import android.bluetooth.BluetoothGattCharacteristic;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ChromeBluetoothRemoteGattService {

    /* renamed from: a  reason: collision with root package name */
    public long f10946a;
    public final Wrappers$BluetoothGattServiceWrapper b;
    public final String c;
    public ChromeBluetoothDevice d;

    public ChromeBluetoothRemoteGattService(long j, Wrappers$BluetoothGattServiceWrapper wrappers$BluetoothGattServiceWrapper, String str, ChromeBluetoothDevice chromeBluetoothDevice) {
        this.f10946a = j;
        this.b = wrappers$BluetoothGattServiceWrapper;
        this.c = str;
        this.d = chromeBluetoothDevice;
    }

    public static ChromeBluetoothRemoteGattService create(long j, Wrappers$BluetoothGattServiceWrapper wrappers$BluetoothGattServiceWrapper, String str, ChromeBluetoothDevice chromeBluetoothDevice) {
        return new ChromeBluetoothRemoteGattService(j, wrappers$BluetoothGattServiceWrapper, str, chromeBluetoothDevice);
    }

    public final void createCharacteristics() {
        Wrappers$BluetoothGattServiceWrapper wrappers$BluetoothGattServiceWrapper = this.b;
        List<BluetoothGattCharacteristic> characteristics = wrappers$BluetoothGattServiceWrapper.f10953a.getCharacteristics();
        ArrayList arrayList = new ArrayList(characteristics.size());
        for (BluetoothGattCharacteristic bluetoothGattCharacteristic : characteristics) {
            Wrappers$BluetoothGattCharacteristicWrapper wrappers$BluetoothGattCharacteristicWrapper = (Wrappers$BluetoothGattCharacteristicWrapper) wrappers$BluetoothGattServiceWrapper.b.b.get(bluetoothGattCharacteristic);
            if (wrappers$BluetoothGattCharacteristicWrapper == null) {
                Wrappers$BluetoothDeviceWrapper wrappers$BluetoothDeviceWrapper = wrappers$BluetoothGattServiceWrapper.b;
                wrappers$BluetoothGattCharacteristicWrapper = new Wrappers$BluetoothGattCharacteristicWrapper(bluetoothGattCharacteristic, wrappers$BluetoothDeviceWrapper);
                wrappers$BluetoothDeviceWrapper.b.put(bluetoothGattCharacteristic, wrappers$BluetoothGattCharacteristicWrapper);
            }
            arrayList.add(wrappers$BluetoothGattCharacteristicWrapper);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Wrappers$BluetoothGattCharacteristicWrapper wrappers$BluetoothGattCharacteristicWrapper2 = (Wrappers$BluetoothGattCharacteristicWrapper) it.next();
            N.MM2CqKoK(this.f10946a, this, this.c + "/" + wrappers$BluetoothGattCharacteristicWrapper2.f10951a.getUuid().toString() + "," + wrappers$BluetoothGattCharacteristicWrapper2.f10951a.getInstanceId(), wrappers$BluetoothGattCharacteristicWrapper2, this.d);
        }
    }

    public final String getUUID() {
        return this.b.f10953a.getUuid().toString();
    }

    public final void onBluetoothRemoteGattServiceAndroidDestruction() {
        this.f10946a = 0;
    }
}
