package com.oculus.companion.server.utils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothUuid;
import android.content.Context;
import android.os.ParcelUuid;
import oculus.internal.OculusBluetoothHidDeviceCompat;

public class BluetoothInputDeviceWrapper implements BluetoothProfileWrapper {
    private final Context mContext;
    private final BluetoothServiceConnection mServiceConnection;

    @Override // com.oculus.companion.server.utils.BluetoothProfileWrapper
    public String getName() {
        return "InputDevice";
    }

    public BluetoothInputDeviceWrapper(Context context, BluetoothAdapter bluetoothAdapter) {
        this.mContext = context;
        this.mServiceConnection = new BluetoothServiceConnection(bluetoothAdapter, 19);
    }

    @Override // com.oculus.companion.server.utils.BluetoothProfileWrapper
    public boolean connect(BluetoothDevice bluetoothDevice) {
        BluetoothProfile serviceBlocking = this.mServiceConnection.getServiceBlocking(this.mContext);
        if (serviceBlocking == null) {
            return false;
        }
        return new OculusBluetoothHidDeviceCompat(serviceBlocking).connect(bluetoothDevice);
    }

    @Override // com.oculus.companion.server.utils.BluetoothProfileWrapper
    public boolean isDeviceConnected(BluetoothDevice bluetoothDevice) {
        BluetoothProfile serviceBlocking = this.mServiceConnection.getServiceBlocking(this.mContext);
        if (serviceBlocking != null && new OculusBluetoothHidDeviceCompat(serviceBlocking).getConnectionState(bluetoothDevice) == 2) {
            return true;
        }
        return false;
    }

    @Override // com.oculus.companion.server.utils.BluetoothProfileWrapper
    public boolean isDeviceSupported(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null || bluetoothDevice.getBluetoothClass() == null || bluetoothDevice.getBluetoothClass().getMajorDeviceClass() != 1280) {
            return false;
        }
        return true;
    }

    @Override // com.oculus.companion.server.utils.BluetoothProfileWrapper
    public boolean isUuidSupported(ParcelUuid parcelUuid) {
        return BluetoothUuid.isInputDevice(parcelUuid) || BluetoothUuid.Hogp.equals(parcelUuid);
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
