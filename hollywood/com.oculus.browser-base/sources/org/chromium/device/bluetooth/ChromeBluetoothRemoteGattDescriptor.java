package org.chromium.device.bluetooth;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ChromeBluetoothRemoteGattDescriptor {

    /* renamed from: a  reason: collision with root package name */
    public long f10945a;
    public final Wrappers$BluetoothGattDescriptorWrapper b;
    public final ChromeBluetoothDevice c;

    public ChromeBluetoothRemoteGattDescriptor(long j, Wrappers$BluetoothGattDescriptorWrapper wrappers$BluetoothGattDescriptorWrapper, ChromeBluetoothDevice chromeBluetoothDevice) {
        this.f10945a = j;
        this.b = wrappers$BluetoothGattDescriptorWrapper;
        this.c = chromeBluetoothDevice;
        chromeBluetoothDevice.f.put(wrappers$BluetoothGattDescriptorWrapper, this);
    }

    public static ChromeBluetoothRemoteGattDescriptor create(long j, Wrappers$BluetoothGattDescriptorWrapper wrappers$BluetoothGattDescriptorWrapper, ChromeBluetoothDevice chromeBluetoothDevice) {
        return new ChromeBluetoothRemoteGattDescriptor(j, wrappers$BluetoothGattDescriptorWrapper, chromeBluetoothDevice);
    }

    public final String getUUID() {
        return this.b.f10952a.getUuid().toString();
    }

    public final void onBluetoothRemoteGattDescriptorAndroidDestruction() {
        this.f10945a = 0;
        this.c.f.remove(this.b);
    }

    public final boolean readRemoteDescriptor() {
        if (this.c.c.f11241a.readDescriptor(this.b.f10952a)) {
            return true;
        }
        AbstractC1220Ua0.d("Bluetooth", "readRemoteDescriptor readDescriptor failed.", new Object[0]);
        return false;
    }

    public final boolean writeRemoteDescriptor(byte[] bArr) {
        if (!this.b.f10952a.setValue(bArr)) {
            AbstractC1220Ua0.d("Bluetooth", "writeRemoteDescriptor setValue failed.", new Object[0]);
            return false;
        }
        if (this.c.c.f11241a.writeDescriptor(this.b.f10952a)) {
            return true;
        }
        AbstractC1220Ua0.d("Bluetooth", "writeRemoteDescriptor writeDescriptor failed.", new Object[0]);
        return false;
    }
}
