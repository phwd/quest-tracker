package X;

import android.content.Context;
import javax.inject.Provider;

/* renamed from: X.00W  reason: invalid class name */
public abstract class AnonymousClass00W extends AnonymousClass04S implements AnonymousClass0Iy, AnonymousClass0QB {
    public final AnonymousClass0J2 A00;

    @Override // X.AbstractC06640p5, X.AnonymousClass04S
    @Deprecated
    public final AnonymousClass0Iy getScopeAwareInjector() {
        return this;
    }

    @Override // X.AnonymousClass0QD, X.AnonymousClass04S, X.AnonymousClass092
    public final <T> AnonymousClass0p1<T> getLazy(C09160zY<T> r3, Context context) {
        Provider<T> provider = this.A00.getScopeUnawareInjector().getProvider(r3, context);
        if (provider instanceof AnonymousClass0p1) {
            return (AnonymousClass0p1) provider;
        }
        return new C01030Iz(provider, this);
    }

    @Override // X.AnonymousClass0QD, X.AnonymousClass04S, X.AnonymousClass092
    public final <T> Provider<T> getProvider(C09160zY<T> r3, Context context) {
        return new AnonymousClass0pM(this, this.A00.getScopeUnawareInjector().getProvider(r3, context));
    }

    public AnonymousClass00W(AnonymousClass0J2 r1) {
        super(r1);
        this.A00 = r1;
    }

    @Override // X.AnonymousClass0QD
    public final Object getInstance(int i, Context context) {
        Object A2Y = A2Y();
        try {
            return this.A00.getScopeUnawareInjector().getInstance(i, context);
        } finally {
            A2b(A2Y);
        }
    }

    @Override // X.AnonymousClass0QD, X.AnonymousClass04S, X.AnonymousClass092
    public final <T> T getInstance(C09160zY<T> r3, Context context) {
        Object A2Y = A2Y();
        try {
            return (T) this.A00.getScopeUnawareInjector().getInstance(r3, context);
        } finally {
            A2b(A2Y);
        }
    }
}
