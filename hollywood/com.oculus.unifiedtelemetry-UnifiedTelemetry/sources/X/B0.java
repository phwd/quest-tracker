package X;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public final class B0 {
    public static final B0 A00 = new B0(false);
    @NullableDecl
    public volatile B0 next;
    @NullableDecl
    public volatile Thread thread;

    public B0() {
        AbstractC00136k.ATOMIC_HELPER.A01(this, Thread.currentThread());
    }

    public B0(boolean z) {
    }
}
