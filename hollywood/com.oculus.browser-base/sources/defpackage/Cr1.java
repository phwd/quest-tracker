package defpackage;

import java.util.List;
import java.util.Objects;
import org.chromium.base.ThreadUtils;

/* renamed from: Cr1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Cr1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Jr1 f7844a;

    public Cr1(Jr1 jr1) {
        this.f7844a = jr1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Jr1 jr1 = this.f7844a;
        Objects.requireNonNull(jr1);
        for (String str : (List) obj) {
            Object obj2 = ThreadUtils.f10596a;
            C3766mj1 mj1 = jr1.f;
            Objects.requireNonNull(mj1);
            C5232vH0 vh0 = new C5232vH0();
            C5232vH0 vh02 = mj1.f10443a;
            C2912hj1 hj1 = new C2912hj1(mj1, str, vh0);
            C3082ij1 ij1 = new C3082ij1();
            vh02.h(hj1);
            vh02.a(ij1);
        }
    }
}
