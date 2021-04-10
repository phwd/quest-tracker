package X;

import android.net.Uri;
import java.net.URI;
import java.util.Locale;
import javax.annotation.Nullable;

/* renamed from: X.0kg  reason: invalid class name and case insensitive filesystem */
public final class C02980kg {
    public static boolean A01(@Nullable String str, @Nullable String str2) {
        if (str != null && !str.equals("")) {
            return str.equals(str2);
        }
        if (str2 == null || str2.equals("")) {
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
        String str2 = "";
        if (!A01) {
            str2 = AnonymousClass006.A07(str2, String.format(Locale.US, "javaUri scheme: \"%s\". androidUri scheme: \"%s\".", create.getScheme(), build.getScheme()));
        }
        if (!A012) {
            str2 = AnonymousClass006.A07(str2, String.format(Locale.US, "javaUri authority: \"%s\". androidUri authority: \"%s\".", create.getAuthority(), build.getAuthority()));
        }
        if (!A013) {
            str2 = AnonymousClass006.A07(str2, String.format(Locale.US, "javaUri path: \"%s\". androidUri path: \"%s\".", create.getPath(), build.getPath()));
        }
        if (A01 && A012 && A013) {
            return build;
        }
        throw new SecurityException(String.format(Locale.US, "java uri \"%s\" not equal to android uri \"%s\". Debug info: %s. Original uri: %s", create.toString(), build.toString(), str2, str));
    }
}
