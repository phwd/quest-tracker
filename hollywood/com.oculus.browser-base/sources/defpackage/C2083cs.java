package defpackage;

/* renamed from: cs  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2083cs extends AbstractC2059ck {
    public Runnable F;
    public final /* synthetic */ C2253ds G;

    public C2083cs(C2253ds dsVar) {
        this.G = dsVar;
    }

    @Override // defpackage.AbstractC2230dk
    public void j(int i, int i2, int i3, int i4, boolean z) {
        if (this.F != null && AbstractC2571fk.a(this.G.c)) {
            this.F.run();
            this.F = null;
        }
    }
}
