package X;

import javax.annotation.Nullable;

/* renamed from: X.0Ll  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC01760Ll<T> implements AbstractC02980bI<T> {
    public T A00;
    public final byte A01 = AnonymousClass0RZ.A01.get().A00;
    public final AbstractC02990bJ A02;
    @Nullable
    public volatile AbstractC03040bP A03;

    public abstract T A00(AbstractC02990bJ v);

    @Override // X.AbstractC07240oz, X.AbstractC02980bI
    public final T get() {
        if (this.A03 != null) {
            synchronized (this) {
                if (this.A03 != null) {
                    AbstractC03040bP r4 = this.A03;
                    AnonymousClass0RZ r3 = AnonymousClass0RZ.A01.get();
                    byte b = this.A01;
                    byte b2 = r3.A00;
                    r3.A00 = (byte) (b | b2);
                    Object A2P = r4.A2P();
                    try {
                        this.A00 = A00(this.A02.getScopeUnawareInjector());
                        this.A03 = null;
                    } finally {
                        r4.A2U(A2P);
                        r3.A00 = b2;
                    }
                }
            }
        }
        return this.A00;
    }

    public AbstractC01760Ll(AbstractC02990bJ r2) {
        this.A02 = r2;
        this.A03 = r2.getScopeAwareInjector();
    }
}
