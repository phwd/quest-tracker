package defpackage;

import J.N;
import org.chromium.device.bluetooth.ChromeBluetoothDevice;

/* renamed from: zq  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC5999zq implements Runnable {
    public final /* synthetic */ int F;
    public final /* synthetic */ int G;
    public final /* synthetic */ C0407Gq H;

    public RunnableC5999zq(C0407Gq gq, int i, int i2) {
        this.H = gq;
        this.F = i;
        this.G = i2;
    }

    public void run() {
        int i = this.F;
        if (i == 2) {
            AbstractC3100ip1.f10165a.d("Bluetooth.Web.Android.onConnectionStateChange.Status.Connected", this.G);
            this.H.f8114a.c.f11241a.discoverServices();
        } else if (i == 0) {
            AbstractC3100ip1.f10165a.d("Bluetooth.Web.Android.onConnectionStateChange.Status.Disconnected", this.G);
            C4668rz1 rz1 = this.H.f8114a.c;
            if (rz1 != null) {
                rz1.f11241a.close();
                this.H.f8114a.c = null;
            }
        } else {
            AbstractC3100ip1.f10165a.d("Bluetooth.Web.Android.onConnectionStateChange.Status.InvalidState", this.G);
        }
        ChromeBluetoothDevice chromeBluetoothDevice = this.H.f8114a;
        long j = chromeBluetoothDevice.f10943a;
        if (j != 0) {
            N.MmnW7gQC(j, chromeBluetoothDevice, this.G, this.F == 2);
        }
    }
}
