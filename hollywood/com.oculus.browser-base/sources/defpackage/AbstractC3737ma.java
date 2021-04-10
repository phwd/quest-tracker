package defpackage;

import java.util.Objects;

/* renamed from: ma  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3737ma {
    public static int a(byte[] bArr, int i, C3566la laVar) {
        int t = t(bArr, i, laVar);
        int i2 = laVar.f10352a;
        if (i2 < 0) {
            throw L30.c();
        } else if (i2 > bArr.length - t) {
            throw L30.e();
        } else if (i2 == 0) {
            laVar.c = AbstractC1248Uk.F;
            return t;
        } else {
            laVar.c = AbstractC1248Uk.c(bArr, t, i2);
            return t + i2;
        }
    }

    public static int b(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    public static long c(byte[] bArr, int i) {
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    public static int d(UO0 uo0, byte[] bArr, int i, int i2, int i3, C3566la laVar) {
        C3597lk0 lk0 = (C3597lk0) uo0;
        C1072Rn0 rn0 = lk0.l;
        AbstractC1125Sj0 sj0 = lk0.g;
        Objects.requireNonNull(rn0);
        Object e = ((AbstractC2360eV) sj0).e(EnumC2190dV.NEW_MUTABLE_INSTANCE, null, null);
        int G = lk0.G(e, bArr, i, i2, i3, laVar);
        lk0.c(e);
        laVar.c = e;
        return G;
    }

    public static int e(UO0 uo0, byte[] bArr, int i, int i2, C3566la laVar) {
        int i3 = i + 1;
        byte b = bArr[i];
        byte b2 = b;
        if (b < 0) {
            i3 = s(b, bArr, i3, laVar);
            b2 = laVar.f10352a;
        }
        if (b2 < 0 || b2 > i2 - i3) {
            throw L30.e();
        }
        Object h = uo0.h();
        int i4 = (b2 == 1 ? 1 : 0) + i3;
        uo0.e(h, bArr, i3, i4, laVar);
        uo0.c(h);
        laVar.c = h;
        return i4;
    }

    public static int f(UO0 uo0, int i, byte[] bArr, int i2, int i3, E30 e30, C3566la laVar) {
        int e = e(uo0, bArr, i2, i3, laVar);
        e30.add(laVar.c);
        while (e < i3) {
            int t = t(bArr, e, laVar);
            if (i != laVar.f10352a) {
                break;
            }
            e = e(uo0, bArr, t, i3, laVar);
            e30.add(laVar.c);
        }
        return e;
    }

    public static int g(byte[] bArr, int i, E30 e30, C3566la laVar) {
        AbstractC1365Wi wi = (AbstractC1365Wi) e30;
        int t = t(bArr, i, laVar);
        int i2 = laVar.f10352a + t;
        while (t < i2) {
            t = v(bArr, t, laVar);
            wi.b(laVar.b != 0);
        }
        if (t == i2) {
            return t;
        }
        throw L30.e();
    }

    public static int h(byte[] bArr, int i, E30 e30, C3566la laVar) {
        IG ig = (IG) e30;
        int t = t(bArr, i, laVar);
        int i2 = laVar.f10352a + t;
        while (t < i2) {
            ig.b(Double.longBitsToDouble(c(bArr, t)));
            t += 8;
        }
        if (t == i2) {
            return t;
        }
        throw L30.e();
    }

    public static int i(byte[] bArr, int i, E30 e30, C3566la laVar) {
        O20 o20 = (O20) e30;
        int t = t(bArr, i, laVar);
        int i2 = laVar.f10352a + t;
        while (t < i2) {
            o20.b(b(bArr, t));
            t += 4;
        }
        if (t == i2) {
            return t;
        }
        throw L30.e();
    }

    public static int j(byte[] bArr, int i, E30 e30, C3566la laVar) {
        AbstractC2716gb0 gb0 = (AbstractC2716gb0) e30;
        int t = t(bArr, i, laVar);
        int i2 = laVar.f10352a + t;
        while (t < i2) {
            gb0.b(c(bArr, t));
            t += 8;
        }
        if (t == i2) {
            return t;
        }
        throw L30.e();
    }

    public static int k(byte[] bArr, int i, E30 e30, C3566la laVar) {
        AbstractC3548lR lRVar = (AbstractC3548lR) e30;
        int t = t(bArr, i, laVar);
        int i2 = laVar.f10352a + t;
        while (t < i2) {
            lRVar.b(Float.intBitsToFloat(b(bArr, t)));
            t += 4;
        }
        if (t == i2) {
            return t;
        }
        throw L30.e();
    }

    public static int l(byte[] bArr, int i, E30 e30, C3566la laVar) {
        O20 o20 = (O20) e30;
        int t = t(bArr, i, laVar);
        int i2 = laVar.f10352a + t;
        while (t < i2) {
            t = t(bArr, t, laVar);
            o20.b(AbstractC5844yv.a(laVar.f10352a));
        }
        if (t == i2) {
            return t;
        }
        throw L30.e();
    }

    public static int m(byte[] bArr, int i, E30 e30, C3566la laVar) {
        AbstractC2716gb0 gb0 = (AbstractC2716gb0) e30;
        int t = t(bArr, i, laVar);
        int i2 = laVar.f10352a + t;
        while (t < i2) {
            t = v(bArr, t, laVar);
            gb0.b(AbstractC5844yv.b(laVar.b));
        }
        if (t == i2) {
            return t;
        }
        throw L30.e();
    }

    public static int n(byte[] bArr, int i, E30 e30, C3566la laVar) {
        O20 o20 = (O20) e30;
        int t = t(bArr, i, laVar);
        int i2 = laVar.f10352a + t;
        while (t < i2) {
            t = t(bArr, t, laVar);
            o20.b(laVar.f10352a);
        }
        if (t == i2) {
            return t;
        }
        throw L30.e();
    }

    public static int o(byte[] bArr, int i, E30 e30, C3566la laVar) {
        AbstractC2716gb0 gb0 = (AbstractC2716gb0) e30;
        int t = t(bArr, i, laVar);
        int i2 = laVar.f10352a + t;
        while (t < i2) {
            t = v(bArr, t, laVar);
            gb0.b(laVar.b);
        }
        if (t == i2) {
            return t;
        }
        throw L30.e();
    }

    public static int p(byte[] bArr, int i, C3566la laVar) {
        int t = t(bArr, i, laVar);
        int i2 = laVar.f10352a;
        if (i2 < 0) {
            throw L30.c();
        } else if (i2 == 0) {
            laVar.c = "";
            return t;
        } else {
            laVar.c = new String(bArr, t, i2, F30.f7992a);
            return t + i2;
        }
    }

    public static int q(byte[] bArr, int i, C3566la laVar) {
        int t = t(bArr, i, laVar);
        int i2 = laVar.f10352a;
        if (i2 < 0) {
            throw L30.c();
        } else if (i2 == 0) {
            laVar.c = "";
            return t;
        } else {
            laVar.c = AbstractC3280js1.f10244a.a(bArr, t, i2);
            return t + i2;
        }
    }

    public static int r(int i, byte[] bArr, int i2, int i3, C5998zp1 zp1, C3566la laVar) {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                int v = v(bArr, i2, laVar);
                zp1.c(i, Long.valueOf(laVar.b));
                return v;
            } else if (i4 == 1) {
                zp1.c(i, Long.valueOf(c(bArr, i2)));
                return i2 + 8;
            } else if (i4 == 2) {
                int t = t(bArr, i2, laVar);
                int i5 = laVar.f10352a;
                if (i5 < 0) {
                    throw L30.c();
                } else if (i5 <= bArr.length - t) {
                    if (i5 == 0) {
                        zp1.c(i, AbstractC1248Uk.F);
                    } else {
                        zp1.c(i, AbstractC1248Uk.c(bArr, t, i5));
                    }
                    return t + i5;
                } else {
                    throw L30.e();
                }
            } else if (i4 == 3) {
                C5998zp1 b = C5998zp1.b();
                int i6 = (i & -8) | 4;
                int i7 = 0;
                while (true) {
                    if (i2 >= i3) {
                        break;
                    }
                    int t2 = t(bArr, i2, laVar);
                    int i8 = laVar.f10352a;
                    if (i8 == i6) {
                        i7 = i8;
                        i2 = t2;
                        break;
                    }
                    i7 = i8;
                    i2 = r(i8, bArr, t2, i3, b, laVar);
                }
                if (i2 > i3 || i7 != i6) {
                    throw L30.d();
                }
                zp1.c(i, b);
                return i2;
            } else if (i4 == 5) {
                zp1.c(i, Integer.valueOf(b(bArr, i2)));
                return i2 + 4;
            } else {
                throw L30.a();
            }
        } else {
            throw L30.a();
        }
    }

    public static int s(int i, byte[] bArr, int i2, C3566la laVar) {
        int i3 = i & 127;
        int i4 = i2 + 1;
        byte b = bArr[i2];
        if (b >= 0) {
            laVar.f10352a = i3 | (b << 7);
            return i4;
        }
        int i5 = i3 | ((b & Byte.MAX_VALUE) << 7);
        int i6 = i4 + 1;
        byte b2 = bArr[i4];
        if (b2 >= 0) {
            laVar.f10352a = i5 | (b2 << 14);
            return i6;
        }
        int i7 = i5 | ((b2 & Byte.MAX_VALUE) << 14);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            laVar.f10352a = i7 | (b3 << 21);
            return i8;
        }
        int i9 = i7 | ((b3 & Byte.MAX_VALUE) << 21);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            laVar.f10352a = i9 | (b4 << 28);
            return i10;
        }
        int i11 = i9 | ((b4 & Byte.MAX_VALUE) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] < 0) {
                i10 = i12;
            } else {
                laVar.f10352a = i11;
                return i12;
            }
        }
    }

    public static int t(byte[] bArr, int i, C3566la laVar) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return s(b, bArr, i2, laVar);
        }
        laVar.f10352a = b;
        return i2;
    }

    public static int u(int i, byte[] bArr, int i2, int i3, E30 e30, C3566la laVar) {
        O20 o20 = (O20) e30;
        int t = t(bArr, i2, laVar);
        o20.b(laVar.f10352a);
        while (t < i3) {
            int t2 = t(bArr, t, laVar);
            if (i != laVar.f10352a) {
                break;
            }
            t = t(bArr, t2, laVar);
            o20.b(laVar.f10352a);
        }
        return t;
    }

    public static int v(byte[] bArr, int i, C3566la laVar) {
        int i2 = i + 1;
        long j = (long) bArr[i];
        if (j >= 0) {
            laVar.b = j;
            return i2;
        }
        int i3 = i2 + 1;
        byte b = bArr[i2];
        long j2 = (j & 127) | (((long) (b & Byte.MAX_VALUE)) << 7);
        int i4 = 7;
        while (b < 0) {
            int i5 = i3 + 1;
            byte b2 = bArr[i3];
            i4 += 7;
            j2 |= ((long) (b2 & Byte.MAX_VALUE)) << i4;
            b = b2;
            i3 = i5;
        }
        laVar.b = j2;
        return i3;
    }
}
