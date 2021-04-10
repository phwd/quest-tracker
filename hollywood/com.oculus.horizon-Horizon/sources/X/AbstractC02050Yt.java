package X;

import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;

/* renamed from: X.0Yt  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC02050Yt {
    @Nonnull
    public final AnonymousClass0ZR A00;
    public final ExecutorService A01;
    public final AnonymousClass0Z0 A02;

    public final synchronized void A00(@Nonnull AnonymousClass0ZS r8) {
        AnonymousClass0ZR r6 = this.A00;
        AnonymousClass0ZS A002 = r6.A00(r8);
        if (A002 != null) {
            r6.A04(A002, new AnonymousClass0ZS(A002.A02, A002.A00(), A002.A01 - 10, A002.A00 + 1));
            r6.A02();
        }
    }

    public AbstractC02050Yt(ExecutorService executorService, AnonymousClass0Z0 r4, C06510nV r5) {
        this.A01 = executorService;
        this.A02 = r4;
        this.A00 = new AnonymousClass0ZR(r5.A00(AnonymousClass0WE.ADDRESSES));
    }
}
