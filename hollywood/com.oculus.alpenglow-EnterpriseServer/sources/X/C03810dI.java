package X;

import com.squareup.okhttp.internal.framed.Hpack;

/* renamed from: X.0dI  reason: invalid class name and case insensitive filesystem */
public class C03810dI implements AbstractC008009r {
    public static final C03810dI A00 = new C03810dI();

    @Override // X.AbstractC008009r
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
                        case Hpack.PREFIX_4_BITS /*{ENCODED_INT: 15}*/:
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
