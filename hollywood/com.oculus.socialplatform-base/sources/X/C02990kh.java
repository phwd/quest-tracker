package X;

import android.annotation.TargetApi;
import android.os.Trace;
import com.facebook.soloader.DoNotOptimize;
import javax.annotation.Nullable;

@DoNotOptimize
@TargetApi(18)
/* renamed from: X.0kh  reason: invalid class name and case insensitive filesystem */
public final class C02990kh {
    public static void A01(String str, @Nullable String str2) {
        String A09 = AnonymousClass006.A09(str, str2, "]");
        if (A09.length() > 127 && str2 != null) {
            A09 = AnonymousClass006.A09(str, str2.substring(0, (127 - str.length()) - "]".length()), "]");
        }
        Trace.beginSection(A09);
    }

    public static void A00() {
        Trace.endSection();
    }
}
