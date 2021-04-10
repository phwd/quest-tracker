package com.squareup.okhttp;

import X.Jy;
import X.KJ;
import X.ca;
import X.ci;
import com.squareup.okhttp.internal.Util;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

public abstract class RequestBody {
    public long contentLength() throws IOException {
        return -1;
    }

    public abstract MediaType contentType();

    public abstract void writeTo(KJ kj) throws IOException;

    public static RequestBody create(final MediaType mediaType, final File file) {
        if (file != null) {
            return new RequestBody() {
                /* class com.squareup.okhttp.RequestBody.AnonymousClass3 */

                @Override // com.squareup.okhttp.RequestBody
                public void writeTo(KJ kj) throws IOException {
                    Jy jy = null;
                    try {
                        File file = file;
                        if (file != null) {
                            jy = new Jy(new ca(), new FileInputStream(file));
                            kj.A5p(jy);
                            return;
                        }
                        throw new IllegalArgumentException("file == null");
                    } finally {
                        Util.closeQuietly(jy);
                    }
                }

                @Override // com.squareup.okhttp.RequestBody
                public long contentLength() {
                    return file.length();
                }

                @Override // com.squareup.okhttp.RequestBody
                public MediaType contentType() {
                    return mediaType;
                }
            };
        }
        throw new NullPointerException("content == null");
    }

    public static RequestBody create(MediaType mediaType, String str) {
        Charset charset = Util.UTF_8;
        if (mediaType != null && (charset = mediaType.charset()) == null) {
            charset = Util.UTF_8;
            StringBuilder sb = new StringBuilder();
            sb.append(mediaType);
            sb.append("; charset=utf-8");
            mediaType = MediaType.parse(sb.toString());
        }
        return create(mediaType, str.getBytes(charset));
    }

    public static RequestBody create(final MediaType mediaType, final ci ciVar) {
        return new RequestBody() {
            /* class com.squareup.okhttp.RequestBody.AnonymousClass1 */

            @Override // com.squareup.okhttp.RequestBody
            public long contentLength() throws IOException {
                return (long) ciVar.A07();
            }

            @Override // com.squareup.okhttp.RequestBody
            public void writeTo(KJ kj) throws IOException {
                kj.A5m(ciVar);
            }

            @Override // com.squareup.okhttp.RequestBody
            public MediaType contentType() {
                return mediaType;
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
                public long contentLength() {
                    return (long) i2;
                }

                @Override // com.squareup.okhttp.RequestBody
                public void writeTo(KJ kj) throws IOException {
                    kj.A5o(bArr, i, i2);
                }

                @Override // com.squareup.okhttp.RequestBody
                public MediaType contentType() {
                    return mediaType;
                }
            };
        }
        throw new NullPointerException("content == null");
    }
}
