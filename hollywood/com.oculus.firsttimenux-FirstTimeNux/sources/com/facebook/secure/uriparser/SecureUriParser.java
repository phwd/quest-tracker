package com.facebook.secure.uriparser;

import android.annotation.SuppressLint;
import android.net.Uri;
import com.facebook.secure.logger.Reporter;
import java.net.URI;
import java.util.Locale;
import javax.annotation.Nullable;

public final class SecureUriParser {
    private static final String TAG = "UriParser";

    public static Uri parseUnsafe(String str) {
        return Uri.parse(str);
    }

    public static Uri parseStrict(String str) {
        URI javaUri = URI.create(str);
        Uri androidUri = new Uri.Builder().scheme(javaUri.getScheme()).encodedAuthority(javaUri.getRawAuthority()).encodedPath(javaUri.getRawPath()).encodedQuery(javaUri.getRawQuery()).encodedFragment(javaUri.getRawFragment()).build();
        if (urisMatch(javaUri, androidUri)) {
            return androidUri;
        }
        throw new SecurityException(String.format(Locale.US, "java uri not equal to android uri for uri string %s", str));
    }

    @Nullable
    @SuppressLint({"CatchGeneralException"})
    public static Uri tryParseStrict(String str, Reporter reporter, boolean failOpen) {
        if (reporter == null) {
            throw new IllegalArgumentException("reporter is null");
        }
        try {
            return parseStrict(str);
        } catch (Exception e) {
            reporter.report(TAG, String.format(Locale.US, "Parse uri %s failed. Fail open: %b", str, Boolean.valueOf(failOpen)), e);
            if (failOpen) {
                return Uri.parse(str);
            }
            return null;
        }
    }

    @Nullable
    public static Uri tryParseStrict(String str, Reporter reporter) {
        return tryParseStrict(str, reporter, false);
    }

    public static Uri parseSafe(Uri uri) {
        return parseStrict(uri.toString());
    }

    public static boolean matchHost(Uri uri, String host, boolean includeSubdomains) {
        String uriHost = uri.getHost();
        if (uriHost == null) {
            return false;
        }
        if (uriHost.equalsIgnoreCase(host) || (includeSubdomains && uriHost.endsWith("." + host))) {
            return true;
        }
        return false;
    }

    private static boolean urisMatch(URI javaUri, Uri androidUri) {
        return stringEquals(javaUri.getScheme(), androidUri.getScheme()) && stringEquals(javaUri.getAuthority(), androidUri.getAuthority()) && stringEquals(javaUri.getPath(), androidUri.getPath()) && stringEquals(javaUri.getQuery(), androidUri.getQuery()) && stringEquals(javaUri.getFragment(), androidUri.getFragment());
    }

    private static boolean stringEquals(@Nullable String strOne, @Nullable String strTwo) {
        if (strOne == null) {
            return strTwo == null;
        }
        return strOne.equals(strTwo);
    }
}
