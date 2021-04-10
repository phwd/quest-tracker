package X;

import javax.inject.Provider;

/* renamed from: X.0Hu  reason: invalid class name and case insensitive filesystem */
public final class C00620Hu<T> extends AnonymousClass0VG<T> implements AbstractC03180ld<T> {
    public T A00;
    public boolean A01;
    public final AnonymousClass0mK A02;
    public final Provider<T> A03;

    @Override // javax.inject.Provider, X.AbstractC03180ld
    public final synchronized T get() {
        if (!this.A01) {
            C01130Rh r4 = C01130Rh.A01.get();
            byte b = r4.A00;
            r4.A00 = (byte) (1 | b);
            try {
                AnonymousClass0RH A012 = this.A02.A01();
                try {
                    this.A00 = this.A03.get();
                    this.A01 = true;
                } finally {
                    AnonymousClass0mK.A00(A012);
                }
            } finally {
                r4.A00 = b;
            }
        }
        return this.A00;
    }

    public C00620Hu(AnonymousClass0mK r1, Provider<T> provider) {
        this.A02 = r1;
        this.A03 = provider;
    }
}
