package X;

/* renamed from: X.qP  reason: case insensitive filesystem */
public final class C0478qP {
    public static long A00(long j) {
        int i = (int) (j & 65535);
        if (i == 0) {
            return 500;
        }
        if (i == 1) {
            return 975612;
        }
        if (i == 3) {
            return -4321;
        }
        if (i == 4) {
            return 20;
        }
        if (i != 5) {
            return 0;
        }
        return 15;
    }

    public static String A01(long j) {
        int i = (int) (j & 65535);
        if (i == 0) {
            return "{}";
        }
        if (i == 1) {
            return "Lorem Ipsum";
        }
        if (i != 3) {
            return "";
        }
        return "MobileConfig is a cross-platform framework for Android and iOS apps to access server-side configurations";
    }
}
