package defpackage;

import java.util.Objects;
import org.chromium.components.signin.base.GoogleServiceAuthError;

/* renamed from: q1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4332q1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C4673s1 f11108a;

    public C4332q1(C4673s1 s1Var) {
        this.f11108a = s1Var;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        int i;
        int i2;
        C4673s1 s1Var = this.f11108a;
        Objects.requireNonNull(s1Var);
        if (((GoogleServiceAuthError) obj).f10893a == 1) {
            i = 12;
            i2 = 6;
        } else {
            i = 13;
            i2 = 5;
        }
        AbstractC3901nW0.a(i);
        s1Var.H.l(AbstractC5183v1.e, i2);
    }
}
