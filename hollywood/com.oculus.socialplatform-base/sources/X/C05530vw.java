package X;

/* renamed from: X.0vw  reason: invalid class name and case insensitive filesystem */
public class C05530vw implements AnonymousClass06Z {
    public static final C05530vw A00 = new C05530vw();

    @Override // X.AnonymousClass06Z
    public final int A24(CharSequence charSequence, int i, int i2) {
        int i3 = i2 + i;
        int i4 = 2;
        while (i < i3 && i4 == 2) {
            byte directionality = Character.getDirectionality(charSequence.charAt(i));
            i4 = 1;
            if (directionality != 0) {
                if (!(directionality == 1 || directionality == 2)) {
                    switch (directionality) {
                        case 14:
                        case 15:
                            break;
                        case 16:
                        case 17:
                            break;
                        default:
                            i4 = 2;
                            break;
                    }
                }
                i4 = 0;
            }
            i++;
        }
        return i4;
    }
}
