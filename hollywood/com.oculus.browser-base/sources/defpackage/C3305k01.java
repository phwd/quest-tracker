package defpackage;

/* renamed from: k01  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3305k01 implements Q31 {
    public final C3818n01 F;

    public C3305k01(C3818n01 n01) {
        this.F = n01;
    }

    @Override // defpackage.Q31
    public Object get() {
        C3818n01 n01 = this.F;
        if (((C4778sf1) n01.g).a() != null && ((Boolean) ((C4778sf1) n01.g).a().get()).booleanValue()) {
            return Boolean.TRUE;
        }
        AbstractC4096of1 of1 = n01.h;
        if (of1 == null || ((C4778sf1) of1).a() == null || !((Boolean) ((C4778sf1) n01.h).a().get()).booleanValue()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
