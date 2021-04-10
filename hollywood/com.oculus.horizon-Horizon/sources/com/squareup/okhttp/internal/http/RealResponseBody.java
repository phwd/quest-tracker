package com.squareup.okhttp.internal.http;

import X.AnonymousClass0Lw;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.ResponseBody;

public final class RealResponseBody extends ResponseBody {
    public final Headers headers;
    public final AnonymousClass0Lw source;

    @Override // com.squareup.okhttp.ResponseBody
    public long contentLength() {
        return OkHeaders.contentLength(this.headers);
    }

    @Override // com.squareup.okhttp.ResponseBody
    public MediaType contentType() {
        String str = Headers.get(this.headers.namesAndValues, "Content-Type");
        if (str != null) {
            return MediaType.parse(str);
        }
        return null;
    }

    public RealResponseBody(Headers headers2, AnonymousClass0Lw r2) {
        this.headers = headers2;
        this.source = r2;
    }

    @Override // com.squareup.okhttp.ResponseBody
    public AnonymousClass0Lw source() {
        return this.source;
    }
}
