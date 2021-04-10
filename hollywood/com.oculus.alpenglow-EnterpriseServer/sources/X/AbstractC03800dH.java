package X;

/* renamed from: X.0dH  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC03800dH implements AnonymousClass09q {
    public final AbstractC008009r A00;

    public abstract boolean A00();

    @Override // X.AnonymousClass09q
    public final boolean A5Y(CharSequence charSequence, int i, int i2) {
        if (charSequence == null || i < 0 || i2 < 0 || charSequence.length() - i2 < i) {
            throw new IllegalArgumentException();
        }
        AbstractC008009r r0 = this.A00;
        if (r0 != null) {
            int A1i = r0.A1i(charSequence, i, i2);
            if (A1i == 0) {
                return true;
            }
            if (A1i == 1) {
                return false;
            }
        }
        return A00();
    }

    public AbstractC03800dH(AbstractC008009r r1) {
        this.A00 = r1;
    }
}
