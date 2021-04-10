package com.squareup.okhttp;

import com.facebook.tigon.iface.TigonRequest;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.internal.http.HttpMethod;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.List;

public final class Request {
    public final RequestBody body;
    public volatile CacheControl cacheControl;
    public final Headers headers;
    public volatile URI javaNetUri;
    public volatile URL javaNetUrl;
    public final String method;
    public final Object tag;
    public final HttpUrl url;

    public static class Builder {
        public RequestBody body;
        public Headers.Builder headers;
        public String method;
        public Object tag;
        public HttpUrl url;

        public Builder addHeader(String str, String str2) {
            this.headers.add(str, str2);
            return this;
        }

        public Request build() {
            if (this.url != null) {
                return new Request(this);
            }
            throw new IllegalStateException("url == null");
        }

        public Builder get() {
            method(TigonRequest.GET, null);
            return this;
        }

        public Builder head() {
            method(TigonRequest.HEAD, null);
            return this;
        }

        public Builder header(String str, String str2) {
            this.headers.set(str, str2);
            return this;
        }

        public Builder method(String str, RequestBody requestBody) {
            String str2;
            StringBuilder sb;
            String str3;
            if (str == null || str.length() == 0) {
                str2 = "method == null || method.length() == 0";
            } else {
                if (requestBody != null) {
                    if (!HttpMethod.permitsRequestBody(str)) {
                        sb = new StringBuilder();
                        sb.append("method ");
                        sb.append(str);
                        str3 = " must not have a request body.";
                    }
                    this.method = str;
                    this.body = requestBody;
                    return this;
                }
                if (HttpMethod.requiresRequestBody(str)) {
                    sb = new StringBuilder();
                    sb.append("method ");
                    sb.append(str);
                    str3 = " must have a request body.";
                }
                this.method = str;
                this.body = requestBody;
                return this;
                sb.append(str3);
                str2 = sb.toString();
            }
            throw new IllegalArgumentException(str2);
        }

        public Builder patch(RequestBody requestBody) {
            method("PATCH", requestBody);
            return this;
        }

        public Builder post(RequestBody requestBody) {
            method(TigonRequest.POST, requestBody);
            return this;
        }

        public Builder put(RequestBody requestBody) {
            method("PUT", requestBody);
            return this;
        }

        public Builder removeHeader(String str) {
            this.headers.removeAll(str);
            return this;
        }

        public Builder cacheControl(CacheControl cacheControl) {
            String obj = cacheControl.toString();
            boolean isEmpty = obj.isEmpty();
            Headers.Builder builder = this.headers;
            if (isEmpty) {
                builder.removeAll("Cache-Control");
                return this;
            }
            builder.set("Cache-Control", obj);
            return this;
        }

        public Builder headers(Headers headers2) {
            this.headers = headers2.newBuilder();
            return this;
        }

        public Builder tag(Object obj) {
            this.tag = obj;
            return this;
        }

        public Builder() {
            this.method = TigonRequest.GET;
            this.headers = new Headers.Builder();
        }

        public Builder(Request request) {
            this.url = request.url;
            this.method = request.method;
            this.body = request.body;
            this.tag = request.tag;
            this.headers = request.headers.newBuilder();
        }

        public Builder delete() {
            method("DELETE", RequestBody.create((MediaType) null, new byte[0]));
            return this;
        }

        public Builder delete(RequestBody requestBody) {
            method("DELETE", requestBody);
            return this;
        }

        public Builder url(HttpUrl httpUrl) {
            if (httpUrl != null) {
                this.url = httpUrl;
                return this;
            }
            throw new IllegalArgumentException("url == null");
        }

        /* JADX WARNING: Removed duplicated region for block: B:14:0x0031  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x001f  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.squareup.okhttp.Request.Builder url(java.lang.String r9) {
            /*
                r8 = this;
                r2 = r9
                if (r9 == 0) goto L_0x002e
                r3 = 1
                r4 = 0
                r7 = 3
                java.lang.String r5 = "ws:"
                r6 = r4
                boolean r0 = r2.regionMatches(r3, r4, r5, r6, r7)
                if (r0 == 0) goto L_0x0022
                java.lang.String r1 = "http:"
            L_0x0011:
                java.lang.String r0 = r9.substring(r7)
                java.lang.String r2 = X.AnonymousClass06.A04(r1, r0)
            L_0x0019:
                com.squareup.okhttp.HttpUrl r0 = com.squareup.okhttp.HttpUrl.parse(r2)
                if (r0 == 0) goto L_0x0031
                r8.url = r0
                return r8
            L_0x0022:
                r7 = 4
                java.lang.String r5 = "wss:"
                boolean r0 = r2.regionMatches(r3, r4, r5, r6, r7)
                if (r0 == 0) goto L_0x0019
                java.lang.String r1 = "https:"
                goto L_0x0011
            L_0x002e:
                java.lang.String r1 = "url == null"
                goto L_0x0037
            L_0x0031:
                java.lang.String r0 = "unexpected url: "
                java.lang.String r1 = X.AnonymousClass06.A04(r0, r2)
            L_0x0037:
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                r0.<init>(r1)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.Request.Builder.url(java.lang.String):com.squareup.okhttp.Request$Builder");
        }

        public Builder url(URL url2) {
            String str;
            if (url2 != null) {
                HttpUrl httpUrl = HttpUrl.get(url2);
                if (httpUrl != null) {
                    this.url = httpUrl;
                    return this;
                }
                StringBuilder sb = new StringBuilder("unexpected url: ");
                sb.append(url2);
                str = sb.toString();
            } else {
                str = "url == null";
            }
            throw new IllegalArgumentException(str);
        }
    }

    public CacheControl cacheControl() {
        CacheControl cacheControl2 = this.cacheControl;
        if (cacheControl2 != null) {
            return cacheControl2;
        }
        CacheControl parse = CacheControl.parse(this.headers);
        this.cacheControl = parse;
        return parse;
    }

    public String header(String str) {
        return Headers.get(this.headers.namesAndValues, str);
    }

    public boolean isHttps() {
        return this.url.isHttps();
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Request{method=");
        sb.append(this.method);
        sb.append(", url=");
        sb.append(this.url);
        sb.append(", tag=");
        Object obj = this.tag;
        if (obj == this) {
            obj = null;
        }
        sb.append(obj);
        sb.append('}');
        return sb.toString();
    }

    public URI uri() throws IOException {
        try {
            URI uri = this.javaNetUri;
            if (uri != null) {
                return uri;
            }
            URI uri2 = this.url.uri();
            this.javaNetUri = uri2;
            return uri2;
        } catch (IllegalStateException e) {
            throw new IOException(e.getMessage());
        }
    }

    public URL url() {
        URL url2 = this.javaNetUrl;
        if (url2 != null) {
            return url2;
        }
        URL url3 = this.url.url();
        this.javaNetUrl = url3;
        return url3;
    }

    public String urlString() {
        return this.url.toString();
    }

    public RequestBody body() {
        return this.body;
    }

    public HttpUrl httpUrl() {
        return this.url;
    }

    public String method() {
        return this.method;
    }

    public Object tag() {
        return this.tag;
    }

    public Request(Builder builder) {
        this.url = builder.url;
        this.method = builder.method;
        this.headers = new Headers(builder.headers);
        this.body = builder.body;
        Object obj = builder.tag;
        this.tag = obj != null ? obj : this;
    }

    public Headers headers() {
        return this.headers;
    }

    public List<String> headers(String str) {
        return this.headers.values(str);
    }
}
