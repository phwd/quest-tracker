package X;

import java.util.concurrent.Executor;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0HF  reason: invalid class name */
public final class AnonymousClass0HF {
    public static final AnonymousClass0HF A03 = new AnonymousClass0HF(null, null);
    @NullableDecl
    public AnonymousClass0HF A00;
    public final Runnable A01;
    public final Executor A02;

    public AnonymousClass0HF(Runnable runnable, Executor executor) {
        this.A01 = runnable;
        this.A02 = executor;
    }
}
