package X;

import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.ContextScoped;

/* renamed from: X.15N  reason: invalid class name */
public final class AnonymousClass15N extends AnonymousClass0Lj implements AnonymousClass0bE {
    @Override // X.AnonymousClass0bE
    public final void A1m(AnonymousClass0Ql r4) {
        this.mBinder = r4;
        AnonymousClass0Lh r2 = (AnonymousClass0Lh) r4.A3l();
        bindScope(ContextScoped.class, new C03020bM(r2));
        bindScope(ApplicationScoped.class, new C03060bR(r2));
    }
}
