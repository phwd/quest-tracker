package X;

import android.net.Uri;
import java.net.URI;
import java.util.Locale;

public final class g5 {
    public static Uri A00(String str) {
        boolean equals;
        boolean equals2;
        boolean equals3;
        boolean equals4;
        boolean equals5;
        URI create = URI.create(str);
        Uri build = new Uri.Builder().scheme(create.getScheme()).encodedAuthority(create.getRawAuthority()).encodedPath(create.getRawPath()).encodedQuery(create.getRawQuery()).encodedFragment(create.getRawFragment()).build();
        String scheme = create.getScheme();
        String scheme2 = build.getScheme();
        if (scheme == null) {
            equals = false;
            if (scheme2 == null) {
                equals = true;
            }
        } else {
            equals = scheme.equals(scheme2);
        }
        String authority = create.getAuthority();
        String authority2 = build.getAuthority();
        if (authority == null) {
            equals2 = false;
            if (authority2 == null) {
                equals2 = true;
            }
        } else {
            equals2 = authority.equals(authority2);
        }
        String path = create.getPath();
        String path2 = build.getPath();
        if (path == null) {
            equals3 = false;
            if (path2 == null) {
                equals3 = true;
            }
        } else {
            equals3 = path.equals(path2);
        }
        String query = create.getQuery();
        String query2 = build.getQuery();
        if (query == null) {
            equals4 = false;
            if (query2 == null) {
                equals4 = true;
            }
        } else {
            equals4 = query.equals(query2);
        }
        String fragment = create.getFragment();
        String fragment2 = build.getFragment();
        if (fragment == null) {
            equals5 = false;
            if (fragment2 == null) {
                equals5 = true;
            }
        } else {
            equals5 = fragment.equals(fragment2);
        }
        String str2 = "";
        if (!equals) {
            str2 = AnonymousClass06.A03(str2, String.format(Locale.US, "javaUri scheme: \"%s\". androidUri scheme: \"%s\".", create.getScheme(), build.getScheme()));
        }
        if (!equals2) {
            str2 = AnonymousClass06.A03(str2, String.format(Locale.US, "javaUri authority: \"%s\". androidUri authority: \"%s\".", create.getAuthority(), build.getAuthority()));
        }
        if (!equals3) {
            str2 = AnonymousClass06.A03(str2, String.format(Locale.US, "javaUri path: \"%s\". androidUri path: \"%s\".", create.getPath(), build.getPath()));
        }
        if (!equals4) {
            str2 = AnonymousClass06.A03(str2, String.format(Locale.US, "javaUri query: \"%s\". androidUri query: \"%s\".", create.getQuery(), build.getQuery()));
        }
        if (!equals5) {
            str2 = AnonymousClass06.A03(str2, String.format(Locale.US, "javaUri fragment: \"%s\". androidUri fragment: \"%s\".", create.getFragment(), build.getFragment()));
        }
        if (equals && equals2 && equals3 && equals4 && equals5) {
            return build;
        }
        throw new SecurityException(String.format(Locale.US, "java uri \"%s\" not equal to android uri \"%s\". Debug info: %s", create.toString(), build.toString(), str2));
    }
}
