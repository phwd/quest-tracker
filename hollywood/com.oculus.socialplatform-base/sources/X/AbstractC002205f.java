package X;

import android.content.Context;
import java.lang.annotation.Annotation;
import javax.inject.Provider;

/* renamed from: X.05f  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC002205f extends AbstractC00640Hx implements AbstractC01140Ri {
    public final AnonymousClass0VF A00;

    @Override // X.AnonymousClass0RF, X.AbstractC00640Hx
    public abstract <T> T getInstance(AnonymousClass14P<T> v, Context context);

    @Override // X.AnonymousClass0RF, X.AbstractC00640Hx
    public abstract <T> AbstractC03180ld<T> getLazy(AnonymousClass14P<T> v, Context context);

    @Override // X.AnonymousClass0RF, X.AbstractC00640Hx
    public abstract <T> Provider<T> getProvider(AnonymousClass14P<T> v, Context context);

    @Override // X.AbstractC00640Hx, X.AnonymousClass0lg
    public final AnonymousClass0lg getApplicationInjector() {
        return this.A00.getApplicationInjector();
    }

    @Override // X.AnonymousClass0lg
    public final AnonymousClass0RH getInjectorThreadStack() {
        return this.A00.getInjectorThreadStack();
    }

    @Override // X.AnonymousClass0RF
    public final <T extends AbstractC01120Rf> T getScope(Class<? extends Annotation> cls) {
        return (T) this.A00.getScope(cls);
    }

    @Override // X.AnonymousClass0lg
    public AnonymousClass0VD getScopeAwareInjector() {
        return this.A00.getScopeAwareInjector();
    }

    @Override // X.AnonymousClass0lg
    public final C002105e getScopeUnawareInjector() {
        return this.A00.getScopeUnawareInjector();
    }

    public AbstractC002205f(AnonymousClass0VF r1) {
        this.A00 = r1;
    }
}
