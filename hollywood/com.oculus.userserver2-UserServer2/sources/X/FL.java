package X;

import java.util.concurrent.Executor;

public final class FL {
    public static final int A01;
    public static final int A02;
    public static final FL A03 = new FL();
    public final Executor A00 = new FK();

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        A01 = availableProcessors + 1;
        A02 = (availableProcessors << 1) + 1;
    }
}
