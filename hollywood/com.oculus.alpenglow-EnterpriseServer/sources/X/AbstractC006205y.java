package X;

import android.content.Context;

/* renamed from: X.05y  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC006205y extends AbstractC007708r implements AnonymousClass0Lf, AnonymousClass0R6 {
    public final AnonymousClass0Lh A00;

    @Override // X.AbstractC007708r, X.AbstractC02990bJ
    @Deprecated
    public final AnonymousClass0Lf getScopeAwareInjector() {
        return this;
    }

    @Override // X.AbstractC01370Gk, X.AbstractC007708r, X.AnonymousClass0R8
    public final <T> AbstractC02980bI<T> getLazy(C01440Gz<T> r3, Context context) {
        AbstractC07240oz<T> provider = this.A00.getScopeUnawareInjector().getProvider(r3, context);
        if (provider instanceof AbstractC02980bI) {
            return (AbstractC02980bI) provider;
        }
        return new C01740Lg(provider, this);
    }

    @Override // X.AbstractC01370Gk, X.AbstractC007708r, X.AnonymousClass0R8
    public final <T> AbstractC07240oz<T> getProvider(C01440Gz<T> r3, Context context) {
        return new C03050bQ(this, this.A00.getScopeUnawareInjector().getProvider(r3, context));
    }

    public AbstractC006205y(AnonymousClass0Lh r1) {
        super(r1);
        this.A00 = r1;
    }

    @Override // X.AnonymousClass0R8
    public final Object getInstance(int i, Context context) {
        Object A2P = A2P();
        try {
            return this.A00.getScopeUnawareInjector().getInstance(i, context);
        } finally {
            A2U(A2P);
        }
    }

    @Override // X.AbstractC01370Gk, X.AbstractC007708r, X.AnonymousClass0R8
    public final <T> T getInstance(C01440Gz<T> r3, Context context) {
        Object A2P = A2P();
        try {
            return (T) this.A00.getScopeUnawareInjector().getInstance(r3, context);
        } finally {
            A2U(A2P);
        }
    }
}
