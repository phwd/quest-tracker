package X;

import android.annotation.SuppressLint;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.debug.log.BLogLevelCallback;
import com.facebook.infer.annotation.NullsafeStrict;
import java.util.ArrayList;
import java.util.List;

@NullsafeStrict
@SuppressLint({"StringFormatUse", "BadMethodUse-android.util.Log.v", "BadMethodUse-android.util.Log.d", "BadMethodUse-android.util.Log.i", "BadMethodUse-android.util.Log.w", "BadMethodUse-android.util.Log.e"})
public final class Mu {
    public static final List<BLogLevelCallback> A00 = new ArrayList();
    public static volatile K7 A01 = I5.A01;

    static {
        A01.A52(5);
        K6.A00 = A01;
    }

    public static void A00(String str, String str2) {
        if (A01.A3F(6)) {
            A01.A1q(str, str2);
        }
    }

    public static void A01(String str, String str2) {
        if (A01.A3F(5)) {
            A01.A5d(str, str2);
        }
    }

    public static void A02(String str, String str2, Throwable th) {
        if (A01.A3F(6)) {
            A01.A1r(str, str2, th);
        }
    }

    public static void A03(String str, String str2, Throwable th) {
        if (A01.A3F(5)) {
            A01.A5e(str, str2, th);
        }
    }

    public static void A04(String str, String str2, Throwable th) {
        if (A01.A3F(6)) {
            A01.A60(str, str2, th);
        }
    }

    public static void A05(String str, String str2, Object... objArr) {
        if (A01.A3F(6)) {
            A00(str, StringFormatUtil.formatStrLocaleSafe(str2, objArr));
        }
    }

    public static void A06(String str, String str2, Object... objArr) {
        if (A01.A3F(5)) {
            A01(str, StringFormatUtil.formatStrLocaleSafe(str2, objArr));
        }
    }

    public static void A07(String str, String str2, Object... objArr) {
        if (A01.A3F(6)) {
            String formatStrLocaleSafe = StringFormatUtil.formatStrLocaleSafe(str2, objArr);
            if (A01.A3F(6)) {
                A01.A5z(str, formatStrLocaleSafe);
            }
        }
    }

    public static void A08(String str, Throwable th, String str2) {
        if (A01.A3F(6)) {
            A01.A1r(str, str2, th);
        }
    }

    public static void A09(String str, Throwable th, String str2) {
        if (A01.A3F(5)) {
            A01.A5e(str, str2, th);
        }
    }

    public static void A0A(String str, Throwable th, String str2) {
        if (A01.A3F(6)) {
            A01.A60(str, str2, th);
        }
    }

    public static void A0B(String str, Throwable th, String str2, Object... objArr) {
        if (A01.A3F(6)) {
            A02(str, StringFormatUtil.formatStrLocaleSafe(str2, objArr), th);
        }
    }

    public static void A0C(String str, Throwable th, String str2, Object... objArr) {
        if (A01.A3F(5)) {
            A03(str, StringFormatUtil.formatStrLocaleSafe(str2, objArr), th);
        }
    }
}
