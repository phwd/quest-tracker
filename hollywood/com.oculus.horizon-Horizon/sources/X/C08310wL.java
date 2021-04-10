package X;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0wL  reason: invalid class name and case insensitive filesystem */
public final class C08310wL {
    public static final C08310wL A00 = new C08310wL(false);
    @NullableDecl
    public volatile C08310wL next;
    @NullableDecl
    public volatile Thread thread;

    public C08310wL() {
        AnonymousClass06Z.ATOMIC_HELPER.A01(this, Thread.currentThread());
    }

    public C08310wL(boolean z) {
    }
}
