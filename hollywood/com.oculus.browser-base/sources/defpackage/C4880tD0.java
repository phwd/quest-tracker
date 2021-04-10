package defpackage;

import java.util.Objects;

/* renamed from: tD0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4880tD0 implements AbstractC0838Ns0 {

    /* renamed from: a  reason: collision with root package name */
    public final C5390wD0 f11330a;

    public C4880tD0(C5390wD0 wd0) {
        this.f11330a = wd0;
    }

    @Override // defpackage.AbstractC0838Ns0
    public void b(Exception exc) {
        C5390wD0 wd0 = this.f11330a;
        Objects.requireNonNull(wd0);
        AbstractC1220Ua0.d("PlayInline", "completeUpdate() failed.", new Object[0]);
        C5390wD0.f(2);
        wd0.g = 5;
        wd0.e();
    }
}
