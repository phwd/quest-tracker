package defpackage;

import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: la0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3567la0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C3909na0 f10353a;

    public C3567la0(C3909na0 na0) {
        this.f10353a = na0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C3909na0 na0 = this.f10353a;
        AbstractView$OnClickListenerC5272va0 va0 = na0.F;
        int intValue = ((Integer) obj).intValue();
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        va0.setLayoutDirection(intValue);
        na0.f10497J.f();
    }
}
