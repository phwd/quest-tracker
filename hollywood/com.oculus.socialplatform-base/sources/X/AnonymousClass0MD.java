package X;

import android.annotation.SuppressLint;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.infer.annotation.NullsafeStrict;
import java.util.ArrayList;
import java.util.List;

@NullsafeStrict
@SuppressLint({"StringFormatUse", "BadMethodUse-android.util.Log.v", "BadMethodUse-android.util.Log.d", "BadMethodUse-android.util.Log.i", "BadMethodUse-android.util.Log.w", "BadMethodUse-android.util.Log.e"})
/* renamed from: X.0MD  reason: invalid class name */
public final class AnonymousClass0MD {
    public static final List<AnonymousClass0ME> A00 = new ArrayList();
    public static volatile AnonymousClass0J6 A01 = AnonymousClass0VP.A01;

    static {
        A01.AA2(5);
        AnonymousClass0J5.A00 = A01;
    }

    public static synchronized void A00(int i) {
        synchronized (AnonymousClass0MD.class) {
            A01.AA2(i);
            for (AnonymousClass0ME r0 : A00) {
                r0.A7H(i);
            }
        }
    }

    public static void A01(Class<?> cls, String str, Throwable th) {
        if (A01.A64(6)) {
            A01.A2i(cls.getSimpleName(), str, th);
        }
    }

    public static void A02(Class<?> cls, String str, Object... objArr) {
        if (A01.A64(5)) {
            A05(cls.getSimpleName(), StringFormatUtil.formatStrLocaleSafe(str, objArr));
        }
    }

    public static void A03(Class<?> cls, Throwable th, String str, Object... objArr) {
        if (A01.A64(6)) {
            A07(cls.getSimpleName(), StringFormatUtil.formatStrLocaleSafe(str, objArr), th);
        }
    }

    public static void A04(String str, String str2) {
        if (A01.A64(6)) {
            A01.A2h(str, str2);
        }
    }

    public static void A05(String str, String str2) {
        if (A01.A64(5)) {
            A01.AB0(str, str2);
        }
    }

    public static void A06(String str, String str2) {
        if (A01.A64(6)) {
            A01.ABM(str, str2);
        }
    }

    public static void A07(String str, String str2, Throwable th) {
        if (A01.A64(6)) {
            A01.A2i(str, str2, th);
        }
    }

    public static void A08(String str, String str2, Throwable th) {
        if (A01.A64(5)) {
            A01.AB1(str, str2, th);
        }
    }

    public static void A09(String str, String str2, Object... objArr) {
        if (A01.A64(6)) {
            A04(str, StringFormatUtil.formatStrLocaleSafe(str2, objArr));
        }
    }

    public static void A0A(String str, String str2, Object... objArr) {
        if (A01.A64(5)) {
            A05(str, StringFormatUtil.formatStrLocaleSafe(str2, objArr));
        }
    }

    public static void A0B(String str, String str2, Object... objArr) {
        if (A01.A64(6)) {
            A06(str, StringFormatUtil.formatStrLocaleSafe(str2, objArr));
        }
    }

    public static void A0C(String str, Throwable th, String str2) {
        if (A01.A64(6)) {
            A01.A2i(str, str2, th);
        }
    }

    public static void A0D(String str, Throwable th, String str2) {
        if (A01.A64(5)) {
            A01.AB1(str, str2, th);
        }
    }

    public static void A0E(String str, Throwable th, String str2, Object... objArr) {
        if (A01.A64(6)) {
            A07(str, StringFormatUtil.formatStrLocaleSafe(str2, objArr), th);
        }
    }

    public static void A0F(String str, Throwable th, String str2, Object... objArr) {
        if (A01.A64(5)) {
            A08(str, StringFormatUtil.formatStrLocaleSafe(str2, objArr), th);
        }
    }
}
