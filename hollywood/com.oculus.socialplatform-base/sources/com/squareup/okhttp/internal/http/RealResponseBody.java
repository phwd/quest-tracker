package com.squareup.okhttp.internal.http;

import com.facebook.acra.util.HttpRequestMultipart;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.ResponseBody;
import okio.BufferedSource;

public final class RealResponseBody extends ResponseBody {
    public final Headers headers;
    public final BufferedSource source;

    @Override // com.squareup.okhttp.ResponseBody
    public long contentLength() {
        return OkHeaders.contentLength(this.headers);
    }

    @Override // com.squareup.okhttp.ResponseBody
    public MediaType contentType() {
        String str = Headers.get(this.headers.namesAndValues, HttpRequestMultipart.CONTENT_TYPE);
        if (str != null) {
            return MediaType.parse(str);
        }
        return null;
    }

    public RealResponseBody(Headers headers2, BufferedSource bufferedSource) {
        this.headers = headers2;
        this.source = bufferedSource;
    }

    @Override // com.squareup.okhttp.ResponseBody
    public BufferedSource source() {
        return this.source;
    }
}
