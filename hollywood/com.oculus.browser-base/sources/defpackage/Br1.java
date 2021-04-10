package defpackage;

import java.util.List;
import java.util.Objects;
import org.chromium.base.ThreadUtils;

/* renamed from: Br1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Br1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Jr1 f7763a;

    public Br1(Jr1 jr1) {
        this.f7763a = jr1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Jr1 jr1 = this.f7763a;
        List list = (List) obj;
        Objects.requireNonNull(jr1);
        Object obj2 = ThreadUtils.f10596a;
        jr1.c(list, false);
        C4172p41 p41 = jr1.e;
        Objects.requireNonNull(p41);
        C5232vH0 vh0 = new C5232vH0();
        p41.d.g(new C3317k41(p41, false, list, vh0));
        p41.d = vh0;
    }
}
