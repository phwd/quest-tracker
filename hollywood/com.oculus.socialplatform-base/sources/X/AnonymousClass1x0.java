package X;

import java.util.concurrent.ThreadFactory;

/* renamed from: X.1x0  reason: invalid class name */
public final class AnonymousClass1x0 {
    public long A00;
    public final int A01;
    public final AnonymousClass1x1[] A02;

    public final AnonymousClass1x1 A00() {
        int i = this.A01;
        if (i == 0) {
            return C12201wz.A02;
        }
        AnonymousClass1x1[] r4 = this.A02;
        long j = this.A00;
        this.A00 = 1 + j;
        return r4[(int) (j % ((long) i))];
    }

    public AnonymousClass1x0(int i, ThreadFactory threadFactory) {
        this.A01 = i;
        this.A02 = new AnonymousClass1x1[i];
        for (int i2 = 0; i2 < i; i2++) {
            this.A02[i2] = new AnonymousClass1x1(threadFactory);
        }
    }
}
