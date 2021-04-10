package X;

import android.content.Context;

/* renamed from: X.0S  reason: invalid class name */
public abstract class AnonymousClass0S extends AnonymousClass3H implements BX, Ol {
    public final BZ A00;

    @Override // X.AnonymousClass3H, X.On, X.AnonymousClass8H
    public final <T> SY<T> getLazy(gz<T> gzVar, Context context) {
        AbstractC0192Xx<T> provider = this.A00.getScopeUnawareInjector().getProvider(gzVar, context);
        if (provider instanceof SY) {
            return (SY) provider;
        }
        return new BY(provider, this);
    }

    @Override // X.AnonymousClass3H, X.On, X.AnonymousClass8H
    public final <T> AbstractC0192Xx<T> getProvider(gz<T> gzVar, Context context) {
        return new Sg(this, this.A00.getScopeUnawareInjector().getProvider(gzVar, context));
    }

    public AnonymousClass0S(BZ bz) {
        super(bz);
        this.A00 = bz;
    }

    @Override // X.On
    public final Object getInstance(int i, Context context) {
        Object A1U = A1U();
        try {
            return this.A00.getScopeUnawareInjector().getInstance(i, context);
        } finally {
            A1W(A1U);
        }
    }

    @Override // X.AnonymousClass3H, X.On, X.AnonymousClass8H
    public final <T> T getInstance(gz<T> gzVar, Context context) {
        Object A1U = A1U();
        try {
            return (T) this.A00.getScopeUnawareInjector().getInstance(gzVar, context);
        } finally {
            A1W(A1U);
        }
    }
}
