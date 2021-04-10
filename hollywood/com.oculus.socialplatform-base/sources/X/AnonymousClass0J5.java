package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0J5  reason: invalid class name */
public final class AnonymousClass0J5 {
    public static AnonymousClass0J6 A00 = C05020ri.A01;

    public static void A03(Class<?> cls, Throwable th, String str, Object... objArr) {
        AnonymousClass0J6 r3 = A00;
        boolean A64 = r3.A64(5);
        if (A64) {
            String format = String.format(null, str, objArr);
            if (A64) {
                r3.AB1(cls.getSimpleName(), format, th);
            }
        }
    }

    public static void A00(Class<?> cls, String str) {
        AnonymousClass0J6 r1 = A00;
        if (r1.A64(6)) {
            r1.A2h(cls.getSimpleName(), str);
        }
    }

    public static void A01(Class<?> cls, String str, Object... objArr) {
        AnonymousClass0J6 r2 = A00;
        if (r2.A64(6)) {
            r2.A2h(cls.getSimpleName(), String.format(null, str, objArr));
        }
    }

    public static void A02(Class<?> cls, String str, Object... objArr) {
        AnonymousClass0J6 r2 = A00;
        if (r2.A64(5)) {
            r2.AB0(cls.getSimpleName(), String.format(null, str, objArr));
        }
    }

    public static void A04(String str, String str2, Throwable th) {
        AnonymousClass0J6 r1 = A00;
        if (r1.A64(6)) {
            r1.A2i(str, str2, th);
        }
    }

    public static void A05(String str, String str2, Object... objArr) {
        AnonymousClass0J6 r1 = A00;
        if (r1.A64(5)) {
            r1.AB0(str, String.format(null, str2, objArr));
        }
    }

    public static void A06(String str, String str2, Object... objArr) {
        AnonymousClass0J6 r1 = A00;
        if (r1.A64(6)) {
            r1.ABM(str, String.format(null, str2, objArr));
        }
    }
}
