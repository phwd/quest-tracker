package X;

import javax.inject.Provider;

/* renamed from: X.091  reason: invalid class name */
public final class AnonymousClass091<T> extends AnonymousClass0J3<T> implements AnonymousClass0p1<T> {
    public T A00;
    public boolean A01;
    public final AnonymousClass0pN A02;
    public final Provider<T> A03;

    @Override // javax.inject.Provider, X.AnonymousClass0p1
    public final synchronized T get() {
        if (!this.A01) {
            AnonymousClass0Qe r4 = AnonymousClass0Qe.A01.get();
            byte b = r4.A00;
            r4.A00 = (byte) (1 | b);
            try {
                AnonymousClass0QF A012 = this.A02.A01();
                try {
                    this.A00 = this.A03.get();
                    this.A01 = true;
                } finally {
                    AnonymousClass0pN.A00(A012);
                }
            } finally {
                r4.A00 = b;
            }
        }
        return this.A00;
    }

    public AnonymousClass091(AnonymousClass0pN r1, Provider<T> provider) {
        this.A02 = r1;
        this.A03 = provider;
    }
}
