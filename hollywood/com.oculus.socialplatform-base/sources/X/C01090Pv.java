package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.Collections;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.0Pv  reason: invalid class name and case insensitive filesystem */
public final class C01090Pv {
    public static final C00680Ic<Integer> A00;

    static {
        C00680Ic<Integer> r0 = new C00680Ic<>();
        Collections.addAll(r0, 2, 7, 4, 5);
        A00 = r0;
    }

    public static int A00(AnonymousClass0PO r2, @Nullable AnonymousClass0PL r3, AnonymousClass0PZ r4, boolean z) {
        if (!z || r3 == null) {
            return 8;
        }
        A02(r2, r4);
        C00680Ic<Integer> r1 = A00;
        AnonymousClass0PZ.A06(r4);
        if (r1.contains(Integer.valueOf(r4.A00))) {
            A01(r2, r4);
        }
        AnonymousClass0PZ.A06(r4);
        AnonymousClass0PZ.A06(r4);
        throw new NullPointerException("width");
    }

    public static int A02(AnonymousClass0PO r2, AnonymousClass0PZ r3) {
        int i;
        int i2 = r2.A00;
        if (i2 == -2) {
            return 0;
        }
        AnonymousClass0PZ.A06(r3);
        int i3 = r3.A02;
        if (i3 == 90 || i3 == 180 || i3 == 270) {
            AnonymousClass0PZ.A06(r3);
            i = r3.A02;
        } else {
            i = 0;
        }
        if (i2 == -1) {
            return i;
        }
        if (i2 != -1) {
            return (i + i2) % 360;
        }
        throw new IllegalStateException("Rotation is set to use EXIF");
    }

    public static int A01(AnonymousClass0PO r5, AnonymousClass0PZ r6) {
        AnonymousClass0PZ.A06(r6);
        int i = r6.A00;
        C00680Ic<Integer> r4 = A00;
        int indexOf = r4.indexOf(Integer.valueOf(i));
        if (indexOf >= 0) {
            int i2 = 0;
            int i3 = r5.A00;
            if (i3 != -1) {
                if (i3 == -1) {
                    throw new IllegalStateException("Rotation is set to use EXIF");
                }
                i2 = i3;
            }
            return r4.get((indexOf + (i2 / 90)) % r4.size()).intValue();
        }
        throw new IllegalArgumentException("Only accepts inverted exif orientations");
    }
}
