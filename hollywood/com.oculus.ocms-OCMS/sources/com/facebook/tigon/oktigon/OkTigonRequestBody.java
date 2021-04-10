package com.facebook.tigon.oktigon;

import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import javax.annotation.Nullable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

@Nullsafe(Nullsafe.Mode.LOCAL)
class OkTigonRequestBody extends RequestBody {
    private static final MediaType DEFAULT_MEDIA_TYPE = MediaType.parse("application/octet-stream");
    private final byte[] mBody;
    private final MediaType mMediaType;

    public OkTigonRequestBody(byte[] bArr, @Nullable String str) {
        this.mBody = bArr;
        this.mMediaType = str != null ? MediaType.parse(str) : DEFAULT_MEDIA_TYPE;
    }

    @Override // okhttp3.RequestBody
    public MediaType contentType() {
        return this.mMediaType;
    }

    @Override // okhttp3.RequestBody
    public void writeTo(BufferedSink bufferedSink) throws IOException {
        bufferedSink.write(this.mBody);
    }

    @Override // okhttp3.RequestBody
    public long contentLength() {
        return (long) this.mBody.length;
    }
}
