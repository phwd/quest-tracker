package X;

import java.util.concurrent.Executor;

/* renamed from: X.Cv  reason: case insensitive filesystem */
public final class C0076Cv {
    public static final int A01;
    public static final int A02;
    public static final C0076Cv A03 = new C0076Cv();
    public final Executor A00 = new ExecutorC0075Cu();

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        A01 = availableProcessors + 1;
        A02 = (availableProcessors << 1) + 1;
    }
}
