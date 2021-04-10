package com.squareup.okhttp.internal.http;

import X.KC;
import com.facebook.acra.util.HttpRequestMultipart;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.ResponseBody;

public final class RealResponseBody extends ResponseBody {
    public final Headers headers;
    public final KC source;

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

    public RealResponseBody(Headers headers2, KC kc) {
        this.headers = headers2;
        this.source = kc;
    }

    @Override // com.squareup.okhttp.ResponseBody
    public KC source() {
        return this.source;
    }
}
