package X;

import android.content.Context;
import java.lang.annotation.Annotation;

/* renamed from: X.3H  reason: invalid class name */
public abstract class AnonymousClass3H extends AnonymousClass8H implements PG {
    public final BZ A00;

    @Override // X.On, X.AnonymousClass8H
    public abstract <T> T getInstance(gz<T> gzVar, Context context);

    @Override // X.On, X.AnonymousClass8H
    public abstract <T> SY<T> getLazy(gz<T> gzVar, Context context);

    @Override // X.On, X.AnonymousClass8H
    public abstract <T> AbstractC0192Xx<T> getProvider(gz<T> gzVar, Context context);

    @Override // X.SZ, X.AnonymousClass8H
    public final SZ getApplicationInjector() {
        return this.A00.getApplicationInjector();
    }

    @Override // X.SZ
    public final Op getInjectorThreadStack() {
        return this.A00.getInjectorThreadStack();
    }

    @Override // X.On
    public final <T extends PC> T getScope(Class<? extends Annotation> cls) {
        return (T) this.A00.getScope(cls);
    }

    @Override // X.SZ
    public final BX getScopeAwareInjector() {
        if (!(this instanceof AnonymousClass0S)) {
            return this.A00.getScopeAwareInjector();
        }
        return (AnonymousClass0S) this;
    }

    @Override // X.SZ
    public final AnonymousClass3G getScopeUnawareInjector() {
        return this.A00.getScopeUnawareInjector();
    }

    public AnonymousClass3H(BZ bz) {
        this.A00 = bz;
    }
}
