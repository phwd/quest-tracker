package com.oculus.companion.server.utils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattServer;
import android.bluetooth.BluetoothGattServerCallback;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.content.Context;
import java.util.List;

public class BluetoothWrapper {
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothManager mBluetoothManager;
    private BluetoothGattWrapper mGattWrapper;

    public class BluetoothGattWrapper {
        private final BluetoothGattServer mGattServer;

        public BluetoothGattWrapper(BluetoothGattServer bluetoothGattServer) {
            this.mGattServer = bluetoothGattServer;
        }

        public void cancelConnection(BluetoothDevice bluetoothDevice) {
            this.mGattServer.cancelConnection(bluetoothDevice);
        }

        public List<BluetoothGattService> getServices() {
            return this.mGattServer.getServices();
        }

        public void close() {
            this.mGattServer.close();
        }

        public void addService(BluetoothGattService bluetoothGattService) {
            this.mGattServer.addService(bluetoothGattService);
        }

        public void notifyCharacteristicChanged(BluetoothDevice bluetoothDevice, BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
            this.mGattServer.notifyCharacteristicChanged(bluetoothDevice, bluetoothGattCharacteristic, z);
        }

        public void sendResponse(BluetoothDevice bluetoothDevice, int i, int i2, int i3, byte[] bArr) {
            this.mGattServer.sendResponse(bluetoothDevice, i, i2, i3, bArr);
        }

        public void clearServices() {
            this.mGattServer.clearServices();
        }
    }

    public BluetoothWrapper(Context context) {
        reinit(context);
    }

    public void reinit(Context context) {
        this.mBluetoothManager = (BluetoothManager) context.getSystemService("bluetooth");
        this.mBluetoothAdapter = this.mBluetoothManager.getAdapter();
    }

    public boolean isEnabled() {
        BluetoothAdapter bluetoothAdapter;
        if (this.mBluetoothManager == null || (bluetoothAdapter = this.mBluetoothAdapter) == null) {
            return false;
        }
        return bluetoothAdapter.isEnabled();
    }

    public void enable() {
        this.mBluetoothAdapter.enable();
    }

    public BluetoothGattWrapper openGattServer(Context context, BluetoothGattServerCallback bluetoothGattServerCallback) {
        BluetoothGattServer openGattServer = this.mBluetoothManager.openGattServer(context, bluetoothGattServerCallback);
        if (openGattServer == null) {
            return null;
        }
        this.mGattWrapper = new BluetoothGattWrapper(openGattServer);
        return this.mGattWrapper;
    }

    public boolean isMultipleAdvertisementSupported() {
        return this.mBluetoothAdapter.isMultipleAdvertisementSupported();
    }

    public BluetoothLeAdvertiser getBluetoothLeAdvertiser() {
        return this.mBluetoothAdapter.getBluetoothLeAdvertiser();
    }

    public BluetoothGattWrapper getGatt() {
        return this.mGattWrapper;
    }

    public void clearGatt() {
        this.mGattWrapper = null;
    }
}
