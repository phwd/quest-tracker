package defpackage;

import com.oculus.os.Version;

/* renamed from: xK0 */
public abstract /* synthetic */ class AbstractC5580xK0 {

    /* renamed from: a */
    public static /* synthetic */ int[] f11605a;
    public static /* synthetic */ int[] b;
    public static /* synthetic */ int[] c;
    public static /* synthetic */ int[] d;
    public static /* synthetic */ int[] e;
    public static /* synthetic */ int[] f;
    public static /* synthetic */ int[] g;
    public static /* synthetic */ int[] h;
    public static /* synthetic */ int[] i;
    public static /* synthetic */ int[] j;
    public static /* synthetic */ int[] k;
    public static /* synthetic */ int[] l;
    public static /* synthetic */ int[] m;
    public static /* synthetic */ int[] n;
    public static /* synthetic */ int[] o;
    public static /* synthetic */ int[] p;
    public static /* synthetic */ int[] q;
    public static /* synthetic */ int[] r;
    public static /* synthetic */ int[] s;
    public static /* synthetic */ int[] t;
    public static /* synthetic */ int[] u;
    public static /* synthetic */ int[] v;
    public static /* synthetic */ int[] w;
    public static /* synthetic */ int[] x;
    public static /* synthetic */ int[] y;
    public static /* synthetic */ int[] z;

    public static /* synthetic */ int a(int i2) {
        if (i2 != 0) {
            return i2 - 1;
        }
        throw null;
    }

    public static /* synthetic */ int b(int i2) {
        if (i2 == 1) {
            return -1;
        }
        if (i2 == 2) {
            return 0;
        }
        if (i2 == 3) {
            return 1;
        }
        if (i2 == 4) {
            return 2;
        }
        throw null;
    }

    public static /* synthetic */ int c(int i2) {
        if (i2 == 1) {
            return -1;
        }
        if (i2 == 2) {
            return 0;
        }
        if (i2 == 3) {
            return 1;
        }
        throw null;
    }

    public static /* synthetic */ int d(int i2) {
        if (i2 == 1) {
            return 0;
        }
        if (i2 == 2) {
            return 1;
        }
        if (i2 == 3) {
            return 2;
        }
        if (i2 == 4) {
            return -1;
        }
        throw null;
    }

    public static /* synthetic */ int e(int i2) {
        if (i2 == 1) {
            return 0;
        }
        if (i2 == 2) {
            return 1;
        }
        if (i2 == 3) {
            return 2;
        }
        if (i2 == 4) {
            return -1;
        }
        throw null;
    }

    public static int f(int i2) {
        if (i2 == -1) {
            return 1;
        }
        if (i2 == 0) {
            return 2;
        }
        if (i2 != 1) {
            return i2 != 2 ? 0 : 4;
        }
        return 3;
    }

    public static int g(int i2) {
        if (i2 == -1) {
            return 1;
        }
        if (i2 != 0) {
            return i2 != 1 ? 0 : 3;
        }
        return 2;
    }

    public static int h(int i2) {
        switch (i2) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 6;
            case 6:
                return 7;
            case Version.VERSION_7:
                return 8;
            case Version.VERSION_8:
                return 9;
            case Version.VERSION_9:
                return 10;
            case Version.VERSION_10:
                return 11;
            case Version.VERSION_11:
                return 12;
            case Version.VERSION_12:
                return 13;
            case Version.VERSION_13:
                return 14;
            case Version.VERSION_14:
                return 15;
            default:
                return 0;
        }
    }

    public static int i(int i2) {
        if (i2 == 0) {
            return 1;
        }
        if (i2 != 1) {
            return i2 != 2 ? 0 : 3;
        }
        return 2;
    }

    public static final int j(int i2) {
        if (i2 != 4) {
            return d(i2);
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }

    public static final int k(int i2) {
        if (i2 != 4) {
            return e(i2);
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }
}
