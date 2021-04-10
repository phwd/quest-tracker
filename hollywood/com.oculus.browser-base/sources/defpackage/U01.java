package defpackage;

import java.util.Objects;

/* renamed from: U01  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class U01 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Y01 f9002a;

    public U01(Y01 y01) {
        this.f9002a = y01;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Y01 y01 = this.f9002a;
        Objects.requireNonNull(y01);
        if (((Boolean) obj).booleanValue() || y01.f9248a.h(Z01.f)) {
            y01.a((C0517Ik) y01.f.get());
        }
    }
}
