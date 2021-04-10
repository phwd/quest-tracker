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
    public abstract MediaType contentType();

    public abstract void writeTo(BufferedSink bufferedSink) throws IOException;

    public long contentLength() throws IOException {
        return -1;
    }

    public static RequestBody create(MediaType contentType, String content) {
        Charset charset = Util.UTF_8;
        if (contentType != null && (charset = contentType.charset()) == null) {
            charset = Util.UTF_8;
            contentType = MediaType.parse(contentType + "; charset=utf-8");
        }
        return create(contentType, content.getBytes(charset));
    }

    public static RequestBody create(final MediaType contentType, final ByteString content) {
        return new RequestBody() {
            /* class com.squareup.okhttp.RequestBody.AnonymousClass1 */

            @Override // com.squareup.okhttp.RequestBody
            public MediaType contentType() {
                return contentType;
            }

            @Override // com.squareup.okhttp.RequestBody
            public long contentLength() throws IOException {
                return (long) content.size();
            }

            @Override // com.squareup.okhttp.RequestBody
            public void writeTo(BufferedSink sink) throws IOException {
                sink.write(content);
            }
        };
    }

    public static RequestBody create(MediaType contentType, byte[] content) {
        return create(contentType, content, 0, content.length);
    }

    public static RequestBody create(final MediaType contentType, final byte[] content, final int offset, final int byteCount) {
        if (content == null) {
            throw new NullPointerException("content == null");
        }
        Util.checkOffsetAndCount((long) content.length, (long) offset, (long) byteCount);
        return new RequestBody() {
            /* class com.squareup.okhttp.RequestBody.AnonymousClass2 */

            @Override // com.squareup.okhttp.RequestBody
            public MediaType contentType() {
                return contentType;
            }

            @Override // com.squareup.okhttp.RequestBody
            public long contentLength() {
                return (long) byteCount;
            }

            @Override // com.squareup.okhttp.RequestBody
            public void writeTo(BufferedSink sink) throws IOException {
                sink.write(content, offset, byteCount);
            }
        };
    }

    public static RequestBody create(final MediaType contentType, final File file) {
        if (file != null) {
            return new RequestBody() {
                /* class com.squareup.okhttp.RequestBody.AnonymousClass3 */

                @Override // com.squareup.okhttp.RequestBody
                public MediaType contentType() {
                    return contentType;
                }

                @Override // com.squareup.okhttp.RequestBody
                public long contentLength() {
                    return file.length();
                }

                @Override // com.squareup.okhttp.RequestBody
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
