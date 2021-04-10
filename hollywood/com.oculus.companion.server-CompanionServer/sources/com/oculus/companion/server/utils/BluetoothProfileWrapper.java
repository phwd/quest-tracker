package com.oculus.companion.server.utils;

import android.bluetooth.BluetoothDevice;
import android.os.ParcelUuid;

public interface BluetoothProfileWrapper {
    boolean connect(BluetoothDevice bluetoothDevice);

    String getName();

    void initialize();

    boolean isDeviceConnected(BluetoothDevice bluetoothDevice);

    boolean isDeviceSupported(BluetoothDevice bluetoothDevice);

    boolean isReady();

    boolean isUuidSupported(ParcelUuid parcelUuid);
}
