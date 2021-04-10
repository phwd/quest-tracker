package X;

/* renamed from: X.aF  reason: case insensitive filesystem */
public abstract class AbstractC0291aF implements AnonymousClass6S {
    public final AnonymousClass6T A00;

    public abstract boolean A00();

    @Override // X.AnonymousClass6S
    public final boolean A3H(CharSequence charSequence, int i, int i2) {
        if (charSequence == null || i < 0 || i2 < 0 || charSequence.length() - i2 < i) {
            throw new IllegalArgumentException();
        }
        AnonymousClass6T r0 = this.A00;
        if (r0 != null) {
            int A1Z = r0.A1Z(charSequence, i, i2);
            if (A1Z == 0) {
                return true;
            }
            if (A1Z == 1) {
                return false;
            }
        }
        return A00();
    }

    public AbstractC0291aF(AnonymousClass6T r1) {
        this.A00 = r1;
    }
}
