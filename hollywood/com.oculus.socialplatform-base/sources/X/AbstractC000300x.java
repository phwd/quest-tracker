package X;

import android.content.Context;
import javax.inject.Provider;

/* renamed from: X.00x  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC000300x extends AbstractC002205f implements AnonymousClass0VD, AnonymousClass0RD {
    public final AnonymousClass0VF A00;

    @Override // X.AbstractC002205f, X.AnonymousClass0lg
    @Deprecated
    public final AnonymousClass0VD getScopeAwareInjector() {
        return this;
    }

    @Override // X.AnonymousClass0RF, X.AbstractC002205f, X.AbstractC00640Hx
    public final <T> AbstractC03180ld<T> getLazy(AnonymousClass14P<T> r3, Context context) {
        Provider<T> provider = this.A00.getScopeUnawareInjector().getProvider(r3, context);
        if (provider instanceof AbstractC03180ld) {
            return (AbstractC03180ld) provider;
        }
        return new AnonymousClass0VE(provider, this);
    }

    @Override // X.AnonymousClass0RF, X.AbstractC002205f, X.AbstractC00640Hx
    public final <T> Provider<T> getProvider(AnonymousClass14P<T> r3, Context context) {
        return new C03280lx(this, this.A00.getScopeUnawareInjector().getProvider(r3, context));
    }

    public AbstractC000300x(AnonymousClass0VF r1) {
        super(r1);
        this.A00 = r1;
    }

    @Override // X.AnonymousClass0RF
    public final Object getInstance(int i, Context context) {
        Object A2s = A2s();
        try {
            return this.A00.getScopeUnawareInjector().getInstance(i, context);
        } finally {
            A2u(A2s);
        }
    }

    @Override // X.AnonymousClass0RF, X.AbstractC002205f, X.AbstractC00640Hx
    public final <T> T getInstance(AnonymousClass14P<T> r3, Context context) {
        Object A2s = A2s();
        try {
            return (T) this.A00.getScopeUnawareInjector().getInstance(r3, context);
        } finally {
            A2u(A2s);
        }
    }
}
