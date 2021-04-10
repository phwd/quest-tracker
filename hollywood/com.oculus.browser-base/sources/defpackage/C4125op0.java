package defpackage;

/* renamed from: op0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4125op0 implements AbstractC3087il0 {
    public final /* synthetic */ C4296pp0 F;

    public C4125op0(C4296pp0 pp0) {
        this.F = pp0;
    }

    @Override // defpackage.AbstractC3087il0
    public void d(UH0 uh0, int i) {
        if (i == 0) {
            C4296pp0 pp0 = this.F;
            pp0.b.b(pp0.c, 1);
        } else if (i == 1) {
            C4296pp0 pp02 = this.F;
            pp02.b.b(pp02.c, 2);
        }
    }

    @Override // defpackage.AbstractC3087il0
    public void f(UH0 uh0, int i) {
        if (i == 1) {
            this.F.d.onResult(Boolean.TRUE);
        } else {
            this.F.d.onResult(Boolean.FALSE);
        }
        this.F.b.a();
    }
}
