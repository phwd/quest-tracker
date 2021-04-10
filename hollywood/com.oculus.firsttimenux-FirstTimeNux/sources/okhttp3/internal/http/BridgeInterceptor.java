package okhttp3.internal.http;

import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.util.List;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.Version;
import okio.GzipSource;
import okio.Okio;

public final class BridgeInterceptor implements Interceptor {
    private final CookieJar cookieJar;

    public BridgeInterceptor(CookieJar cookieJar2) {
        this.cookieJar = cookieJar2;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request userRequest = chain.request();
        Request.Builder requestBuilder = userRequest.newBuilder();
        RequestBody body = userRequest.body();
        if (body != null) {
            MediaType contentType = body.contentType();
            if (contentType != null) {
                requestBuilder.header(HttpHeaders.CONTENT_TYPE, contentType.toString());
            }
            long contentLength = body.contentLength();
            if (contentLength != -1) {
                requestBuilder.header(HttpHeaders.CONTENT_LENGTH, Long.toString(contentLength));
                requestBuilder.removeHeader(HttpHeaders.TRANSFER_ENCODING);
            } else {
                requestBuilder.header(HttpHeaders.TRANSFER_ENCODING, "chunked");
                requestBuilder.removeHeader(HttpHeaders.CONTENT_LENGTH);
            }
        }
        if (userRequest.header(HttpHeaders.HOST) == null) {
            requestBuilder.header(HttpHeaders.HOST, Util.hostHeader(userRequest.url(), false));
        }
        if (userRequest.header(HttpHeaders.CONNECTION) == null) {
            requestBuilder.header(HttpHeaders.CONNECTION, "Keep-Alive");
        }
        boolean transparentGzip = false;
        if (userRequest.header(HttpHeaders.ACCEPT_ENCODING) == null && userRequest.header(HttpHeaders.RANGE) == null) {
            transparentGzip = true;
            requestBuilder.header(HttpHeaders.ACCEPT_ENCODING, "gzip");
        }
        List<Cookie> cookies = this.cookieJar.loadForRequest(userRequest.url());
        if (!cookies.isEmpty()) {
            requestBuilder.header(HttpHeaders.COOKIE, cookieHeader(cookies));
        }
        if (userRequest.header(HttpHeaders.USER_AGENT) == null) {
            requestBuilder.header(HttpHeaders.USER_AGENT, Version.userAgent());
        }
        Response networkResponse = chain.proceed(requestBuilder.build());
        HttpHeaders.receiveHeaders(this.cookieJar, userRequest.url(), networkResponse.headers());
        Response.Builder responseBuilder = networkResponse.newBuilder().request(userRequest);
        if (transparentGzip && "gzip".equalsIgnoreCase(networkResponse.header(HttpHeaders.CONTENT_ENCODING)) && HttpHeaders.hasBody(networkResponse)) {
            GzipSource responseBody = new GzipSource(networkResponse.body().source());
            responseBuilder.headers(networkResponse.headers().newBuilder().removeAll(HttpHeaders.CONTENT_ENCODING).removeAll(HttpHeaders.CONTENT_LENGTH).build());
            responseBuilder.body(new RealResponseBody(networkResponse.header(HttpHeaders.CONTENT_TYPE), -1, Okio.buffer(responseBody)));
        }
        return responseBuilder.build();
    }

    private String cookieHeader(List<Cookie> cookies) {
        StringBuilder cookieHeader = new StringBuilder();
        int size = cookies.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                cookieHeader.append("; ");
            }
            Cookie cookie = cookies.get(i);
            cookieHeader.append(cookie.name()).append('=').append(cookie.value());
        }
        return cookieHeader.toString();
    }
}
