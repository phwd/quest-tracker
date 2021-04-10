package com.oculus.companion.server.utils;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothUuid;
import android.content.Context;
import android.os.ParcelUuid;

public class BluetoothA2dpWrapper implements BluetoothProfileWrapper {
    private final Context mContext;
    private final BluetoothServiceConnection mServiceConnection;

    @Override // com.oculus.companion.server.utils.BluetoothProfileWrapper
    public String getName() {
        return "a2dp";
    }

    public BluetoothA2dpWrapper(Context context, BluetoothAdapter bluetoothAdapter) {
        this.mContext = context;
        this.mServiceConnection = new BluetoothServiceConnection(bluetoothAdapter, 2);
    }

    @Override // com.oculus.companion.server.utils.BluetoothProfileWrapper
    public boolean connect(BluetoothDevice bluetoothDevice) {
        BluetoothA2dp bluetoothA2dp = (BluetoothA2dp) this.mServiceConnection.getServiceBlocking(this.mContext);
        return bluetoothA2dp != null && bluetoothA2dp.connect(bluetoothDevice);
    }

    @Override // com.oculus.companion.server.utils.BluetoothProfileWrapper
    public boolean isDeviceConnected(BluetoothDevice bluetoothDevice) {
        BluetoothA2dp bluetoothA2dp = (BluetoothA2dp) this.mServiceConnection.getServiceBlocking(this.mContext);
        return bluetoothA2dp != null && bluetoothA2dp.getConnectionState(bluetoothDevice) == 2;
    }

    @Override // com.oculus.companion.server.utils.BluetoothProfileWrapper
    public boolean isDeviceSupported(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null || bluetoothDevice.getBluetoothClass() == null || bluetoothDevice.getBluetoothClass().getMajorDeviceClass() != 1024) {
            return false;
        }
        return true;
    }

    @Override // com.oculus.companion.server.utils.BluetoothProfileWrapper
    public boolean isUuidSupported(ParcelUuid parcelUuid) {
        return BluetoothUuid.isHeadset(parcelUuid);
    }

    @Override // com.oculus.companion.server.utils.BluetoothProfileWrapper
    public boolean isReady() {
        return this.mServiceConnection.checkService();
    }

    @Override // com.oculus.companion.server.utils.BluetoothProfileWrapper
    public void initialize() {
        this.mServiceConnection.getServiceBlocking(this.mContext);
    }
}
