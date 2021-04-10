package defpackage;

import com.oculus.os.Version;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import sun.misc.Unsafe;

/* renamed from: lk0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3597lk0 implements UO0 {

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f10368a = new int[0];
    public static final Unsafe b = Op1.o();
    public final int[] c;
    public final Object[] d;
    public final int e;
    public final int f;
    public final AbstractC1125Sj0 g;
    public final boolean h;
    public final int[] i;
    public final int j;
    public final int k;
    public final C1072Rn0 l;
    public final N80 m;
    public final Ap1 n;
    public final VM o;
    public final C0129Cc0 p;

    public C3597lk0(int[] iArr, Object[] objArr, int i2, int i3, AbstractC1125Sj0 sj0, boolean z, boolean z2, int[] iArr2, int i4, int i5, C1072Rn0 rn0, N80 n80, Ap1 ap1, VM vm, C0129Cc0 cc0) {
        this.c = iArr;
        this.d = objArr;
        this.e = i2;
        this.f = i3;
        boolean z3 = sj0 instanceof AbstractC2360eV;
        this.h = z;
        this.i = iArr2;
        this.j = i4;
        this.k = i5;
        this.l = rn0;
        this.m = n80;
        this.n = ap1;
        this.o = vm;
        this.g = sj0;
        this.p = cc0;
    }

    public static double A(Object obj, long j2) {
        return ((Double) Op1.n(obj, j2)).doubleValue();
    }

    public static float B(Object obj, long j2) {
        return ((Float) Op1.n(obj, j2)).floatValue();
    }

    public static int C(Object obj, long j2) {
        return ((Integer) Op1.n(obj, j2)).intValue();
    }

    public static long D(Object obj, long j2) {
        return ((Long) Op1.n(obj, j2)).longValue();
    }

    public static Field J(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            StringBuilder sb = new StringBuilder(String.valueOf(arrays).length() + name.length() + String.valueOf(str).length() + 40);
            sb.append("Field ");
            sb.append(str);
            sb.append(" for ");
            sb.append(name);
            throw new RuntimeException(AbstractC2531fV.h(sb, " not found. Known fields are ", arrays));
        }
    }

    public static int N(int i2) {
        return (i2 & 267386880) >>> 20;
    }

    public static C5998zp1 n(Object obj) {
        AbstractC2360eV eVVar = (AbstractC2360eV) obj;
        C5998zp1 zp1 = eVVar.c;
        if (zp1 != C5998zp1.f11772a) {
            return zp1;
        }
        C5998zp1 b2 = C5998zp1.b();
        eVVar.c = b2;
        return b2;
    }

    public static List t(Object obj, long j2) {
        return (List) Op1.n(obj, j2);
    }

    public static C3597lk0 w(AbstractC1003Qj0 qj0, C1072Rn0 rn0, N80 n80, Ap1 ap1, VM vm, C0129Cc0 cc0) {
        if (qj0 instanceof UJ0) {
            return x((UJ0) qj0, rn0, n80, ap1, vm, cc0);
        }
        C5859z.a(qj0);
        throw null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:160:0x0361  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x0364  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x0367  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static defpackage.C3597lk0 x(defpackage.UJ0 r36, defpackage.C1072Rn0 r37, defpackage.N80 r38, defpackage.Ap1 r39, defpackage.VM r40, defpackage.C0129Cc0 r41) {
        /*
        // Method dump skipped, instructions count: 1071
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C3597lk0.x(UJ0, Rn0, N80, Ap1, VM, Cc0):lk0");
    }

    public static long y(int i2) {
        return (long) (i2 & 1048575);
    }

    public static boolean z(Object obj, long j2) {
        return ((Boolean) Op1.n(obj, j2)).booleanValue();
    }

    public final int E(Object obj, byte[] bArr, int i2, int i3, int i4, long j2, C3566la laVar) {
        Unsafe unsafe = b;
        Object obj2 = this.d[(i4 / 3) * 2];
        Object object = unsafe.getObject(obj, j2);
        Objects.requireNonNull(this.p);
        if (!((C0068Bc0) object).G) {
            Objects.requireNonNull(this.p);
            C0068Bc0 bc0 = C0068Bc0.F;
            C0068Bc0 bc02 = bc0.isEmpty() ? new C0068Bc0() : new C0068Bc0(bc0);
            this.p.b(bc02, object);
            unsafe.putObject(obj, j2, bc02);
        }
        Objects.requireNonNull(this.p);
        C5859z.a(obj2);
        throw null;
    }

    public final int F(Object obj, byte[] bArr, int i2, int i3, int i4, int i5, int i6, int i7, int i8, long j2, int i9, C3566la laVar) {
        Unsafe unsafe = b;
        long j3 = (long) (this.c[i9 + 2] & 1048575);
        switch (i8) {
            case 51:
                if (i6 == 1) {
                    unsafe.putObject(obj, j2, Double.valueOf(Double.longBitsToDouble(AbstractC3737ma.c(bArr, i2))));
                    int i10 = i2 + 8;
                    unsafe.putInt(obj, j3, i5);
                    return i10;
                }
                break;
            case 52:
                if (i6 == 5) {
                    unsafe.putObject(obj, j2, Float.valueOf(Float.intBitsToFloat(AbstractC3737ma.b(bArr, i2))));
                    int i11 = i2 + 4;
                    unsafe.putInt(obj, j3, i5);
                    return i11;
                }
                break;
            case 53:
            case 54:
                if (i6 == 0) {
                    int v = AbstractC3737ma.v(bArr, i2, laVar);
                    unsafe.putObject(obj, j2, Long.valueOf(laVar.b));
                    unsafe.putInt(obj, j3, i5);
                    return v;
                }
                break;
            case 55:
            case 62:
                if (i6 == 0) {
                    int t = AbstractC3737ma.t(bArr, i2, laVar);
                    unsafe.putObject(obj, j2, Integer.valueOf(laVar.f10352a));
                    unsafe.putInt(obj, j3, i5);
                    return t;
                }
                break;
            case 56:
            case 65:
                if (i6 == 1) {
                    unsafe.putObject(obj, j2, Long.valueOf(AbstractC3737ma.c(bArr, i2)));
                    int i12 = i2 + 8;
                    unsafe.putInt(obj, j3, i5);
                    return i12;
                }
                break;
            case 57:
            case 64:
                if (i6 == 5) {
                    unsafe.putObject(obj, j2, Integer.valueOf(AbstractC3737ma.b(bArr, i2)));
                    int i13 = i2 + 4;
                    unsafe.putInt(obj, j3, i5);
                    return i13;
                }
                break;
            case 58:
                if (i6 == 0) {
                    int v2 = AbstractC3737ma.v(bArr, i2, laVar);
                    unsafe.putObject(obj, j2, Boolean.valueOf(laVar.b != 0));
                    unsafe.putInt(obj, j3, i5);
                    return v2;
                }
                break;
            case 59:
                if (i6 == 2) {
                    int t2 = AbstractC3737ma.t(bArr, i2, laVar);
                    int i14 = laVar.f10352a;
                    if (i14 == 0) {
                        unsafe.putObject(obj, j2, "");
                    } else if ((i7 & 536870912) == 0 || AbstractC3280js1.e(bArr, t2, t2 + i14)) {
                        unsafe.putObject(obj, j2, new String(bArr, t2, i14, F30.f7992a));
                        t2 += i14;
                    } else {
                        throw L30.b();
                    }
                    unsafe.putInt(obj, j3, i5);
                    return t2;
                }
                break;
            case 60:
                if (i6 == 2) {
                    int e2 = AbstractC3737ma.e(m(i9), bArr, i2, i3, laVar);
                    Object object = unsafe.getInt(obj, j3) == i5 ? unsafe.getObject(obj, j2) : null;
                    if (object == null) {
                        unsafe.putObject(obj, j2, laVar.c);
                    } else {
                        unsafe.putObject(obj, j2, F30.c(object, laVar.c));
                    }
                    unsafe.putInt(obj, j3, i5);
                    return e2;
                }
                break;
            case 61:
                if (i6 == 2) {
                    int a2 = AbstractC3737ma.a(bArr, i2, laVar);
                    unsafe.putObject(obj, j2, laVar.c);
                    unsafe.putInt(obj, j3, i5);
                    return a2;
                }
                break;
            case 63:
                if (i6 == 0) {
                    int t3 = AbstractC3737ma.t(bArr, i2, laVar);
                    int i15 = laVar.f10352a;
                    D30 d30 = (D30) this.d[((i9 / 3) * 2) + 1];
                    if (d30 == null || d30.a(i15)) {
                        unsafe.putObject(obj, j2, Integer.valueOf(i15));
                        unsafe.putInt(obj, j3, i5);
                    } else {
                        n(obj).c(i4, Long.valueOf((long) i15));
                    }
                    return t3;
                }
                break;
            case 66:
                if (i6 == 0) {
                    int t4 = AbstractC3737ma.t(bArr, i2, laVar);
                    unsafe.putObject(obj, j2, Integer.valueOf(AbstractC5844yv.a(laVar.f10352a)));
                    unsafe.putInt(obj, j3, i5);
                    return t4;
                }
                break;
            case 67:
                if (i6 == 0) {
                    int v3 = AbstractC3737ma.v(bArr, i2, laVar);
                    unsafe.putObject(obj, j2, Long.valueOf(AbstractC5844yv.b(laVar.b)));
                    unsafe.putInt(obj, j3, i5);
                    return v3;
                }
                break;
            case 68:
                if (i6 == 3) {
                    int d2 = AbstractC3737ma.d(m(i9), bArr, i2, i3, (i4 & -8) | 4, laVar);
                    Object object2 = unsafe.getInt(obj, j3) == i5 ? unsafe.getObject(obj, j2) : null;
                    if (object2 == null) {
                        unsafe.putObject(obj, j2, laVar.c);
                    } else {
                        unsafe.putObject(obj, j2, F30.c(object2, laVar.c));
                    }
                    unsafe.putInt(obj, j3, i5);
                    return d2;
                }
                break;
        }
        return i2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v39, types: [int] */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x03dc, code lost:
        if (r0 != r15) goto L_0x03de;
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int G(java.lang.Object r29, byte[] r30, int r31, int r32, int r33, defpackage.C3566la r34) {
        /*
        // Method dump skipped, instructions count: 1212
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C3597lk0.G(java.lang.Object, byte[], int, int, int, la):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:113:0x02cd, code lost:
        if (r0 != r3) goto L_0x031c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x031a, code lost:
        if (r0 != r15) goto L_0x031c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x0323, code lost:
        r2 = r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int H(java.lang.Object r29, byte[] r30, int r31, int r32, defpackage.C3566la r33) {
        /*
        // Method dump skipped, instructions count: 916
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C3597lk0.H(java.lang.Object, byte[], int, int, la):int");
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:213:0x0148 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:215:0x0159 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:216:0x0177 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:217:0x0159 */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:57)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:15)
        */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x021c  */
    /* JADX WARNING: Removed duplicated region for block: B:226:0x03de A[SYNTHETIC] */
    public final int I(java.lang.Object r18, byte[] r19, int r20, int r21, int r22, int r23, int r24, int r25, long r26, int r28, long r29, defpackage.C3566la r31) {
        /*
        // Method dump skipped, instructions count: 1060
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C3597lk0.I(java.lang.Object, byte[], int, int, int, int, int, int, long, int, long, la):int");
    }

    public final void K(Object obj, int i2) {
        int i3 = this.c[i2 + 2];
        long j2 = (long) (1048575 & i3);
        if (j2 != 1048575) {
            Op1.e.o(obj, j2, (1 << (i3 >>> 20)) | Op1.l(obj, j2));
        }
    }

    public final void L(Object obj, int i2, int i3) {
        Op1.e.o(obj, (long) (this.c[i3 + 2] & 1048575), i2);
    }

    public final int M(int i2, int i3) {
        int length = (this.c.length / 3) - 1;
        while (i3 <= length) {
            int i4 = (length + i3) >>> 1;
            int i5 = i4 * 3;
            int i6 = this.c[i5];
            if (i2 == i6) {
                return i5;
            }
            if (i2 < i6) {
                length = i4 - 1;
            } else {
                i3 = i4 + 1;
            }
        }
        return -1;
    }

    public final int O(int i2) {
        return this.c[i2 + 1];
    }

    public final void P(Object obj, C0112Bv bv) {
        int i2;
        int length = this.c.length;
        Unsafe unsafe = b;
        int i3 = 1048575;
        int i4 = 1048575;
        int i5 = 0;
        int i6 = 0;
        while (i5 < length) {
            int O = O(i5);
            int i7 = this.c[i5];
            int N = N(O);
            if (this.h || N > 17) {
                i2 = 0;
            } else {
                int i8 = this.c[i5 + 2];
                int i9 = i8 & i3;
                if (i9 != i4) {
                    i6 = unsafe.getInt(obj, (long) i9);
                    i4 = i9;
                }
                i2 = 1 << (i8 >>> 20);
            }
            long y = y(O);
            switch (N) {
                case 0:
                    if ((i6 & i2) != 0) {
                        bv.c(i7, Op1.j(obj, y));
                        continue;
                    }
                    i5 += 3;
                    i3 = 1048575;
                case 1:
                    if ((i6 & i2) != 0) {
                        bv.f(i7, Op1.k(obj, y));
                    } else {
                        continue;
                    }
                    i5 += 3;
                    i3 = 1048575;
                case 2:
                    if ((i6 & i2) != 0) {
                        bv.h(i7, unsafe.getLong(obj, y));
                    } else {
                        continue;
                    }
                    i5 += 3;
                    i3 = 1048575;
                case 3:
                    if ((i6 & i2) != 0) {
                        bv.o(i7, unsafe.getLong(obj, y));
                    } else {
                        continue;
                    }
                    i5 += 3;
                    i3 = 1048575;
                case 4:
                    if ((i6 & i2) != 0) {
                        bv.f7771a.J(i7, unsafe.getInt(obj, y));
                    } else {
                        continue;
                    }
                    i5 += 3;
                    i3 = 1048575;
                case 5:
                    if ((i6 & i2) != 0) {
                        bv.e(i7, unsafe.getLong(obj, y));
                    } else {
                        continue;
                    }
                    i5 += 3;
                    i3 = 1048575;
                case 6:
                    if ((i6 & i2) != 0) {
                        bv.d(i7, unsafe.getInt(obj, y));
                    } else {
                        continue;
                    }
                    i5 += 3;
                    i3 = 1048575;
                case Version.VERSION_7:
                    if ((i6 & i2) != 0) {
                        bv.a(i7, Op1.f(obj, y));
                    } else {
                        continue;
                    }
                    i5 += 3;
                    i3 = 1048575;
                case Version.VERSION_8:
                    if ((i6 & i2) != 0) {
                        R(i7, unsafe.getObject(obj, y), bv);
                    } else {
                        continue;
                    }
                    i5 += 3;
                    i3 = 1048575;
                case Version.VERSION_9:
                    if ((i6 & i2) != 0) {
                        bv.i(i7, unsafe.getObject(obj, y), m(i5));
                    } else {
                        continue;
                    }
                    i5 += 3;
                    i3 = 1048575;
                case Version.VERSION_10:
                    if ((i6 & i2) != 0) {
                        bv.b(i7, (AbstractC1248Uk) unsafe.getObject(obj, y));
                    } else {
                        continue;
                    }
                    i5 += 3;
                    i3 = 1048575;
                case Version.VERSION_11:
                    if ((i6 & i2) != 0) {
                        bv.n(i7, unsafe.getInt(obj, y));
                    } else {
                        continue;
                    }
                    i5 += 3;
                    i3 = 1048575;
                case Version.VERSION_12:
                    if ((i6 & i2) != 0) {
                        bv.f7771a.J(i7, unsafe.getInt(obj, y));
                    } else {
                        continue;
                    }
                    i5 += 3;
                    i3 = 1048575;
                case Version.VERSION_13:
                    if ((i6 & i2) != 0) {
                        bv.j(i7, unsafe.getInt(obj, y));
                    } else {
                        continue;
                    }
                    i5 += 3;
                    i3 = 1048575;
                case Version.VERSION_14:
                    if ((i6 & i2) != 0) {
                        bv.k(i7, unsafe.getLong(obj, y));
                    } else {
                        continue;
                    }
                    i5 += 3;
                    i3 = 1048575;
                case Version.VERSION_15:
                    if ((i6 & i2) != 0) {
                        bv.l(i7, unsafe.getInt(obj, y));
                    } else {
                        continue;
                    }
                    i5 += 3;
                    i3 = 1048575;
                case Version.VERSION_16:
                    if ((i6 & i2) != 0) {
                        bv.m(i7, unsafe.getLong(obj, y));
                    } else {
                        continue;
                    }
                    i5 += 3;
                    i3 = 1048575;
                case Version.VERSION_17:
                    if ((i6 & i2) != 0) {
                        bv.g(i7, unsafe.getObject(obj, y), m(i5));
                    } else {
                        continue;
                    }
                    i5 += 3;
                    i3 = 1048575;
                case Version.VERSION_18:
                    WO0.E(this.c[i5], (List) unsafe.getObject(obj, y), bv, false);
                    continue;
                    i5 += 3;
                    i3 = 1048575;
                case Version.VERSION_19:
                    WO0.I(this.c[i5], (List) unsafe.getObject(obj, y), bv, false);
                    continue;
                    i5 += 3;
                    i3 = 1048575;
                case Version.VERSION_20:
                    WO0.L(this.c[i5], (List) unsafe.getObject(obj, y), bv, false);
                    continue;
                    i5 += 3;
                    i3 = 1048575;
                case Version.VERSION_21:
                    WO0.T(this.c[i5], (List) unsafe.getObject(obj, y), bv, false);
                    continue;
                    i5 += 3;
                    i3 = 1048575;
                case Version.VERSION_22:
                    WO0.K(this.c[i5], (List) unsafe.getObject(obj, y), bv, false);
                    continue;
                    i5 += 3;
                    i3 = 1048575;
                case Version.VERSION_23:
                    WO0.H(this.c[i5], (List) unsafe.getObject(obj, y), bv, false);
                    continue;
                    i5 += 3;
                    i3 = 1048575;
                case Version.VERSION_24:
                    WO0.G(this.c[i5], (List) unsafe.getObject(obj, y), bv, false);
                    continue;
                    i5 += 3;
                    i3 = 1048575;
                case Version.VERSION_25:
                    WO0.C(this.c[i5], (List) unsafe.getObject(obj, y), bv, false);
                    continue;
                    i5 += 3;
                    i3 = 1048575;
                case Version.VERSION_26:
                    WO0.R(this.c[i5], (List) unsafe.getObject(obj, y), bv);
                    break;
                case Version.VERSION_27:
                    WO0.M(this.c[i5], (List) unsafe.getObject(obj, y), bv, m(i5));
                    break;
                case Version.VERSION_28:
                    WO0.D(this.c[i5], (List) unsafe.getObject(obj, y), bv);
                    break;
                case Version.VERSION_29:
                    WO0.S(this.c[i5], (List) unsafe.getObject(obj, y), bv, false);
                    break;
                case Version.VERSION_30:
                    WO0.F(this.c[i5], (List) unsafe.getObject(obj, y), bv, false);
                    break;
                case Version.VERSION_31:
                    WO0.N(this.c[i5], (List) unsafe.getObject(obj, y), bv, false);
                    break;
                case Version.VERSION_32:
                    WO0.O(this.c[i5], (List) unsafe.getObject(obj, y), bv, false);
                    break;
                case 33:
                    WO0.P(this.c[i5], (List) unsafe.getObject(obj, y), bv, false);
                    break;
                case 34:
                    WO0.Q(this.c[i5], (List) unsafe.getObject(obj, y), bv, false);
                    break;
                case 35:
                    WO0.E(this.c[i5], (List) unsafe.getObject(obj, y), bv, true);
                    break;
                case 36:
                    WO0.I(this.c[i5], (List) unsafe.getObject(obj, y), bv, true);
                    break;
                case 37:
                    WO0.L(this.c[i5], (List) unsafe.getObject(obj, y), bv, true);
                    break;
                case 38:
                    WO0.T(this.c[i5], (List) unsafe.getObject(obj, y), bv, true);
                    break;
                case 39:
                    WO0.K(this.c[i5], (List) unsafe.getObject(obj, y), bv, true);
                    break;
                case 40:
                    WO0.H(this.c[i5], (List) unsafe.getObject(obj, y), bv, true);
                    break;
                case 41:
                    WO0.G(this.c[i5], (List) unsafe.getObject(obj, y), bv, true);
                    break;
                case 42:
                    WO0.C(this.c[i5], (List) unsafe.getObject(obj, y), bv, true);
                    break;
                case 43:
                    WO0.S(this.c[i5], (List) unsafe.getObject(obj, y), bv, true);
                    break;
                case 44:
                    WO0.F(this.c[i5], (List) unsafe.getObject(obj, y), bv, true);
                    break;
                case 45:
                    WO0.N(this.c[i5], (List) unsafe.getObject(obj, y), bv, true);
                    break;
                case 46:
                    WO0.O(this.c[i5], (List) unsafe.getObject(obj, y), bv, true);
                    break;
                case 47:
                    WO0.P(this.c[i5], (List) unsafe.getObject(obj, y), bv, true);
                    break;
                case 48:
                    WO0.Q(this.c[i5], (List) unsafe.getObject(obj, y), bv, true);
                    break;
                case 49:
                    WO0.J(this.c[i5], (List) unsafe.getObject(obj, y), bv, m(i5));
                    break;
                case 50:
                    Q(bv, i7, unsafe.getObject(obj, y), i5);
                    break;
                case 51:
                    if (s(obj, i7, i5)) {
                        bv.c(i7, A(obj, y));
                        break;
                    }
                    break;
                case 52:
                    if (s(obj, i7, i5)) {
                        bv.f(i7, B(obj, y));
                        break;
                    }
                    break;
                case 53:
                    if (s(obj, i7, i5)) {
                        bv.h(i7, D(obj, y));
                        break;
                    }
                    break;
                case 54:
                    if (s(obj, i7, i5)) {
                        bv.o(i7, D(obj, y));
                        break;
                    }
                    break;
                case 55:
                    if (s(obj, i7, i5)) {
                        bv.f7771a.J(i7, C(obj, y));
                        break;
                    }
                    break;
                case 56:
                    if (s(obj, i7, i5)) {
                        bv.e(i7, D(obj, y));
                        break;
                    }
                    break;
                case 57:
                    if (s(obj, i7, i5)) {
                        bv.d(i7, C(obj, y));
                        break;
                    }
                    break;
                case 58:
                    if (s(obj, i7, i5)) {
                        bv.a(i7, z(obj, y));
                        break;
                    }
                    break;
                case 59:
                    if (s(obj, i7, i5)) {
                        R(i7, unsafe.getObject(obj, y), bv);
                        break;
                    }
                    break;
                case 60:
                    if (s(obj, i7, i5)) {
                        bv.i(i7, unsafe.getObject(obj, y), m(i5));
                        break;
                    }
                    break;
                case 61:
                    if (s(obj, i7, i5)) {
                        bv.b(i7, (AbstractC1248Uk) unsafe.getObject(obj, y));
                        break;
                    }
                    break;
                case 62:
                    if (s(obj, i7, i5)) {
                        bv.n(i7, C(obj, y));
                        break;
                    }
                    break;
                case 63:
                    if (s(obj, i7, i5)) {
                        bv.f7771a.J(i7, C(obj, y));
                        break;
                    }
                    break;
                case 64:
                    if (s(obj, i7, i5)) {
                        bv.j(i7, C(obj, y));
                        break;
                    }
                    break;
                case 65:
                    if (s(obj, i7, i5)) {
                        bv.k(i7, D(obj, y));
                        break;
                    }
                    break;
                case 66:
                    if (s(obj, i7, i5)) {
                        bv.l(i7, C(obj, y));
                        break;
                    }
                    break;
                case 67:
                    if (s(obj, i7, i5)) {
                        bv.m(i7, D(obj, y));
                        break;
                    }
                    break;
                case 68:
                    if (s(obj, i7, i5)) {
                        bv.g(i7, unsafe.getObject(obj, y), m(i5));
                        break;
                    }
                    break;
            }
            i5 += 3;
            i3 = 1048575;
        }
        Objects.requireNonNull(this.n);
        ((AbstractC2360eV) obj).c.e(bv);
    }

    public final void Q(C0112Bv bv, int i2, Object obj, int i3) {
        if (obj != null) {
            C0129Cc0 cc0 = this.p;
            Object obj2 = this.d[(i3 / 3) * 2];
            Objects.requireNonNull(cc0);
            C5859z.a(obj2);
            throw null;
        }
    }

    public final void R(int i2, Object obj, C0112Bv bv) {
        if (obj instanceof String) {
            C6014zv zvVar = bv.f7771a;
            zvVar.M((i2 << 3) | 2);
            zvVar.L((String) obj);
            return;
        }
        C6014zv zvVar2 = bv.f7771a;
        zvVar2.M((i2 << 3) | 2);
        zvVar2.F((AbstractC1248Uk) obj);
    }

    @Override // defpackage.UO0
    public void a(Object obj, C0112Bv bv) {
        Objects.requireNonNull(bv);
        if (this.h) {
            int length = this.c.length;
            for (int i2 = 0; i2 < length; i2 += 3) {
                int O = O(i2);
                int i3 = this.c[i2];
                switch (N(O)) {
                    case 0:
                        if (q(obj, i2)) {
                            bv.c(i3, Op1.j(obj, y(O)));
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (q(obj, i2)) {
                            bv.f(i3, Op1.k(obj, y(O)));
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (q(obj, i2)) {
                            bv.h(i3, Op1.m(obj, y(O)));
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (q(obj, i2)) {
                            bv.o(i3, Op1.m(obj, y(O)));
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (q(obj, i2)) {
                            bv.f7771a.J(i3, Op1.l(obj, y(O)));
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (q(obj, i2)) {
                            bv.e(i3, Op1.m(obj, y(O)));
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (q(obj, i2)) {
                            bv.d(i3, Op1.l(obj, y(O)));
                            break;
                        } else {
                            break;
                        }
                    case Version.VERSION_7:
                        if (q(obj, i2)) {
                            bv.a(i3, Op1.f(obj, y(O)));
                            break;
                        } else {
                            break;
                        }
                    case Version.VERSION_8:
                        if (q(obj, i2)) {
                            R(i3, Op1.n(obj, y(O)), bv);
                            break;
                        } else {
                            break;
                        }
                    case Version.VERSION_9:
                        if (q(obj, i2)) {
                            bv.i(i3, Op1.n(obj, y(O)), m(i2));
                            break;
                        } else {
                            break;
                        }
                    case Version.VERSION_10:
                        if (q(obj, i2)) {
                            bv.b(i3, (AbstractC1248Uk) Op1.n(obj, y(O)));
                            break;
                        } else {
                            break;
                        }
                    case Version.VERSION_11:
                        if (q(obj, i2)) {
                            bv.n(i3, Op1.l(obj, y(O)));
                            break;
                        } else {
                            break;
                        }
                    case Version.VERSION_12:
                        if (q(obj, i2)) {
                            bv.f7771a.J(i3, Op1.l(obj, y(O)));
                            break;
                        } else {
                            break;
                        }
                    case Version.VERSION_13:
                        if (q(obj, i2)) {
                            bv.j(i3, Op1.l(obj, y(O)));
                            break;
                        } else {
                            break;
                        }
                    case Version.VERSION_14:
                        if (q(obj, i2)) {
                            bv.k(i3, Op1.m(obj, y(O)));
                            break;
                        } else {
                            break;
                        }
                    case Version.VERSION_15:
                        if (q(obj, i2)) {
                            bv.l(i3, Op1.l(obj, y(O)));
                            break;
                        } else {
                            break;
                        }
                    case Version.VERSION_16:
                        if (q(obj, i2)) {
                            bv.m(i3, Op1.m(obj, y(O)));
                            break;
                        } else {
                            break;
                        }
                    case Version.VERSION_17:
                        if (q(obj, i2)) {
                            bv.g(i3, Op1.n(obj, y(O)), m(i2));
                            break;
                        } else {
                            break;
                        }
                    case Version.VERSION_18:
                        WO0.E(this.c[i2], (List) Op1.n(obj, y(O)), bv, false);
                        break;
                    case Version.VERSION_19:
                        WO0.I(this.c[i2], (List) Op1.n(obj, y(O)), bv, false);
                        break;
                    case Version.VERSION_20:
                        WO0.L(this.c[i2], (List) Op1.n(obj, y(O)), bv, false);
                        break;
                    case Version.VERSION_21:
                        WO0.T(this.c[i2], (List) Op1.n(obj, y(O)), bv, false);
                        break;
                    case Version.VERSION_22:
                        WO0.K(this.c[i2], (List) Op1.n(obj, y(O)), bv, false);
                        break;
                    case Version.VERSION_23:
                        WO0.H(this.c[i2], (List) Op1.n(obj, y(O)), bv, false);
                        break;
                    case Version.VERSION_24:
                        WO0.G(this.c[i2], (List) Op1.n(obj, y(O)), bv, false);
                        break;
                    case Version.VERSION_25:
                        WO0.C(this.c[i2], (List) Op1.n(obj, y(O)), bv, false);
                        break;
                    case Version.VERSION_26:
                        WO0.R(this.c[i2], (List) Op1.n(obj, y(O)), bv);
                        break;
                    case Version.VERSION_27:
                        WO0.M(this.c[i2], (List) Op1.n(obj, y(O)), bv, m(i2));
                        break;
                    case Version.VERSION_28:
                        WO0.D(this.c[i2], (List) Op1.n(obj, y(O)), bv);
                        break;
                    case Version.VERSION_29:
                        WO0.S(this.c[i2], (List) Op1.n(obj, y(O)), bv, false);
                        break;
                    case Version.VERSION_30:
                        WO0.F(this.c[i2], (List) Op1.n(obj, y(O)), bv, false);
                        break;
                    case Version.VERSION_31:
                        WO0.N(this.c[i2], (List) Op1.n(obj, y(O)), bv, false);
                        break;
                    case Version.VERSION_32:
                        WO0.O(this.c[i2], (List) Op1.n(obj, y(O)), bv, false);
                        break;
                    case 33:
                        WO0.P(this.c[i2], (List) Op1.n(obj, y(O)), bv, false);
                        break;
                    case 34:
                        WO0.Q(this.c[i2], (List) Op1.n(obj, y(O)), bv, false);
                        break;
                    case 35:
                        WO0.E(this.c[i2], (List) Op1.n(obj, y(O)), bv, true);
                        break;
                    case 36:
                        WO0.I(this.c[i2], (List) Op1.n(obj, y(O)), bv, true);
                        break;
                    case 37:
                        WO0.L(this.c[i2], (List) Op1.n(obj, y(O)), bv, true);
                        break;
                    case 38:
                        WO0.T(this.c[i2], (List) Op1.n(obj, y(O)), bv, true);
                        break;
                    case 39:
                        WO0.K(this.c[i2], (List) Op1.n(obj, y(O)), bv, true);
                        break;
                    case 40:
                        WO0.H(this.c[i2], (List) Op1.n(obj, y(O)), bv, true);
                        break;
                    case 41:
                        WO0.G(this.c[i2], (List) Op1.n(obj, y(O)), bv, true);
                        break;
                    case 42:
                        WO0.C(this.c[i2], (List) Op1.n(obj, y(O)), bv, true);
                        break;
                    case 43:
                        WO0.S(this.c[i2], (List) Op1.n(obj, y(O)), bv, true);
                        break;
                    case 44:
                        WO0.F(this.c[i2], (List) Op1.n(obj, y(O)), bv, true);
                        break;
                    case 45:
                        WO0.N(this.c[i2], (List) Op1.n(obj, y(O)), bv, true);
                        break;
                    case 46:
                        WO0.O(this.c[i2], (List) Op1.n(obj, y(O)), bv, true);
                        break;
                    case 47:
                        WO0.P(this.c[i2], (List) Op1.n(obj, y(O)), bv, true);
                        break;
                    case 48:
                        WO0.Q(this.c[i2], (List) Op1.n(obj, y(O)), bv, true);
                        break;
                    case 49:
                        WO0.J(this.c[i2], (List) Op1.n(obj, y(O)), bv, m(i2));
                        break;
                    case 50:
                        Q(bv, i3, Op1.n(obj, y(O)), i2);
                        break;
                    case 51:
                        if (s(obj, i3, i2)) {
                            bv.c(i3, A(obj, y(O)));
                            break;
                        } else {
                            break;
                        }
                    case 52:
                        if (s(obj, i3, i2)) {
                            bv.f(i3, B(obj, y(O)));
                            break;
                        } else {
                            break;
                        }
                    case 53:
                        if (s(obj, i3, i2)) {
                            bv.h(i3, D(obj, y(O)));
                            break;
                        } else {
                            break;
                        }
                    case 54:
                        if (s(obj, i3, i2)) {
                            bv.o(i3, D(obj, y(O)));
                            break;
                        } else {
                            break;
                        }
                    case 55:
                        if (s(obj, i3, i2)) {
                            bv.f7771a.J(i3, C(obj, y(O)));
                            break;
                        } else {
                            break;
                        }
                    case 56:
                        if (s(obj, i3, i2)) {
                            bv.e(i3, D(obj, y(O)));
                            break;
                        } else {
                            break;
                        }
                    case 57:
                        if (s(obj, i3, i2)) {
                            bv.d(i3, C(obj, y(O)));
                            break;
                        } else {
                            break;
                        }
                    case 58:
                        if (s(obj, i3, i2)) {
                            bv.a(i3, z(obj, y(O)));
                            break;
                        } else {
                            break;
                        }
                    case 59:
                        if (s(obj, i3, i2)) {
                            R(i3, Op1.n(obj, y(O)), bv);
                            break;
                        } else {
                            break;
                        }
                    case 60:
                        if (s(obj, i3, i2)) {
                            bv.i(i3, Op1.n(obj, y(O)), m(i2));
                            break;
                        } else {
                            break;
                        }
                    case 61:
                        if (s(obj, i3, i2)) {
                            bv.b(i3, (AbstractC1248Uk) Op1.n(obj, y(O)));
                            break;
                        } else {
                            break;
                        }
                    case 62:
                        if (s(obj, i3, i2)) {
                            bv.n(i3, C(obj, y(O)));
                            break;
                        } else {
                            break;
                        }
                    case 63:
                        if (s(obj, i3, i2)) {
                            bv.f7771a.J(i3, C(obj, y(O)));
                            break;
                        } else {
                            break;
                        }
                    case 64:
                        if (s(obj, i3, i2)) {
                            bv.j(i3, C(obj, y(O)));
                            break;
                        } else {
                            break;
                        }
                    case 65:
                        if (s(obj, i3, i2)) {
                            bv.k(i3, D(obj, y(O)));
                            break;
                        } else {
                            break;
                        }
                    case 66:
                        if (s(obj, i3, i2)) {
                            bv.l(i3, C(obj, y(O)));
                            break;
                        } else {
                            break;
                        }
                    case 67:
                        if (s(obj, i3, i2)) {
                            bv.m(i3, D(obj, y(O)));
                            break;
                        } else {
                            break;
                        }
                    case 68:
                        if (s(obj, i3, i2)) {
                            bv.g(i3, Op1.n(obj, y(O)), m(i2));
                            break;
                        } else {
                            break;
                        }
                }
            }
            Objects.requireNonNull(this.n);
            ((AbstractC2360eV) obj).c.e(bv);
            return;
        }
        P(obj, bv);
    }

    @Override // defpackage.UO0
    public void b(Object obj, Object obj2) {
        Objects.requireNonNull(obj2);
        int i2 = 0;
        while (true) {
            int[] iArr = this.c;
            if (i2 < iArr.length) {
                int i3 = iArr[i2 + 1];
                long y = y(i3);
                int i4 = this.c[i2];
                switch (N(i3)) {
                    case 0:
                        if (!q(obj2, i2)) {
                            break;
                        } else {
                            Op1.s(obj, y, Op1.j(obj2, y));
                            K(obj, i2);
                            break;
                        }
                    case 1:
                        if (!q(obj2, i2)) {
                            break;
                        } else {
                            Op1.e.n(obj, y, Op1.k(obj2, y));
                            K(obj, i2);
                            break;
                        }
                    case 2:
                        if (!q(obj2, i2)) {
                            break;
                        } else {
                            Op1.t(obj, y, Op1.m(obj2, y));
                            K(obj, i2);
                            break;
                        }
                    case 3:
                        if (!q(obj2, i2)) {
                            break;
                        } else {
                            Op1.t(obj, y, Op1.m(obj2, y));
                            K(obj, i2);
                            break;
                        }
                    case 4:
                        if (!q(obj2, i2)) {
                            break;
                        } else {
                            Op1.e.o(obj, y, Op1.l(obj2, y));
                            K(obj, i2);
                            break;
                        }
                    case 5:
                        if (!q(obj2, i2)) {
                            break;
                        } else {
                            Op1.t(obj, y, Op1.m(obj2, y));
                            K(obj, i2);
                            break;
                        }
                    case 6:
                        if (!q(obj2, i2)) {
                            break;
                        } else {
                            Op1.e.o(obj, y, Op1.l(obj2, y));
                            K(obj, i2);
                            break;
                        }
                    case Version.VERSION_7:
                        if (!q(obj2, i2)) {
                            break;
                        } else {
                            Op1.e.k(obj, y, Op1.f(obj2, y));
                            K(obj, i2);
                            break;
                        }
                    case Version.VERSION_8:
                        if (!q(obj2, i2)) {
                            break;
                        } else {
                            Op1.e.q(obj, y, Op1.n(obj2, y));
                            K(obj, i2);
                            break;
                        }
                    case Version.VERSION_9:
                        u(obj, obj2, i2);
                        break;
                    case Version.VERSION_10:
                        if (!q(obj2, i2)) {
                            break;
                        } else {
                            Op1.e.q(obj, y, Op1.n(obj2, y));
                            K(obj, i2);
                            break;
                        }
                    case Version.VERSION_11:
                        if (!q(obj2, i2)) {
                            break;
                        } else {
                            Op1.e.o(obj, y, Op1.l(obj2, y));
                            K(obj, i2);
                            break;
                        }
                    case Version.VERSION_12:
                        if (!q(obj2, i2)) {
                            break;
                        } else {
                            Op1.e.o(obj, y, Op1.l(obj2, y));
                            K(obj, i2);
                            break;
                        }
                    case Version.VERSION_13:
                        if (!q(obj2, i2)) {
                            break;
                        } else {
                            Op1.e.o(obj, y, Op1.l(obj2, y));
                            K(obj, i2);
                            break;
                        }
                    case Version.VERSION_14:
                        if (!q(obj2, i2)) {
                            break;
                        } else {
                            Op1.t(obj, y, Op1.m(obj2, y));
                            K(obj, i2);
                            break;
                        }
                    case Version.VERSION_15:
                        if (!q(obj2, i2)) {
                            break;
                        } else {
                            Op1.e.o(obj, y, Op1.l(obj2, y));
                            K(obj, i2);
                            break;
                        }
                    case Version.VERSION_16:
                        if (!q(obj2, i2)) {
                            break;
                        } else {
                            Op1.t(obj, y, Op1.m(obj2, y));
                            K(obj, i2);
                            break;
                        }
                    case Version.VERSION_17:
                        u(obj, obj2, i2);
                        break;
                    case Version.VERSION_18:
                    case Version.VERSION_19:
                    case Version.VERSION_20:
                    case Version.VERSION_21:
                    case Version.VERSION_22:
                    case Version.VERSION_23:
                    case Version.VERSION_24:
                    case Version.VERSION_25:
                    case Version.VERSION_26:
                    case Version.VERSION_27:
                    case Version.VERSION_28:
                    case Version.VERSION_29:
                    case Version.VERSION_30:
                    case Version.VERSION_31:
                    case Version.VERSION_32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                        this.m.b(obj, obj2, y);
                        break;
                    case 50:
                        C0129Cc0 cc0 = this.p;
                        Class cls = WO0.f9145a;
                        Op1.e.q(obj, y, cc0.b(Op1.n(obj, y), Op1.n(obj2, y)));
                        break;
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                    case 58:
                    case 59:
                        if (!s(obj2, i4, i2)) {
                            break;
                        } else {
                            Op1.e.q(obj, y, Op1.n(obj2, y));
                            L(obj, i4, i2);
                            break;
                        }
                    case 60:
                        v(obj, obj2, i2);
                        break;
                    case 61:
                    case 62:
                    case 63:
                    case 64:
                    case 65:
                    case 66:
                    case 67:
                        if (!s(obj2, i4, i2)) {
                            break;
                        } else {
                            Op1.e.q(obj, y, Op1.n(obj2, y));
                            L(obj, i4, i2);
                            break;
                        }
                    case 68:
                        v(obj, obj2, i2);
                        break;
                }
                i2 += 3;
            } else {
                WO0.z(this.n, obj, obj2);
                return;
            }
        }
    }

    @Override // defpackage.UO0
    public void c(Object obj) {
        int i2;
        int i3 = this.j;
        while (true) {
            i2 = this.k;
            if (i3 >= i2) {
                break;
            }
            long y = y(O(this.i[i3]));
            Object n2 = Op1.n(obj, y);
            if (n2 != null) {
                Objects.requireNonNull(this.p);
                ((C0068Bc0) n2).G = false;
                Op1.e.q(obj, y, n2);
            }
            i3++;
        }
        int length = this.i.length;
        while (i2 < length) {
            this.m.a(obj, (long) this.i[i2]);
            i2++;
        }
        Objects.requireNonNull(this.n);
        ((AbstractC2360eV) obj).c.f = false;
    }

    @Override // defpackage.UO0
    public final boolean d(Object obj) {
        int i2;
        int i3;
        int i4 = 1048575;
        int i5 = 0;
        int i6 = 0;
        while (true) {
            boolean z = true;
            if (i6 >= this.j) {
                return true;
            }
            int i7 = this.i[i6];
            int i8 = this.c[i7];
            int O = O(i7);
            int i9 = this.c[i7 + 2];
            int i10 = i9 & 1048575;
            int i11 = 1 << (i9 >>> 20);
            if (i10 != i4) {
                if (i10 != 1048575) {
                    i5 = b.getInt(obj, (long) i10);
                }
                i2 = i5;
                i3 = i10;
            } else {
                i3 = i4;
                i2 = i5;
            }
            if (((268435456 & O) != 0) && !r(obj, i7, i3, i2, i11)) {
                return false;
            }
            int N = N(O);
            if (N != 9 && N != 17) {
                if (N != 27) {
                    if (N == 60 || N == 68) {
                        if (s(obj, i8, i7) && !m(i7).d(Op1.n(obj, y(O)))) {
                            return false;
                        }
                    } else if (N != 49) {
                        if (N != 50) {
                            continue;
                        } else {
                            C0129Cc0 cc0 = this.p;
                            Object n2 = Op1.n(obj, y(O));
                            Objects.requireNonNull(cc0);
                            if (!((C0068Bc0) n2).isEmpty()) {
                                Object obj2 = this.d[(i7 / 3) * 2];
                                Objects.requireNonNull(this.p);
                                C5859z.a(obj2);
                                throw null;
                            }
                        }
                    }
                }
                List list = (List) Op1.n(obj, y(O));
                if (!list.isEmpty()) {
                    UO0 m2 = m(i7);
                    int i12 = 0;
                    while (true) {
                        if (i12 >= list.size()) {
                            break;
                        } else if (!m2.d(list.get(i12))) {
                            z = false;
                            break;
                        } else {
                            i12++;
                        }
                    }
                }
                if (!z) {
                    return false;
                }
            } else if (r(obj, i7, i3, i2, i11) && !m(i7).d(Op1.n(obj, y(O)))) {
                return false;
            }
            i6++;
            i4 = i3;
            i5 = i2;
        }
    }

    @Override // defpackage.UO0
    public void e(Object obj, byte[] bArr, int i2, int i3, C3566la laVar) {
        if (this.h) {
            H(obj, bArr, i2, i3, laVar);
        } else {
            G(obj, bArr, i2, i3, 0, laVar);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0041, code lost:
        if (defpackage.WO0.A(defpackage.Op1.n(r10, r5), defpackage.Op1.n(r11, r5)) != false) goto L_0x01cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0073, code lost:
        if (defpackage.WO0.A(defpackage.Op1.n(r10, r5), defpackage.Op1.n(r11, r5)) != false) goto L_0x01cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0087, code lost:
        if (defpackage.Op1.m(r10, r5) == defpackage.Op1.m(r11, r5)) goto L_0x01cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0099, code lost:
        if (defpackage.Op1.l(r10, r5) == defpackage.Op1.l(r11, r5)) goto L_0x01cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00ad, code lost:
        if (defpackage.Op1.m(r10, r5) == defpackage.Op1.m(r11, r5)) goto L_0x01cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00bf, code lost:
        if (defpackage.Op1.l(r10, r5) == defpackage.Op1.l(r11, r5)) goto L_0x01cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00d1, code lost:
        if (defpackage.Op1.l(r10, r5) == defpackage.Op1.l(r11, r5)) goto L_0x01cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00e3, code lost:
        if (defpackage.Op1.l(r10, r5) == defpackage.Op1.l(r11, r5)) goto L_0x01cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00f9, code lost:
        if (defpackage.WO0.A(defpackage.Op1.n(r10, r5), defpackage.Op1.n(r11, r5)) != false) goto L_0x01cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x010f, code lost:
        if (defpackage.WO0.A(defpackage.Op1.n(r10, r5), defpackage.Op1.n(r11, r5)) != false) goto L_0x01cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0125, code lost:
        if (defpackage.WO0.A(defpackage.Op1.n(r10, r5), defpackage.Op1.n(r11, r5)) != false) goto L_0x01cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0137, code lost:
        if (defpackage.Op1.f(r10, r5) == defpackage.Op1.f(r11, r5)) goto L_0x01cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0149, code lost:
        if (defpackage.Op1.l(r10, r5) == defpackage.Op1.l(r11, r5)) goto L_0x01cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x015d, code lost:
        if (defpackage.Op1.m(r10, r5) == defpackage.Op1.m(r11, r5)) goto L_0x01cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x016f, code lost:
        if (defpackage.Op1.l(r10, r5) == defpackage.Op1.l(r11, r5)) goto L_0x01cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0182, code lost:
        if (defpackage.Op1.m(r10, r5) == defpackage.Op1.m(r11, r5)) goto L_0x01cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0195, code lost:
        if (defpackage.Op1.m(r10, r5) == defpackage.Op1.m(r11, r5)) goto L_0x01cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01ae, code lost:
        if (java.lang.Float.floatToIntBits(defpackage.Op1.k(r10, r5)) == java.lang.Float.floatToIntBits(defpackage.Op1.k(r11, r5))) goto L_0x01cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x01c9, code lost:
        if (java.lang.Double.doubleToLongBits(defpackage.Op1.j(r10, r5)) == java.lang.Double.doubleToLongBits(defpackage.Op1.j(r11, r5))) goto L_0x01cd;
     */
    @Override // defpackage.UO0
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean f(java.lang.Object r10, java.lang.Object r11) {
        /*
        // Method dump skipped, instructions count: 636
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C3597lk0.f(java.lang.Object, java.lang.Object):boolean");
    }

    @Override // defpackage.UO0
    public int g(Object obj) {
        return this.h ? p(obj) : o(obj);
    }

    @Override // defpackage.UO0
    public Object h() {
        C1072Rn0 rn0 = this.l;
        AbstractC1125Sj0 sj0 = this.g;
        Objects.requireNonNull(rn0);
        return ((AbstractC2360eV) sj0).e(EnumC2190dV.NEW_MUTABLE_INSTANCE, null, null);
    }

    @Override // defpackage.UO0
    public int i(Object obj) {
        int i2;
        int i3;
        int length = this.c.length;
        int i4 = 0;
        for (int i5 = 0; i5 < length; i5 += 3) {
            int O = O(i5);
            int i6 = this.c[i5];
            long y = y(O);
            int i7 = 37;
            switch (N(O)) {
                case 0:
                    i3 = i4 * 53;
                    i2 = F30.b(Double.doubleToLongBits(Op1.j(obj, y)));
                    i4 = i2 + i3;
                    break;
                case 1:
                    i3 = i4 * 53;
                    i2 = Float.floatToIntBits(Op1.k(obj, y));
                    i4 = i2 + i3;
                    break;
                case 2:
                    i3 = i4 * 53;
                    i2 = F30.b(Op1.m(obj, y));
                    i4 = i2 + i3;
                    break;
                case 3:
                    i3 = i4 * 53;
                    i2 = F30.b(Op1.m(obj, y));
                    i4 = i2 + i3;
                    break;
                case 4:
                    i3 = i4 * 53;
                    i2 = Op1.l(obj, y);
                    i4 = i2 + i3;
                    break;
                case 5:
                    i3 = i4 * 53;
                    i2 = F30.b(Op1.m(obj, y));
                    i4 = i2 + i3;
                    break;
                case 6:
                    i3 = i4 * 53;
                    i2 = Op1.l(obj, y);
                    i4 = i2 + i3;
                    break;
                case Version.VERSION_7:
                    i3 = i4 * 53;
                    i2 = F30.a(Op1.f(obj, y));
                    i4 = i2 + i3;
                    break;
                case Version.VERSION_8:
                    i3 = i4 * 53;
                    i2 = ((String) Op1.n(obj, y)).hashCode();
                    i4 = i2 + i3;
                    break;
                case Version.VERSION_9:
                    Object n2 = Op1.n(obj, y);
                    if (n2 != null) {
                        i7 = n2.hashCode();
                    }
                    i4 = (i4 * 53) + i7;
                    break;
                case Version.VERSION_10:
                    i3 = i4 * 53;
                    i2 = Op1.n(obj, y).hashCode();
                    i4 = i2 + i3;
                    break;
                case Version.VERSION_11:
                    i3 = i4 * 53;
                    i2 = Op1.l(obj, y);
                    i4 = i2 + i3;
                    break;
                case Version.VERSION_12:
                    i3 = i4 * 53;
                    i2 = Op1.l(obj, y);
                    i4 = i2 + i3;
                    break;
                case Version.VERSION_13:
                    i3 = i4 * 53;
                    i2 = Op1.l(obj, y);
                    i4 = i2 + i3;
                    break;
                case Version.VERSION_14:
                    i3 = i4 * 53;
                    i2 = F30.b(Op1.m(obj, y));
                    i4 = i2 + i3;
                    break;
                case Version.VERSION_15:
                    i3 = i4 * 53;
                    i2 = Op1.l(obj, y);
                    i4 = i2 + i3;
                    break;
                case Version.VERSION_16:
                    i3 = i4 * 53;
                    i2 = F30.b(Op1.m(obj, y));
                    i4 = i2 + i3;
                    break;
                case Version.VERSION_17:
                    Object n3 = Op1.n(obj, y);
                    if (n3 != null) {
                        i7 = n3.hashCode();
                    }
                    i4 = (i4 * 53) + i7;
                    break;
                case Version.VERSION_18:
                case Version.VERSION_19:
                case Version.VERSION_20:
                case Version.VERSION_21:
                case Version.VERSION_22:
                case Version.VERSION_23:
                case Version.VERSION_24:
                case Version.VERSION_25:
                case Version.VERSION_26:
                case Version.VERSION_27:
                case Version.VERSION_28:
                case Version.VERSION_29:
                case Version.VERSION_30:
                case Version.VERSION_31:
                case Version.VERSION_32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i3 = i4 * 53;
                    i2 = Op1.n(obj, y).hashCode();
                    i4 = i2 + i3;
                    break;
                case 50:
                    i3 = i4 * 53;
                    i2 = Op1.n(obj, y).hashCode();
                    i4 = i2 + i3;
                    break;
                case 51:
                    if (s(obj, i6, i5)) {
                        i3 = i4 * 53;
                        i2 = F30.b(Double.doubleToLongBits(A(obj, y)));
                        i4 = i2 + i3;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (s(obj, i6, i5)) {
                        i3 = i4 * 53;
                        i2 = Float.floatToIntBits(B(obj, y));
                        i4 = i2 + i3;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (s(obj, i6, i5)) {
                        i3 = i4 * 53;
                        i2 = F30.b(D(obj, y));
                        i4 = i2 + i3;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (s(obj, i6, i5)) {
                        i3 = i4 * 53;
                        i2 = F30.b(D(obj, y));
                        i4 = i2 + i3;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (s(obj, i6, i5)) {
                        i3 = i4 * 53;
                        i2 = C(obj, y);
                        i4 = i2 + i3;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (s(obj, i6, i5)) {
                        i3 = i4 * 53;
                        i2 = F30.b(D(obj, y));
                        i4 = i2 + i3;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (s(obj, i6, i5)) {
                        i3 = i4 * 53;
                        i2 = C(obj, y);
                        i4 = i2 + i3;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (s(obj, i6, i5)) {
                        i3 = i4 * 53;
                        i2 = F30.a(z(obj, y));
                        i4 = i2 + i3;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (s(obj, i6, i5)) {
                        i3 = i4 * 53;
                        i2 = ((String) Op1.n(obj, y)).hashCode();
                        i4 = i2 + i3;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (s(obj, i6, i5)) {
                        i3 = i4 * 53;
                        i2 = Op1.n(obj, y).hashCode();
                        i4 = i2 + i3;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (s(obj, i6, i5)) {
                        i3 = i4 * 53;
                        i2 = Op1.n(obj, y).hashCode();
                        i4 = i2 + i3;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (s(obj, i6, i5)) {
                        i3 = i4 * 53;
                        i2 = C(obj, y);
                        i4 = i2 + i3;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (s(obj, i6, i5)) {
                        i3 = i4 * 53;
                        i2 = C(obj, y);
                        i4 = i2 + i3;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (s(obj, i6, i5)) {
                        i3 = i4 * 53;
                        i2 = C(obj, y);
                        i4 = i2 + i3;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (s(obj, i6, i5)) {
                        i3 = i4 * 53;
                        i2 = F30.b(D(obj, y));
                        i4 = i2 + i3;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (s(obj, i6, i5)) {
                        i3 = i4 * 53;
                        i2 = C(obj, y);
                        i4 = i2 + i3;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (s(obj, i6, i5)) {
                        i3 = i4 * 53;
                        i2 = F30.b(D(obj, y));
                        i4 = i2 + i3;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (s(obj, i6, i5)) {
                        i3 = i4 * 53;
                        i2 = Op1.n(obj, y).hashCode();
                        i4 = i2 + i3;
                        break;
                    } else {
                        break;
                    }
            }
        }
        Objects.requireNonNull(this.n);
        return ((AbstractC2360eV) obj).c.hashCode() + (i4 * 53);
    }

    public final boolean j(Object obj, Object obj2, int i2) {
        return q(obj, i2) == q(obj2, i2);
    }

    public final D30 k(int i2) {
        return (D30) this.d[((i2 / 3) * 2) + 1];
    }

    public final Object l(int i2) {
        return this.d[(i2 / 3) * 2];
    }

    public final UO0 m(int i2) {
        int i3 = (i2 / 3) * 2;
        Object[] objArr = this.d;
        UO0 uo0 = (UO0) objArr[i3];
        if (uo0 != null) {
            return uo0;
        }
        UO0 a2 = C2163dI0.f9768a.a((Class) objArr[i3 + 1]);
        this.d[i3] = a2;
        return a2;
    }

    public final int o(Object obj) {
        int i2;
        int d2;
        int i3;
        int i4;
        int i5;
        int i6;
        Unsafe unsafe = b;
        int i7 = 1048575;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 1048575;
        while (i10 < this.c.length) {
            int O = O(i10);
            int i12 = this.c[i10];
            int N = N(O);
            if (N <= 17) {
                int i13 = this.c[i10 + 2];
                int i14 = i7 & i13;
                i2 = 1 << (i13 >>> 20);
                if (i14 != i11) {
                    i8 = unsafe.getInt(obj, (long) i14);
                    i11 = i14;
                }
            } else {
                i2 = 0;
            }
            long y = y(O);
            switch (N) {
                case 0:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        d2 = C6014zv.d(i12, 0.0d);
                        i9 += d2;
                        break;
                    }
                case 1:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        d2 = C6014zv.h(i12, 0.0f);
                        i9 += d2;
                        break;
                    }
                case 2:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        d2 = C6014zv.l(i12, unsafe.getLong(obj, y));
                        i9 += d2;
                        break;
                    }
                case 3:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        d2 = C6014zv.y(i12, unsafe.getLong(obj, y));
                        i9 += d2;
                        break;
                    }
                case 4:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        d2 = C6014zv.j(i12, unsafe.getInt(obj, y));
                        i9 += d2;
                        break;
                    }
                case 5:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        d2 = C6014zv.g(i12, 0);
                        i9 += d2;
                        break;
                    }
                case 6:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        d2 = C6014zv.f(i12, 0);
                        i9 += d2;
                        break;
                    }
                case Version.VERSION_7:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        d2 = C6014zv.a(i12, true);
                        i9 += d2;
                        break;
                    }
                case Version.VERSION_8:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        Object object = unsafe.getObject(obj, y);
                        if (object instanceof AbstractC1248Uk) {
                            i3 = C6014zv.b(i12, (AbstractC1248Uk) object);
                        } else {
                            i3 = C6014zv.t(i12, (String) object);
                        }
                        i9 += i3;
                        break;
                    }
                case Version.VERSION_9:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        d2 = WO0.n(i12, unsafe.getObject(obj, y), m(i10));
                        i9 += d2;
                        break;
                    }
                case Version.VERSION_10:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        d2 = C6014zv.b(i12, (AbstractC1248Uk) unsafe.getObject(obj, y));
                        i9 += d2;
                        break;
                    }
                case Version.VERSION_11:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        d2 = C6014zv.w(i12, unsafe.getInt(obj, y));
                        i9 += d2;
                        break;
                    }
                case Version.VERSION_12:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        d2 = C6014zv.e(i12, unsafe.getInt(obj, y));
                        i9 += d2;
                        break;
                    }
                case Version.VERSION_13:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        d2 = C6014zv.n(i12, 0);
                        i9 += d2;
                        break;
                    }
                case Version.VERSION_14:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        d2 = C6014zv.o(i12, 0);
                        i9 += d2;
                        break;
                    }
                case Version.VERSION_15:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        d2 = C6014zv.p(i12, unsafe.getInt(obj, y));
                        i9 += d2;
                        break;
                    }
                case Version.VERSION_16:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        d2 = C6014zv.r(i12, unsafe.getLong(obj, y));
                        i9 += d2;
                        break;
                    }
                case Version.VERSION_17:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        d2 = C6014zv.i(i12, (AbstractC1125Sj0) unsafe.getObject(obj, y), m(i10));
                        i9 += d2;
                        break;
                    }
                case Version.VERSION_18:
                    d2 = WO0.g(i12, (List) unsafe.getObject(obj, y), false);
                    i9 += d2;
                    break;
                case Version.VERSION_19:
                    d2 = WO0.e(i12, (List) unsafe.getObject(obj, y), false);
                    i9 += d2;
                    break;
                case Version.VERSION_20:
                    d2 = WO0.l(i12, (List) unsafe.getObject(obj, y), false);
                    i9 += d2;
                    break;
                case Version.VERSION_21:
                    d2 = WO0.w(i12, (List) unsafe.getObject(obj, y), false);
                    i9 += d2;
                    break;
                case Version.VERSION_22:
                    d2 = WO0.j(i12, (List) unsafe.getObject(obj, y), false);
                    i9 += d2;
                    break;
                case Version.VERSION_23:
                    d2 = WO0.g(i12, (List) unsafe.getObject(obj, y), false);
                    i9 += d2;
                    break;
                case Version.VERSION_24:
                    d2 = WO0.e(i12, (List) unsafe.getObject(obj, y), false);
                    i9 += d2;
                    break;
                case Version.VERSION_25:
                    d2 = WO0.a(i12, (List) unsafe.getObject(obj, y), false);
                    i9 += d2;
                    break;
                case Version.VERSION_26:
                    d2 = WO0.t(i12, (List) unsafe.getObject(obj, y));
                    i9 += d2;
                    break;
                case Version.VERSION_27:
                    d2 = WO0.o(i12, (List) unsafe.getObject(obj, y), m(i10));
                    i9 += d2;
                    break;
                case Version.VERSION_28:
                    d2 = WO0.b(i12, (List) unsafe.getObject(obj, y));
                    i9 += d2;
                    break;
                case Version.VERSION_29:
                    d2 = WO0.u(i12, (List) unsafe.getObject(obj, y), false);
                    i9 += d2;
                    break;
                case Version.VERSION_30:
                    d2 = WO0.c(i12, (List) unsafe.getObject(obj, y), false);
                    i9 += d2;
                    break;
                case Version.VERSION_31:
                    d2 = WO0.e(i12, (List) unsafe.getObject(obj, y), false);
                    i9 += d2;
                    break;
                case Version.VERSION_32:
                    d2 = WO0.g(i12, (List) unsafe.getObject(obj, y), false);
                    i9 += d2;
                    break;
                case 33:
                    d2 = WO0.p(i12, (List) unsafe.getObject(obj, y), false);
                    i9 += d2;
                    break;
                case 34:
                    d2 = WO0.r(i12, (List) unsafe.getObject(obj, y), false);
                    i9 += d2;
                    break;
                case 35:
                    i6 = WO0.h((List) unsafe.getObject(obj, y));
                    if (i6 <= 0) {
                        break;
                    } else {
                        i5 = C6014zv.v(i12);
                        i4 = C6014zv.x(i6);
                        i9 += i4 + i5 + i6;
                        break;
                    }
                case 36:
                    i6 = WO0.f((List) unsafe.getObject(obj, y));
                    if (i6 <= 0) {
                        break;
                    } else {
                        i5 = C6014zv.v(i12);
                        i4 = C6014zv.x(i6);
                        i9 += i4 + i5 + i6;
                        break;
                    }
                case 37:
                    i6 = WO0.m((List) unsafe.getObject(obj, y));
                    if (i6 <= 0) {
                        break;
                    } else {
                        i5 = C6014zv.v(i12);
                        i4 = C6014zv.x(i6);
                        i9 += i4 + i5 + i6;
                        break;
                    }
                case 38:
                    i6 = WO0.x((List) unsafe.getObject(obj, y));
                    if (i6 <= 0) {
                        break;
                    } else {
                        i5 = C6014zv.v(i12);
                        i4 = C6014zv.x(i6);
                        i9 += i4 + i5 + i6;
                        break;
                    }
                case 39:
                    i6 = WO0.k((List) unsafe.getObject(obj, y));
                    if (i6 <= 0) {
                        break;
                    } else {
                        i5 = C6014zv.v(i12);
                        i4 = C6014zv.x(i6);
                        i9 += i4 + i5 + i6;
                        break;
                    }
                case 40:
                    i6 = WO0.h((List) unsafe.getObject(obj, y));
                    if (i6 <= 0) {
                        break;
                    } else {
                        i5 = C6014zv.v(i12);
                        i4 = C6014zv.x(i6);
                        i9 += i4 + i5 + i6;
                        break;
                    }
                case 41:
                    i6 = WO0.f((List) unsafe.getObject(obj, y));
                    if (i6 <= 0) {
                        break;
                    } else {
                        i5 = C6014zv.v(i12);
                        i4 = C6014zv.x(i6);
                        i9 += i4 + i5 + i6;
                        break;
                    }
                case 42:
                    Class cls = WO0.f9145a;
                    i6 = ((List) unsafe.getObject(obj, y)).size();
                    if (i6 <= 0) {
                        break;
                    } else {
                        i5 = C6014zv.v(i12);
                        i4 = C6014zv.x(i6);
                        i9 += i4 + i5 + i6;
                        break;
                    }
                case 43:
                    i6 = WO0.v((List) unsafe.getObject(obj, y));
                    if (i6 <= 0) {
                        break;
                    } else {
                        i5 = C6014zv.v(i12);
                        i4 = C6014zv.x(i6);
                        i9 += i4 + i5 + i6;
                        break;
                    }
                case 44:
                    i6 = WO0.d((List) unsafe.getObject(obj, y));
                    if (i6 <= 0) {
                        break;
                    } else {
                        i5 = C6014zv.v(i12);
                        i4 = C6014zv.x(i6);
                        i9 += i4 + i5 + i6;
                        break;
                    }
                case 45:
                    i6 = WO0.f((List) unsafe.getObject(obj, y));
                    if (i6 <= 0) {
                        break;
                    } else {
                        i5 = C6014zv.v(i12);
                        i4 = C6014zv.x(i6);
                        i9 += i4 + i5 + i6;
                        break;
                    }
                case 46:
                    i6 = WO0.h((List) unsafe.getObject(obj, y));
                    if (i6 <= 0) {
                        break;
                    } else {
                        i5 = C6014zv.v(i12);
                        i4 = C6014zv.x(i6);
                        i9 += i4 + i5 + i6;
                        break;
                    }
                case 47:
                    i6 = WO0.q((List) unsafe.getObject(obj, y));
                    if (i6 <= 0) {
                        break;
                    } else {
                        i5 = C6014zv.v(i12);
                        i4 = C6014zv.x(i6);
                        i9 += i4 + i5 + i6;
                        break;
                    }
                case 48:
                    i6 = WO0.s((List) unsafe.getObject(obj, y));
                    if (i6 <= 0) {
                        break;
                    } else {
                        i5 = C6014zv.v(i12);
                        i4 = C6014zv.x(i6);
                        i9 += i4 + i5 + i6;
                        break;
                    }
                case 49:
                    d2 = WO0.i(i12, (List) unsafe.getObject(obj, y), m(i10));
                    i9 += d2;
                    break;
                case 50:
                    this.p.a(i12, unsafe.getObject(obj, y), l(i10));
                    i9 += 0;
                    break;
                case 51:
                    if (!s(obj, i12, i10)) {
                        break;
                    } else {
                        d2 = C6014zv.d(i12, 0.0d);
                        i9 += d2;
                        break;
                    }
                case 52:
                    if (!s(obj, i12, i10)) {
                        break;
                    } else {
                        d2 = C6014zv.h(i12, 0.0f);
                        i9 += d2;
                        break;
                    }
                case 53:
                    if (!s(obj, i12, i10)) {
                        break;
                    } else {
                        d2 = C6014zv.l(i12, D(obj, y));
                        i9 += d2;
                        break;
                    }
                case 54:
                    if (!s(obj, i12, i10)) {
                        break;
                    } else {
                        d2 = C6014zv.y(i12, D(obj, y));
                        i9 += d2;
                        break;
                    }
                case 55:
                    if (!s(obj, i12, i10)) {
                        break;
                    } else {
                        d2 = C6014zv.j(i12, C(obj, y));
                        i9 += d2;
                        break;
                    }
                case 56:
                    if (!s(obj, i12, i10)) {
                        break;
                    } else {
                        d2 = C6014zv.g(i12, 0);
                        i9 += d2;
                        break;
                    }
                case 57:
                    if (!s(obj, i12, i10)) {
                        break;
                    } else {
                        d2 = C6014zv.f(i12, 0);
                        i9 += d2;
                        break;
                    }
                case 58:
                    if (!s(obj, i12, i10)) {
                        break;
                    } else {
                        d2 = C6014zv.a(i12, true);
                        i9 += d2;
                        break;
                    }
                case 59:
                    if (!s(obj, i12, i10)) {
                        break;
                    } else {
                        Object object2 = unsafe.getObject(obj, y);
                        if (object2 instanceof AbstractC1248Uk) {
                            i3 = C6014zv.b(i12, (AbstractC1248Uk) object2);
                        } else {
                            i3 = C6014zv.t(i12, (String) object2);
                        }
                        i9 += i3;
                        break;
                    }
                case 60:
                    if (!s(obj, i12, i10)) {
                        break;
                    } else {
                        d2 = WO0.n(i12, unsafe.getObject(obj, y), m(i10));
                        i9 += d2;
                        break;
                    }
                case 61:
                    if (!s(obj, i12, i10)) {
                        break;
                    } else {
                        d2 = C6014zv.b(i12, (AbstractC1248Uk) unsafe.getObject(obj, y));
                        i9 += d2;
                        break;
                    }
                case 62:
                    if (!s(obj, i12, i10)) {
                        break;
                    } else {
                        d2 = C6014zv.w(i12, C(obj, y));
                        i9 += d2;
                        break;
                    }
                case 63:
                    if (!s(obj, i12, i10)) {
                        break;
                    } else {
                        d2 = C6014zv.e(i12, C(obj, y));
                        i9 += d2;
                        break;
                    }
                case 64:
                    if (!s(obj, i12, i10)) {
                        break;
                    } else {
                        d2 = C6014zv.n(i12, 0);
                        i9 += d2;
                        break;
                    }
                case 65:
                    if (!s(obj, i12, i10)) {
                        break;
                    } else {
                        d2 = C6014zv.o(i12, 0);
                        i9 += d2;
                        break;
                    }
                case 66:
                    if (!s(obj, i12, i10)) {
                        break;
                    } else {
                        d2 = C6014zv.p(i12, C(obj, y));
                        i9 += d2;
                        break;
                    }
                case 67:
                    if (!s(obj, i12, i10)) {
                        break;
                    } else {
                        d2 = C6014zv.r(i12, D(obj, y));
                        i9 += d2;
                        break;
                    }
                case 68:
                    if (!s(obj, i12, i10)) {
                        break;
                    } else {
                        d2 = C6014zv.i(i12, (AbstractC1125Sj0) unsafe.getObject(obj, y), m(i10));
                        i9 += d2;
                        break;
                    }
            }
            i10 += 3;
            i7 = 1048575;
            i11 = i11;
        }
        Objects.requireNonNull(this.n);
        return ((AbstractC2360eV) obj).c.a() + i9;
    }

    public final int p(Object obj) {
        int i2;
        int i3;
        int i4;
        int i5;
        Unsafe unsafe = b;
        int i6 = 0;
        for (int i7 = 0; i7 < this.c.length; i7 += 3) {
            int O = O(i7);
            int N = N(O);
            int i8 = this.c[i7];
            long y = y(O);
            if (N >= DP.DOUBLE_LIST_PACKED.G0 && N <= DP.SINT64_LIST_PACKED.G0) {
                int i9 = this.c[i7 + 2];
            }
            switch (N) {
                case 0:
                    if (q(obj, i7)) {
                        i2 = C6014zv.d(i8, 0.0d);
                        i6 += i2;
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (q(obj, i7)) {
                        i2 = C6014zv.h(i8, 0.0f);
                        i6 += i2;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (q(obj, i7)) {
                        i2 = C6014zv.l(i8, Op1.m(obj, y));
                        i6 += i2;
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (q(obj, i7)) {
                        i2 = C6014zv.y(i8, Op1.m(obj, y));
                        i6 += i2;
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (q(obj, i7)) {
                        i2 = C6014zv.j(i8, Op1.l(obj, y));
                        i6 += i2;
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (q(obj, i7)) {
                        i2 = C6014zv.g(i8, 0);
                        i6 += i2;
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (q(obj, i7)) {
                        i2 = C6014zv.f(i8, 0);
                        i6 += i2;
                        break;
                    } else {
                        break;
                    }
                case Version.VERSION_7:
                    if (q(obj, i7)) {
                        i2 = C6014zv.a(i8, true);
                        i6 += i2;
                        break;
                    } else {
                        break;
                    }
                case Version.VERSION_8:
                    if (q(obj, i7)) {
                        Object n2 = Op1.n(obj, y);
                        if (n2 instanceof AbstractC1248Uk) {
                            i2 = C6014zv.b(i8, (AbstractC1248Uk) n2);
                        } else {
                            i2 = C6014zv.t(i8, (String) n2);
                        }
                        i6 += i2;
                        break;
                    } else {
                        break;
                    }
                case Version.VERSION_9:
                    if (q(obj, i7)) {
                        i2 = WO0.n(i8, Op1.n(obj, y), m(i7));
                        i6 += i2;
                        break;
                    } else {
                        break;
                    }
                case Version.VERSION_10:
                    if (q(obj, i7)) {
                        i2 = C6014zv.b(i8, (AbstractC1248Uk) Op1.n(obj, y));
                        i6 += i2;
                        break;
                    } else {
                        break;
                    }
                case Version.VERSION_11:
                    if (q(obj, i7)) {
                        i2 = C6014zv.w(i8, Op1.l(obj, y));
                        i6 += i2;
                        break;
                    } else {
                        break;
                    }
                case Version.VERSION_12:
                    if (q(obj, i7)) {
                        i2 = C6014zv.e(i8, Op1.l(obj, y));
                        i6 += i2;
                        break;
                    } else {
                        break;
                    }
                case Version.VERSION_13:
                    if (q(obj, i7)) {
                        i2 = C6014zv.n(i8, 0);
                        i6 += i2;
                        break;
                    } else {
                        break;
                    }
                case Version.VERSION_14:
                    if (q(obj, i7)) {
                        i2 = C6014zv.o(i8, 0);
                        i6 += i2;
                        break;
                    } else {
                        break;
                    }
                case Version.VERSION_15:
                    if (q(obj, i7)) {
                        i2 = C6014zv.p(i8, Op1.l(obj, y));
                        i6 += i2;
                        break;
                    } else {
                        break;
                    }
                case Version.VERSION_16:
                    if (q(obj, i7)) {
                        i2 = C6014zv.r(i8, Op1.m(obj, y));
                        i6 += i2;
                        break;
                    } else {
                        break;
                    }
                case Version.VERSION_17:
                    if (q(obj, i7)) {
                        i2 = C6014zv.i(i8, (AbstractC1125Sj0) Op1.n(obj, y), m(i7));
                        i6 += i2;
                        break;
                    } else {
                        break;
                    }
                case Version.VERSION_18:
                    i2 = WO0.g(i8, t(obj, y), false);
                    i6 += i2;
                    break;
                case Version.VERSION_19:
                    i2 = WO0.e(i8, t(obj, y), false);
                    i6 += i2;
                    break;
                case Version.VERSION_20:
                    i2 = WO0.l(i8, t(obj, y), false);
                    i6 += i2;
                    break;
                case Version.VERSION_21:
                    i2 = WO0.w(i8, t(obj, y), false);
                    i6 += i2;
                    break;
                case Version.VERSION_22:
                    i2 = WO0.j(i8, t(obj, y), false);
                    i6 += i2;
                    break;
                case Version.VERSION_23:
                    i2 = WO0.g(i8, t(obj, y), false);
                    i6 += i2;
                    break;
                case Version.VERSION_24:
                    i2 = WO0.e(i8, t(obj, y), false);
                    i6 += i2;
                    break;
                case Version.VERSION_25:
                    i2 = WO0.a(i8, t(obj, y), false);
                    i6 += i2;
                    break;
                case Version.VERSION_26:
                    i2 = WO0.t(i8, t(obj, y));
                    i6 += i2;
                    break;
                case Version.VERSION_27:
                    i2 = WO0.o(i8, t(obj, y), m(i7));
                    i6 += i2;
                    break;
                case Version.VERSION_28:
                    i2 = WO0.b(i8, t(obj, y));
                    i6 += i2;
                    break;
                case Version.VERSION_29:
                    i2 = WO0.u(i8, t(obj, y), false);
                    i6 += i2;
                    break;
                case Version.VERSION_30:
                    i2 = WO0.c(i8, t(obj, y), false);
                    i6 += i2;
                    break;
                case Version.VERSION_31:
                    i2 = WO0.e(i8, t(obj, y), false);
                    i6 += i2;
                    break;
                case Version.VERSION_32:
                    i2 = WO0.g(i8, t(obj, y), false);
                    i6 += i2;
                    break;
                case 33:
                    i2 = WO0.p(i8, t(obj, y), false);
                    i6 += i2;
                    break;
                case 34:
                    i2 = WO0.r(i8, t(obj, y), false);
                    i6 += i2;
                    break;
                case 35:
                    i5 = WO0.h((List) unsafe.getObject(obj, y));
                    if (i5 > 0) {
                        i4 = C6014zv.v(i8);
                        i3 = C6014zv.x(i5);
                        i6 += i3 + i4 + i5;
                        break;
                    } else {
                        break;
                    }
                case 36:
                    i5 = WO0.f((List) unsafe.getObject(obj, y));
                    if (i5 > 0) {
                        i4 = C6014zv.v(i8);
                        i3 = C6014zv.x(i5);
                        i6 += i3 + i4 + i5;
                        break;
                    } else {
                        break;
                    }
                case 37:
                    i5 = WO0.m((List) unsafe.getObject(obj, y));
                    if (i5 > 0) {
                        i4 = C6014zv.v(i8);
                        i3 = C6014zv.x(i5);
                        i6 += i3 + i4 + i5;
                        break;
                    } else {
                        break;
                    }
                case 38:
                    i5 = WO0.x((List) unsafe.getObject(obj, y));
                    if (i5 > 0) {
                        i4 = C6014zv.v(i8);
                        i3 = C6014zv.x(i5);
                        i6 += i3 + i4 + i5;
                        break;
                    } else {
                        break;
                    }
                case 39:
                    i5 = WO0.k((List) unsafe.getObject(obj, y));
                    if (i5 > 0) {
                        i4 = C6014zv.v(i8);
                        i3 = C6014zv.x(i5);
                        i6 += i3 + i4 + i5;
                        break;
                    } else {
                        break;
                    }
                case 40:
                    i5 = WO0.h((List) unsafe.getObject(obj, y));
                    if (i5 > 0) {
                        i4 = C6014zv.v(i8);
                        i3 = C6014zv.x(i5);
                        i6 += i3 + i4 + i5;
                        break;
                    } else {
                        break;
                    }
                case 41:
                    i5 = WO0.f((List) unsafe.getObject(obj, y));
                    if (i5 > 0) {
                        i4 = C6014zv.v(i8);
                        i3 = C6014zv.x(i5);
                        i6 += i3 + i4 + i5;
                        break;
                    } else {
                        break;
                    }
                case 42:
                    Class cls = WO0.f9145a;
                    i5 = ((List) unsafe.getObject(obj, y)).size();
                    if (i5 > 0) {
                        i4 = C6014zv.v(i8);
                        i3 = C6014zv.x(i5);
                        i6 += i3 + i4 + i5;
                        break;
                    } else {
                        break;
                    }
                case 43:
                    i5 = WO0.v((List) unsafe.getObject(obj, y));
                    if (i5 > 0) {
                        i4 = C6014zv.v(i8);
                        i3 = C6014zv.x(i5);
                        i6 += i3 + i4 + i5;
                        break;
                    } else {
                        break;
                    }
                case 44:
                    i5 = WO0.d((List) unsafe.getObject(obj, y));
                    if (i5 > 0) {
                        i4 = C6014zv.v(i8);
                        i3 = C6014zv.x(i5);
                        i6 += i3 + i4 + i5;
                        break;
                    } else {
                        break;
                    }
                case 45:
                    i5 = WO0.f((List) unsafe.getObject(obj, y));
                    if (i5 > 0) {
                        i4 = C6014zv.v(i8);
                        i3 = C6014zv.x(i5);
                        i6 += i3 + i4 + i5;
                        break;
                    } else {
                        break;
                    }
                case 46:
                    i5 = WO0.h((List) unsafe.getObject(obj, y));
                    if (i5 > 0) {
                        i4 = C6014zv.v(i8);
                        i3 = C6014zv.x(i5);
                        i6 += i3 + i4 + i5;
                        break;
                    } else {
                        break;
                    }
                case 47:
                    i5 = WO0.q((List) unsafe.getObject(obj, y));
                    if (i5 > 0) {
                        i4 = C6014zv.v(i8);
                        i3 = C6014zv.x(i5);
                        i6 += i3 + i4 + i5;
                        break;
                    } else {
                        break;
                    }
                case 48:
                    i5 = WO0.s((List) unsafe.getObject(obj, y));
                    if (i5 > 0) {
                        i4 = C6014zv.v(i8);
                        i3 = C6014zv.x(i5);
                        i6 += i3 + i4 + i5;
                        break;
                    } else {
                        break;
                    }
                case 49:
                    i2 = WO0.i(i8, t(obj, y), m(i7));
                    i6 += i2;
                    break;
                case 50:
                    this.p.a(i8, Op1.n(obj, y), l(i7));
                    i6 += 0;
                    break;
                case 51:
                    if (s(obj, i8, i7)) {
                        i2 = C6014zv.d(i8, 0.0d);
                        i6 += i2;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (s(obj, i8, i7)) {
                        i2 = C6014zv.h(i8, 0.0f);
                        i6 += i2;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (s(obj, i8, i7)) {
                        i2 = C6014zv.l(i8, D(obj, y));
                        i6 += i2;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (s(obj, i8, i7)) {
                        i2 = C6014zv.y(i8, D(obj, y));
                        i6 += i2;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (s(obj, i8, i7)) {
                        i2 = C6014zv.j(i8, C(obj, y));
                        i6 += i2;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (s(obj, i8, i7)) {
                        i2 = C6014zv.g(i8, 0);
                        i6 += i2;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (s(obj, i8, i7)) {
                        i2 = C6014zv.f(i8, 0);
                        i6 += i2;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (s(obj, i8, i7)) {
                        i2 = C6014zv.a(i8, true);
                        i6 += i2;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (s(obj, i8, i7)) {
                        Object n3 = Op1.n(obj, y);
                        if (n3 instanceof AbstractC1248Uk) {
                            i2 = C6014zv.b(i8, (AbstractC1248Uk) n3);
                        } else {
                            i2 = C6014zv.t(i8, (String) n3);
                        }
                        i6 += i2;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (s(obj, i8, i7)) {
                        i2 = WO0.n(i8, Op1.n(obj, y), m(i7));
                        i6 += i2;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (s(obj, i8, i7)) {
                        i2 = C6014zv.b(i8, (AbstractC1248Uk) Op1.n(obj, y));
                        i6 += i2;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (s(obj, i8, i7)) {
                        i2 = C6014zv.w(i8, C(obj, y));
                        i6 += i2;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (s(obj, i8, i7)) {
                        i2 = C6014zv.e(i8, C(obj, y));
                        i6 += i2;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (s(obj, i8, i7)) {
                        i2 = C6014zv.n(i8, 0);
                        i6 += i2;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (s(obj, i8, i7)) {
                        i2 = C6014zv.o(i8, 0);
                        i6 += i2;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (s(obj, i8, i7)) {
                        i2 = C6014zv.p(i8, C(obj, y));
                        i6 += i2;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (s(obj, i8, i7)) {
                        i2 = C6014zv.r(i8, D(obj, y));
                        i6 += i2;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (s(obj, i8, i7)) {
                        i2 = C6014zv.i(i8, (AbstractC1125Sj0) Op1.n(obj, y), m(i7));
                        i6 += i2;
                        break;
                    } else {
                        break;
                    }
            }
        }
        Objects.requireNonNull(this.n);
        return ((AbstractC2360eV) obj).c.a() + i6;
    }

    public final boolean q(Object obj, int i2) {
        int[] iArr = this.c;
        int i3 = iArr[i2 + 2];
        long j2 = (long) (1048575 & i3);
        if (j2 == 1048575) {
            int i4 = iArr[i2 + 1];
            long y = y(i4);
            switch (N(i4)) {
                case 0:
                    return Op1.j(obj, y) != 0.0d;
                case 1:
                    return Op1.k(obj, y) != 0.0f;
                case 2:
                    return Op1.m(obj, y) != 0;
                case 3:
                    return Op1.m(obj, y) != 0;
                case 4:
                    return Op1.l(obj, y) != 0;
                case 5:
                    return Op1.m(obj, y) != 0;
                case 6:
                    return Op1.l(obj, y) != 0;
                case Version.VERSION_7:
                    return Op1.f(obj, y);
                case Version.VERSION_8:
                    Object n2 = Op1.n(obj, y);
                    if (n2 instanceof String) {
                        return !((String) n2).isEmpty();
                    }
                    if (n2 instanceof AbstractC1248Uk) {
                        return !AbstractC1248Uk.F.equals(n2);
                    }
                    throw new IllegalArgumentException();
                case Version.VERSION_9:
                    return Op1.n(obj, y) != null;
                case Version.VERSION_10:
                    return !AbstractC1248Uk.F.equals(Op1.n(obj, y));
                case Version.VERSION_11:
                    return Op1.l(obj, y) != 0;
                case Version.VERSION_12:
                    return Op1.l(obj, y) != 0;
                case Version.VERSION_13:
                    return Op1.l(obj, y) != 0;
                case Version.VERSION_14:
                    return Op1.m(obj, y) != 0;
                case Version.VERSION_15:
                    return Op1.l(obj, y) != 0;
                case Version.VERSION_16:
                    return Op1.m(obj, y) != 0;
                case Version.VERSION_17:
                    return Op1.n(obj, y) != null;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            return (Op1.l(obj, j2) & (1 << (i3 >>> 20))) != 0;
        }
    }

    public final boolean r(Object obj, int i2, int i3, int i4, int i5) {
        if (i3 == 1048575) {
            return q(obj, i2);
        }
        return (i4 & i5) != 0;
    }

    public final boolean s(Object obj, int i2, int i3) {
        return Op1.l(obj, (long) (this.c[i3 + 2] & 1048575)) == i2;
    }

    public final void u(Object obj, Object obj2, int i2) {
        long y = y(this.c[i2 + 1]);
        if (q(obj2, i2)) {
            Object n2 = Op1.n(obj, y);
            Object n3 = Op1.n(obj2, y);
            if (n2 != null && n3 != null) {
                Op1.e.q(obj, y, F30.c(n2, n3));
                K(obj, i2);
            } else if (n3 != null) {
                Op1.e.q(obj, y, n3);
                K(obj, i2);
            }
        }
    }

    public final void v(Object obj, Object obj2, int i2) {
        int[] iArr = this.c;
        int i3 = iArr[i2 + 1];
        int i4 = iArr[i2];
        long y = y(i3);
        if (s(obj2, i4, i2)) {
            Object n2 = Op1.n(obj, y);
            Object n3 = Op1.n(obj2, y);
            if (n2 != null && n3 != null) {
                Op1.e.q(obj, y, F30.c(n2, n3));
                L(obj, i4, i2);
            } else if (n3 != null) {
                Op1.e.q(obj, y, n3);
                L(obj, i4, i2);
            }
        }
    }
}
