package defpackage;

/* renamed from: zt  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C6008zt implements Q31 {
    public final AbstractActivityC2601fu F;

    public C6008zt(AbstractActivityC2601fu fuVar) {
        this.F = fuVar;
    }

    @Override // defpackage.Q31
    public Object get() {
        Q31 q31;
        AbstractActivityC2601fu fuVar = this.F;
        I61 i61 = ((O71) fuVar.b1.V.Z0).N;
        i61.getClass();
        C3818n01 n01 = (C3818n01) ((AbstractC2451f01) fuVar.I1.get());
        AbstractC5959zc1 zc1 = n01.i;
        if (zc1 != null) {
            q31 = zc1.p();
        } else {
            q31 = new C3305k01(n01);
        }
        boolean booleanValue = Boolean.valueOf(i61.d()).booleanValue();
        if (q31 != null) {
            booleanValue = booleanValue || ((Boolean) q31.get()).booleanValue();
        }
        return Boolean.valueOf(booleanValue);
    }
}
