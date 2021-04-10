package oculus.internal;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHidDevice;
import android.bluetooth.BluetoothProfile;
import java.util.List;

public class OculusBluetoothHidDeviceCompat implements BluetoothProfile, OculusBluetoothHidDeviceInterface {
    public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.hiddevice.profile.action.CONNECTION_STATE_CHANGED";
    public static final int DEVICE_TYPE = 19;
    private BluetoothHidDevice mDevice;

    public OculusBluetoothHidDeviceCompat(BluetoothProfile bluetoothProfile) {
        this.mDevice = (BluetoothHidDevice) bluetoothProfile;
    }

    @Override // android.bluetooth.BluetoothProfile
    public List<BluetoothDevice> getConnectedDevices() {
        return this.mDevice.getConnectedDevices();
    }

    @Override // android.bluetooth.BluetoothProfile
    public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
        return this.mDevice.getDevicesMatchingConnectionStates(states);
    }

    public int getConnectionState(BluetoothDevice bluetoothDevice) {
        return this.mDevice.getConnectionState(bluetoothDevice);
    }

    @Override // oculus.internal.OculusBluetoothHidDeviceInterface
    public boolean connect(BluetoothDevice device) {
        return this.mDevice.connect(device);
    }

    @Override // oculus.internal.OculusBluetoothHidDeviceInterface
    public boolean disconnect(BluetoothDevice device) {
        return this.mDevice.disconnect(device);
    }
}
