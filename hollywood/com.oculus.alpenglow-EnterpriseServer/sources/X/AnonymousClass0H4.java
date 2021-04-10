package X;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0H4  reason: invalid class name */
public final class AnonymousClass0H4 {
    public static final AnonymousClass0H4 A00 = new AnonymousClass0H4(false);
    @NullableDecl
    public volatile AnonymousClass0H4 next;
    @NullableDecl
    public volatile Thread thread;

    public AnonymousClass0H4() {
        AnonymousClass0BX.ATOMIC_HELPER.A01(this, Thread.currentThread());
    }

    public AnonymousClass0H4(boolean z) {
    }
}
