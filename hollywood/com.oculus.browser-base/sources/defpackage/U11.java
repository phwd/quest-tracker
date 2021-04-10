package defpackage;

/* renamed from: U11  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class U11 implements Runnable {
    public final Z11 F;

    public U11(Z11 z11) {
        this.F = z11;
    }

    public void run() {
        Z11 z11 = this.F;
        if (!z11.h) {
            IJ a2 = z11.g.a();
            a2.c.put(z11.e, z11.f);
            z11.h = true;
        }
    }
}
