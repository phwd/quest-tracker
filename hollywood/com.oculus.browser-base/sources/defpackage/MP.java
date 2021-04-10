package defpackage;

/* renamed from: MP  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class MP implements Runnable {
    public final NP F;
    public final byte[] G;

    public MP(NP np, byte[] bArr) {
        this.F = np;
        this.G = bArr;
    }

    public void run() {
        NP np = this.F;
        np.i.d.onResult(this.G);
    }
}
