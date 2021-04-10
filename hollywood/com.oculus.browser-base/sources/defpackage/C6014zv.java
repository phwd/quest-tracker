package defpackage;

import java.util.Objects;
import java.util.logging.Level;

/* renamed from: zv  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C6014zv extends AbstractC0882Ok {

    /* renamed from: a  reason: collision with root package name */
    public static final boolean f11779a = Op1.g;
    public C0112Bv b;
    public final byte[] c;
    public final int d;
    public int e;

    public C6014zv(byte[] bArr, int i, int i2) {
        Objects.requireNonNull(bArr, "buffer");
        int i3 = i + i2;
        if ((i | i2 | (bArr.length - i3)) >= 0) {
            this.c = bArr;
            this.e = i;
            this.d = i3;
            return;
        }
        throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)));
    }

    public static int A(int i) {
        return (i >> 31) ^ (i << 1);
    }

    public static long B(long j) {
        return (j >> 63) ^ (j << 1);
    }

    public static int a(int i, boolean z) {
        return v(i) + 1;
    }

    public static int b(int i, AbstractC1248Uk uk) {
        return v(i) + m(uk.size());
    }

    public static int c(AbstractC1248Uk uk) {
        return m(uk.size());
    }

    public static int d(int i, double d2) {
        return v(i) + 8;
    }

    public static int e(int i, int i2) {
        return v(i) + k(i2);
    }

    public static int f(int i, int i2) {
        return v(i) + 4;
    }

    public static int g(int i, long j) {
        return v(i) + 8;
    }

    public static int h(int i, float f) {
        return v(i) + 4;
    }

    @Deprecated
    public static int i(int i, AbstractC1125Sj0 sj0, UO0 uo0) {
        return (v(i) * 2) + ((AbstractC2790h) sj0).b(uo0);
    }

    public static int j(int i, int i2) {
        return k(i2) + v(i);
    }

    public static int k(int i) {
        if (i >= 0) {
            return x(i);
        }
        return 10;
    }

    public static int l(int i, long j) {
        return v(i) + z(j);
    }

    public static int m(int i) {
        return x(i) + i;
    }

    public static int n(int i, int i2) {
        return v(i) + 4;
    }

    public static int o(int i, long j) {
        return v(i) + 8;
    }

    public static int p(int i, int i2) {
        return q(i2) + v(i);
    }

    public static int q(int i) {
        return x(A(i));
    }

    public static int r(int i, long j) {
        return s(j) + v(i);
    }

    public static int s(long j) {
        return z(B(j));
    }

    public static int t(int i, String str) {
        return u(str) + v(i);
    }

    public static int u(String str) {
        int i;
        try {
            i = AbstractC3280js1.c(str);
        } catch (C2939hs1 unused) {
            i = str.getBytes(F30.f7992a).length;
        }
        return m(i);
    }

    public static int v(int i) {
        return x((i << 3) | 0);
    }

    public static int w(int i, int i2) {
        return x(i2) + v(i);
    }

    public static int x(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        return (i & -268435456) == 0 ? 4 : 5;
    }

    public static int y(int i, long j) {
        return z(j) + v(i);
    }

    public static int z(long j) {
        int i;
        if ((-128 & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if ((-34359738368L & j) != 0) {
            i = 6;
            j >>>= 28;
        } else {
            i = 2;
        }
        if ((-2097152 & j) != 0) {
            i += 2;
            j >>>= 14;
        }
        return (j & -16384) != 0 ? i + 1 : i;
    }

    public final int C() {
        return this.d - this.e;
    }

    public final void D(byte b2) {
        try {
            byte[] bArr = this.c;
            int i = this.e;
            this.e = i + 1;
            bArr[i] = b2;
        } catch (IndexOutOfBoundsException e2) {
            throw new C0051Av(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.e), Integer.valueOf(this.d), 1), e2);
        }
    }

    public final void E(byte[] bArr, int i, int i2) {
        try {
            System.arraycopy(bArr, i, this.c, this.e, i2);
            this.e += i2;
        } catch (IndexOutOfBoundsException e2) {
            throw new C0051Av(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.e), Integer.valueOf(this.d), Integer.valueOf(i2)), e2);
        }
    }

    public final void F(AbstractC1248Uk uk) {
        M(uk.size());
        C1309Vk vk = (C1309Vk) uk;
        E(vk.I, vk.o(), vk.size());
    }

    public final void G(int i) {
        if (i >= 0) {
            M(i);
        } else {
            N((long) i);
        }
    }

    public final void H(int i) {
        try {
            byte[] bArr = this.c;
            int i2 = this.e;
            int i3 = i2 + 1;
            this.e = i3;
            bArr[i2] = (byte) (i & 255);
            int i4 = i3 + 1;
            this.e = i4;
            bArr[i3] = (byte) ((i >> 8) & 255);
            int i5 = i4 + 1;
            this.e = i5;
            bArr[i4] = (byte) ((i >> 16) & 255);
            this.e = i5 + 1;
            bArr[i5] = (byte) ((i >> 24) & 255);
        } catch (IndexOutOfBoundsException e2) {
            throw new C0051Av(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.e), Integer.valueOf(this.d), 1), e2);
        }
    }

    public final void I(long j) {
        try {
            byte[] bArr = this.c;
            int i = this.e;
            int i2 = i + 1;
            this.e = i2;
            bArr[i] = (byte) (((int) j) & 255);
            int i3 = i2 + 1;
            this.e = i3;
            bArr[i2] = (byte) (((int) (j >> 8)) & 255);
            int i4 = i3 + 1;
            this.e = i4;
            bArr[i3] = (byte) (((int) (j >> 16)) & 255);
            int i5 = i4 + 1;
            this.e = i5;
            bArr[i4] = (byte) (((int) (j >> 24)) & 255);
            int i6 = i5 + 1;
            this.e = i6;
            bArr[i5] = (byte) (((int) (j >> 32)) & 255);
            int i7 = i6 + 1;
            this.e = i7;
            bArr[i6] = (byte) (((int) (j >> 40)) & 255);
            int i8 = i7 + 1;
            this.e = i8;
            bArr[i7] = (byte) (((int) (j >> 48)) & 255);
            this.e = i8 + 1;
            bArr[i8] = (byte) (((int) (j >> 56)) & 255);
        } catch (IndexOutOfBoundsException e2) {
            throw new C0051Av(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.e), Integer.valueOf(this.d), 1), e2);
        }
    }

    public final void J(int i, int i2) {
        M((i << 3) | 0);
        if (i2 >= 0) {
            M(i2);
        } else {
            N((long) i2);
        }
    }

    public final void K(int i) {
        if (i >= 0) {
            M(i);
        } else {
            N((long) i);
        }
    }

    public final void L(String str) {
        int i = this.e;
        try {
            int x = x(str.length() * 3);
            int x2 = x(str.length());
            if (x2 == x) {
                int i2 = i + x2;
                this.e = i2;
                int b2 = AbstractC3280js1.f10244a.b(str, this.c, i2, C());
                this.e = i;
                M((b2 - i) - x2);
                this.e = b2;
                return;
            }
            M(AbstractC3280js1.c(str));
            this.e = AbstractC3280js1.f10244a.b(str, this.c, this.e, C());
        } catch (C2939hs1 unused) {
            this.e = i;
            Level level = Level.WARNING;
            throw null;
        } catch (IndexOutOfBoundsException e2) {
            throw new C0051Av(e2);
        }
    }

    public final void M(int i) {
        if (!f11779a || P4.a() || C() < 5) {
            while ((i & -128) != 0) {
                byte[] bArr = this.c;
                int i2 = this.e;
                this.e = i2 + 1;
                bArr[i2] = (byte) ((i & 127) | 128);
                i >>>= 7;
            }
            try {
                byte[] bArr2 = this.c;
                int i3 = this.e;
                this.e = i3 + 1;
                bArr2[i3] = (byte) i;
            } catch (IndexOutOfBoundsException e2) {
                throw new C0051Av(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.e), Integer.valueOf(this.d), 1), e2);
            }
        } else if ((i & -128) == 0) {
            byte[] bArr3 = this.c;
            int i4 = this.e;
            this.e = i4 + 1;
            Op1.p(bArr3, (long) i4, (byte) i);
        } else {
            byte[] bArr4 = this.c;
            int i5 = this.e;
            this.e = i5 + 1;
            Op1.p(bArr4, (long) i5, (byte) (i | 128));
            int i6 = i >>> 7;
            if ((i6 & -128) == 0) {
                byte[] bArr5 = this.c;
                int i7 = this.e;
                this.e = i7 + 1;
                Op1.p(bArr5, (long) i7, (byte) i6);
                return;
            }
            byte[] bArr6 = this.c;
            int i8 = this.e;
            this.e = i8 + 1;
            Op1.p(bArr6, (long) i8, (byte) (i6 | 128));
            int i9 = i6 >>> 7;
            if ((i9 & -128) == 0) {
                byte[] bArr7 = this.c;
                int i10 = this.e;
                this.e = i10 + 1;
                Op1.p(bArr7, (long) i10, (byte) i9);
                return;
            }
            byte[] bArr8 = this.c;
            int i11 = this.e;
            this.e = i11 + 1;
            Op1.p(bArr8, (long) i11, (byte) (i9 | 128));
            int i12 = i9 >>> 7;
            if ((i12 & -128) == 0) {
                byte[] bArr9 = this.c;
                int i13 = this.e;
                this.e = i13 + 1;
                Op1.p(bArr9, (long) i13, (byte) i12);
                return;
            }
            byte[] bArr10 = this.c;
            int i14 = this.e;
            this.e = i14 + 1;
            Op1.p(bArr10, (long) i14, (byte) (i12 | 128));
            byte[] bArr11 = this.c;
            int i15 = this.e;
            this.e = i15 + 1;
            Op1.p(bArr11, (long) i15, (byte) (i12 >>> 7));
        }
    }

    public final void N(long j) {
        if (!f11779a || C() < 10) {
            while ((j & -128) != 0) {
                byte[] bArr = this.c;
                int i = this.e;
                this.e = i + 1;
                bArr[i] = (byte) ((((int) j) & 127) | 128);
                j >>>= 7;
            }
            try {
                byte[] bArr2 = this.c;
                int i2 = this.e;
                this.e = i2 + 1;
                bArr2[i2] = (byte) ((int) j);
            } catch (IndexOutOfBoundsException e2) {
                throw new C0051Av(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.e), Integer.valueOf(this.d), 1), e2);
            }
        } else {
            while ((j & -128) != 0) {
                byte[] bArr3 = this.c;
                int i3 = this.e;
                this.e = i3 + 1;
                Op1.p(bArr3, (long) i3, (byte) ((((int) j) & 127) | 128));
                j >>>= 7;
            }
            byte[] bArr4 = this.c;
            int i4 = this.e;
            this.e = i4 + 1;
            Op1.p(bArr4, (long) i4, (byte) ((int) j));
        }
    }
}
