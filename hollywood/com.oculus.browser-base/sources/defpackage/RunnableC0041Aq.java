package defpackage;

import J.N;
import android.bluetooth.BluetoothGattService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.device.bluetooth.ChromeBluetoothDevice;
import org.chromium.device.bluetooth.Wrappers$BluetoothGattServiceWrapper;

/* renamed from: Aq  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC0041Aq implements Runnable {
    public final /* synthetic */ int F;
    public final /* synthetic */ C0407Gq G;

    public RunnableC0041Aq(C0407Gq gq, int i) {
        this.G = gq;
        this.F = i;
    }

    public void run() {
        ChromeBluetoothDevice chromeBluetoothDevice = this.G.f8114a;
        if (chromeBluetoothDevice.f10943a == 0) {
            return;
        }
        if (chromeBluetoothDevice.c == null) {
            AbstractC3100ip1.f10165a.d("Bluetooth.Web.Android.onServicesDiscovered.Status.Disconnected", this.F);
            return;
        }
        AbstractC3100ip1.f10165a.d("Bluetooth.Web.Android.onServicesDiscovered.Status.Connected", this.F);
        C4668rz1 rz1 = this.G.f8114a.c;
        List<BluetoothGattService> services = rz1.f11241a.getServices();
        ArrayList arrayList = new ArrayList(services.size());
        for (BluetoothGattService bluetoothGattService : services) {
            arrayList.add(new Wrappers$BluetoothGattServiceWrapper(bluetoothGattService, rz1.b));
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Wrappers$BluetoothGattServiceWrapper wrappers$BluetoothGattServiceWrapper = (Wrappers$BluetoothGattServiceWrapper) it.next();
            ChromeBluetoothDevice chromeBluetoothDevice2 = this.G.f8114a;
            N.MAoRk69U(chromeBluetoothDevice2.f10943a, chromeBluetoothDevice2, this.G.f8114a.getAddress() + "/" + wrappers$BluetoothGattServiceWrapper.f10953a.getUuid().toString() + "," + wrappers$BluetoothGattServiceWrapper.f10953a.getInstanceId(), wrappers$BluetoothGattServiceWrapper);
        }
        ChromeBluetoothDevice chromeBluetoothDevice3 = this.G.f8114a;
        N.M9HSgyay(chromeBluetoothDevice3.f10943a, chromeBluetoothDevice3);
    }
}
