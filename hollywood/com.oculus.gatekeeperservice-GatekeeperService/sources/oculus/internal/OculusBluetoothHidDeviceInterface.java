package oculus.internal;

import android.bluetooth.BluetoothDevice;

public interface OculusBluetoothHidDeviceInterface {
    boolean connect(BluetoothDevice bluetoothDevice);

    boolean disconnect(BluetoothDevice bluetoothDevice);
}
