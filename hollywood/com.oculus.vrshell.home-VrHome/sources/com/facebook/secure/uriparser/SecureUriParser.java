package com.facebook.secure.uriparser;

import android.net.Uri;
import java.net.URI;
import java.util.Locale;
import javax.annotation.Nullable;

public final class SecureUriParser {
    public static Uri parseStrict(String str) {
        URI javaUri = URI.create(str);
        Uri androidUri = new Uri.Builder().scheme(javaUri.getScheme()).encodedAuthority(javaUri.getRawAuthority()).encodedPath(javaUri.getRawPath()).encodedQuery(javaUri.getRawQuery()).encodedFragment(javaUri.getRawFragment()).build();
        if (urisMatch(javaUri, androidUri)) {
            return androidUri;
        }
        throw new SecurityException(String.format(Locale.US, "java uri not equal to android uri for uri string %s", str));
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
