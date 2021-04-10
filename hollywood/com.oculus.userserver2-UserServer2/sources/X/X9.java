package X;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public final class X9 {
    public static final X9 A00 = new X9(false);
    @NullableDecl
    public volatile X9 next;
    @NullableDecl
    public volatile Thread thread;

    public X9() {
        AnonymousClass6f.ATOMIC_HELPER.A01(this, Thread.currentThread());
    }

    public X9(boolean z) {
    }
}
