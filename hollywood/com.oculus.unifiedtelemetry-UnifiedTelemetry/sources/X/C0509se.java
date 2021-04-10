package X;

import android.annotation.TargetApi;
import android.os.Trace;
import com.facebook.soloader.DoNotOptimize;
import com.squareup.okhttp.internal.framed.Hpack;
import javax.annotation.Nullable;

@DoNotOptimize
@TargetApi(18)
/* renamed from: X.se  reason: case insensitive filesystem */
public final class C0509se {
    public static void A01(String str, @Nullable String str2) {
        String A05 = AnonymousClass06.A05(str, str2, "]");
        if (A05.length() > 127 && str2 != null) {
            A05 = AnonymousClass06.A05(str, str2.substring(0, (Hpack.PREFIX_7_BITS - str.length()) - "]".length()), "]");
        }
        Trace.beginSection(A05);
    }

    public static void A00() {
        Trace.endSection();
    }
}
