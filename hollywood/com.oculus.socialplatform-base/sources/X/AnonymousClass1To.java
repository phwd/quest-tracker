package X;

import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.ContextScoped;

/* renamed from: X.1To  reason: invalid class name */
public final class AnonymousClass1To extends AnonymousClass0VH implements AbstractC03160lZ {
    @Override // X.AbstractC03160lZ
    public final void A2D(AnonymousClass0Qr r4) {
        this.mBinder = r4;
        AnonymousClass0VF r2 = (AnonymousClass0VF) r4.A4A();
        bindScope(ContextScoped.class, new C03210lj(r2));
        bindScope(ApplicationScoped.class, new AnonymousClass0mK(r2));
    }
}
