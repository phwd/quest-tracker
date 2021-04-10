package X;

/* renamed from: X.0vv  reason: invalid class name */
public abstract class AnonymousClass0vv implements AnonymousClass06Y {
    public final AnonymousClass06Z A00;

    public abstract boolean A00();

    @Override // X.AnonymousClass06Y
    public final boolean A69(CharSequence charSequence, int i, int i2) {
        if (charSequence == null || i < 0 || i2 < 0 || charSequence.length() - i2 < i) {
            throw new IllegalArgumentException();
        }
        AnonymousClass06Z r0 = this.A00;
        if (r0 != null) {
            int A24 = r0.A24(charSequence, i, i2);
            if (A24 == 0) {
                return true;
            }
            if (A24 == 1) {
                return false;
            }
        }
        return A00();
    }

    public AnonymousClass0vv(AnonymousClass06Z r1) {
        this.A00 = r1;
    }
}
