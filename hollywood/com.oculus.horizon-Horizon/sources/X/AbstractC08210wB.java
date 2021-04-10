package X;

import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.Charset;

/* renamed from: X.0wB  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC08210wB implements Closeable {
    public abstract long A00();

    public abstract C08370wR A02();

    public abstract AnonymousClass0Lw A03();

    public final String A01() throws IOException {
        Charset charset;
        Charset charset2;
        AnonymousClass0Lw A03 = A03();
        try {
            C08370wR A02 = A02();
            if (A02 != null) {
                charset2 = C08160w5.A04;
                charset = charset2;
                String str = A02.A00;
                if (str != null) {
                    charset = Charset.forName(str);
                }
            } else {
                charset = C08160w5.A04;
                charset2 = charset;
            }
            C07700vD r1 = C08160w5.A0D;
            if (A03.A7n(0, r1)) {
                A03.A94((long) r1.A07());
                charset = charset2;
            } else {
                C07700vD r12 = C08160w5.A09;
                if (A03.A7n(0, r12)) {
                    A03.A94((long) r12.A07());
                    charset = C08160w5.A00;
                } else {
                    C07700vD r13 = C08160w5.A0A;
                    if (A03.A7n(0, r13)) {
                        A03.A94((long) r13.A07());
                        charset = C08160w5.A01;
                    } else {
                        C07700vD r14 = C08160w5.A0B;
                        if (A03.A7n(0, r14)) {
                            A03.A94((long) r14.A07());
                            charset = C08160w5.A02;
                        } else {
                            C07700vD r15 = C08160w5.A0C;
                            if (A03.A7n(0, r15)) {
                                A03.A94((long) r15.A07());
                                charset = C08160w5.A03;
                            }
                        }
                    }
                }
            }
            return A03.A80(charset);
        } finally {
            C08160w5.A06(A03);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        C08160w5.A06(A03());
    }
}
