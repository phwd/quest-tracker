package okhttp3.internal.http;

import com.facebook.acra.util.HttpRequestMultipart;
import com.oculus.http.core.interceptor.GzipInterceptor;
import java.io.IOException;
import java.util.List;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.Util;
import okio.GzipSource;
import okio.Okio;

public final class BridgeInterceptor implements Interceptor {
    public final CookieJar cookieJar;

    private String cookieHeader(List<Cookie> list) {
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append("; ");
            }
            Cookie cookie = list.get(i);
            sb.append(cookie.name);
            sb.append('=');
            sb.append(cookie.value);
        }
        return sb.toString();
    }

    public BridgeInterceptor(CookieJar cookieJar2) {
        this.cookieJar = cookieJar2;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder newBuilder = request.newBuilder();
        RequestBody requestBody = request.body;
        if (requestBody != null) {
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                newBuilder.header(HttpRequestMultipart.CONTENT_TYPE, contentType.toString());
            }
            long contentLength = requestBody.contentLength();
            if (contentLength != -1) {
                newBuilder.header("Content-Length", Long.toString(contentLength));
                newBuilder.removeHeader("Transfer-Encoding");
            } else {
                newBuilder.header("Transfer-Encoding", "chunked");
                newBuilder.removeHeader("Content-Length");
            }
        }
        boolean z = false;
        if (request.header("Host") == null) {
            newBuilder.header("Host", Util.hostHeader(request.url, false));
        }
        if (request.header("Connection") == null) {
            newBuilder.header("Connection", "Keep-Alive");
        }
        if (request.header("Accept-Encoding") == null && request.header("Range") == null) {
            z = true;
            newBuilder.header("Accept-Encoding", "gzip");
        }
        List<Cookie> loadForRequest = this.cookieJar.loadForRequest(request.url);
        if (!loadForRequest.isEmpty()) {
            newBuilder.header("Cookie", cookieHeader(loadForRequest));
        }
        if (request.header("User-Agent") == null) {
            newBuilder.header("User-Agent", "okhttp/3.6.0");
        }
        Response proceed = chain.proceed(newBuilder.build());
        HttpHeaders.receiveHeaders(this.cookieJar, request.url, proceed.headers);
        Response.Builder newBuilder2 = proceed.newBuilder();
        newBuilder2.request = request;
        if (z && "gzip".equalsIgnoreCase(proceed.header(GzipInterceptor.HEADER_CONTENT_ENCODING)) && HttpHeaders.hasBody(proceed)) {
            GzipSource gzipSource = new GzipSource(proceed.body.source());
            Headers.Builder newBuilder3 = proceed.headers.newBuilder();
            newBuilder3.removeAll(GzipInterceptor.HEADER_CONTENT_ENCODING);
            newBuilder3.removeAll("Content-Length");
            Headers headers = new Headers(newBuilder3);
            newBuilder2.headers = headers.newBuilder();
            newBuilder2.body = new RealResponseBody(headers, Okio.buffer(gzipSource));
        }
        return newBuilder2.build();
    }
}
