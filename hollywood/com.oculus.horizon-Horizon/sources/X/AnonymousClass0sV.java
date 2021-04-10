package X;

/* renamed from: X.0sV  reason: invalid class name */
public abstract class AnonymousClass0sV implements AnonymousClass06S {
    public final AnonymousClass06T A00;

    public abstract boolean A00();

    @Override // X.AnonymousClass06S
    public final boolean A56(CharSequence charSequence, int i, int i2) {
        if (charSequence == null || i < 0 || i2 < 0 || charSequence.length() - i2 < i) {
            throw new IllegalArgumentException();
        }
        AnonymousClass06T r0 = this.A00;
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

    public AnonymousClass0sV(AnonymousClass06T r1) {
        this.A00 = r1;
    }
}
