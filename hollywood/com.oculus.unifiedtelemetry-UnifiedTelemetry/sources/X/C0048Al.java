package X;

import java.util.concurrent.Executor;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.Al  reason: case insensitive filesystem */
public final class C0048Al {
    public static final C0048Al A03 = new C0048Al(null, null);
    @NullableDecl
    public C0048Al A00;
    public final Runnable A01;
    public final Executor A02;

    public C0048Al(Runnable runnable, Executor executor) {
        this.A01 = runnable;
        this.A02 = executor;
    }
}
