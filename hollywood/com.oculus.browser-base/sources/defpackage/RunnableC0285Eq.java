package defpackage;

import J.N;
import org.chromium.device.bluetooth.ChromeBluetoothRemoteGattDescriptor;
import org.chromium.device.bluetooth.Wrappers$BluetoothGattDescriptorWrapper;

/* renamed from: Eq  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC0285Eq implements Runnable {
    public final /* synthetic */ Wrappers$BluetoothGattDescriptorWrapper F;
    public final /* synthetic */ int G;
    public final /* synthetic */ C0407Gq H;

    public RunnableC0285Eq(C0407Gq gq, Wrappers$BluetoothGattDescriptorWrapper wrappers$BluetoothGattDescriptorWrapper, int i) {
        this.H = gq;
        this.F = wrappers$BluetoothGattDescriptorWrapper;
        this.G = i;
    }

    public void run() {
        ChromeBluetoothRemoteGattDescriptor chromeBluetoothRemoteGattDescriptor = (ChromeBluetoothRemoteGattDescriptor) this.H.f8114a.f.get(this.F);
        if (chromeBluetoothRemoteGattDescriptor != null) {
            AbstractC3100ip1.f10165a.d("Bluetooth.Web.Android.onDescriptorRead.Status", this.G);
            int i = this.G;
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(i);
            objArr[1] = i == 0 ? "OK" : "Error";
            AbstractC1220Ua0.d("Bluetooth", "onDescriptorRead status:%d==%s", objArr);
            long j = chromeBluetoothRemoteGattDescriptor.f10945a;
            if (j != 0) {
                N.MaKNZo8k(j, chromeBluetoothRemoteGattDescriptor, i, chromeBluetoothRemoteGattDescriptor.b.f10952a.getValue());
            }
        }
    }
}
