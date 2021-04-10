package defpackage;

/* renamed from: wi0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5466wi0 implements Q31 {
    public final C5976zi0 F;

    public C5466wi0(C5976zi0 zi0) {
        this.F = zi0;
    }

    @Override // defpackage.Q31
    public Object get() {
        C5976zi0 zi0 = this.F;
        return Boolean.valueOf(zi0.f11760a.isFinishing() || zi0.f11760a.isDestroyed());
    }
}
