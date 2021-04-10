package com.squareup.okhttp;

import X.AnonymousClass06;
import X.AnonymousClass98;
import X.C0311cZ;
import X.KC;
import com.squareup.okhttp.internal.Util;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

public abstract class ResponseBody implements Closeable {
    public Reader reader;

    public abstract long contentLength() throws IOException;

    public abstract MediaType contentType();

    public abstract KC source() throws IOException;

    public final Reader charStream() throws IOException {
        Reader reader2 = this.reader;
        if (reader2 != null) {
            return reader2;
        }
        InputStreamReader inputStreamReader = new InputStreamReader(byteStream(), charset());
        this.reader = inputStreamReader;
        return inputStreamReader;
    }

    private Charset charset() {
        MediaType contentType = contentType();
        if (contentType != null) {
            return contentType.charset(Util.UTF_8);
        }
        return Util.UTF_8;
    }

    public final InputStream byteStream() throws IOException {
        return source().A37();
    }

    /* JADX INFO: finally extract failed */
    public final byte[] bytes() throws IOException {
        String str;
        long contentLength = contentLength();
        if (contentLength <= 2147483647L) {
            KC source = source();
            try {
                byte[] A4S = source.A4S();
                Util.closeQuietly(source);
                if (contentLength == -1 || contentLength == ((long) A4S.length)) {
                    return A4S;
                }
                str = "Content-Length and stream length disagree";
            } catch (Throwable th) {
                Util.closeQuietly(source);
                throw th;
            }
        } else {
            str = AnonymousClass06.A03("Cannot buffer entire body for content length: ", contentLength);
        }
        throw new IOException(str);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        source().close();
    }

    public final String string() throws IOException {
        return new String(bytes(), charset().name());
    }

    public static ResponseBody create(final MediaType mediaType, final long j, final KC kc) {
        if (kc != null) {
            return new ResponseBody() {
                /* class com.squareup.okhttp.ResponseBody.AnonymousClass1 */

                @Override // com.squareup.okhttp.ResponseBody
                public long contentLength() {
                    return j;
                }

                @Override // com.squareup.okhttp.ResponseBody
                public MediaType contentType() {
                    return mediaType;
                }

                @Override // com.squareup.okhttp.ResponseBody
                public KC source() {
                    return kc;
                }
            };
        }
        throw new NullPointerException("source == null");
    }

    public static ResponseBody create(MediaType mediaType, String str) {
        String str2;
        StringBuilder sb;
        Charset charset = Util.UTF_8;
        if (mediaType != null && (charset = mediaType.charset()) == null) {
            charset = Util.UTF_8;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(mediaType);
            sb2.append("; charset=utf-8");
            mediaType = MediaType.parse(sb2.toString());
        }
        AnonymousClass98 r4 = new AnonymousClass98();
        int length = str.length();
        if (length < 0) {
            sb = new StringBuilder("endIndex < beginIndex: ");
            sb.append(length);
            sb.append(" < ");
            sb.append(0);
        } else if (length > length) {
            sb = new StringBuilder("endIndex > string.length: ");
            sb.append(length);
            sb.append(" > ");
            sb.append(length);
        } else if (charset != null) {
            if (charset.equals(C0311cZ.A00)) {
                r4.A0H(str, 0, length);
            } else {
                byte[] bytes = str.substring(0, length).getBytes(charset);
                r4.A0K(bytes, 0, bytes.length);
            }
            return create(mediaType, r4.A00, r4);
        } else {
            str2 = "charset == null";
            throw new IllegalArgumentException(str2);
        }
        str2 = sb.toString();
        throw new IllegalArgumentException(str2);
    }

    public static ResponseBody create(MediaType mediaType, byte[] bArr) {
        AnonymousClass98 r2 = new AnonymousClass98();
        r2.A0J(bArr);
        return create(mediaType, (long) bArr.length, r2);
    }
}
