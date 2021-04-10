package defpackage;

import J.N;
import org.chromium.device.bluetooth.ChromeBluetoothRemoteGattCharacteristic;
import org.chromium.device.bluetooth.Wrappers$BluetoothGattCharacteristicWrapper;

/* renamed from: Dq  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC0224Dq implements Runnable {
    public final /* synthetic */ Wrappers$BluetoothGattCharacteristicWrapper F;
    public final /* synthetic */ int G;
    public final /* synthetic */ C0407Gq H;

    public RunnableC0224Dq(C0407Gq gq, Wrappers$BluetoothGattCharacteristicWrapper wrappers$BluetoothGattCharacteristicWrapper, int i) {
        this.H = gq;
        this.F = wrappers$BluetoothGattCharacteristicWrapper;
        this.G = i;
    }

    public void run() {
        ChromeBluetoothRemoteGattCharacteristic chromeBluetoothRemoteGattCharacteristic = (ChromeBluetoothRemoteGattCharacteristic) this.H.f8114a.e.get(this.F);
        if (chromeBluetoothRemoteGattCharacteristic != null) {
            AbstractC3100ip1.f10165a.d("Bluetooth.Web.Android.onCharacteristicWrite.Status", this.G);
            int i = this.G;
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(i);
            objArr[1] = i == 0 ? "OK" : "Error";
            AbstractC1220Ua0.d("Bluetooth", "onCharacteristicWrite status:%d==%s", objArr);
            long j = chromeBluetoothRemoteGattCharacteristic.f10944a;
            if (j != 0) {
                N.M3TCxDs5(j, chromeBluetoothRemoteGattCharacteristic, i);
            }
        }
    }
}
