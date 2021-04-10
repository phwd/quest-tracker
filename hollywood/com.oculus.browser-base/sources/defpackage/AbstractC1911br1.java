package defpackage;

import J.N;
import android.text.TextUtils;
import org.chromium.url.GURL;

/* renamed from: br1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1911br1 {
    public static GURL a(String str) {
        if (TextUtils.isEmpty(str)) {
            return GURL.emptyGURL();
        }
        GURL.b();
        return (GURL) N.Ml2KxI$W(str);
    }

    public static String b(String str) {
        return N.MR6Af3ZS(str, 0);
    }

    public static String c(GURL gurl, int i) {
        return gurl == null ? "" : N.MN7bz_Mm(gurl, i);
    }
}
