package X;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public final class UM {
    public static final UM A02 = new UM(false, null);
    public static final UM A03 = new UM(true, null);
    @NullableDecl
    public final Throwable A00;
    public final boolean A01;

    static {
        if (!AnonymousClass6f.GENERATE_CANCELLATION_CAUSES) {
        }
    }

    public UM(boolean z, @NullableDecl Throwable th) {
        this.A01 = z;
        this.A00 = th;
    }
}
