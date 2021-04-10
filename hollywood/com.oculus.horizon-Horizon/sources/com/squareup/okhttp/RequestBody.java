package com.squareup.okhttp;

import X.AnonymousClass0Lo;
import X.AnonymousClass0Lx;
import X.C07620v5;
import X.C07700vD;
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

    public abstract void writeTo(AnonymousClass0Lx v) throws IOException;

    public static RequestBody create(final MediaType mediaType, final File file) {
        if (file != null) {
            return new RequestBody() {
                /* class com.squareup.okhttp.RequestBody.AnonymousClass3 */

                @Override // com.squareup.okhttp.RequestBody
                public void writeTo(AnonymousClass0Lx r5) throws IOException {
                    AnonymousClass0Lo r3 = null;
                    try {
                        File file = file;
                        if (file != null) {
                            r3 = new AnonymousClass0Lo(new C07620v5(), new FileInputStream(file));
                            r5.AA9(r3);
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
            StringBuilder sb = new StringBuilder();
            sb.append(mediaType);
            sb.append("; charset=utf-8");
            mediaType = MediaType.parse(sb.toString());
        }
        return create(mediaType, str.getBytes(charset));
    }

    public static RequestBody create(final MediaType mediaType, final C07700vD r2) {
        return new RequestBody() {
            /* class com.squareup.okhttp.RequestBody.AnonymousClass1 */

            @Override // com.squareup.okhttp.RequestBody
            public long contentLength() throws IOException {
                return (long) r2.A07();
            }

            @Override // com.squareup.okhttp.RequestBody
            public void writeTo(AnonymousClass0Lx r2) throws IOException {
                r2.AA6(r2);
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
                public void writeTo(AnonymousClass0Lx r4) throws IOException {
                    r4.AA8(bArr, i, i2);
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
