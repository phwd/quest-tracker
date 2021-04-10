package X;

/* renamed from: X.0sW  reason: invalid class name */
public class AnonymousClass0sW implements AnonymousClass06T {
    public static final AnonymousClass0sW A00 = new AnonymousClass0sW();

    @Override // X.AnonymousClass06T
    public final int A1i(CharSequence charSequence, int i, int i2) {
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
