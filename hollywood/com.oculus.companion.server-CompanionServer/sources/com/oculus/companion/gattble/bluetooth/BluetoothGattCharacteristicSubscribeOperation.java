package com.oculus.companion.gattble.bluetooth;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.util.Log;
import java.util.Locale;
import java.util.UUID;

public class BluetoothGattCharacteristicSubscribeOperation extends BluetoothGattOperation {
    private static final String TAG = "BluetoothGattCharacteristicSubscribeOperation";
    private static final UUID UUID_DESCRIPTOR_CLIENT_CHARACTERISTIC_CONFIG = UUID.fromString("00002902-0000-1000-8000-00805F9B34FB");

    public BluetoothGattCharacteristicSubscribeOperation(UUID uuid, UUID uuid2) {
        super(uuid, uuid2);
    }

    @Override // com.oculus.companion.gattble.bluetooth.BluetoothGattOperation
    public boolean run(BluetoothGatt bluetoothGatt) {
        BluetoothGattCharacteristic characteristic = getCharacteristic(bluetoothGatt);
        if (characteristic == null) {
            return false;
        }
        BluetoothGattDescriptor descriptor = characteristic.getDescriptor(UUID_DESCRIPTOR_CLIENT_CHARACTERISTIC_CONFIG);
        if (descriptor == null) {
            Log.e(TAG, String.format(Locale.US, "Unable to find BluetoothGattDescriptor<%s>", UUID_DESCRIPTOR_CLIENT_CHARACTERISTIC_CONFIG.toString()));
            return false;
        } else if (!descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE) || !bluetoothGatt.setCharacteristicNotification(characteristic, true) || !bluetoothGatt.writeDescriptor(descriptor)) {
            return false;
        } else {
            return true;
        }
    }
}
