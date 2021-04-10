package X;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public final class AY {
    public static final AY A02 = new AY(false, null);
    public static final AY A03 = new AY(true, null);
    @NullableDecl
    public final Throwable A00;
    public final boolean A01;

    static {
        if (!AbstractC00136k.GENERATE_CANCELLATION_CAUSES) {
        }
    }

    public AY(boolean z, @NullableDecl Throwable th) {
        this.A01 = z;
        this.A00 = th;
    }
}
