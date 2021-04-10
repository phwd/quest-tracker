package X;

import android.net.Uri;
import com.facebook.assistant.oacr.OacrConstants;
import java.net.URI;
import java.util.Locale;

/* renamed from: X.Jx  reason: case insensitive filesystem */
public final class C0209Jx {
    public static boolean A01(String str, String str2) {
        if (str != null && !str.equals(OacrConstants.AUTO_SPEECH_DOMAIN)) {
            return str.equals(str2);
        }
        if (str2 == null || str2.equals(OacrConstants.AUTO_SPEECH_DOMAIN)) {
            return true;
        }
        return false;
    }

    public static Uri A00(String str) {
        URI create = URI.create(str);
        Uri build = new Uri.Builder().scheme(create.getScheme()).encodedAuthority(create.getRawAuthority()).encodedPath(create.getRawPath()).encodedQuery(create.getRawQuery()).encodedFragment(create.getRawFragment()).build();
        boolean A01 = A01(create.getScheme(), build.getScheme());
        boolean A012 = A01(create.getAuthority(), build.getAuthority());
        boolean A013 = A01(create.getPath(), build.getPath());
        boolean A014 = A01(create.getQuery(), build.getQuery());
        boolean A015 = A01(create.getFragment(), build.getFragment());
        String str2 = OacrConstants.AUTO_SPEECH_DOMAIN;
        if (!A01) {
            str2 = AnonymousClass08.A04(str2, String.format(Locale.US, "javaUri scheme: \"%s\". androidUri scheme: \"%s\".", create.getScheme(), build.getScheme()));
        }
        if (!A012) {
            str2 = AnonymousClass08.A04(str2, String.format(Locale.US, "javaUri authority: \"%s\". androidUri authority: \"%s\".", create.getAuthority(), build.getAuthority()));
        }
        if (!A013) {
            str2 = AnonymousClass08.A04(str2, String.format(Locale.US, "javaUri path: \"%s\". androidUri path: \"%s\".", create.getPath(), build.getPath()));
        }
        if (!A014) {
            str2 = AnonymousClass08.A04(str2, String.format(Locale.US, "javaUri query: \"%s\". androidUri query: \"%s\".", create.getQuery(), build.getQuery()));
        }
        if (!A015) {
            str2 = AnonymousClass08.A04(str2, String.format(Locale.US, "javaUri fragment: \"%s\". androidUri fragment: \"%s\".", create.getFragment(), build.getFragment()));
        }
        if (A01 && A012 && A013 && A014 && A015) {
            return build;
        }
        throw new SecurityException(String.format(Locale.US, "java uri \"%s\" not equal to android uri \"%s\". Debug info: %s", create.toString(), build.toString(), str2));
    }
}
