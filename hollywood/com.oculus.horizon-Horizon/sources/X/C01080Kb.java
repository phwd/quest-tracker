package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Kb  reason: invalid class name and case insensitive filesystem */
public final class C01080Kb {
    public static AbstractC01090Kc A00 = C06750pl.A01;

    public static void A03(Class<?> cls, Throwable th, String str, Object... objArr) {
        AbstractC01090Kc r3 = A00;
        boolean A54 = r3.A54(5);
        if (A54) {
            String format = String.format(null, str, objArr);
            if (A54) {
                r3.A9y(cls.getSimpleName(), format, th);
            }
        }
    }

    public static void A00(Class<?> cls, String str) {
        AbstractC01090Kc r1 = A00;
        if (r1.A54(6)) {
            r1.A2J(cls.getSimpleName(), str);
        }
    }

    public static void A01(Class<?> cls, String str, Object... objArr) {
        AbstractC01090Kc r2 = A00;
        if (r2.A54(6)) {
            r2.A2J(cls.getSimpleName(), String.format(null, str, objArr));
        }
    }

    public static void A02(Class<?> cls, String str, Object... objArr) {
        AbstractC01090Kc r2 = A00;
        if (r2.A54(5)) {
            r2.A9x(cls.getSimpleName(), String.format(null, str, objArr));
        }
    }

    public static void A04(String str, String str2, Throwable th) {
        AbstractC01090Kc r1 = A00;
        if (r1.A54(6)) {
            r1.A2K(str, str2, th);
        }
    }

    public static void A05(String str, String str2, Object... objArr) {
        AbstractC01090Kc r1 = A00;
        if (r1.A54(5)) {
            r1.A9x(str, String.format(null, str2, objArr));
        }
    }

    public static void A06(String str, String str2, Object... objArr) {
        AbstractC01090Kc r1 = A00;
        if (r1.A54(6)) {
            r1.AAK(str, String.format(null, str2, objArr));
        }
    }
}
