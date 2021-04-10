package X;

import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.xplat.fbglog.FbGlog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: X.Dd  reason: case insensitive filesystem */
public final class C0139Dd {
    public static final List A00 = new ArrayList();
    public static volatile CM A01 = C0566c3.A01;

    static {
        A01.A51(5);
        CL.A00 = A01;
    }

    public static synchronized void A00(int i) {
        synchronized (C0139Dd.class) {
            A01.A51(i);
            Iterator it = A00.iterator();
            while (it.hasNext()) {
                it.next();
                FbGlog.setLogLevel(i);
            }
        }
    }

    public static void A01(Class cls, String str) {
        if (A01.A3Y(3)) {
            A01.A1b(cls.getSimpleName(), str);
        }
    }

    public static void A02(Class cls, String str) {
        if (A01.A3Y(6)) {
            A01.A1j(cls.getSimpleName(), str);
        }
    }

    public static void A03(Class cls, String str) {
        if (A01.A3Y(4)) {
            A01.A3C(cls.getSimpleName(), str);
        }
    }

    public static void A04(Class cls, String str) {
        if (A01.A3Y(5)) {
            A01.A5Y(cls.getSimpleName(), str);
        }
    }

    public static void A05(Class cls, String str, Object obj) {
        if (A01.A3Y(3)) {
            A01(cls, StringFormatUtil.formatStrLocaleSafe(str, obj));
        }
    }

    public static void A06(Class cls, String str, Throwable th) {
        if (A01.A3Y(6)) {
            A01.A1k(cls.getSimpleName(), str, th);
        }
    }

    public static void A07(Class cls, Throwable th, String str, Object... objArr) {
        if (A01.A3Y(6)) {
            A0L(cls.getSimpleName(), StringFormatUtil.formatStrLocaleSafe(str, objArr), th);
        }
    }

    public static void A08(String str, Object obj) {
        if (A01.A3Y(2)) {
            String formatStrLocaleSafe = StringFormatUtil.formatStrLocaleSafe(str, obj);
            if (A01.A3Y(2)) {
                A01.A5L(C0892oE.class.getSimpleName(), formatStrLocaleSafe);
            }
        }
    }

    public static void A09(String str, String str2) {
        if (A01.A3Y(3)) {
            A01.A1b(str, str2);
        }
    }

    public static void A0A(String str, String str2) {
        if (A01.A3Y(6)) {
            A01.A1j(str, str2);
        }
    }

    public static void A0B(String str, String str2) {
        if (A01.A3Y(4)) {
            A01.A3C(str, str2);
        }
    }

    public static void A0C(String str, String str2) {
        if (A01.A3Y(2)) {
            A01.A5L(str, str2);
        }
    }

    public static void A0D(String str, String str2) {
        if (A01.A3Y(5)) {
            A01.A5Y(str, str2);
        }
    }

    public static void A0E(String str, String str2) {
        if (A01.A3Y(6)) {
            A01.A60(str, str2);
        }
    }

    public static void A0F(String str, String str2, Object obj) {
        if (A01.A3Y(3)) {
            A09(str, StringFormatUtil.formatStrLocaleSafe(str2, obj));
        }
    }

    public static void A0G(String str, String str2, Object obj) {
        if (A01.A3Y(4)) {
            A0B(str, StringFormatUtil.formatStrLocaleSafe(str2, obj));
        }
    }

    public static void A0H(String str, String str2, Object obj, Object obj2) {
        if (A01.A3Y(3)) {
            A09(str, StringFormatUtil.formatStrLocaleSafe(str2, obj, obj2));
        }
    }

    public static void A0I(String str, String str2, Object obj, Object obj2) {
        if (A01.A3Y(4)) {
            A0B(str, StringFormatUtil.formatStrLocaleSafe(str2, obj, obj2));
        }
    }

    public static void A0J(String str, String str2, Object obj, Object obj2, Object obj3) {
        if (A01.A3Y(3)) {
            A09(str, StringFormatUtil.formatStrLocaleSafe(str2, obj, obj2, obj3));
        }
    }

    public static void A0K(String str, String str2, Object obj, Object obj2, Object obj3, Object obj4) {
        if (A01.A3Y(3)) {
            A09(str, StringFormatUtil.formatStrLocaleSafe(str2, obj, obj2, obj3, obj4));
        }
    }

    public static void A0L(String str, String str2, Throwable th) {
        if (A01.A3Y(6)) {
            A01.A1k(str, str2, th);
        }
    }

    public static void A0M(String str, String str2, Throwable th) {
        if (A01.A3Y(5)) {
            A01.A5Z(str, str2, th);
        }
    }

    public static void A0N(String str, String str2, Throwable th) {
        if (A01.A3Y(6)) {
            A01.A61(str, str2, th);
        }
    }

    public static void A0O(String str, String str2, Object... objArr) {
        if (A01.A3Y(6)) {
            A0A(str, StringFormatUtil.formatStrLocaleSafe(str2, objArr));
        }
    }

    public static void A0P(String str, String str2, Object... objArr) {
        if (A01.A3Y(5)) {
            A0D(str, StringFormatUtil.formatStrLocaleSafe(str2, objArr));
        }
    }

    public static void A0Q(String str, String str2, Object... objArr) {
        if (A01.A3Y(6)) {
            A0E(str, StringFormatUtil.formatStrLocaleSafe(str2, objArr));
        }
    }

    public static void A0R(String str, Throwable th) {
        if (A01.A3Y(5)) {
            A01.A5Z(C0213Kd.class.getSimpleName(), str, th);
        }
    }

    public static void A0S(String str, Throwable th, String str2) {
        if (A01.A3Y(6)) {
            A01.A1k(str, str2, th);
        }
    }

    public static void A0T(String str, Throwable th, String str2) {
        if (A01.A3Y(5)) {
            A01.A5Z(str, str2, th);
        }
    }

    public static void A0U(String str, Throwable th, String str2) {
        if (A01.A3Y(6)) {
            A01.A61(str, str2, th);
        }
    }

    public static void A0V(String str, Throwable th, String str2, Object... objArr) {
        if (A01.A3Y(6)) {
            A0L(str, StringFormatUtil.formatStrLocaleSafe(str2, objArr), th);
        }
    }

    public static void A0W(String str, Throwable th, String str2, Object... objArr) {
        if (A01.A3Y(5)) {
            A0M(str, StringFormatUtil.formatStrLocaleSafe(str2, objArr), th);
        }
    }
}
