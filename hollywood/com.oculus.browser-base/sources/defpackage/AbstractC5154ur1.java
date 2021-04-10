package defpackage;

import J.N;
import android.net.Uri;
import android.text.TextUtils;
import java.util.HashSet;
import java.util.regex.Pattern;
import org.chromium.url.GURL;

/* renamed from: ur1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5154ur1 {

    /* renamed from: a  reason: collision with root package name */
    public static final Pattern f11440a = Pattern.compile("^(www[0-9]*|web|ftp|wap|home|mobile|amp)\\.");
    public static final HashSet b = AbstractC0417Gv.d("chrome", "chrome-native", "about");

    public static String a(String str) {
        return C4271ph.c().d(f11440a.matcher(N.M25QTkfm(N.MpCt7siL(str))).replaceFirst(""));
    }

    public static String b(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        return N.M3l9sLoY(str, z);
    }

    public static String c(GURL gurl) {
        if (!GURL.k(gurl) && j(gurl)) {
            return gurl.f();
        }
        return "";
    }

    public static boolean d(String str) {
        return i(Uri.parse(str).getScheme());
    }

    public static boolean e(GURL gurl) {
        return i(gurl.g());
    }

    public static boolean f(GURL gurl) {
        return b.contains(gurl.g());
    }

    @Deprecated
    public static boolean g(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return h(AbstractC1911br1.a(str));
    }

    public static boolean h(GURL gurl) {
        if (!gurl.b || !b.contains(gurl.g())) {
            return false;
        }
        return "newtab".equals(gurl.d());
    }

    public static boolean i(String str) {
        return "http".equals(str) || "https".equals(str);
    }

    public static boolean j(GURL gurl) {
        return gurl != null && gurl.g().equals("tel");
    }

    public static String k(String str) {
        String trim = str.trim();
        if (trim.startsWith("https://")) {
            return trim.substring(8);
        }
        return trim.startsWith("http://") ? trim.substring(7) : trim;
    }
}
