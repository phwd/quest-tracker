package oculus.internal;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHidDevice;
import android.bluetooth.BluetoothProfile;
import java.util.List;

public class OculusBluetoothHidDeviceCompat implements BluetoothProfile, OculusBluetoothHidDeviceInterface {
    private BluetoothHidDevice mDevice;

    public OculusBluetoothHidDeviceCompat(BluetoothProfile bluetoothProfile) {
        this.mDevice = (BluetoothHidDevice) bluetoothProfile;
    }

    @Override // android.bluetooth.BluetoothProfile
    public List<BluetoothDevice> getConnectedDevices() {
        return this.mDevice.getConnectedDevices();
    }

    @Override // android.bluetooth.BluetoothProfile
    public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] iArr) {
        return this.mDevice.getDevicesMatchingConnectionStates(iArr);
    }

    public int getConnectionState(BluetoothDevice bluetoothDevice) {
        return this.mDevice.getConnectionState(bluetoothDevice);
    }

    public boolean connect(BluetoothDevice bluetoothDevice) {
        return this.mDevice.connect(bluetoothDevice);
    }
}
