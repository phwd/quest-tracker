package X;

import java.util.concurrent.Executor;

/* renamed from: X.0Cw  reason: invalid class name and case insensitive filesystem */
public final class C00750Cw {
    public static final int A01;
    public static final int A02;
    public static final C00750Cw A03 = new C00750Cw();
    public final Executor A00 = new ExecutorC00740Cv();

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        A01 = availableProcessors + 1;
        A02 = (availableProcessors << 1) + 1;
    }
}
