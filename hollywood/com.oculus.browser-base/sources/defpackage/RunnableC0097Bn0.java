package defpackage;

/* renamed from: Bn0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC0097Bn0 implements Runnable {
    public final /* synthetic */ long F;
    public final /* synthetic */ int G;
    public final /* synthetic */ boolean H;
    public final /* synthetic */ C0402Gn0 I;

    public RunnableC0097Bn0(C0402Gn0 gn0, long j, int i, boolean z) {
        this.I = gn0;
        this.F = j;
        this.G = i;
        this.H = z;
    }

    public void run() {
        this.I.b.e.b(this.F, this.G);
        if (this.H) {
            this.I.b.e.a(this.G);
            this.I.b.e.d(new long[]{this.F});
        }
    }
}
