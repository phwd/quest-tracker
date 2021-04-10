package X;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.117  reason: invalid class name */
public final class AnonymousClass117 {
    public static final AnonymousClass117 A00 = new AnonymousClass117(false);
    @NullableDecl
    public volatile AnonymousClass117 next;
    @NullableDecl
    public volatile Thread thread;

    public AnonymousClass117() {
        AnonymousClass0BR.ATOMIC_HELPER.A01(this, Thread.currentThread());
    }

    public AnonymousClass117(boolean z) {
    }
}
