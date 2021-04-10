package X;

/* renamed from: X.0vy  reason: invalid class name and case insensitive filesystem */
public class C05540vy implements AnonymousClass06Z {
    public static final C05540vy A01 = new C05540vy();
    public final boolean A00 = true;

    @Override // X.AnonymousClass06Z
    public final int A24(CharSequence charSequence, int i, int i2) {
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
