package com.squareup.okhttp;

import X.AnonymousClass006;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.internal.http.OkHeaders;
import java.util.Collections;
import java.util.List;

public final class Response {
    public final ResponseBody body;
    public volatile CacheControl cacheControl;
    public Response cacheResponse;
    public final int code;
    public final Handshake handshake;
    public final Headers headers;
    public final String message;
    public Response networkResponse;
    public final Response priorResponse;
    public final Protocol protocol;
    public final Request request;

    public static class Builder {
        public ResponseBody body;
        public Response cacheResponse;
        public int code;
        public Handshake handshake;
        public Headers.Builder headers;
        public String message;
        public Response networkResponse;
        public Response priorResponse;
        public Protocol protocol;
        public Request request;

        private void checkPriorResponse(Response response) {
            if (response.body != null) {
                throw new IllegalArgumentException("priorResponse.body != null");
            }
        }

        private void checkSupportResponse(String str, Response response) {
            if (response.body != null) {
                throw new IllegalArgumentException(AnonymousClass006.A07(str, ".body != null"));
            } else if (response.networkResponse != null) {
                throw new IllegalArgumentException(AnonymousClass006.A07(str, ".networkResponse != null"));
            } else if (response.cacheResponse != null) {
                throw new IllegalArgumentException(AnonymousClass006.A07(str, ".cacheResponse != null"));
            } else if (response.priorResponse != null) {
                throw new IllegalArgumentException(AnonymousClass006.A07(str, ".priorResponse != null"));
            }
        }

        public Builder addHeader(String str, String str2) {
            this.headers.add(str, str2);
            return this;
        }

        public Response build() {
            if (this.request == null) {
                throw new IllegalStateException("request == null");
            } else if (this.protocol != null) {
                int i = this.code;
                if (i >= 0) {
                    return new Response(this);
                }
                throw new IllegalStateException(AnonymousClass006.A03("code < 0: ", i));
            } else {
                throw new IllegalStateException("protocol == null");
            }
        }

        public Builder cacheResponse(Response response) {
            if (response != null) {
                checkSupportResponse("cacheResponse", response);
            }
            this.cacheResponse = response;
            return this;
        }

        public Builder header(String str, String str2) {
            this.headers.set(str, str2);
            return this;
        }

        public Builder networkResponse(Response response) {
            if (response != null) {
                checkSupportResponse("networkResponse", response);
            }
            this.networkResponse = response;
            return this;
        }

        public Builder priorResponse(Response response) {
            if (response != null) {
                checkPriorResponse(response);
            }
            this.priorResponse = response;
            return this;
        }

        public Builder removeHeader(String str) {
            this.headers.removeAll(str);
            return this;
        }

        public Builder headers(Headers headers2) {
            this.headers = headers2.newBuilder();
            return this;
        }

        public Builder body(ResponseBody responseBody) {
            this.body = responseBody;
            return this;
        }

        public Builder code(int i) {
            this.code = i;
            return this;
        }

        public Builder handshake(Handshake handshake2) {
            this.handshake = handshake2;
            return this;
        }

        public Builder message(String str) {
            this.message = str;
            return this;
        }

        public Builder protocol(Protocol protocol2) {
            this.protocol = protocol2;
            return this;
        }

        public Builder request(Request request2) {
            this.request = request2;
            return this;
        }

        public Builder() {
            this.code = -1;
            this.headers = new Headers.Builder();
        }

        public Builder(Response response) {
            this.code = -1;
            this.request = response.request;
            this.protocol = response.protocol;
            this.code = response.code;
            this.message = response.message;
            this.handshake = response.handshake;
            this.headers = response.headers.newBuilder();
            this.body = response.body;
            this.networkResponse = response.networkResponse;
            this.cacheResponse = response.cacheResponse;
            this.priorResponse = response.priorResponse;
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

    public List<Challenge> challenges() {
        String str;
        int i = this.code;
        if (i == 401) {
            str = "WWW-Authenticate";
        } else if (i != 407) {
            return Collections.emptyList();
        } else {
            str = "Proxy-Authenticate";
        }
        return OkHeaders.parseChallenges(this.headers, str);
    }

    public boolean isRedirect() {
        int i = this.code;
        if (i == 307 || i == 308) {
            return true;
        }
        switch (i) {
            case 300:
            case 301:
            case 302:
            case 303:
                return true;
            default:
                return false;
        }
    }

    public boolean isSuccessful() {
        int i = this.code;
        if (i < 200 || i >= 300) {
            return false;
        }
        return true;
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Response{protocol=");
        sb.append(this.protocol);
        sb.append(", code=");
        sb.append(this.code);
        sb.append(", message=");
        sb.append(this.message);
        sb.append(", url=");
        sb.append(this.request.url.toString());
        sb.append('}');
        return sb.toString();
    }

    public ResponseBody body() {
        return this.body;
    }

    public Response cacheResponse() {
        return this.cacheResponse;
    }

    public int code() {
        return this.code;
    }

    public Handshake handshake() {
        return this.handshake;
    }

    public String message() {
        return this.message;
    }

    public Response networkResponse() {
        return this.networkResponse;
    }

    public Response priorResponse() {
        return this.priorResponse;
    }

    public Protocol protocol() {
        return this.protocol;
    }

    public Request request() {
        return this.request;
    }

    public Response(Builder builder) {
        this.request = builder.request;
        this.protocol = builder.protocol;
        this.code = builder.code;
        this.message = builder.message;
        this.handshake = builder.handshake;
        this.headers = new Headers(builder.headers);
        this.body = builder.body;
        this.networkResponse = builder.networkResponse;
        this.cacheResponse = builder.cacheResponse;
        this.priorResponse = builder.priorResponse;
    }

    public String header(String str) {
        return header(str, null);
    }

    public String header(String str, String str2) {
        String str3 = Headers.get(this.headers.namesAndValues, str);
        return str3 == null ? str2 : str3;
    }

    public Headers headers() {
        return this.headers;
    }

    public List<String> headers(String str) {
        return this.headers.values(str);
    }
}
