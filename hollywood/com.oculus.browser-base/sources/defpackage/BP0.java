package defpackage;

/* renamed from: BP0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class BP0 implements Runnable {
    public final GP0 F;

    public BP0(GP0 gp0) {
        this.F = gp0;
    }

    public void run() {
        GP0 gp0 = this.F;
        ZH0 zh0 = gp0.d;
        if (zh0 != null) {
            zh0.b();
        }
        NP0 np0 = gp0.c;
        if (np0 != null) {
            AbstractC2417ep1.k(np0);
        }
        gp0.c = null;
        gp0.d = null;
    }
}
