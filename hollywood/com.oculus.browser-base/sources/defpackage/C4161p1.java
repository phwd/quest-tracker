package defpackage;

import java.util.Objects;

/* renamed from: p1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4161p1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C4673s1 f11044a;

    public C4161p1(C4673s1 s1Var) {
        this.f11044a = s1Var;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C4673s1 s1Var = this.f11044a;
        Objects.requireNonNull(s1Var);
        if (((Boolean) obj).booleanValue()) {
            s1Var.H.l(AbstractC5183v1.e, 1);
        }
    }
}
