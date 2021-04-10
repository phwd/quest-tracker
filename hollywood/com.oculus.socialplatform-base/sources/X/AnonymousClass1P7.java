package X;

import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;

/* renamed from: X.1P7  reason: invalid class name */
public abstract class AnonymousClass1P7 {
    @Nonnull
    public final AnonymousClass1P9 A00;
    public final ExecutorService A01;
    public final AnonymousClass24D A02;

    public final synchronized void A00(@Nonnull AnonymousClass1P5 r8) {
        AnonymousClass1P9 r6 = this.A00;
        AnonymousClass1P5 A002 = r6.A00(r8);
        if (A002 != null) {
            r6.A04(A002, new AnonymousClass1P5(A002.A02, A002.A00(), A002.A01 - 10, A002.A00 + 1));
            r6.A02();
        }
    }

    public AnonymousClass1P7(ExecutorService executorService, AnonymousClass24D r4, AnonymousClass1PM r5) {
        this.A01 = executorService;
        this.A02 = r4;
        this.A00 = new AnonymousClass1P9(r5.A00(AnonymousClass1PL.ADDRESSES));
    }
}
