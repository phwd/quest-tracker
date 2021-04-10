package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.Collections;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1qg  reason: invalid class name */
public final class AnonymousClass1qg {
    public static final AnonymousClass0KO<Integer> A00;

    static {
        AnonymousClass0KO<Integer> r0 = new AnonymousClass0KO<>();
        Collections.addAll(r0, 2, 7, 4, 5);
        A00 = r0;
    }

    public static int A01(AnonymousClass1pN r2, AnonymousClass1qQ r3) {
        int i;
        int i2 = r2.A00;
        if (i2 == -2) {
            return 0;
        }
        AnonymousClass1qQ.A05(r3);
        int i3 = r3.A02;
        if (i3 == 90 || i3 == 180 || i3 == 270) {
            AnonymousClass1qQ.A05(r3);
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

    public static int A00(AnonymousClass1pN r5, AnonymousClass1qQ r6) {
        AnonymousClass1qQ.A05(r6);
        int i = r6.A00;
        AnonymousClass0KO<Integer> r4 = A00;
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
