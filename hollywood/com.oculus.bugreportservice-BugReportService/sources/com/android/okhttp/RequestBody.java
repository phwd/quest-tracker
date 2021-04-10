package com.android.okhttp;

import com.android.okhttp.internal.Util;
import com.android.okhttp.okio.BufferedSink;
import com.android.okhttp.okio.Okio;
import com.android.okhttp.okio.Source;
import java.io.File;
import java.nio.charset.Charset;

public abstract class RequestBody {
    public long contentLength() {
        return -1;
    }

    public abstract MediaType contentType();

    public abstract void writeTo(BufferedSink bufferedSink);

    public static RequestBody create(MediaType mediaType, String str) {
        Charset charset = Util.UTF_8;
        if (mediaType != null && (charset = mediaType.charset()) == null) {
            charset = Util.UTF_8;
            mediaType = MediaType.parse(mediaType + "; charset=utf-8");
        }
        return create(mediaType, str.getBytes(charset));
    }

    public static RequestBody create(MediaType mediaType, byte[] bArr) {
        return create(mediaType, bArr, 0, bArr.length);
    }

    public static RequestBody create(final MediaType mediaType, final byte[] bArr, final int i, final int i2) {
        if (bArr != null) {
            Util.checkOffsetAndCount((long) bArr.length, (long) i, (long) i2);
            return new RequestBody() {
                /* class com.android.okhttp.RequestBody.AnonymousClass2 */

                @Override // com.android.okhttp.RequestBody
                public MediaType contentType() {
                    return MediaType.this;
                }

                @Override // com.android.okhttp.RequestBody
                public long contentLength() {
                    return (long) i2;
                }

                @Override // com.android.okhttp.RequestBody
                public void writeTo(BufferedSink bufferedSink) {
                    bufferedSink.write(bArr, i, i2);
                }
            };
        }
        throw new NullPointerException("content == null");
    }

    public static RequestBody create(final MediaType mediaType, final File file) {
        if (file != null) {
            return new RequestBody() {
                /* class com.android.okhttp.RequestBody.AnonymousClass3 */

                @Override // com.android.okhttp.RequestBody
                public MediaType contentType() {
                    return MediaType.this;
                }

                @Override // com.android.okhttp.RequestBody
                public long contentLength() {
                    return file.length();
                }

                @Override // com.android.okhttp.RequestBody
                public void writeTo(BufferedSink bufferedSink) {
                    Source source = null;
                    try {
                        source = Okio.source(file);
                        bufferedSink.writeAll(source);
                    } finally {
                        Util.closeQuietly(source);
                    }
                }
            };
        }
        throw new NullPointerException("content == null");
    }
}
