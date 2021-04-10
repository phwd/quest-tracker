package org.chromium.device.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.content.Context;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Wrappers$BluetoothAdapterWrapper {

    /* renamed from: a  reason: collision with root package name */
    public final BluetoothAdapter f10949a;
    public final Context b;
    public C4838sz1 c;

    public Wrappers$BluetoothAdapterWrapper(BluetoothAdapter bluetoothAdapter, Context context) {
        this.f10949a = bluetoothAdapter;
        this.b = context;
    }

    public static Wrappers$BluetoothAdapterWrapper createWithDefaultAdapter() {
        boolean z = true;
        if (!(ContextUtils.getApplicationContext().checkCallingOrSelfPermission("android.permission.BLUETOOTH") == 0 && ContextUtils.getApplicationContext().checkCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN") == 0)) {
            AbstractC1220Ua0.f("Bluetooth", "BluetoothAdapterWrapper.create failed: Lacking Bluetooth permissions.", new Object[0]);
            return null;
        }
        if (!ContextUtils.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
            z = false;
        }
        if (!z) {
            AbstractC1220Ua0.d("Bluetooth", "BluetoothAdapterWrapper.create failed: No Low Energy support.", new Object[0]);
            return null;
        }
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            return new Wrappers$BluetoothAdapterWrapper(defaultAdapter, ContextUtils.getApplicationContext());
        }
        AbstractC1220Ua0.d("Bluetooth", "BluetoothAdapterWrapper.create failed: Default adapter not found.", new Object[0]);
        return null;
    }

    public C4838sz1 a() {
        BluetoothLeScanner bluetoothLeScanner = this.f10949a.getBluetoothLeScanner();
        if (bluetoothLeScanner == null) {
            return null;
        }
        if (this.c == null) {
            this.c = new C4838sz1(bluetoothLeScanner);
        }
        return this.c;
    }
}
