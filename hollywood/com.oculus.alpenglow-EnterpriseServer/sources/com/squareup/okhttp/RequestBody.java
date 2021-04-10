package com.squareup.okhttp;

import X.AnonymousClass0OS;
import X.AnonymousClass0Oe;
import X.C04540gz;
import X.C04610h7;
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

    public abstract void writeTo(AnonymousClass0Oe v) throws IOException;

    public static RequestBody create(final MediaType mediaType, final File file) {
        if (file != null) {
            return new RequestBody() {
                /* class com.squareup.okhttp.RequestBody.AnonymousClass3 */

                @Override // com.squareup.okhttp.RequestBody
                public void writeTo(AnonymousClass0Oe r5) throws IOException {
                    AnonymousClass0OS r3 = null;
                    try {
                        File file = file;
                        if (file != null) {
                            r3 = new AnonymousClass0OS(new C04540gz(), new FileInputStream(file));
                            r5.A8y(r3);
                            return;
                        }
                        throw new IllegalArgumentException("file == null");
                    } finally {
                        Util.closeQuietly(r3);
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
            mediaType = MediaType.parse(mediaType + "; charset=utf-8");
        }
        return create(mediaType, str.getBytes(charset));
    }

    public static RequestBody create(final MediaType mediaType, final C04610h7 r2) {
        return new RequestBody() {
            /* class com.squareup.okhttp.RequestBody.AnonymousClass1 */

            @Override // com.squareup.okhttp.RequestBody
            public long contentLength() throws IOException {
                return (long) r2.A07();
            }

            @Override // com.squareup.okhttp.RequestBody
            public MediaType contentType() {
                return mediaType;
            }

            @Override // com.squareup.okhttp.RequestBody
            public void writeTo(AnonymousClass0Oe r2) throws IOException {
                r2.A8v(r2);
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
                public MediaType contentType() {
                    return mediaType;
                }

                @Override // com.squareup.okhttp.RequestBody
                public void writeTo(AnonymousClass0Oe r4) throws IOException {
                    r4.A8x(bArr, i, i2);
                }
            };
        }
        throw new NullPointerException("content == null");
    }
}
