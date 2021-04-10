package com.oculus.companion.gattble.bluetooth;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import java.util.UUID;

public class BluetoothGattCharacteristicWriteOperation extends BluetoothGattOperation {
    private final byte[] mCommand;

    public BluetoothGattCharacteristicWriteOperation(UUID uuid, UUID uuid2, byte[] bArr) {
        super(uuid, uuid2);
        this.mCommand = bArr;
    }

    @Override // com.oculus.companion.gattble.bluetooth.BluetoothGattOperation
    public boolean run(BluetoothGatt bluetoothGatt) {
        BluetoothGattCharacteristic characteristic = getCharacteristic(bluetoothGatt);
        if (characteristic == null) {
            return false;
        }
        characteristic.setValue(this.mCommand);
        characteristic.setWriteType(2);
        return bluetoothGatt.writeCharacteristic(characteristic);
    }
}
