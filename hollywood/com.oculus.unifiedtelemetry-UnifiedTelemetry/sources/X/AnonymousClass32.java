package X;

/* renamed from: X.32  reason: invalid class name */
public final class AnonymousClass32 extends AnonymousClass7I {
    public static final AnonymousClass32 A01 = new AnonymousClass32(false);
    public static final AnonymousClass32 A02 = new AnonymousClass32(true);
    public final boolean A00;

    @Override // X.AbstractC0222Wi
    public final boolean equals(Object obj) {
        if (obj != this) {
            return obj != null && obj.getClass() == getClass() && this.A00 == ((AnonymousClass32) obj).A00;
        }
        return true;
    }

    @Override // X.AbstractC0222Wi
    public final String A06() {
        if (this.A00) {
            return "true";
        }
        return "false";
    }

    public AnonymousClass32(boolean z) {
        this.A00 = z;
    }

    @Override // X.AbstractC0222Wi
    public final boolean A03() {
        return this.A00;
    }

    @Override // X.AbstractC0222Wi
    public final Integer A04() {
        return AnonymousClass07.A02;
    }
}
