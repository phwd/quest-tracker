package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1ty  reason: invalid class name */
public final class AnonymousClass1ty {
    public static final AnonymousClass1ty A05 = new AnonymousClass1ty();
    @Nullable
    public final AnonymousClass1u3 A00;
    @Nullable
    public final AnonymousClass1u5 A01;
    @Nullable
    public final AnonymousClass1u2 A02;
    @Nullable
    public final AnonymousClass0LF A03;
    @Nullable
    public final AnonymousClass0VH A04;

    public AnonymousClass1ty() {
        this.A03 = null;
        this.A04 = null;
        this.A02 = null;
        this.A00 = null;
        this.A01 = null;
    }

    public AnonymousClass1ty(@Nullable AnonymousClass0TD r2, @Nullable AnonymousClass0LF r3, @Nullable AnonymousClass0VH r4) {
        this.A03 = r3;
        this.A04 = r4;
        this.A00 = new AnonymousClass1u3(r4);
        this.A02 = new AnonymousClass1u2(r2, r3, r4);
        this.A01 = new AnonymousClass1u5(r4);
    }
}
