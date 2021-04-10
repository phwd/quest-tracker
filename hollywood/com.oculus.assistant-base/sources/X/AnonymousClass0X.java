package X;

/* renamed from: X.0X  reason: invalid class name */
public final class AnonymousClass0X extends PV {
    public static final AnonymousClass0X A01 = new AnonymousClass0X(false);
    public static final AnonymousClass0X A02 = new AnonymousClass0X(true);
    public final boolean A00;

    public final boolean equals(Object obj) {
        if (obj != this) {
            return obj != null && obj.getClass() == getClass() && this.A00 == ((AnonymousClass0X) obj).A00;
        }
        return true;
    }

    public AnonymousClass0X(boolean z) {
        this.A00 = z;
    }
}
