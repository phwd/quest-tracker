package X;

/* renamed from: X.03A  reason: invalid class name */
public final class AnonymousClass03A extends AnonymousClass07G {
    public static final AnonymousClass03A A01 = new AnonymousClass03A(false);
    public static final AnonymousClass03A A02 = new AnonymousClass03A(true);
    public final boolean A00;

    @Override // X.AbstractC03980gY
    public final boolean equals(Object obj) {
        if (obj != this) {
            return obj != null && obj.getClass() == getClass() && this.A00 == ((AnonymousClass03A) obj).A00;
        }
        return true;
    }

    @Override // X.AbstractC03980gY
    public final String A02() {
        if (this.A00) {
            return "true";
        }
        return "false";
    }

    public AnonymousClass03A(boolean z) {
        this.A00 = z;
    }
}
