package defpackage;

/* renamed from: m6  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3663m6 implements AbstractC3087il0 {
    public final /* synthetic */ Runnable F;
    public final /* synthetic */ C2746gl0 G;
    public final /* synthetic */ Runnable H;

    public C3663m6(Runnable runnable, C2746gl0 gl0, Runnable runnable2) {
        this.F = runnable;
        this.G = gl0;
        this.H = runnable2;
    }

    @Override // defpackage.AbstractC3087il0
    public void d(UH0 uh0, int i) {
        if (i == 0) {
            this.F.run();
            this.G.b(uh0, 1);
        }
    }

    @Override // defpackage.AbstractC3087il0
    public void f(UH0 uh0, int i) {
        if (i != 1) {
            this.H.run();
        }
    }
}
