package com.squareup.okhttp.internal.http;

import X.AnonymousClass006;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Request;
import java.net.Proxy;

public final class RequestLine {
    public static String get(Request request, Proxy.Type type) {
        StringBuilder sb = new StringBuilder();
        sb.append(request.method);
        sb.append(' ');
        boolean includeAuthorityInRequestLine = includeAuthorityInRequestLine(request, type);
        HttpUrl httpUrl = request.url;
        if (includeAuthorityInRequestLine) {
            sb.append(httpUrl);
        } else {
            sb.append(requestPath(httpUrl));
        }
        sb.append(" HTTP/1.1");
        return sb.toString();
    }

    public static boolean includeAuthorityInRequestLine(Request request, Proxy.Type type) {
        if (request.url.isHttps() || type != Proxy.Type.HTTP) {
            return false;
        }
        return true;
    }

    public static String requestPath(HttpUrl httpUrl) {
        String encodedPath = httpUrl.encodedPath();
        String encodedQuery = httpUrl.encodedQuery();
        if (encodedQuery != null) {
            return AnonymousClass006.A02(encodedPath, '?', encodedQuery);
        }
        return encodedPath;
    }
}
