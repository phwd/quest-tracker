package X;

import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;

/* renamed from: X.0xJ  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC08480xJ {
    @Nonnull
    public final C08410xC A00;
    public final ExecutorService A01;
    public final C07630vg A02;

    public final synchronized void A00(@Nonnull C08440xF r8) {
        C08410xC r6 = this.A00;
        C08440xF A002 = r6.A00(r8);
        if (A002 != null) {
            r6.A04(A002, new C08440xF(A002.A02, A002.A00(), A002.A01 - 10, A002.A00 + 1));
            r6.A02();
        }
    }

    public AbstractC08480xJ(ExecutorService executorService, C07630vg r4, C07710vp r5) {
        this.A01 = executorService;
        this.A02 = r4;
        this.A00 = new C08410xC(r5.A00(EnumC07690vn.ADDRESSES));
    }
}
