package defpackage;

/* renamed from: Uz1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Uz1 implements Runnable {
    public final /* synthetic */ C3506lA1 F;
    public final /* synthetic */ Sz1 G;

    public Uz1(Sz1 sz1, C3506lA1 la1) {
        this.G = sz1;
        this.F = la1;
    }

    public final void run() {
        Exception exc;
        synchronized (this.G.b) {
            AbstractC0838Ns0 ns0 = this.G.c;
            if (ns0 != null) {
                C3506lA1 la1 = this.F;
                synchronized (la1.f10328a) {
                    exc = la1.e;
                }
                ns0.b(exc);
            }
        }
    }
}
