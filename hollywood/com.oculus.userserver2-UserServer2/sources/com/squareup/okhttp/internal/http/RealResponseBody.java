package com.squareup.okhttp.internal.http;

import X.Dp;
import com.facebook.acra.util.HttpRequestMultipart;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.ResponseBody;

public final class RealResponseBody extends ResponseBody {
    public final Headers headers;
    public final Dp source;

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

    public RealResponseBody(Headers headers2, Dp dp) {
        this.headers = headers2;
        this.source = dp;
    }

    @Override // com.squareup.okhttp.ResponseBody
    public Dp source() {
        return this.source;
    }
}
