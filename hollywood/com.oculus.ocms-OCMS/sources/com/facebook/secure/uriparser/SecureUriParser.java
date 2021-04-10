package com.facebook.secure.uriparser;

import android.annotation.SuppressLint;
import android.net.Uri;
import com.facebook.secure.logger.Reporter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;
import javax.annotation.Nullable;

public final class SecureUriParser {
    private static final String TAG = "UriParser";

    public static Uri parseUnsafe(String str) {
        return Uri.parse(str);
    }

    public static Uri parseStrict(String str) {
        URI create = URI.create(str);
        Uri build = new Uri.Builder().scheme(create.getScheme()).encodedAuthority(create.getRawAuthority()).encodedPath(create.getRawPath()).encodedQuery(create.getRawQuery()).encodedFragment(create.getRawFragment()).build();
        urisMatchMightThrowOnHier(create, build);
        return build;
    }

    public static Uri parseEncodedRFC2396(String str) throws Exception {
        Uri parse = Uri.parse(str);
        if (!validateScheme(parse)) {
            return parseEncodedRFC2396Reverse(str);
        }
        validateMightThrow(parse, str);
        return parse;
    }

    private static void validateMightThrow(Uri uri, String str) throws Exception {
        if (uri.isOpaque()) {
            urisMatchMightThrowOnOpaque(createOpaqueJavaUriFromAndroidUri(uri, str), uri);
        } else {
            urisMatchMightThrowOnHier(createHierJavaUriFromAndroidUri(uri, str), uri);
        }
    }

    private static boolean validateScheme(Uri uri) {
        if (uri.getScheme() == null) {
            return true;
        }
        return uri.getScheme().matches("([a-zA-Z][a-zA-Z0-9+.-]*)?");
    }

    private static Uri parseEncodedRFC2396Reverse(String str) throws Exception {
        try {
            URI create = URI.create(str);
            if (create.isOpaque()) {
                Uri createOpaqueAndroidUriFromJavaUri = createOpaqueAndroidUriFromJavaUri(create);
                urisMatchMightThrowOnOpaque(create, createOpaqueAndroidUriFromJavaUri);
                return createOpaqueAndroidUriFromJavaUri;
            }
            Uri createHierAndroidUriFromJavaUri = createHierAndroidUriFromJavaUri(create);
            urisMatchMightThrowOnHier(create, createHierAndroidUriFromJavaUri);
            return createHierAndroidUriFromJavaUri;
        } catch (IllegalArgumentException e) {
            throw createOnParsingJavaUriFailException(e, str);
        }
    }

    private static Uri createOpaqueAndroidUriFromJavaUri(URI uri) {
        return new Uri.Builder().scheme(uri.getScheme()).encodedOpaquePart(uri.getRawSchemeSpecificPart()).encodedFragment(uri.getRawFragment()).build();
    }

    private static Uri createHierAndroidUriFromJavaUri(URI uri) {
        return new Uri.Builder().scheme(uri.getScheme()).encodedAuthority(uri.getRawAuthority()).encodedPath(uri.getRawPath()).encodedQuery(uri.getRawQuery()).encodedFragment(uri.getRawFragment()).build();
    }

    private static URI createOpaqueJavaUriFromAndroidUri(Uri uri, String str) throws Exception {
        try {
            return new URI(uri.getScheme(), uri.getSchemeSpecificPart(), uri.getFragment());
        } catch (URISyntaxException e) {
            throw createOnParsingJavaUriFailException(e, str);
        }
    }

    private static URI createHierJavaUriFromAndroidUri(Uri uri, String str) throws Exception {
        try {
            return new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), uri.getPath(), uri.getQuery(), uri.getFragment());
        } catch (URISyntaxException e) {
            throw createOnParsingJavaUriFailException(e, str);
        }
    }

    private static SecurityException createOnParsingJavaUriFailException(Exception exc, String str) {
        return new SecurityException(String.format(Locale.US, "Parsing url caused exception: %s. The original url is '%s'", exc.getMessage(), str));
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

    private static void urisMatchMightThrowOnOpaque(URI uri, Uri uri2) {
        boolean stringEquals = stringEquals(uri.getScheme(), uri2.getScheme());
        boolean stringEquals2 = stringEquals(uri.getSchemeSpecificPart(), uri2.getSchemeSpecificPart());
        boolean stringEquals3 = stringEquals(uri.getFragment(), uri2.getFragment());
        if (!stringEquals || !stringEquals2 || !stringEquals3) {
            String str = "";
            if (!stringEquals) {
                str = str + String.format(Locale.US, "javaUri scheme: \"%s\". androidUri scheme: \"%s\".", uri.getScheme(), uri2.getScheme());
            }
            if (!stringEquals2) {
                str = str + String.format(Locale.US, "javaUri opaque part: \"%s\". androidUri opaque part: \"%s\".", uri.getSchemeSpecificPart(), uri2.getSchemeSpecificPart());
            }
            if (!stringEquals3) {
                str = str + String.format(Locale.US, "javaUri fragment: \"%s\". androidUri fragment: \"%s\".", uri.getFragment(), uri2.getFragment());
            }
            throw new SecurityException(String.format(Locale.US, "java uri \"%s\" not equal to android uri \"%s\". Debug info: %s", uri.toString(), uri2.toString(), str));
        }
    }

    private static void urisMatchMightThrowOnHier(URI uri, Uri uri2) {
        boolean stringEquals = stringEquals(uri.getScheme(), uri2.getScheme());
        boolean stringEquals2 = stringEquals(uri.getAuthority(), uri2.getAuthority());
        boolean stringEquals3 = stringEquals(uri.getPath(), uri2.getPath());
        boolean stringEquals4 = stringEquals(uri.getQuery(), uri2.getQuery());
        boolean stringEquals5 = stringEquals(uri.getFragment(), uri2.getFragment());
        String str = "";
        if (!stringEquals) {
            str = str + String.format(Locale.US, "javaUri scheme: \"%s\". androidUri scheme: \"%s\".", uri.getScheme(), uri2.getScheme());
        }
        if (!stringEquals2) {
            str = str + String.format(Locale.US, "javaUri authority: \"%s\". androidUri authority: \"%s\".", uri.getAuthority(), uri2.getAuthority());
        }
        if (!stringEquals3) {
            str = str + String.format(Locale.US, "javaUri path: \"%s\". androidUri path: \"%s\".", uri.getPath(), uri2.getPath());
        }
        if (!stringEquals4) {
            str = str + String.format(Locale.US, "javaUri query: \"%s\". androidUri query: \"%s\".", uri.getQuery(), uri2.getQuery());
        }
        if (!stringEquals5) {
            str = str + String.format(Locale.US, "javaUri fragment: \"%s\". androidUri fragment: \"%s\".", uri.getFragment(), uri2.getFragment());
        }
        if (!stringEquals || !stringEquals2 || !stringEquals3 || !stringEquals4 || !stringEquals5) {
            throw new SecurityException(String.format(Locale.US, "java uri \"%s\" not equal to android uri \"%s\". Debug info: %s", uri.toString(), uri2.toString(), str));
        }
    }

    private static boolean stringEquals(@Nullable String str, @Nullable String str2) {
        if (str == null) {
            return str2 == null;
        }
        return str.equals(str2);
    }
}
