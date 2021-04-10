package com.squareup.okhttp.internal.http;

import X.AnonymousClass006;
import X.AnonymousClass1eW;
import com.squareup.okhttp.Authenticator;
import com.squareup.okhttp.Challenge;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.internal.Util;
import java.io.IOException;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public final class OkHeaders {
    public static final Comparator<String> FIELD_NAME_COMPARATOR = new Comparator<String>() {
        /* class com.squareup.okhttp.internal.http.OkHeaders.AnonymousClass1 */

        public int compare(String str, String str2) {
            if (str == str2) {
                return 0;
            }
            if (str == null) {
                return -1;
            }
            if (str2 == null) {
                return 1;
            }
            return String.CASE_INSENSITIVE_ORDER.compare(str, str2);
        }
    };
    public static final String PREFIX = "OkHttp";
    public static final String RECEIVED_MILLIS = AnonymousClass006.A05("OkHttp", "-Received-Millis");
    public static final String RESPONSE_SOURCE = AnonymousClass006.A05("OkHttp", "-Response-Source");
    public static final String SELECTED_PROTOCOL = AnonymousClass006.A05("OkHttp", "-Selected-Protocol");
    public static final String SENT_MILLIS = AnonymousClass006.A05("OkHttp", "-Sent-Millis");

    public static boolean isEndToEnd(String str) {
        if ("Connection".equalsIgnoreCase(str) || "Keep-Alive".equalsIgnoreCase(str) || "Proxy-Authenticate".equalsIgnoreCase(str) || "Proxy-Authorization".equalsIgnoreCase(str) || "TE".equalsIgnoreCase(str) || "Trailers".equalsIgnoreCase(str) || "Transfer-Encoding".equalsIgnoreCase(str) || AnonymousClass1eW.HEADER_CONNECTION_VALUE.equalsIgnoreCase(str)) {
            return false;
        }
        return true;
    }

    public static List<Challenge> parseChallenges(Headers headers, String str) {
        ArrayList arrayList = new ArrayList();
        int length = headers.namesAndValues.length >> 1;
        for (int i = 0; i < length; i++) {
            if (str.equalsIgnoreCase(headers.name(i))) {
                String value = headers.value(i);
                int i2 = 0;
                while (i2 < value.length()) {
                    int skipUntil = HeaderParser.skipUntil(value, i2, " ");
                    String trim = value.substring(i2, skipUntil).trim();
                    int skipWhitespace = HeaderParser.skipWhitespace(value, skipUntil);
                    if (!value.regionMatches(true, skipWhitespace, "realm=\"", 0, 7)) {
                        break;
                    }
                    int i3 = skipWhitespace + 7;
                    int skipUntil2 = HeaderParser.skipUntil(value, i3, "\"");
                    String substring = value.substring(i3, skipUntil2);
                    i2 = HeaderParser.skipWhitespace(value, HeaderParser.skipUntil(value, skipUntil2 + 1, ",") + 1);
                    arrayList.add(new Challenge(trim, substring));
                }
            }
        }
        return arrayList;
    }

    public static Request processAuthHeader(Authenticator authenticator, Response response, Proxy proxy) throws IOException {
        if (response.code == 407) {
            return authenticator.authenticateProxy(proxy, response);
        }
        return authenticator.authenticate(proxy, response);
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

    public static Map<String, List<String>> toMultimap(Headers headers, String str) {
        TreeMap treeMap = new TreeMap(FIELD_NAME_COMPARATOR);
        int length = headers.namesAndValues.length >> 1;
        for (int i = 0; i < length; i++) {
            String name = headers.name(i);
            String value = headers.value(i);
            ArrayList arrayList = new ArrayList();
            Collection collection = (Collection) treeMap.get(name);
            if (collection != null) {
                arrayList.addAll(collection);
            }
            arrayList.add(value);
            treeMap.put(name, Collections.unmodifiableList(arrayList));
        }
        if (str != null) {
            treeMap.put(null, Collections.unmodifiableList(Collections.singletonList(str)));
        }
        return Collections.unmodifiableMap(treeMap);
    }

    public static boolean varyMatches(Response response, Headers headers, Request request) {
        for (String str : varyFields(response.headers)) {
            if (!Util.equal(headers.values(str), request.headers.values(str))) {
                return false;
            }
        }
        return true;
    }

    public static void addCookies(Request.Builder builder, Map<String, List<String>> map) {
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            if (("Cookie".equalsIgnoreCase(key) || "Cookie2".equalsIgnoreCase(key)) && !entry.getValue().isEmpty()) {
                builder.headers.add(key, buildCookieHeader(entry.getValue()));
            }
        }
    }

    public static String buildCookieHeader(List<String> list) {
        if (list.size() == 1) {
            return list.get(0);
        }
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append("; ");
            }
            sb.append(list.get(i));
        }
        return sb.toString();
    }

    public static long contentLength(Headers headers) {
        return stringToLong(Headers.get(headers.namesAndValues, "Content-Length"));
    }

    public static long contentLength(Request request) {
        return contentLength(request.headers);
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
            if ("Vary".equalsIgnoreCase(headers.name(i))) {
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
            String name = headers.name(i);
            if (varyFields.contains(name)) {
                builder.add(name, headers.value(i));
            }
        }
        return new Headers(builder);
    }

    public static Headers varyHeaders(Response response) {
        return varyHeaders(response.networkResponse.request.headers, response.headers);
    }
}
