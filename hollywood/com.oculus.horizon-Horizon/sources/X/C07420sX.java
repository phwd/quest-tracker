package X;

/* renamed from: X.0sX  reason: invalid class name and case insensitive filesystem */
public class C07420sX implements AnonymousClass06T {
    public static final C07420sX A01 = new C07420sX();
    public final boolean A00 = true;

    @Override // X.AnonymousClass06T
    public final int A1i(CharSequence charSequence, int i, int i2) {
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
