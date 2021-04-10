package defpackage;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* renamed from: WO0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class WO0 {

    /* renamed from: a  reason: collision with root package name */
    public static final Class f9145a;
    public static final Ap1 b = y(false);
    public static final Ap1 c = y(true);
    public static final Ap1 d = new Ap1();

    static {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessageV3");
        } catch (Throwable unused) {
            cls = null;
        }
        f9145a = cls;
    }

    public static boolean A(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static Object B(int i, int i2, Object obj, Ap1 ap1) {
        if (obj == null) {
            Objects.requireNonNull(ap1);
            obj = C5998zp1.b();
        }
        Objects.requireNonNull(ap1);
        ((C5998zp1) obj).c((i << 3) | 0, Long.valueOf((long) i2));
        return obj;
    }

    public static void C(int i, List list, C0112Bv bv, boolean z) {
        if (!(list == null || list.isEmpty())) {
            Objects.requireNonNull(bv);
            if (z) {
                bv.f7771a.M((i << 3) | 2);
                int i2 = 0;
                for (int i3 = 0; i3 < list.size(); i3++) {
                    ((Boolean) list.get(i3)).booleanValue();
                    boolean z2 = C6014zv.f11779a;
                    i2++;
                }
                bv.f7771a.M(i2);
                for (int i4 = 0; i4 < list.size(); i4++) {
                    bv.f7771a.D(((Boolean) list.get(i4)).booleanValue() ? (byte) 1 : 0);
                }
                return;
            }
            for (int i5 = 0; i5 < list.size(); i5++) {
                C6014zv zvVar = bv.f7771a;
                boolean booleanValue = ((Boolean) list.get(i5)).booleanValue();
                zvVar.M((i << 3) | 0);
                zvVar.D(booleanValue ? (byte) 1 : 0);
            }
        }
    }

    public static void D(int i, List list, C0112Bv bv) {
        if (!(list == null || list.isEmpty())) {
            Objects.requireNonNull(bv);
            for (int i2 = 0; i2 < list.size(); i2++) {
                C6014zv zvVar = bv.f7771a;
                zvVar.M((i << 3) | 2);
                zvVar.F((AbstractC1248Uk) list.get(i2));
            }
        }
    }

    public static void E(int i, List list, C0112Bv bv, boolean z) {
        if (!(list == null || list.isEmpty())) {
            Objects.requireNonNull(bv);
            int i2 = 0;
            if (z) {
                bv.f7771a.M((i << 3) | 2);
                int i3 = 0;
                for (int i4 = 0; i4 < list.size(); i4++) {
                    ((Double) list.get(i4)).doubleValue();
                    boolean z2 = C6014zv.f11779a;
                    i3 += 8;
                }
                bv.f7771a.M(i3);
                while (i2 < list.size()) {
                    C6014zv zvVar = bv.f7771a;
                    double doubleValue = ((Double) list.get(i2)).doubleValue();
                    Objects.requireNonNull(zvVar);
                    zvVar.I(Double.doubleToRawLongBits(doubleValue));
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                C6014zv zvVar2 = bv.f7771a;
                double doubleValue2 = ((Double) list.get(i2)).doubleValue();
                Objects.requireNonNull(zvVar2);
                long doubleToRawLongBits = Double.doubleToRawLongBits(doubleValue2);
                zvVar2.M((i << 3) | 1);
                zvVar2.I(doubleToRawLongBits);
                i2++;
            }
        }
    }

    public static void F(int i, List list, C0112Bv bv, boolean z) {
        if (!(list == null || list.isEmpty())) {
            Objects.requireNonNull(bv);
            int i2 = 0;
            if (z) {
                bv.f7771a.M((i << 3) | 2);
                int i3 = 0;
                for (int i4 = 0; i4 < list.size(); i4++) {
                    i3 += C6014zv.k(((Integer) list.get(i4)).intValue());
                }
                bv.f7771a.M(i3);
                while (i2 < list.size()) {
                    bv.f7771a.G(((Integer) list.get(i2)).intValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                bv.f7771a.J(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
    }

    public static void G(int i, List list, C0112Bv bv, boolean z) {
        if (!(list == null || list.isEmpty())) {
            Objects.requireNonNull(bv);
            int i2 = 0;
            if (z) {
                bv.f7771a.M((i << 3) | 2);
                int i3 = 0;
                for (int i4 = 0; i4 < list.size(); i4++) {
                    ((Integer) list.get(i4)).intValue();
                    boolean z2 = C6014zv.f11779a;
                    i3 += 4;
                }
                bv.f7771a.M(i3);
                while (i2 < list.size()) {
                    bv.f7771a.H(((Integer) list.get(i2)).intValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                C6014zv zvVar = bv.f7771a;
                int intValue = ((Integer) list.get(i2)).intValue();
                zvVar.M((i << 3) | 5);
                zvVar.H(intValue);
                i2++;
            }
        }
    }

    public static void H(int i, List list, C0112Bv bv, boolean z) {
        if (!(list == null || list.isEmpty())) {
            Objects.requireNonNull(bv);
            int i2 = 0;
            if (z) {
                bv.f7771a.M((i << 3) | 2);
                int i3 = 0;
                for (int i4 = 0; i4 < list.size(); i4++) {
                    ((Long) list.get(i4)).longValue();
                    boolean z2 = C6014zv.f11779a;
                    i3 += 8;
                }
                bv.f7771a.M(i3);
                while (i2 < list.size()) {
                    bv.f7771a.I(((Long) list.get(i2)).longValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                C6014zv zvVar = bv.f7771a;
                long longValue = ((Long) list.get(i2)).longValue();
                zvVar.M((i << 3) | 1);
                zvVar.I(longValue);
                i2++;
            }
        }
    }

    public static void I(int i, List list, C0112Bv bv, boolean z) {
        if (!(list == null || list.isEmpty())) {
            Objects.requireNonNull(bv);
            int i2 = 0;
            if (z) {
                bv.f7771a.M((i << 3) | 2);
                int i3 = 0;
                for (int i4 = 0; i4 < list.size(); i4++) {
                    ((Float) list.get(i4)).floatValue();
                    boolean z2 = C6014zv.f11779a;
                    i3 += 4;
                }
                bv.f7771a.M(i3);
                while (i2 < list.size()) {
                    C6014zv zvVar = bv.f7771a;
                    float floatValue = ((Float) list.get(i2)).floatValue();
                    Objects.requireNonNull(zvVar);
                    zvVar.H(Float.floatToRawIntBits(floatValue));
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                C6014zv zvVar2 = bv.f7771a;
                float floatValue2 = ((Float) list.get(i2)).floatValue();
                Objects.requireNonNull(zvVar2);
                int floatToRawIntBits = Float.floatToRawIntBits(floatValue2);
                zvVar2.M((i << 3) | 5);
                zvVar2.H(floatToRawIntBits);
                i2++;
            }
        }
    }

    public static void J(int i, List list, C0112Bv bv, UO0 uo0) {
        if (!(list == null || list.isEmpty())) {
            Objects.requireNonNull(bv);
            for (int i2 = 0; i2 < list.size(); i2++) {
                bv.g(i, list.get(i2), uo0);
            }
        }
    }

    public static void K(int i, List list, C0112Bv bv, boolean z) {
        if (!(list == null || list.isEmpty())) {
            Objects.requireNonNull(bv);
            int i2 = 0;
            if (z) {
                bv.f7771a.M((i << 3) | 2);
                int i3 = 0;
                for (int i4 = 0; i4 < list.size(); i4++) {
                    i3 += C6014zv.k(((Integer) list.get(i4)).intValue());
                }
                bv.f7771a.M(i3);
                while (i2 < list.size()) {
                    bv.f7771a.K(((Integer) list.get(i2)).intValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                bv.f7771a.J(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
    }

    public static void L(int i, List list, C0112Bv bv, boolean z) {
        if (!(list == null || list.isEmpty())) {
            Objects.requireNonNull(bv);
            if (z) {
                bv.f7771a.M((i << 3) | 2);
                int i2 = 0;
                for (int i3 = 0; i3 < list.size(); i3++) {
                    i2 += C6014zv.z(((Long) list.get(i3)).longValue());
                }
                bv.f7771a.M(i2);
                for (int i4 = 0; i4 < list.size(); i4++) {
                    bv.f7771a.N(((Long) list.get(i4)).longValue());
                }
                return;
            }
            for (int i5 = 0; i5 < list.size(); i5++) {
                C6014zv zvVar = bv.f7771a;
                long longValue = ((Long) list.get(i5)).longValue();
                zvVar.M((i << 3) | 0);
                zvVar.N(longValue);
            }
        }
    }

    public static void M(int i, List list, C0112Bv bv, UO0 uo0) {
        if (!(list == null || list.isEmpty())) {
            Objects.requireNonNull(bv);
            for (int i2 = 0; i2 < list.size(); i2++) {
                bv.i(i, list.get(i2), uo0);
            }
        }
    }

    public static void N(int i, List list, C0112Bv bv, boolean z) {
        if (!(list == null || list.isEmpty())) {
            Objects.requireNonNull(bv);
            int i2 = 0;
            if (z) {
                bv.f7771a.M((i << 3) | 2);
                int i3 = 0;
                for (int i4 = 0; i4 < list.size(); i4++) {
                    ((Integer) list.get(i4)).intValue();
                    boolean z2 = C6014zv.f11779a;
                    i3 += 4;
                }
                bv.f7771a.M(i3);
                while (i2 < list.size()) {
                    bv.f7771a.H(((Integer) list.get(i2)).intValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                C6014zv zvVar = bv.f7771a;
                int intValue = ((Integer) list.get(i2)).intValue();
                zvVar.M((i << 3) | 5);
                zvVar.H(intValue);
                i2++;
            }
        }
    }

    public static void O(int i, List list, C0112Bv bv, boolean z) {
        if (!(list == null || list.isEmpty())) {
            Objects.requireNonNull(bv);
            int i2 = 0;
            if (z) {
                bv.f7771a.M((i << 3) | 2);
                int i3 = 0;
                for (int i4 = 0; i4 < list.size(); i4++) {
                    ((Long) list.get(i4)).longValue();
                    boolean z2 = C6014zv.f11779a;
                    i3 += 8;
                }
                bv.f7771a.M(i3);
                while (i2 < list.size()) {
                    bv.f7771a.I(((Long) list.get(i2)).longValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                C6014zv zvVar = bv.f7771a;
                long longValue = ((Long) list.get(i2)).longValue();
                zvVar.M((i << 3) | 1);
                zvVar.I(longValue);
                i2++;
            }
        }
    }

    public static void P(int i, List list, C0112Bv bv, boolean z) {
        if (!(list == null || list.isEmpty())) {
            Objects.requireNonNull(bv);
            if (z) {
                bv.f7771a.M((i << 3) | 2);
                int i2 = 0;
                for (int i3 = 0; i3 < list.size(); i3++) {
                    i2 += C6014zv.q(((Integer) list.get(i3)).intValue());
                }
                bv.f7771a.M(i2);
                for (int i4 = 0; i4 < list.size(); i4++) {
                    bv.f7771a.M(C6014zv.A(((Integer) list.get(i4)).intValue()));
                }
                return;
            }
            for (int i5 = 0; i5 < list.size(); i5++) {
                C6014zv zvVar = bv.f7771a;
                int A = C6014zv.A(((Integer) list.get(i5)).intValue());
                zvVar.M((i << 3) | 0);
                zvVar.M(A);
            }
        }
    }

    public static void Q(int i, List list, C0112Bv bv, boolean z) {
        if (!(list == null || list.isEmpty())) {
            Objects.requireNonNull(bv);
            if (z) {
                bv.f7771a.M((i << 3) | 2);
                int i2 = 0;
                for (int i3 = 0; i3 < list.size(); i3++) {
                    i2 += C6014zv.s(((Long) list.get(i3)).longValue());
                }
                bv.f7771a.M(i2);
                for (int i4 = 0; i4 < list.size(); i4++) {
                    bv.f7771a.N(C6014zv.B(((Long) list.get(i4)).longValue()));
                }
                return;
            }
            for (int i5 = 0; i5 < list.size(); i5++) {
                C6014zv zvVar = bv.f7771a;
                long B = C6014zv.B(((Long) list.get(i5)).longValue());
                zvVar.M((i << 3) | 0);
                zvVar.N(B);
            }
        }
    }

    public static void R(int i, List list, C0112Bv bv) {
        if (list != null && !list.isEmpty()) {
            Objects.requireNonNull(bv);
            int i2 = 0;
            if (list instanceof R70) {
                R70 r70 = (R70) list;
                while (i2 < list.size()) {
                    Object i3 = r70.i(i2);
                    if (i3 instanceof String) {
                        C6014zv zvVar = bv.f7771a;
                        zvVar.M((i << 3) | 2);
                        zvVar.L((String) i3);
                    } else {
                        C6014zv zvVar2 = bv.f7771a;
                        zvVar2.M((i << 3) | 2);
                        zvVar2.F((AbstractC1248Uk) i3);
                    }
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                C6014zv zvVar3 = bv.f7771a;
                zvVar3.M((i << 3) | 2);
                zvVar3.L((String) list.get(i2));
                i2++;
            }
        }
    }

    public static void S(int i, List list, C0112Bv bv, boolean z) {
        if (!(list == null || list.isEmpty())) {
            Objects.requireNonNull(bv);
            if (z) {
                bv.f7771a.M((i << 3) | 2);
                int i2 = 0;
                for (int i3 = 0; i3 < list.size(); i3++) {
                    i2 += C6014zv.x(((Integer) list.get(i3)).intValue());
                }
                bv.f7771a.M(i2);
                for (int i4 = 0; i4 < list.size(); i4++) {
                    bv.f7771a.M(((Integer) list.get(i4)).intValue());
                }
                return;
            }
            for (int i5 = 0; i5 < list.size(); i5++) {
                C6014zv zvVar = bv.f7771a;
                int intValue = ((Integer) list.get(i5)).intValue();
                zvVar.M((i << 3) | 0);
                zvVar.M(intValue);
            }
        }
    }

    public static void T(int i, List list, C0112Bv bv, boolean z) {
        if (!(list == null || list.isEmpty())) {
            Objects.requireNonNull(bv);
            if (z) {
                bv.f7771a.M((i << 3) | 2);
                int i2 = 0;
                for (int i3 = 0; i3 < list.size(); i3++) {
                    i2 += C6014zv.z(((Long) list.get(i3)).longValue());
                }
                bv.f7771a.M(i2);
                for (int i4 = 0; i4 < list.size(); i4++) {
                    bv.f7771a.N(((Long) list.get(i4)).longValue());
                }
                return;
            }
            for (int i5 = 0; i5 < list.size(); i5++) {
                C6014zv zvVar = bv.f7771a;
                long longValue = ((Long) list.get(i5)).longValue();
                zvVar.M((i << 3) | 0);
                zvVar.N(longValue);
            }
        }
    }

    public static int a(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (!z) {
            return C6014zv.a(i, true) * size;
        }
        return C6014zv.m(size) + C6014zv.v(i);
    }

    public static int b(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int v = C6014zv.v(i) * size;
        for (int i2 = 0; i2 < list.size(); i2++) {
            v += C6014zv.c((AbstractC1248Uk) list.get(i2));
        }
        return v;
    }

    public static int c(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int d2 = d(list);
        if (!z) {
            return (C6014zv.v(i) * size) + d2;
        }
        return C6014zv.m(d2) + C6014zv.v(i);
    }

    public static int d(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof O20) {
            O20 o20 = (O20) list;
            i = 0;
            while (i2 < size) {
                i += C6014zv.k(o20.c(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += C6014zv.k(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    public static int e(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (!z) {
            return C6014zv.f(i, 0) * size;
        }
        return C6014zv.m(size * 4) + C6014zv.v(i);
    }

    public static int f(List list) {
        return list.size() * 4;
    }

    public static int g(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (!z) {
            return C6014zv.g(i, 0) * size;
        }
        return C6014zv.m(size * 8) + C6014zv.v(i);
    }

    public static int h(List list) {
        return list.size() * 8;
    }

    public static int i(int i, List list, UO0 uo0) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += C6014zv.i(i, (AbstractC1125Sj0) list.get(i3), uo0);
        }
        return i2;
    }

    public static int j(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int k = k(list);
        if (!z) {
            return (C6014zv.v(i) * size) + k;
        }
        return C6014zv.m(k) + C6014zv.v(i);
    }

    public static int k(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof O20) {
            O20 o20 = (O20) list;
            i = 0;
            while (i2 < size) {
                i += C6014zv.k(o20.c(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += C6014zv.k(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    public static int l(int i, List list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        int m = m(list);
        if (z) {
            return C6014zv.m(m) + C6014zv.v(i);
        }
        return (C6014zv.v(i) * list.size()) + m;
    }

    public static int m(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof AbstractC2716gb0) {
            AbstractC2716gb0 gb0 = (AbstractC2716gb0) list;
            i = 0;
            while (i2 < size) {
                i += C6014zv.z(gb0.c(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += C6014zv.z(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    public static int n(int i, Object obj, UO0 uo0) {
        return C6014zv.m(((AbstractC2790h) ((AbstractC1125Sj0) obj)).b(uo0)) + C6014zv.v(i);
    }

    public static int o(int i, List list, UO0 uo0) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int v = C6014zv.v(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            v += C6014zv.m(((AbstractC2790h) ((AbstractC1125Sj0) list.get(i2))).b(uo0));
        }
        return v;
    }

    public static int p(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int q = q(list);
        if (!z) {
            return (C6014zv.v(i) * size) + q;
        }
        return C6014zv.m(q) + C6014zv.v(i);
    }

    public static int q(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof O20) {
            O20 o20 = (O20) list;
            i = 0;
            while (i2 < size) {
                i += C6014zv.q(o20.c(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += C6014zv.q(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    public static int r(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int s = s(list);
        if (!z) {
            return (C6014zv.v(i) * size) + s;
        }
        return C6014zv.m(s) + C6014zv.v(i);
    }

    public static int s(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof AbstractC2716gb0) {
            AbstractC2716gb0 gb0 = (AbstractC2716gb0) list;
            i = 0;
            while (i2 < size) {
                i += C6014zv.s(gb0.c(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += C6014zv.s(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    public static int t(int i, List list) {
        int i2;
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        int v = C6014zv.v(i) * size;
        if (list instanceof R70) {
            R70 r70 = (R70) list;
            while (i4 < size) {
                Object i5 = r70.i(i4);
                if (i5 instanceof AbstractC1248Uk) {
                    i3 = C6014zv.c((AbstractC1248Uk) i5);
                } else {
                    i3 = C6014zv.u((String) i5);
                }
                v = i3 + v;
                i4++;
            }
        } else {
            while (i4 < size) {
                Object obj = list.get(i4);
                if (obj instanceof AbstractC1248Uk) {
                    i2 = C6014zv.c((AbstractC1248Uk) obj);
                } else {
                    i2 = C6014zv.u((String) obj);
                }
                v = i2 + v;
                i4++;
            }
        }
        return v;
    }

    public static int u(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int v = v(list);
        if (!z) {
            return (C6014zv.v(i) * size) + v;
        }
        return C6014zv.m(v) + C6014zv.v(i);
    }

    public static int v(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof O20) {
            O20 o20 = (O20) list;
            i = 0;
            while (i2 < size) {
                i += C6014zv.x(o20.c(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += C6014zv.x(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    public static int w(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int x = x(list);
        if (!z) {
            return (C6014zv.v(i) * size) + x;
        }
        return C6014zv.m(x) + C6014zv.v(i);
    }

    public static int x(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof AbstractC2716gb0) {
            AbstractC2716gb0 gb0 = (AbstractC2716gb0) list;
            i = 0;
            while (i2 < size) {
                i += C6014zv.z(gb0.c(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += C6014zv.z(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    public static Ap1 y(boolean z) {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            cls = null;
        }
        if (cls == null) {
            return null;
        }
        try {
            return (Ap1) cls.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable unused2) {
            return null;
        }
    }

    public static void z(Ap1 ap1, Object obj, Object obj2) {
        Objects.requireNonNull(ap1);
        AbstractC2360eV eVVar = (AbstractC2360eV) obj;
        C5998zp1 zp1 = eVVar.c;
        C5998zp1 zp12 = ((AbstractC2360eV) obj2).c;
        if (!zp12.equals(C5998zp1.f11772a)) {
            int i = zp1.b + zp12.b;
            int[] copyOf = Arrays.copyOf(zp1.c, i);
            System.arraycopy(zp12.c, 0, copyOf, zp1.b, zp12.b);
            Object[] copyOf2 = Arrays.copyOf(zp1.d, i);
            System.arraycopy(zp12.d, 0, copyOf2, zp1.b, zp12.b);
            zp1 = new C5998zp1(i, copyOf, copyOf2, true);
        }
        eVVar.c = zp1;
    }
}
