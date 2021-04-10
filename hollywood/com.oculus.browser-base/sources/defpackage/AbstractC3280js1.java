package defpackage;

/* renamed from: js1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3280js1 {

    /* renamed from: a  reason: collision with root package name */
    public static final AbstractC2597fs1 f10244a;

    static {
        AbstractC2597fs1 fs1;
        if (!(Op1.g && Op1.f) || P4.a()) {
            fs1 = new C2768gs1();
        } else {
            fs1 = new C3109is1();
        }
        f10244a = fs1;
    }

    public static int a(int i, int i2) {
        if (i > -12 || i2 > -65) {
            return -1;
        }
        return i ^ (i2 << 8);
    }

    public static int b(byte[] bArr, int i, int i2) {
        byte b = bArr[i - 1];
        int i3 = i2 - i;
        int i4 = -1;
        if (i3 == 0) {
            if (b > -12) {
                b = -1;
            }
            return b;
        } else if (i3 == 1) {
            byte b2 = bArr[i];
            if (b <= -12 && b2 <= -65) {
                i4 = b ^ (b2 << 8);
            }
            return i4;
        } else if (i3 == 2) {
            return d(b, bArr[i], bArr[i + 1]);
        } else {
            throw new AssertionError();
        }
    }

    public static int c(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        int i2 = 0;
        while (i2 < length && charSequence.charAt(i2) < 128) {
            i2++;
        }
        int i3 = length;
        while (true) {
            if (i2 >= length) {
                break;
            }
            char charAt = charSequence.charAt(i2);
            if (charAt < 2048) {
                i3 += (127 - charAt) >>> 31;
                i2++;
            } else {
                int length2 = charSequence.length();
                while (i2 < length2) {
                    char charAt2 = charSequence.charAt(i2);
                    if (charAt2 < 2048) {
                        i += (127 - charAt2) >>> 31;
                    } else {
                        i += 2;
                        if (55296 <= charAt2 && charAt2 <= 57343) {
                            if (Character.codePointAt(charSequence, i2) >= 65536) {
                                i2++;
                            } else {
                                throw new C2939hs1(i2, length2);
                            }
                        }
                    }
                    i2++;
                }
                i3 += i;
            }
        }
        if (i3 >= length) {
            return i3;
        }
        throw new IllegalArgumentException(AbstractC2531fV.u(54, "UTF-8 length does not fit in int: ", ((long) i3) + 4294967296L));
    }

    public static int d(int i, int i2, int i3) {
        if (i > -12 || i2 > -65 || i3 > -65) {
            return -1;
        }
        return (i ^ (i2 << 8)) ^ (i3 << 16);
    }

    public static boolean e(byte[] bArr, int i, int i2) {
        if (f10244a.c(0, bArr, i, i2) == 0) {
            return true;
        }
        return false;
    }
}
