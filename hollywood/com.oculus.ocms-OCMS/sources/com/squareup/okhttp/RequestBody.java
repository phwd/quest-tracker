package com.squareup.okhttp;

import com.squareup.okhttp.internal.Util;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import okio.BufferedSink;
import okio.ByteString;
import okio.Okio;
import okio.Source;

public abstract class RequestBody {
    public long contentLength() throws IOException {
        return -1;
    }

    public abstract MediaType contentType();

    public abstract void writeTo(BufferedSink bufferedSink) throws IOException;

    public static RequestBody create(MediaType mediaType, String str) {
        Charset charset = Util.UTF_8;
        if (mediaType != null && (charset = mediaType.charset()) == null) {
            charset = Util.UTF_8;
            mediaType = MediaType.parse(mediaType + "; charset=utf-8");
        }
        return create(mediaType, str.getBytes(charset));
    }

    public static RequestBody create(final MediaType mediaType, final ByteString byteString) {
        return new RequestBody() {
            /* class com.squareup.okhttp.RequestBody.AnonymousClass1 */

            @Override // com.squareup.okhttp.RequestBody
            public MediaType contentType() {
                return mediaType;
            }

            @Override // com.squareup.okhttp.RequestBody
            public long contentLength() throws IOException {
                return (long) byteString.size();
            }

            @Override // com.squareup.okhttp.RequestBody
            public void writeTo(BufferedSink bufferedSink) throws IOException {
                bufferedSink.write(byteString);
            }
        };
    }

    public static RequestBody create(MediaType mediaType, byte[] bArr) {
        return create(mediaType, bArr, 0, bArr.length);
    }

    public static RequestBody create(final MediaType mediaType, final byte[] bArr, final int i, final int i2) {
        if (bArr != null) {
            Util.checkOffsetAndCount((long) bArr.length, (long) i, (long) i2);
            return new RequestBody() {
                /* class com.squareup.okhttp.RequestBody.AnonymousClass2 */

                @Override // com.squareup.okhttp.RequestBody
                public MediaType contentType() {
                    return mediaType;
                }

                @Override // com.squareup.okhttp.RequestBody
                public long contentLength() {
                    return (long) i2;
                }

                @Override // com.squareup.okhttp.RequestBody
                public void writeTo(BufferedSink bufferedSink) throws IOException {
                    bufferedSink.write(bArr, i, i2);
                }
            };
        }
        throw new NullPointerException("content == null");
    }

    public static RequestBody create(final MediaType mediaType, final File file) {
        if (file != null) {
            return new RequestBody() {
                /* class com.squareup.okhttp.RequestBody.AnonymousClass3 */

                @Override // com.squareup.okhttp.RequestBody
                public MediaType contentType() {
                    return mediaType;
                }

                @Override // com.squareup.okhttp.RequestBody
                public long contentLength() {
                    return file.length();
                }

                @Override // com.squareup.okhttp.RequestBody
                public void writeTo(BufferedSink bufferedSink) throws IOException {
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
