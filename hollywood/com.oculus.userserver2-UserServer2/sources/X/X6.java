package X;

import java.util.concurrent.Executor;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public final class X6 {
    public static final X6 A03 = new X6(null, null);
    @NullableDecl
    public X6 A00;
    public final Runnable A01;
    public final Executor A02;

    public X6(Runnable runnable, Executor executor) {
        this.A01 = runnable;
        this.A02 = executor;
    }
}
