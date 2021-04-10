package com.facebook.stetho.server.http;

import com.facebook.stetho.common.Utf8Charset;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public abstract class LightHttpBody {
    public abstract int contentLength();

    public abstract String contentType();

    public abstract void writeTo(OutputStream outputStream) throws IOException;

    public static LightHttpBody create(String str, String str2) {
        try {
            return create(str.getBytes(Utf8Charset.NAME), str2);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static LightHttpBody create(final byte[] bArr, final String str) {
        return new LightHttpBody() {
            /* class com.facebook.stetho.server.http.LightHttpBody.AnonymousClass1 */

            @Override // com.facebook.stetho.server.http.LightHttpBody
            public String contentType() {
                return str;
            }

            @Override // com.facebook.stetho.server.http.LightHttpBody
            public int contentLength() {
                return bArr.length;
            }

            @Override // com.facebook.stetho.server.http.LightHttpBody
            public void writeTo(OutputStream outputStream) throws IOException {
                outputStream.write(bArr);
            }
        };
    }
}
