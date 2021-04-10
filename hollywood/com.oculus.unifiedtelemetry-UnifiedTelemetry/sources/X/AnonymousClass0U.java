package X;

import android.content.Context;

/* renamed from: X.0U  reason: invalid class name */
public abstract class AnonymousClass0U extends AnonymousClass42 implements AbstractC0094Hs, QB {
    public final AbstractC0096Hu A00;

    @Override // X.AbstractC0247Xu, X.AnonymousClass42
    @Deprecated
    public final AbstractC0094Hs getScopeAwareInjector() {
        return this;
    }

    @Override // X.QD, X.AnonymousClass42, X.AnonymousClass8g
    public final <T> AbstractC0246Xt<T> getLazy(C0475qE<T> qEVar, Context context) {
        eJ<T> provider = this.A00.getScopeUnawareInjector().getProvider(qEVar, context);
        if (provider instanceof AbstractC0246Xt) {
            return (AbstractC0246Xt) provider;
        }
        return new C0095Ht(provider, this);
    }

    @Override // X.QD, X.AnonymousClass42, X.AnonymousClass8g
    public final <T> eJ<T> getProvider(C0475qE<T> qEVar, Context context) {
        return new Y1(this, this.A00.getScopeUnawareInjector().getProvider(qEVar, context));
    }

    public AnonymousClass0U(AbstractC0096Hu hu) {
        super(hu);
        this.A00 = hu;
    }

    @Override // X.QD
    public final Object getInstance(int i, Context context) {
        Object A1y = A1y();
        try {
            return this.A00.getScopeUnawareInjector().getInstance(i, context);
        } finally {
            A20(A1y);
        }
    }

    @Override // X.QD, X.AnonymousClass42, X.AnonymousClass8g
    public final <T> T getInstance(C0475qE<T> qEVar, Context context) {
        Object A1y = A1y();
        try {
            return (T) this.A00.getScopeUnawareInjector().getInstance(qEVar, context);
        } finally {
            A20(A1y);
        }
    }
}
