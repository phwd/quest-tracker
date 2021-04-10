package X;

import android.os.Trace;

public final class KD {
    public static void A01(String str, String str2) {
        String A05 = AnonymousClass08.A05(str, str2, "]");
        if (A05.length() > 127 && str2 != null) {
            A05 = AnonymousClass08.A05(str, str2.substring(0, (127 - str.length()) - "]".length()), "]");
        }
        Trace.beginSection(A05);
    }

    public static void A00() {
        Trace.endSection();
    }
}
