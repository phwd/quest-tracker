package X;

/* renamed from: X.Tw  reason: case insensitive filesystem */
public class C0150Tw implements AnonymousClass7D {
    public static final C0150Tw A01 = new C0150Tw();
    public final boolean A00 = true;

    @Override // X.AnonymousClass7D
    public final int A1A(CharSequence charSequence, int i, int i2) {
        int i3 = i2 + i;
        boolean z = false;
        while (i < i3) {
            byte directionality = Character.getDirectionality(charSequence.charAt(i));
            if (directionality == 0) {
                if (!this.A00) {
                    return 1;
                }
                z = true;
            } else if (directionality == 1 || directionality == 2) {
                if (this.A00) {
                    return 0;
                }
                z = true;
            }
            i++;
        }
        if (z) {
            return this.A00 ? 1 : 0;
        }
        return 2;
    }
}
