package X;

import com.squareup.okhttp.internal.framed.Hpack;

/* renamed from: X.Tv  reason: case insensitive filesystem */
public class C0149Tv implements AnonymousClass7D {
    public static final C0149Tv A00 = new C0149Tv();

    @Override // X.AnonymousClass7D
    public final int A1A(CharSequence charSequence, int i, int i2) {
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
