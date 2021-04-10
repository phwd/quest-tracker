package defpackage;

import java.util.Objects;

/* renamed from: Dr1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Dr1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Jr1 f7917a;

    public Dr1(Jr1 jr1) {
        this.f7917a = jr1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Exception exc = (Exception) obj;
        C3538lM lMVar = this.f7917a.c;
        Objects.requireNonNull(lMVar);
        C5232vH0 vh0 = new C5232vH0();
        C5232vH0 vh02 = lMVar.b;
        C2684gM gMVar = new C2684gM(lMVar, vh0);
        C2855hM hMVar = new C2855hM();
        vh02.h(gMVar);
        vh02.a(hMVar);
        vh0.a(new Ir1());
    }
}
