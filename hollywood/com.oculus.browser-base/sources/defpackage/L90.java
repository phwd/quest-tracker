package defpackage;

/* renamed from: L90  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class L90 extends AbstractC4312pu1 {
    public static final AbstractC4483qu1 b = new K90();
    public MY0 c = new MY0(10);

    @Override // defpackage.AbstractC4312pu1
    public void a() {
        if (this.c.i() <= 0) {
            MY0 my0 = this.c;
            int i = my0.f8482J;
            Object[] objArr = my0.I;
            for (int i2 = 0; i2 < i; i2++) {
                objArr[i2] = null;
            }
            my0.f8482J = 0;
            my0.G = false;
            return;
        }
        C5859z.a(this.c.j(0));
        throw null;
    }
}
