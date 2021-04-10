package defpackage;

import java.util.List;
import java.util.Objects;

/* renamed from: Fr1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Fr1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Jr1 f8042a;
    public final List b;

    public Fr1(Jr1 jr1, List list) {
        this.f8042a = jr1;
        this.b = list;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Jr1 jr1 = this.f8042a;
        List list = this.b;
        Exception exc = (Exception) obj;
        C3538lM lMVar = jr1.c;
        Objects.requireNonNull(lMVar);
        C5232vH0 vh0 = new C5232vH0();
        C5232vH0 vh02 = lMVar.b;
        C3367kM kMVar = new C3367kM(lMVar, list, vh0);
        YL yl = new YL();
        vh02.h(kMVar);
        vh02.a(yl);
        vh0.a(new Gr1());
    }
}
