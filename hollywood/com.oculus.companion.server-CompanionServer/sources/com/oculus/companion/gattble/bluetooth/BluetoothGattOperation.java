package com.oculus.companion.gattble.bluetooth;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.util.Log;
import java.util.Locale;
import java.util.UUID;

public abstract class BluetoothGattOperation {
    private static final String TAG = "BluetoothGattOperation";
    protected final UUID mCharacteristicUUID;
    protected final UUID mServiceUUID;

    public abstract boolean run(BluetoothGatt bluetoothGatt);

    public BluetoothGattOperation(UUID uuid, UUID uuid2) {
        this.mServiceUUID = uuid;
        this.mCharacteristicUUID = uuid2;
    }

    /* access modifiers changed from: protected */
    public BluetoothGattCharacteristic getCharacteristic(BluetoothGatt bluetoothGatt) {
        BluetoothGattService service = bluetoothGatt.getService(this.mServiceUUID);
        if (service == null) {
            Log.e(TAG, String.format(Locale.US, "Unable to find BluetoothGattService<%s>", this.mServiceUUID.toString()));
            return null;
        }
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(this.mCharacteristicUUID);
        if (characteristic != null) {
            return characteristic;
        }
        Log.e(TAG, String.format(Locale.US, "Unable to find BluetoothGattCharacteristic<%s>", this.mCharacteristicUUID.toString()));
        return null;
    }
}
