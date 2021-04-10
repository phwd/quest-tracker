package defpackage;

/* renamed from: od0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4089od0 {
    public static boolean a(float f, float f2) {
        return Math.abs(f - f2) < 0.001f;
    }

    public static float b(float f, float f2, float f3) {
        int i = (f2 > f3 ? 1 : (f2 == f3 ? 0 : -1));
        float f4 = i > 0 ? f3 : f2;
        if (i <= 0) {
            f2 = f3;
        }
        return f < f4 ? f4 : f > f2 ? f2 : f;
    }

    public static int c(int i, int i2, int i3) {
        int i4 = i2 > i3 ? i3 : i2;
        if (i2 <= i3) {
            i2 = i3;
        }
        return i < i4 ? i4 : i > i2 ? i2 : i;
    }

    public static long d(long j, long j2, long j3) {
        int i = (j2 > j3 ? 1 : (j2 == j3 ? 0 : -1));
        long j4 = i > 0 ? j3 : j2;
        if (i <= 0) {
            j2 = j3;
        }
        return j < j4 ? j4 : j > j2 ? j2 : j;
    }

    public static float e(float f, float f2, float f3) {
        return AbstractC2531fV.a(f2, f, f3, f);
    }
}
