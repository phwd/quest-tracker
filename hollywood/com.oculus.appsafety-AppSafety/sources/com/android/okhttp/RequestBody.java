package com.android.okhttp;

import com.android.okhttp.internal.Util;
import com.android.okhttp.okio.BufferedSink;
import com.android.okhttp.okio.ByteString;
import com.android.okhttp.okio.Okio;
import com.android.okhttp.okio.Source;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public abstract class RequestBody {
    public abstract MediaType contentType();

    public abstract void writeTo(BufferedSink bufferedSink) throws IOException;

    public long contentLength() throws IOException {
        return -1;
    }

    public static RequestBody create(MediaType contentType, String content) {
        Charset charset = Util.UTF_8;
        if (contentType != null && (charset = contentType.charset()) == null) {
            charset = Util.UTF_8;
            contentType = MediaType.parse(((Object) contentType) + "; charset=utf-8");
        }
        return create(contentType, content.getBytes(charset));
    }

    public static RequestBody create(final MediaType contentType, final ByteString content) {
        return new RequestBody() {
            /* class com.android.okhttp.RequestBody.AnonymousClass1 */

            @Override // com.android.okhttp.RequestBody
            public MediaType contentType() {
                return MediaType.this;
            }

            @Override // com.android.okhttp.RequestBody
            public long contentLength() throws IOException {
                return (long) content.size();
            }

            @Override // com.android.okhttp.RequestBody
            public void writeTo(BufferedSink sink) throws IOException {
                sink.write(content);
            }
        };
    }

    public static RequestBody create(MediaType contentType, byte[] content) {
        return create(contentType, content, 0, content.length);
    }

    public static RequestBody create(final MediaType contentType, final byte[] content, final int offset, final int byteCount) {
        if (content != null) {
            Util.checkOffsetAndCount((long) content.length, (long) offset, (long) byteCount);
            return new RequestBody() {
                /* class com.android.okhttp.RequestBody.AnonymousClass2 */

                @Override // com.android.okhttp.RequestBody
                public MediaType contentType() {
                    return MediaType.this;
                }

                @Override // com.android.okhttp.RequestBody
                public long contentLength() {
                    return (long) byteCount;
                }

                @Override // com.android.okhttp.RequestBody
                public void writeTo(BufferedSink sink) throws IOException {
                    sink.write(content, offset, byteCount);
                }
            };
        }
        throw new NullPointerException("content == null");
    }

    public static RequestBody create(final MediaType contentType, final File file) {
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
                public void writeTo(BufferedSink sink) throws IOException {
                    Source source = null;
                    try {
                        source = Okio.source(file);
                        sink.writeAll(source);
                    } finally {
                        Util.closeQuietly(source);
                    }
                }
            };
        }
        throw new NullPointerException("content == null");
    }
}
