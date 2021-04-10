package defpackage;

import org.chromium.device.nfc.NfcDelegate;

/* renamed from: Do0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0221Do0 implements AbstractC0099Bo0 {
    public NfcDelegate F;
    public C0038Ao0 G;

    public C0221Do0(NfcDelegate nfcDelegate) {
        this.F = nfcDelegate;
    }

    @Override // defpackage.AbstractC0543Ix
    public void Y(C5475wl0 wl0) {
    }

    @Override // java.io.Closeable, defpackage.AbstractC3313k30, java.lang.AutoCloseable
    public void close() {
        C0038Ao0 ao0 = this.G;
        if (ao0 != null) {
            LN0 ln0 = ao0.H;
            if (ln0 != null) {
                ln0.close();
                ao0.H = null;
            }
            this.G = null;
        }
    }

    @Override // defpackage.AbstractC0099Bo0
    public void d0() {
        C0038Ao0 ao0 = this.G;
        if (ao0 != null) {
            ao0.M = true;
            ao0.i0();
            ao0.g0(ao0.h0(4, "The push operation is already cancelled."));
        }
    }

    @Override // defpackage.AbstractC0099Bo0
    public void s(int i, B30 b30) {
        LN0 ln0;
        C0038Ao0 ao0 = this.G;
        if (!(ao0 == null || (ln0 = ao0.H) == null)) {
            ln0.close();
            ao0.H = null;
        }
        this.G = new C0038Ao0(i, this.F, b30);
    }

    @Override // defpackage.AbstractC0099Bo0
    public void v() {
        C0038Ao0 ao0 = this.G;
        if (ao0 != null) {
            ao0.M = false;
            ao0.j0();
        }
    }
}
