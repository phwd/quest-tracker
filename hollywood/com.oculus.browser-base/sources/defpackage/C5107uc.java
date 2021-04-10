package defpackage;

import java.util.Objects;

/* renamed from: uc  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5107uc extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C0859Oc f11421a;

    public C5107uc(C0859Oc oc) {
        this.f11421a = oc;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C0859Oc oc = this.f11421a;
        Objects.requireNonNull(oc);
        oc.I = ((A31) obj).b;
    }
}
