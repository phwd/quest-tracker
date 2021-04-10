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
/* renamed from: X.0NK  reason: invalid class name */
public final class AnonymousClass0NK {
    public static final List<BLogLevelCallback> A00 = new ArrayList();
    public static volatile AnonymousClass0Ke A01 = C01810Lv.A01;

    public static synchronized void A00() {
        synchronized (AnonymousClass0NK.class) {
            A01.A82(5);
            Iterator<BLogLevelCallback> it = A00.iterator();
            while (it.hasNext()) {
                it.next();
                FbGlog.setLogLevel(5);
            }
        }
    }

    static {
        A01.A82(5);
        AnonymousClass0Kd.A00 = A01;
    }

    public static void A01(String str, String str2) {
        if (A01.A5V(6)) {
            A01.A2C(str, str2);
        }
    }

    public static void A02(String str, String str2) {
        if (A01.A5V(5)) {
            A01.A8m(str, str2);
        }
    }

    public static void A03(String str, String str2) {
        if (A01.A5V(6)) {
            A01.A9G(str, str2);
        }
    }

    public static void A04(String str, String str2, Throwable th) {
        if (A01.A5V(6)) {
            A01.A2D(str, str2, th);
        }
    }

    public static void A05(String str, String str2, Throwable th) {
        if (A01.A5V(6)) {
            A01.A9H(str, str2, th);
        }
    }

    public static void A06(String str, String str2, Object... objArr) {
        if (A01.A5V(6)) {
            A01(str, StringFormatUtil.formatStrLocaleSafe(str2, objArr));
        }
    }

    public static void A07(String str, String str2, Object... objArr) {
        if (A01.A5V(5)) {
            A02(str, StringFormatUtil.formatStrLocaleSafe(str2, objArr));
        }
    }

    public static void A08(String str, String str2, Object... objArr) {
        if (A01.A5V(6)) {
            A03(str, StringFormatUtil.formatStrLocaleSafe(str2, objArr));
        }
    }

    public static void A09(String str, Throwable th, String str2) {
        if (A01.A5V(6)) {
            A01.A2D(str, str2, th);
        }
    }

    public static void A0A(String str, Throwable th, String str2) {
        if (A01.A5V(5)) {
            A01.A8n(str, str2, th);
        }
    }

    public static void A0B(String str, Throwable th, String str2) {
        if (A01.A5V(6)) {
            A01.A9H(str, str2, th);
        }
    }

    public static void A0C(String str, Throwable th, String str2, Object... objArr) {
        if (A01.A5V(6)) {
            A04(str, StringFormatUtil.formatStrLocaleSafe(str2, objArr), th);
        }
    }

    public static void A0D(String str, Throwable th, String str2, Object... objArr) {
        if (A01.A5V(5)) {
            String formatStrLocaleSafe = StringFormatUtil.formatStrLocaleSafe(str2, objArr);
            if (A01.A5V(5)) {
                A01.A8n(str, formatStrLocaleSafe, th);
            }
        }
    }
}
