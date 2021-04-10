package defpackage;

/* renamed from: JL  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class JL {

    /* renamed from: a  reason: collision with root package name */
    public boolean f8285a;
    public long b;
    public boolean c;
    public long d;
    public boolean e;
    public boolean f;

    public final void a() {
        if (!this.c && this.d != 0) {
            this.c = true;
            AbstractC3364kK0.j("EphemeralTab.DurationOpened", (System.nanoTime() - this.d) / 1000000);
        }
    }

    public final void b() {
        if (!this.f8285a && this.b != 0) {
            this.f8285a = true;
            AbstractC3364kK0.j("EphemeralTab.DurationPeeked", (System.nanoTime() - this.b) / 1000000);
        }
    }

    public void c(int i) {
        if (this.e) {
            b();
            a();
            AbstractC3100ip1.f10165a.a("EphemeralTab.CtrPeek", this.f);
            AbstractC3100ip1.f10165a.a("EphemeralTab.Ctr", this.c);
            AbstractC3364kK0.g("EphemeralTab.BottomSheet.CloseReason", i, 10);
            this.f8285a = false;
            this.b = 0;
            this.c = false;
            this.d = 0;
            this.e = false;
            this.f = false;
        }
    }
}
