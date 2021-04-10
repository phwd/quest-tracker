package com.squareup.okhttp;

import X.Dh;
import X.Du;
import X.WE;
import X.WM;
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

    public abstract void writeTo(Du du) throws IOException;

    public static RequestBody create(final MediaType mediaType, final File file) {
        if (file != null) {
            return new RequestBody() {
                /* class com.squareup.okhttp.RequestBody.AnonymousClass3 */

                @Override // com.squareup.okhttp.RequestBody
                public void writeTo(Du du) throws IOException {
                    Dh dh = null;
                    try {
                        File file = file;
                        if (file != null) {
                            dh = new Dh(new WE(), new FileInputStream(file));
                            du.A3x(dh);
                            return;
                        }
                        throw new IllegalArgumentException("file == null");
                    } finally {
                        Util.closeQuietly(dh);
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

    public static RequestBody create(final MediaType mediaType, final WM wm) {
        return new RequestBody() {
            /* class com.squareup.okhttp.RequestBody.AnonymousClass1 */

            @Override // com.squareup.okhttp.RequestBody
            public long contentLength() throws IOException {
                return (long) wm.A07();
            }

            @Override // com.squareup.okhttp.RequestBody
            public void writeTo(Du du) throws IOException {
                du.A3u(wm);
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
                public void writeTo(Du du) throws IOException {
                    du.A3w(bArr, i, i2);
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
