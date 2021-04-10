package defpackage;

import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanSettings;
import java.util.HashMap;
import java.util.List;

/* renamed from: sz1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4838sz1 {

    /* renamed from: a  reason: collision with root package name */
    public final BluetoothLeScanner f11313a;
    public final HashMap b = new HashMap();

    public C4838sz1(BluetoothLeScanner bluetoothLeScanner) {
        this.f11313a = bluetoothLeScanner;
    }

    public void a(List list, int i, C5659xq xqVar) {
        ScanSettings build = new ScanSettings.Builder().setScanMode(i).build();
        C5178uz1 uz1 = new C5178uz1(xqVar);
        this.b.put(xqVar, uz1);
        this.f11313a.startScan(list, build, uz1);
    }
}
