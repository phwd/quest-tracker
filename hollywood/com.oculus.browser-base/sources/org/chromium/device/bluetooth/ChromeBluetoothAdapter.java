package org.chromium.device.bluetooth;

import J.N;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.oculus.os.Version;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ChromeBluetoothAdapter extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public long f10942a;
    public final Wrappers$BluetoothAdapterWrapper b;
    public C5659xq c;

    public ChromeBluetoothAdapter(long j, Wrappers$BluetoothAdapterWrapper wrappers$BluetoothAdapterWrapper) {
        this.f10942a = j;
        this.b = wrappers$BluetoothAdapterWrapper;
        if (wrappers$BluetoothAdapterWrapper != null) {
            wrappers$BluetoothAdapterWrapper.b.registerReceiver(this, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
        }
        if (wrappers$BluetoothAdapterWrapper == null) {
            AbstractC1220Ua0.d("Bluetooth", "ChromeBluetoothAdapter created with no adapterWrapper.", new Object[0]);
        } else {
            AbstractC1220Ua0.d("Bluetooth", "ChromeBluetoothAdapter created with provided adapterWrapper.", new Object[0]);
        }
    }

    public static ChromeBluetoothAdapter create(long j, Wrappers$BluetoothAdapterWrapper wrappers$BluetoothAdapterWrapper) {
        return new ChromeBluetoothAdapter(j, wrappers$BluetoothAdapterWrapper);
    }

    public final String getAddress() {
        return isPresent() ? this.b.f10949a.getAddress() : "";
    }

    public final String getName() {
        return isPresent() ? this.b.f10949a.getName() : "";
    }

    public final boolean isDiscoverable() {
        return isPresent() && this.b.f10949a.getScanMode() == 23;
    }

    public final boolean isDiscovering() {
        return isPresent() && (this.b.f10949a.isDiscovering() || this.c != null);
    }

    public final boolean isPowered() {
        return isPresent() && this.b.f10949a.isEnabled();
    }

    public final boolean isPresent() {
        return this.b != null;
    }

    public final void onBluetoothAdapterAndroidDestruction() {
        stopScan();
        this.f10942a = 0;
        Wrappers$BluetoothAdapterWrapper wrappers$BluetoothAdapterWrapper = this.b;
        if (wrappers$BluetoothAdapterWrapper != null) {
            wrappers$BluetoothAdapterWrapper.b.unregisterReceiver(this);
        }
    }

    public void onReceive(Context context, Intent intent) {
        String str;
        String action = intent.getAction();
        if (isPresent() && "android.bluetooth.adapter.action.STATE_CHANGED".equals(action)) {
            int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
            Object[] objArr = new Object[1];
            switch (intExtra) {
                case Version.VERSION_10:
                    str = "STATE_OFF";
                    break;
                case Version.VERSION_11:
                    str = "STATE_TURNING_ON";
                    break;
                case Version.VERSION_12:
                    str = "STATE_ON";
                    break;
                case Version.VERSION_13:
                    str = "STATE_TURNING_OFF";
                    break;
                default:
                    str = AbstractC2531fV.w("illegal state: ", intExtra);
                    break;
            }
            objArr[0] = str;
            AbstractC1220Ua0.f("Bluetooth", "onReceive: BluetoothAdapter.ACTION_STATE_CHANGED: %s", objArr);
            if (intExtra == 10) {
                N.MGGbKqrZ(this.f10942a, this, false);
            } else if (intExtra == 12) {
                N.MGGbKqrZ(this.f10942a, this, true);
            }
        }
    }

    public final boolean setPowered(boolean z) {
        return z ? isPresent() && this.b.f10949a.enable() : isPresent() && this.b.f10949a.disable();
    }

    public final boolean startScan(List list) {
        C4838sz1 a2 = this.b.a();
        if (a2 == null) {
            return false;
        }
        C1159Ta0 a3 = C1159Ta0.a();
        if (!(a3.c() && a3.e())) {
            return false;
        }
        C5659xq xqVar = new C5659xq(this, null);
        this.c = xqVar;
        try {
            a2.a(list, 2, xqVar);
            return true;
        } catch (IllegalArgumentException e) {
            AbstractC1220Ua0.a("Bluetooth", "Cannot start scan: " + e, new Object[0]);
            this.c = null;
            return false;
        } catch (IllegalStateException e2) {
            AbstractC1220Ua0.a("Bluetooth", "Adapter is off. Cannot start scan: " + e2, new Object[0]);
            this.c = null;
            return false;
        }
    }

    public final boolean stopScan() {
        if (this.c == null) {
            return false;
        }
        try {
            C4838sz1 a2 = this.b.a();
            if (a2 != null) {
                C5659xq xqVar = this.c;
                a2.f11313a.stopScan((C5178uz1) a2.b.remove(xqVar));
            }
        } catch (IllegalArgumentException e) {
            AbstractC1220Ua0.a("Bluetooth", "Cannot stop scan: " + e, new Object[0]);
        } catch (IllegalStateException e2) {
            AbstractC1220Ua0.a("Bluetooth", "Adapter is off. Cannot stop scan: " + e2, new Object[0]);
        }
        this.c = null;
        return true;
    }
}
