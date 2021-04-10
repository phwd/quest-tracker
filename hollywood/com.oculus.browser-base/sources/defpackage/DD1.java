package defpackage;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: DD1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class DD1 {
    public static int a(int i, int i2) {
        String str;
        if (i >= 0 && i < i2) {
            return i;
        }
        if (i < 0) {
            str = AD1.a("%s (%s) must not be negative", "index", Integer.valueOf(i));
        } else if (i2 < 0) {
            throw new IllegalArgumentException(AbstractC2531fV.s(26, "negative size: ", i2));
        } else {
            str = AD1.a("%s (%s) must be less than size (%s)", "index", Integer.valueOf(i), Integer.valueOf(i2));
        }
        throw new IndexOutOfBoundsException(str);
    }

    public static String b(int i, int i2, @NullableDecl String str) {
        if (i < 0) {
            return AD1.a("%s (%s) must not be negative", str, Integer.valueOf(i));
        } else if (i2 >= 0) {
            return AD1.a("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i), Integer.valueOf(i2));
        } else {
            throw new IllegalArgumentException(AbstractC2531fV.s(26, "negative size: ", i2));
        }
    }

    public static void c(int i, int i2, int i3) {
        String str;
        if (i < 0 || i2 < i || i2 > i3) {
            if (i < 0 || i > i3) {
                str = b(i, i3, "start index");
            } else if (i2 < 0 || i2 > i3) {
                str = b(i2, i3, "end index");
            } else {
                str = AD1.a("end index (%s) must not be less than start index (%s)", Integer.valueOf(i2), Integer.valueOf(i));
            }
            throw new IndexOutOfBoundsException(str);
        }
    }

    public static void d(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static void e(boolean z, @NullableDecl String str, char c) {
        if (!z) {
            throw new IllegalArgumentException(AD1.a(str, Character.valueOf(c)));
        }
    }
}
