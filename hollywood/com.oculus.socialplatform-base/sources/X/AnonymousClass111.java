package X;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.111  reason: invalid class name */
public final class AnonymousClass111 {
    public static final AnonymousClass111 A02 = new AnonymousClass111(false, null);
    public static final AnonymousClass111 A03 = new AnonymousClass111(true, null);
    @NullableDecl
    public final Throwable A00;
    public final boolean A01;

    static {
        if (!AnonymousClass0BR.GENERATE_CANCELLATION_CAUSES) {
        }
    }

    public AnonymousClass111(boolean z, @NullableDecl Throwable th) {
        this.A01 = z;
        this.A00 = th;
    }
}
