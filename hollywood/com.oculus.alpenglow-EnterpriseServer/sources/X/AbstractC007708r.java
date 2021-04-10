package X;

import android.content.Context;
import java.lang.annotation.Annotation;

/* renamed from: X.08r  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC007708r extends AbstractC01370Gk implements AbstractC02020Rb {
    public final AnonymousClass0Lh A00;

    @Override // X.AbstractC01370Gk, X.AnonymousClass0R8
    public abstract <T> T getInstance(C01440Gz<T> v, Context context);

    @Override // X.AbstractC01370Gk, X.AnonymousClass0R8
    public abstract <T> AbstractC02980bI<T> getLazy(C01440Gz<T> v, Context context);

    @Override // X.AbstractC01370Gk, X.AnonymousClass0R8
    public abstract <T> AbstractC07240oz<T> getProvider(C01440Gz<T> v, Context context);

    @Override // X.AbstractC01370Gk, X.AbstractC02990bJ
    public final AbstractC02990bJ getApplicationInjector() {
        return this.A00.getApplicationInjector();
    }

    @Override // X.AbstractC02990bJ
    public final AnonymousClass0RA getInjectorThreadStack() {
        return this.A00.getInjectorThreadStack();
    }

    @Override // X.AnonymousClass0R8
    public final <T extends AnonymousClass0RX> T getScope(Class<? extends Annotation> cls) {
        return (T) this.A00.getScope(cls);
    }

    @Override // X.AbstractC02990bJ
    public AnonymousClass0Lf getScopeAwareInjector() {
        return this.A00.getScopeAwareInjector();
    }

    @Override // X.AbstractC02990bJ
    public final C007508o getScopeUnawareInjector() {
        return this.A00.getScopeUnawareInjector();
    }

    public AbstractC007708r(AnonymousClass0Lh r1) {
        this.A00 = r1;
    }
}
