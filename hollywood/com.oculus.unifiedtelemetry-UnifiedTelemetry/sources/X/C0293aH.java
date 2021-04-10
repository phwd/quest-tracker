package X;

/* renamed from: X.aH  reason: case insensitive filesystem */
public class C0293aH implements AnonymousClass6T {
    public static final C0293aH A01 = new C0293aH();
    public final boolean A00 = true;

    @Override // X.AnonymousClass6T
    public final int A1Z(CharSequence charSequence, int i, int i2) {
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
