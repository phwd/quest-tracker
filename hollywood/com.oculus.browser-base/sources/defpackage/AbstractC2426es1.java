package defpackage;

/* renamed from: es1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2426es1 {
    public static void a(byte b, byte b2, byte b3, byte b4, char[] cArr, int i) {
        if (!e(b2)) {
            if ((((b2 + 112) + (b << 28)) >> 30) == 0 && !e(b3) && !e(b4)) {
                int i2 = ((b & 7) << 18) | ((b2 & 63) << 12) | ((b3 & 63) << 6) | (b4 & 63);
                cArr[i] = (char) ((i2 >>> 10) + 55232);
                cArr[i + 1] = (char) ((i2 & 1023) + 56320);
                return;
            }
        }
        throw L30.b();
    }

    public static boolean b(byte b) {
        return b >= 0;
    }

    public static void c(byte b, byte b2, char[] cArr, int i) {
        if (b < -62 || e(b2)) {
            throw L30.b();
        }
        cArr[i] = (char) (((b & 31) << 6) | (b2 & 63));
    }

    public static void d(byte b, byte b2, byte b3, char[] cArr, int i) {
        if (e(b2) || ((b == -32 && b2 < -96) || ((b == -19 && b2 >= -96) || e(b3)))) {
            throw L30.b();
        }
        cArr[i] = (char) (((b & 15) << 12) | ((b2 & 63) << 6) | (b3 & 63));
    }

    public static boolean e(byte b) {
        return b > -65;
    }
}
