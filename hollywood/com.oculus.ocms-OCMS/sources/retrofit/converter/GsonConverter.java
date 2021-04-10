package retrofit.converter;

import com.facebook.stetho.common.Utf8Charset;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import retrofit.mime.TypedOutput;

public class GsonConverter implements Converter {
    private String charset;
    private final Gson gson;

    public GsonConverter(Gson gson2) {
        this(gson2, Utf8Charset.NAME);
    }

    public GsonConverter(Gson gson2, String str) {
        this.gson = gson2;
        this.charset = str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x003f A[SYNTHETIC, Splitter:B:27:0x003f] */
    @Override // retrofit.converter.Converter
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object fromBody(retrofit.mime.TypedInput r4, java.lang.reflect.Type r5) throws retrofit.converter.ConversionException {
        /*
            r3 = this;
            java.lang.String r0 = r3.charset
            java.lang.String r1 = r4.mimeType()
            if (r1 == 0) goto L_0x0010
            java.lang.String r1 = r4.mimeType()
            java.lang.String r0 = retrofit.mime.MimeUtil.parseCharset(r1, r0)
        L_0x0010:
            r1 = 0
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0036, JsonParseException -> 0x002f }
            java.io.InputStream r4 = r4.in()     // Catch:{ IOException -> 0x0036, JsonParseException -> 0x002f }
            r2.<init>(r4, r0)     // Catch:{ IOException -> 0x0036, JsonParseException -> 0x002f }
            com.google.gson.Gson r4 = r3.gson     // Catch:{ IOException -> 0x002a, JsonParseException -> 0x0027, all -> 0x0024 }
            java.lang.Object r4 = r4.fromJson(r2, r5)     // Catch:{ IOException -> 0x002a, JsonParseException -> 0x0027, all -> 0x0024 }
            r2.close()     // Catch:{ IOException -> 0x0023 }
        L_0x0023:
            return r4
        L_0x0024:
            r4 = move-exception
            r1 = r2
            goto L_0x003d
        L_0x0027:
            r4 = move-exception
            r1 = r2
            goto L_0x0030
        L_0x002a:
            r4 = move-exception
            r1 = r2
            goto L_0x0037
        L_0x002d:
            r4 = move-exception
            goto L_0x003d
        L_0x002f:
            r4 = move-exception
        L_0x0030:
            retrofit.converter.ConversionException r5 = new retrofit.converter.ConversionException     // Catch:{ all -> 0x002d }
            r5.<init>(r4)     // Catch:{ all -> 0x002d }
            throw r5     // Catch:{ all -> 0x002d }
        L_0x0036:
            r4 = move-exception
        L_0x0037:
            retrofit.converter.ConversionException r5 = new retrofit.converter.ConversionException     // Catch:{ all -> 0x002d }
            r5.<init>(r4)     // Catch:{ all -> 0x002d }
            throw r5     // Catch:{ all -> 0x002d }
        L_0x003d:
            if (r1 == 0) goto L_0x0042
            r1.close()     // Catch:{ IOException -> 0x0042 }
        L_0x0042:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: retrofit.converter.GsonConverter.fromBody(retrofit.mime.TypedInput, java.lang.reflect.Type):java.lang.Object");
    }

    @Override // retrofit.converter.Converter
    public TypedOutput toBody(Object obj) {
        try {
            return new JsonTypedOutput(this.gson.toJson(obj).getBytes(this.charset), this.charset);
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    /* access modifiers changed from: private */
    public static class JsonTypedOutput implements TypedOutput {
        private final byte[] jsonBytes;
        private final String mimeType;

        @Override // retrofit.mime.TypedOutput
        public String fileName() {
            return null;
        }

        JsonTypedOutput(byte[] bArr, String str) {
            this.jsonBytes = bArr;
            this.mimeType = "application/json; charset=" + str;
        }

        @Override // retrofit.mime.TypedOutput
        public String mimeType() {
            return this.mimeType;
        }

        @Override // retrofit.mime.TypedOutput
        public long length() {
            return (long) this.jsonBytes.length;
        }

        @Override // retrofit.mime.TypedOutput
        public void writeTo(OutputStream outputStream) throws IOException {
            outputStream.write(this.jsonBytes);
        }
    }
}
