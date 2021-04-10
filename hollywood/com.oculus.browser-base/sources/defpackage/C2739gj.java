package defpackage;

import java.util.Objects;

/* renamed from: gj  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2739gj extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C2910hj f10014a;

    public C2739gj(C2910hj hjVar) {
        this.f10014a = hjVar;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C2910hj hjVar = this.f10014a;
        Objects.requireNonNull(hjVar);
        hjVar.M = ((Boolean) obj).booleanValue();
        hjVar.g();
    }
}
