package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1ig  reason: invalid class name */
public final class AnonymousClass1ig {
    public static void A00(int i, int i2, int i3, int i4, int i5) {
        boolean z = true;
        boolean z2 = false;
        if (i4 >= 0) {
            z2 = true;
        }
        C00740Ii.A01(Boolean.valueOf(z2));
        boolean z3 = false;
        if (i >= 0) {
            z3 = true;
        }
        C00740Ii.A01(Boolean.valueOf(z3));
        boolean z4 = false;
        if (i3 >= 0) {
            z4 = true;
        }
        C00740Ii.A01(Boolean.valueOf(z4));
        boolean z5 = false;
        if (i + i4 <= i5) {
            z5 = true;
        }
        C00740Ii.A01(Boolean.valueOf(z5));
        if (i3 + i4 > i2) {
            z = false;
        }
        C00740Ii.A01(Boolean.valueOf(z));
    }
}
