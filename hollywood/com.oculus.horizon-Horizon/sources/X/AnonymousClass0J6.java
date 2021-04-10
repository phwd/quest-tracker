package X;

import javax.annotation.Nullable;

/* renamed from: X.0J6  reason: invalid class name */
public abstract class AnonymousClass0J6<T> implements AnonymousClass0p1<T> {
    public T A00;
    public final byte A01 = AnonymousClass0Qe.A01.get().A00;
    public final AbstractC06640p5 A02;
    @Nullable
    public volatile AnonymousClass0pL A03;

    public abstract T A00(AbstractC06640p5 v);

    @Override // javax.inject.Provider, X.AnonymousClass0p1
    public final T get() {
        if (this.A03 != null) {
            synchronized (this) {
                if (this.A03 != null) {
                    AnonymousClass0pL r4 = this.A03;
                    AnonymousClass0Qe r3 = AnonymousClass0Qe.A01.get();
                    byte b = this.A01;
                    byte b2 = r3.A00;
                    r3.A00 = (byte) (b | b2);
                    Object A2Y = r4.A2Y();
                    try {
                        this.A00 = A00(this.A02.getScopeUnawareInjector());
                        this.A03 = null;
                    } finally {
                        r4.A2b(A2Y);
                        r3.A00 = b2;
                    }
                }
            }
        }
        return this.A00;
    }

    public AnonymousClass0J6(AbstractC06640p5 r2) {
        this.A02 = r2;
        this.A03 = r2.getScopeAwareInjector();
    }
}
