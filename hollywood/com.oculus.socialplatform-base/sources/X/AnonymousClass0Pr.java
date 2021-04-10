package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.0Pr  reason: invalid class name */
public final class AnonymousClass0Pr {
    public static int A00(AnonymousClass0PL r4, @Nullable AnonymousClass0PZ r5, int i) {
        boolean A07 = AnonymousClass0PZ.A07(r5);
        if (!A07) {
            return 1;
        }
        C00740Ii.A01(Boolean.valueOf(A07));
        if (r4 != null) {
            throw new NullPointerException("height");
        }
        AnonymousClass0PZ.A06(r5);
        AnonymousClass0Oj r3 = AnonymousClass0Oi.A05;
        int i2 = 1;
        AnonymousClass0PZ.A06(r5);
        int i3 = r5.A01;
        AnonymousClass0PZ.A06(r5);
        int max = Math.max(i3, r5.A05);
        float f = (float) i;
        while (((float) (max / i2)) > f) {
            AnonymousClass0PZ.A06(r5);
            if (r5.A07 == r3) {
                i2 <<= 1;
            } else {
                i2++;
            }
        }
        return i2;
    }
}
