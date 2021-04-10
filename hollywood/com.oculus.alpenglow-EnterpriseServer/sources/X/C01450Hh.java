package X;

import java.util.concurrent.Executor;

/* renamed from: X.0Hh  reason: invalid class name and case insensitive filesystem */
public final class C01450Hh {
    public static final int A01;
    public static final int A02;
    public static final C01450Hh A03 = new C01450Hh();
    public final Executor A00 = new AnonymousClass0Hg();

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        A01 = availableProcessors + 1;
        A02 = (availableProcessors << 1) + 1;
    }
}
