package com.oculus.companion.gattble.bluetooth;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import java.util.List;
import java.util.UUID;

public abstract class BluetoothServiceHandler {
    protected final BluetoothServiceDelegate mBluetoothServiceDelegate;

    /* access modifiers changed from: protected */
    public abstract List<UUID> getCharacteristicsToSubscribe();

    public abstract UUID getServiceUUID();

    public abstract void onCharacteristicChanged(BluetoothGattCharacteristic bluetoothGattCharacteristic);

    public abstract void onCharacteristicRead(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i);

    public abstract void onCharacteristicWrite(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i);

    public abstract void onDescriptorRead(BluetoothGattDescriptor bluetoothGattDescriptor, int i);

    public abstract void onDescriptorWrite(BluetoothGattDescriptor bluetoothGattDescriptor, int i);

    public abstract void onDisconnect();

    public BluetoothServiceHandler(BluetoothServiceDelegate bluetoothServiceDelegate) {
        this.mBluetoothServiceDelegate = bluetoothServiceDelegate;
    }

    public void subscribeToCharacteristicsIfAny() {
        for (UUID uuid : getCharacteristicsToSubscribe()) {
            this.mBluetoothServiceDelegate.addBluetoothGattOperation(new BluetoothGattCharacteristicSubscribeOperation(getServiceUUID(), uuid));
        }
    }
}
