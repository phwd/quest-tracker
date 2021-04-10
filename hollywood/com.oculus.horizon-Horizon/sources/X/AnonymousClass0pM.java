package X;

import javax.inject.Provider;

/* renamed from: X.0pM  reason: invalid class name */
public class AnonymousClass0pM implements Provider<T> {
    public final /* synthetic */ AnonymousClass00W A00;
    public final /* synthetic */ Provider A01;

    public AnonymousClass0pM(AnonymousClass00W r1, Provider provider) {
        this.A00 = r1;
        this.A01 = provider;
    }

    @Override // javax.inject.Provider
    public final T get() {
        AnonymousClass00W r2 = this.A00;
        Object A2Y = r2.A2Y();
        try {
            return (T) this.A01.get();
        } finally {
            r2.A2b(A2Y);
        }
    }
}
