package defpackage;

/* renamed from: Wz1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Wz1 implements Runnable {
    public final /* synthetic */ C3506lA1 F;
    public final /* synthetic */ Yz1 G;

    public Wz1(Yz1 yz1, C3506lA1 la1) {
        this.G = yz1;
        this.F = la1;
    }

    public final void run() {
        Object obj;
        synchronized (this.G.b) {
            AbstractC1082Rs0 rs0 = this.G.c;
            if (rs0 != null) {
                C3506lA1 la1 = this.F;
                synchronized (la1.f10328a) {
                    Nz1.a(la1.c, "Task is not yet complete");
                    if (la1.e == null) {
                        obj = la1.d;
                    } else {
                        throw new XN0(la1.e);
                    }
                }
                rs0.a(obj);
            }
        }
    }
}
