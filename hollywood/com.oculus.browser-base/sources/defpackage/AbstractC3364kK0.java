package defpackage;

/* renamed from: kK0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3364kK0 {
    public static int a(long j) {
        if (j > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        if (j < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        return (int) j;
    }

    public static void b(String str, int i) {
        AbstractC3100ip1.f10165a.c(str, i, 1, 1000, 50);
    }

    public static void c(String str, int i) {
        AbstractC3100ip1.f10165a.c(str, i, 1, 100, 50);
    }

    public static void d(String str, int i) {
        AbstractC3100ip1.f10165a.c(str, i, 1, 1000000, 50);
    }

    public static void e(String str, int i, int i2, int i3, int i4) {
        AbstractC3100ip1.f10165a.c(str, i, i2, i3, i4);
    }

    public static void f(String str, long j, long j2, long j3, int i) {
        AbstractC3100ip1.f10165a.c(str, a(j), a(j2), a(j3), i);
    }

    public static void g(String str, int i, int i2) {
        AbstractC3100ip1.f10165a.e(str, i, 1, i2, i2 + 1);
    }

    public static void h(String str, int i, int i2, int i3, int i4) {
        AbstractC3100ip1.f10165a.e(str, i, i2, i3, i4);
    }

    public static void i(String str, long j) {
        f(str, j, 1, 3600000, 50);
    }

    public static void j(String str, long j) {
        f(str, j, 10, 180000, 50);
    }

    public static void k(String str, long j) {
        f(str, j, 1, 10000, 50);
    }
}
