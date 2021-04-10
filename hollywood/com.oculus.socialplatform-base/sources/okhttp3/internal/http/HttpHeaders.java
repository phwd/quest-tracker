package okhttp3.internal.http;

import com.facebook.tigon.iface.TigonRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.Challenge;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;

public final class HttpHeaders {
    public static final Pattern PARAMETER = Pattern.compile(" +([^ \"=]*)=(:?\"([^\"]*)\"|([^ \"=]*)) *(:?,|$)");
    public static final String QUOTED_STRING = "\"([^\"]*)\"";
    public static final String TOKEN = "([^ \"=]*)";

    public static boolean hasBody(Response response) {
        int i;
        if (response.request.method.equals(TigonRequest.HEAD) || ((((i = response.code) >= 100 && i < 200) || i == 204 || i == 304) && contentLength(response.headers) == -1 && !"chunked".equalsIgnoreCase(response.header("Transfer-Encoding")))) {
            return false;
        }
        return true;
    }

    public static List<Challenge> parseChallenges(Headers headers, String str) {
        ArrayList arrayList = new ArrayList();
        for (String str2 : headers.values(str)) {
            int indexOf = str2.indexOf(32);
            if (indexOf != -1) {
                Matcher matcher = PARAMETER.matcher(str2);
                int i = indexOf;
                while (true) {
                    if (!matcher.find(i)) {
                        break;
                    }
                    if (str2.regionMatches(true, matcher.start(1), "realm", 0, 5)) {
                        String substring = str2.substring(0, indexOf);
                        String group = matcher.group(3);
                        if (group != null) {
                            arrayList.add(new Challenge(substring, group));
                            break;
                        }
                    }
                    i = matcher.end();
                }
            }
        }
        return arrayList;
    }

    public static void receiveHeaders(CookieJar cookieJar, HttpUrl httpUrl, Headers headers) {
        if (cookieJar != CookieJar.NO_COOKIES) {
            List<Cookie> parseAll = Cookie.parseAll(httpUrl, headers);
            if (!parseAll.isEmpty()) {
                cookieJar.saveFromResponse(httpUrl, parseAll);
            }
        }
    }

    public static long stringToLong(String str) {
        if (str == null) {
            return -1;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    public static boolean varyMatches(Response response, Headers headers, Request request) {
        for (String str : varyFields(response.headers)) {
            if (!Util.equal(headers.values(str), request.headers.values(str))) {
                return false;
            }
        }
        return true;
    }

    public static int parseSeconds(String str, int i) {
        try {
            long parseLong = Long.parseLong(str);
            if (parseLong > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            if (parseLong < 0) {
                return 0;
            }
            return (int) parseLong;
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public static int skipUntil(String str, int i, String str2) {
        while (i < str.length() && str2.indexOf(str.charAt(i)) == -1) {
            i++;
        }
        return i;
    }

    public static int skipWhitespace(String str, int i) {
        while (i < str.length() && ((r1 = str.charAt(i)) == ' ' || r1 == '\t')) {
            i++;
        }
        return i;
    }

    public static long contentLength(Headers headers) {
        return stringToLong(Headers.get(headers.namesAndValues, "Content-Length"));
    }

    public static long contentLength(Response response) {
        return contentLength(response.headers);
    }

    public static boolean hasVaryAll(Headers headers) {
        return varyFields(headers).contains("*");
    }

    public static boolean hasVaryAll(Response response) {
        return hasVaryAll(response.headers);
    }

    public static Set<String> varyFields(Headers headers) {
        Set<String> emptySet = Collections.emptySet();
        int length = headers.namesAndValues.length >> 1;
        for (int i = 0; i < length; i++) {
            if ("Vary".equalsIgnoreCase(headers.namesAndValues[i << 1])) {
                String value = headers.value(i);
                if (emptySet.isEmpty()) {
                    emptySet = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
                }
                String[] split = value.split(",");
                for (String str : split) {
                    emptySet.add(str.trim());
                }
            }
        }
        return emptySet;
    }

    public static Set<String> varyFields(Response response) {
        return varyFields(response.headers);
    }

    public static Headers varyHeaders(Headers headers, Headers headers2) {
        Set<String> varyFields = varyFields(headers2);
        if (varyFields.isEmpty()) {
            return new Headers(new Headers.Builder());
        }
        Headers.Builder builder = new Headers.Builder();
        int length = headers.namesAndValues.length >> 1;
        for (int i = 0; i < length; i++) {
            String str = headers.namesAndValues[i << 1];
            if (varyFields.contains(str)) {
                builder.add(str, headers.value(i));
            }
        }
        return new Headers(builder);
    }

    public static Headers varyHeaders(Response response) {
        return varyHeaders(response.networkResponse.request.headers, response.headers);
    }
}
