package defpackage;

/* renamed from: DD0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class DD0 implements Runnable {
    public final ED0 F;
    public final boolean G;
    public final C2952hx H;

    public DD0(ED0 ed0, boolean z, C2952hx hxVar) {
        this.F = ed0;
        this.G = z;
        this.H = hxVar;
    }

    public void run() {
        ED0 ed0 = this.F;
        boolean z = this.G;
        C2952hx hxVar = this.H;
        if (z) {
            ed0.k.add(hxVar);
        }
        ed0.j.remove(hxVar);
        if (ed0.j.isEmpty()) {
            ed0.f.run();
        }
    }
}
