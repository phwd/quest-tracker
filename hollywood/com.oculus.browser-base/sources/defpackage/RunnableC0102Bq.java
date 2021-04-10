package defpackage;

import J.N;
import org.chromium.device.bluetooth.ChromeBluetoothRemoteGattCharacteristic;
import org.chromium.device.bluetooth.Wrappers$BluetoothGattCharacteristicWrapper;

/* renamed from: Bq  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC0102Bq implements Runnable {
    public final /* synthetic */ Wrappers$BluetoothGattCharacteristicWrapper F;
    public final /* synthetic */ byte[] G;
    public final /* synthetic */ C0407Gq H;

    public RunnableC0102Bq(C0407Gq gq, Wrappers$BluetoothGattCharacteristicWrapper wrappers$BluetoothGattCharacteristicWrapper, byte[] bArr) {
        this.H = gq;
        this.F = wrappers$BluetoothGattCharacteristicWrapper;
        this.G = bArr;
    }

    public void run() {
        ChromeBluetoothRemoteGattCharacteristic chromeBluetoothRemoteGattCharacteristic = (ChromeBluetoothRemoteGattCharacteristic) this.H.f8114a.e.get(this.F);
        if (chromeBluetoothRemoteGattCharacteristic != null) {
            byte[] bArr = this.G;
            AbstractC1220Ua0.d("Bluetooth", "onCharacteristicChanged", new Object[0]);
            long j = chromeBluetoothRemoteGattCharacteristic.f10944a;
            if (j != 0) {
                N.MZCKcdTH(j, chromeBluetoothRemoteGattCharacteristic, bArr);
            }
        }
    }
}
