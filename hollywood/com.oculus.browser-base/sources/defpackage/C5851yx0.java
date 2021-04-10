package defpackage;

/* renamed from: yx0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5851yx0 extends C4670s0 {
    public final C4491qx0 K;

    public C5851yx0(C5010u0 u0Var, int i, int i2, int i3, C4491qx0 qx0) {
        super(u0Var, i, i2, i3, qx0);
        this.K = qx0;
    }

    @Override // defpackage.AbstractC2504fI0, defpackage.C4670s0
    public void a(int i, Object obj) {
        C2465f50 f50 = (C2465f50) obj;
        super.a(i, f50);
        if (f50 == null || f50.d == null) {
            this.K.a(true);
        }
    }

    @Override // defpackage.C4670s0
    public void b(int i, C2465f50 f50) {
        super.a(i, f50);
        if (f50 == null || f50.d == null) {
            this.K.a(true);
        }
    }
}
