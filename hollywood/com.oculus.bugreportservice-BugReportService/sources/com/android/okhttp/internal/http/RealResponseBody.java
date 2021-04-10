package com.android.okhttp.internal.http;

import com.android.okhttp.Headers;
import com.android.okhttp.ResponseBody;
import com.android.okhttp.okio.BufferedSource;

public final class RealResponseBody extends ResponseBody {
    private final Headers headers;
    private final BufferedSource source;

    public RealResponseBody(Headers headers2, BufferedSource bufferedSource) {
        this.headers = headers2;
        this.source = bufferedSource;
    }

    @Override // com.android.okhttp.ResponseBody
    public long contentLength() {
        return OkHeaders.contentLength(this.headers);
    }

    @Override // com.android.okhttp.ResponseBody
    public BufferedSource source() {
        return this.source;
    }
}
