package com.squareup.okhttp;

import X.AnonymousClass006;
import X.AnonymousClass0HR;
import X.AnonymousClass0Od;
import X.C04530gy;
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

    public abstract AnonymousClass0Od source() throws IOException;

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
        return source().A5H();
    }

    /* JADX INFO: finally extract failed */
    public final byte[] bytes() throws IOException {
        String str;
        long contentLength = contentLength();
        if (contentLength <= 2147483647L) {
            AnonymousClass0Od source = source();
            try {
                byte[] A79 = source.A79();
                Util.closeQuietly(source);
                if (contentLength == -1 || contentLength == ((long) A79.length)) {
                    return A79;
                }
                str = "Content-Length and stream length disagree";
            } catch (Throwable th) {
                Util.closeQuietly(source);
                throw th;
            }
        } else {
            str = AnonymousClass006.A04("Cannot buffer entire body for content length: ", contentLength);
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

    public static ResponseBody create(final MediaType mediaType, final long j, final AnonymousClass0Od r4) {
        if (r4 != null) {
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
                public AnonymousClass0Od source() {
                    return r4;
                }
            };
        }
        throw new NullPointerException("source == null");
    }

    public static ResponseBody create(MediaType mediaType, String str) {
        String str2;
        Charset charset = Util.UTF_8;
        if (mediaType != null && (charset = mediaType.charset()) == null) {
            charset = Util.UTF_8;
            mediaType = MediaType.parse(mediaType + "; charset=utf-8");
        }
        AnonymousClass0HR r4 = new AnonymousClass0HR();
        int length = str.length();
        if (length < 0) {
            str2 = AnonymousClass006.A03("endIndex < beginIndex: ", length, " < ", 0);
        } else if (length > length) {
            str2 = AnonymousClass006.A03("endIndex > string.length: ", length, " > ", length);
        } else if (charset != null) {
            if (charset.equals(C04530gy.A00)) {
                r4.A0G(str, 0, length);
            } else {
                byte[] bytes = str.substring(0, length).getBytes(charset);
                r4.A0J(bytes, 0, bytes.length);
            }
            return create(mediaType, r4.A00, r4);
        } else {
            str2 = "charset == null";
        }
        throw new IllegalArgumentException(str2);
    }

    public static ResponseBody create(MediaType mediaType, byte[] bArr) {
        AnonymousClass0HR r2 = new AnonymousClass0HR();
        r2.A0I(bArr);
        return create(mediaType, (long) bArr.length, r2);
    }
}
