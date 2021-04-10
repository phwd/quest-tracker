package X;

import android.annotation.SuppressLint;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.debug.log.BLogLevelCallback;
import com.facebook.infer.annotation.NullsafeStrict;
import com.facebook.xplat.fbglog.FbGlog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@NullsafeStrict
@SuppressLint({"StringFormatUse", "BadMethodUse-android.util.Log.v", "BadMethodUse-android.util.Log.d", "BadMethodUse-android.util.Log.i", "BadMethodUse-android.util.Log.w", "BadMethodUse-android.util.Log.e"})
/* renamed from: X.0NO  reason: invalid class name */
public final class AnonymousClass0NO {
    public static final List<BLogLevelCallback> A00 = new ArrayList();
    public static volatile AbstractC01090Kc A01 = AnonymousClass0JI.A01;

    static {
        A01.A8g(5);
        C01080Kb.A00 = A01;
    }

    public static synchronized void A00(int i) {
        synchronized (AnonymousClass0NO.class) {
            A01.A8g(i);
            Iterator<BLogLevelCallback> it = A00.iterator();
            while (it.hasNext()) {
                it.next();
                FbGlog.setLogLevel(i);
            }
        }
    }

    public static void A01(Class<?> cls, String str) {
        if (A01.A54(6)) {
            A01.A2J(cls.getSimpleName(), str);
        }
    }

    public static void A02(Class<?> cls, String str) {
        if (A01.A54(5)) {
            A01.A9x(cls.getSimpleName(), str);
        }
    }

    public static void A03(Class<?> cls, String str, Throwable th) {
        if (A01.A54(6)) {
            A01.A2K(cls.getSimpleName(), str, th);
        }
    }

    public static void A04(Class<?> cls, String str, Throwable th) {
        if (A01.A54(5)) {
            A01.A9y(cls.getSimpleName(), str, th);
        }
    }

    public static void A05(Class<?> cls, String str, Object... objArr) {
        if (A01.A54(6)) {
            A08(cls.getSimpleName(), StringFormatUtil.formatStrLocaleSafe(str, objArr));
        }
    }

    public static void A06(Class<?> cls, String str, Object... objArr) {
        if (A01.A54(5)) {
            A09(cls.getSimpleName(), StringFormatUtil.formatStrLocaleSafe(str, objArr));
        }
    }

    public static void A07(Class<?> cls, Throwable th, String str, Object... objArr) {
        if (A01.A54(6)) {
            A0B(cls.getSimpleName(), StringFormatUtil.formatStrLocaleSafe(str, objArr), th);
        }
    }

    public static void A08(String str, String str2) {
        if (A01.A54(6)) {
            A01.A2J(str, str2);
        }
    }

    public static void A09(String str, String str2) {
        if (A01.A54(5)) {
            A01.A9x(str, str2);
        }
    }

    public static void A0A(String str, String str2) {
        if (A01.A54(6)) {
            A01.AAK(str, str2);
        }
    }

    public static void A0B(String str, String str2, Throwable th) {
        if (A01.A54(6)) {
            A01.A2K(str, str2, th);
        }
    }

    public static void A0C(String str, String str2, Throwable th) {
        if (A01.A54(5)) {
            A01.A9y(str, str2, th);
        }
    }

    public static void A0D(String str, String str2, Throwable th) {
        if (A01.A54(6)) {
            A01.AAL(str, str2, th);
        }
    }

    public static void A0E(String str, String str2, Object... objArr) {
        if (A01.A54(6)) {
            A08(str, StringFormatUtil.formatStrLocaleSafe(str2, objArr));
        }
    }

    public static void A0F(String str, String str2, Object... objArr) {
        if (A01.A54(5)) {
            A09(str, StringFormatUtil.formatStrLocaleSafe(str2, objArr));
        }
    }

    public static void A0G(String str, String str2, Object... objArr) {
        if (A01.A54(6)) {
            A0A(str, StringFormatUtil.formatStrLocaleSafe(str2, objArr));
        }
    }

    public static void A0H(String str, Throwable th, String str2) {
        if (A01.A54(6)) {
            A01.A2K(str, str2, th);
        }
    }

    public static void A0I(String str, Throwable th, String str2) {
        if (A01.A54(5)) {
            A01.A9y(str, str2, th);
        }
    }

    public static void A0J(String str, Throwable th, String str2) {
        if (A01.A54(6)) {
            A01.AAL(str, str2, th);
        }
    }

    public static void A0K(String str, Throwable th, String str2, Object... objArr) {
        if (A01.A54(6)) {
            A0B(str, StringFormatUtil.formatStrLocaleSafe(str2, objArr), th);
        }
    }

    public static void A0L(String str, Throwable th, String str2, Object... objArr) {
        if (A01.A54(5)) {
            A0C(str, StringFormatUtil.formatStrLocaleSafe(str2, objArr), th);
        }
    }
}
