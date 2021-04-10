package defpackage;

import J.N;
import java.util.Objects;

/* renamed from: mI0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3700mI0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C4042oI0 f10412a;
    public final AbstractC0804Ne b;

    public C3700mI0(C4042oI0 oi0, AbstractC0804Ne ne) {
        this.f10412a = oi0;
        this.b = ne;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C4042oI0 oi0 = this.f10412a;
        AbstractC0804Ne ne = this.b;
        Objects.requireNonNull(oi0);
        ne.a(((Boolean) obj).booleanValue());
        long j = oi0.f;
        if (j != 0) {
            N.MJHnuE5A(j, oi0);
            oi0.f = 0;
        }
    }
}
