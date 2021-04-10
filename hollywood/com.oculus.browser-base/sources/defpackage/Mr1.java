package defpackage;

import J.N;
import org.chromium.midi.UsbMidiDeviceAndroid;

/* renamed from: Mr1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Mr1 implements Runnable {
    public final /* synthetic */ int F;
    public final /* synthetic */ byte[] G;
    public final /* synthetic */ UsbMidiDeviceAndroid H;

    public Mr1(UsbMidiDeviceAndroid usbMidiDeviceAndroid, int i, byte[] bArr) {
        this.H = usbMidiDeviceAndroid;
        this.F = i;
        this.G = bArr;
    }

    public void run() {
        UsbMidiDeviceAndroid usbMidiDeviceAndroid = this.H;
        if (!usbMidiDeviceAndroid.e) {
            N.MNGB4bj1(usbMidiDeviceAndroid.g, this.F, this.G);
        }
    }
}
