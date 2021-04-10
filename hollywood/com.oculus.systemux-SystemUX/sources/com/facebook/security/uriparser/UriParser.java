package com.facebook.security.uriparser;

import android.annotation.SuppressLint;
import android.net.Uri;
import com.facebook.secure.logger.Reporter;
import java.net.URI;
import java.util.Locale;
import javax.annotation.Nullable;

public final class UriParser {
    private static final String TAG = "UriParser";

    public static Uri parseUnsafe(String str) {
        return Uri.parse(str);
    }

    public static Uri parseStrict(String str) {
        URI create = URI.create(str);
        Uri build = new Uri.Builder().scheme(create.getScheme()).encodedAuthority(create.getRawAuthority()).encodedPath(create.getRawPath()).encodedQuery(create.getRawQuery()).encodedFragment(create.getRawFragment()).build();
        if (urisMatch(create, build)) {
            return build;
        }
        throw new SecurityException(String.format(Locale.US, "java uri not equal to android uri for uri string %s", str));
    }

    @Nullable
    @SuppressLint({"CatchGeneralException"})
    public static Uri tryParseStrict(String str, Reporter reporter, boolean z) {
        if (reporter != null) {
            try {
                return parseStrict(str);
            } catch (Exception e) {
                reporter.report(TAG, String.format(Locale.US, "Parse uri %s failed. Fail open: %b", str, Boolean.valueOf(z)), e);
                if (z) {
                    return Uri.parse(str);
                }
                return null;
            }
        } else {
            throw new IllegalArgumentException("reporter is null");
        }
    }

    @Nullable
    public static Uri tryParseStrict(String str, Reporter reporter) {
        return tryParseStrict(str, reporter, false);
    }

    public static Uri parseSafe(Uri uri) {
        return parseStrict(uri.toString());
    }

    public static boolean matchHost(Uri uri, String str, boolean z) {
        String host = uri.getHost();
        if (host == null) {
            return false;
        }
        if (!host.equalsIgnoreCase(str)) {
            if (!z) {
                return false;
            }
            if (host.endsWith("." + str)) {
                return true;
            }
            return false;
        }
        return true;
    }

    private static boolean urisMatch(URI uri, Uri uri2) {
        return stringEquals(uri.getScheme(), uri2.getScheme()) && stringEquals(uri.getAuthority(), uri2.getAuthority()) && stringEquals(uri.getPath(), uri2.getPath()) && stringEquals(uri.getQuery(), uri2.getQuery()) && stringEquals(uri.getFragment(), uri2.getFragment());
    }

    private static boolean stringEquals(@Nullable String str, @Nullable String str2) {
        if (str == null) {
            return str2 == null;
        }
        return str.equals(str2);
    }
}
