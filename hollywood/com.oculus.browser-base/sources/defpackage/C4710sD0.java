package defpackage;

import java.util.Objects;

/* renamed from: sD0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4710sD0 implements AbstractC1082Rs0 {

    /* renamed from: a  reason: collision with root package name */
    public final C5390wD0 f11258a;

    public C4710sD0(C5390wD0 wd0) {
        this.f11258a = wd0;
    }

    @Override // defpackage.AbstractC1082Rs0
    public void a(Object obj) {
        C5390wD0 wd0 = this.f11258a;
        Void r4 = (Void) obj;
        Objects.requireNonNull(wd0);
        AbstractC1220Ua0.d("PlayInline", "completeUpdate() success.", new Object[0]);
        wd0.e();
    }
}
