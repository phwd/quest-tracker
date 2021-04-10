package defpackage;

/* renamed from: MF  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MF {

    /* renamed from: a  reason: collision with root package name */
    public static MF f8467a;
    public final PU0 b;
    public int c;

    public MF(PU0 pu0) {
        this.b = pu0;
        c(pu0.f("contextual_search_tap_triggered_promo_count", 0));
    }

    public int a() {
        if (b()) {
            return this.c;
        }
        return -1 - this.c;
    }

    public boolean b() {
        return this.c >= 0;
    }

    public final void c(int i) {
        this.c = i;
        this.b.n("contextual_search_tap_triggered_promo_count", i);
    }
}
