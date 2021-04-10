package X;

import android.annotation.TargetApi;
import android.os.Trace;
import com.facebook.soloader.DoNotOptimize;
import com.squareup.okhttp.internal.framed.Hpack;
import javax.annotation.Nullable;

@DoNotOptimize
@TargetApi(18)
/* renamed from: X.0c0  reason: invalid class name and case insensitive filesystem */
public final class C03060c0 {
    public static void A01(String str, @Nullable String str2) {
        String A07 = AnonymousClass006.A07(str, str2, "]");
        if (A07.length() > 127 && str2 != null) {
            A07 = AnonymousClass006.A07(str, str2.substring(0, (Hpack.PREFIX_7_BITS - str.length()) - "]".length()), "]");
        }
        Trace.beginSection(A07);
    }

    public static void A00() {
        Trace.endSection();
    }
}
