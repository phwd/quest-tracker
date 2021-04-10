package X;

import android.content.Context;
import java.lang.annotation.Annotation;
import javax.inject.Provider;

/* renamed from: X.04S  reason: invalid class name */
public abstract class AnonymousClass04S extends AnonymousClass092 implements AnonymousClass0Qg {
    public final AnonymousClass0J2 A00;

    @Override // X.AnonymousClass0QD, X.AnonymousClass092
    public abstract <T> T getInstance(C09160zY<T> v, Context context);

    @Override // X.AnonymousClass0QD, X.AnonymousClass092
    public abstract <T> AnonymousClass0p1<T> getLazy(C09160zY<T> v, Context context);

    @Override // X.AnonymousClass0QD, X.AnonymousClass092
    public abstract <T> Provider<T> getProvider(C09160zY<T> v, Context context);

    @Override // X.AbstractC06640p5, X.AnonymousClass092
    public final AbstractC06640p5 getApplicationInjector() {
        return this.A00.getApplicationInjector();
    }

    @Override // X.AbstractC06640p5
    public final AnonymousClass0QF getInjectorThreadStack() {
        return this.A00.getInjectorThreadStack();
    }

    @Override // X.AnonymousClass0QD
    public final <T extends AbstractC01320Qc> T getScope(Class<? extends Annotation> cls) {
        return (T) this.A00.getScope(cls);
    }

    @Override // X.AbstractC06640p5
    public AnonymousClass0Iy getScopeAwareInjector() {
        return this.A00.getScopeAwareInjector();
    }

    @Override // X.AbstractC06640p5
    public final AnonymousClass04R getScopeUnawareInjector() {
        return this.A00.getScopeUnawareInjector();
    }

    public AnonymousClass04S(AnonymousClass0J2 r1) {
        this.A00 = r1;
    }
}
