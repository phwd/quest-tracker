package X;

import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.ContextScoped;

/* renamed from: X.11Q  reason: invalid class name */
public final class AnonymousClass11Q extends AnonymousClass0J4 implements AnonymousClass0op {
    @Override // X.AnonymousClass0op
    public final void A1n(AnonymousClass0Pp r4) {
        this.mBinder = r4;
        AnonymousClass0J2 r2 = (AnonymousClass0J2) r4.A3Y();
        bindScope(ContextScoped.class, new AnonymousClass0p9(r2));
        bindScope(ApplicationScoped.class, new AnonymousClass0pN(r2));
    }
}
