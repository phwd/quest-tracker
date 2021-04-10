package X;

import java.io.IOException;

/* renamed from: X.0h4  reason: invalid class name and case insensitive filesystem */
public final class C04220h4 implements AbstractC08380wS {
    public final AnonymousClass0h7 A00;

    @Override // X.AbstractC08380wS
    public final C08220wC intercept(AbstractC08390wT r3) throws IOException {
        this.A00.A00.getAndIncrement();
        C08330wN A8H = r3.A8H();
        synchronized (AnonymousClass0h6.class) {
        }
        try {
            C08220wC A7Z = r3.A7Z(A8H);
            synchronized (AnonymousClass0h6.class) {
            }
            return A7Z;
        } catch (IOException e) {
            synchronized (AnonymousClass0h6.class) {
                throw e;
            }
        }
    }

    public C04220h4() {
        AnonymousClass0h7 r0;
        synchronized (AnonymousClass0h7.class) {
            r0 = AnonymousClass0h7.A01;
            if (r0 == null) {
                r0 = new AnonymousClass0h7();
                AnonymousClass0h7.A01 = r0;
            }
        }
        this.A00 = r0;
    }
}
