package defpackage;

import java.util.Arrays;
import java.util.Objects;

/* renamed from: zp1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5998zp1 {

    /* renamed from: a  reason: collision with root package name */
    public static final C5998zp1 f11772a = new C5998zp1(0, new int[0], new Object[0], false);
    public int b;
    public int[] c;
    public Object[] d;
    public int e = -1;
    public boolean f;

    public C5998zp1(int i, int[] iArr, Object[] objArr, boolean z) {
        this.b = i;
        this.c = iArr;
        this.d = objArr;
        this.f = z;
    }

    public static C5998zp1 b() {
        return new C5998zp1(0, new int[8], new Object[8], true);
    }

    public static void d(int i, Object obj, C0112Bv bv) {
        int i2 = i >>> 3;
        int i3 = i & 7;
        if (i3 == 0) {
            bv.h(i2, ((Long) obj).longValue());
        } else if (i3 == 1) {
            bv.e(i2, ((Long) obj).longValue());
        } else if (i3 == 2) {
            bv.b(i2, (AbstractC1248Uk) obj);
        } else if (i3 == 3) {
            int i4 = i2 << 3;
            bv.f7771a.M(i4 | 3);
            ((C5998zp1) obj).e(bv);
            bv.f7771a.M(i4 | 4);
        } else if (i3 == 5) {
            bv.d(i2, ((Integer) obj).intValue());
        } else {
            int i5 = L30.F;
            throw new RuntimeException(new K30("Protocol message tag had invalid wire type."));
        }
    }

    public int a() {
        int i;
        int i2 = this.e;
        if (i2 != -1) {
            return i2;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.b; i4++) {
            int i5 = this.c[i4];
            int i6 = i5 >>> 3;
            int i7 = i5 & 7;
            if (i7 == 0) {
                i = C6014zv.y(i6, ((Long) this.d[i4]).longValue());
            } else if (i7 == 1) {
                i = C6014zv.g(i6, ((Long) this.d[i4]).longValue());
            } else if (i7 == 2) {
                i = C6014zv.b(i6, (AbstractC1248Uk) this.d[i4]);
            } else if (i7 == 3) {
                i3 = ((C5998zp1) this.d[i4]).a() + (C6014zv.v(i6) * 2) + i3;
            } else if (i7 == 5) {
                i = C6014zv.f(i6, ((Integer) this.d[i4]).intValue());
            } else {
                int i8 = L30.F;
                throw new IllegalStateException(new K30("Protocol message tag had invalid wire type."));
            }
            i3 = i + i3;
        }
        this.e = i3;
        return i3;
    }

    public void c(int i, Object obj) {
        if (this.f) {
            int i2 = this.b;
            int[] iArr = this.c;
            if (i2 == iArr.length) {
                int i3 = i2 + (i2 < 4 ? 8 : i2 >> 1);
                this.c = Arrays.copyOf(iArr, i3);
                this.d = Arrays.copyOf(this.d, i3);
            }
            int[] iArr2 = this.c;
            int i4 = this.b;
            iArr2[i4] = i;
            this.d[i4] = obj;
            this.b = i4 + 1;
            return;
        }
        throw new UnsupportedOperationException();
    }

    public void e(C0112Bv bv) {
        if (this.b != 0) {
            Objects.requireNonNull(bv);
            for (int i = 0; i < this.b; i++) {
                d(this.c[i], this.d[i], bv);
            }
        }
    }

    public boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof C5998zp1)) {
            return false;
        }
        C5998zp1 zp1 = (C5998zp1) obj;
        int i = this.b;
        if (i == zp1.b) {
            int[] iArr = this.c;
            int[] iArr2 = zp1.c;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    z = true;
                    break;
                } else if (iArr[i2] != iArr2[i2]) {
                    z = false;
                    break;
                } else {
                    i2++;
                }
            }
            if (z) {
                Object[] objArr = this.d;
                Object[] objArr2 = zp1.d;
                int i3 = this.b;
                int i4 = 0;
                while (true) {
                    if (i4 >= i3) {
                        z2 = true;
                        break;
                    } else if (!objArr[i4].equals(objArr2[i4])) {
                        z2 = false;
                        break;
                    } else {
                        i4++;
                    }
                }
                return z2;
            }
        }
    }

    public int hashCode() {
        int i = this.b;
        int i2 = (527 + i) * 31;
        int[] iArr = this.c;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < i; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = (i2 + i4) * 31;
        Object[] objArr = this.d;
        int i7 = this.b;
        for (int i8 = 0; i8 < i7; i8++) {
            i3 = (i3 * 31) + objArr[i8].hashCode();
        }
        return i6 + i3;
    }
}
