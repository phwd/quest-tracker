package retrofit.converter;

import X.AnonymousClass006;
import X.AnonymousClass13N;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import retrofit.mime.TypedOutput;

public class GsonConverter implements Converter {
    public String charset;
    public final AnonymousClass13N gson;

    public static class JsonTypedOutput implements TypedOutput {
        public final byte[] jsonBytes;
        public final String mimeType;

        @Override // retrofit.mime.TypedOutput
        public String fileName() {
            return null;
        }

        @Override // retrofit.mime.TypedOutput
        public long length() {
            return (long) this.jsonBytes.length;
        }

        @Override // retrofit.mime.TypedOutput
        public void writeTo(OutputStream outputStream) throws IOException {
            outputStream.write(this.jsonBytes);
        }

        public JsonTypedOutput(byte[] bArr, String str) {
            this.jsonBytes = bArr;
            this.mimeType = AnonymousClass006.A07("application/json; charset=", str);
        }

        @Override // retrofit.mime.TypedOutput
        public String mimeType() {
            return this.mimeType;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x003e A[SYNTHETIC, Splitter:B:27:0x003e] */
    @Override // retrofit.converter.Converter
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object fromBody(retrofit.mime.TypedInput r5, java.lang.reflect.Type r6) throws retrofit.converter.ConversionException {
        /*
            r4 = this;
            java.lang.String r1 = r4.charset
            java.lang.String r0 = r5.mimeType()
            if (r0 == 0) goto L_0x0010
            java.lang.String r0 = r5.mimeType()
            java.lang.String r1 = retrofit.mime.MimeUtil.parseCharset(r0, r1)
        L_0x0010:
            r3 = 0
            java.io.InputStream r0 = r5.in()     // Catch:{ IOException -> 0x0031, 13S -> 0x002a }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0031, 13S -> 0x002a }
            r2.<init>(r0, r1)     // Catch:{ IOException -> 0x0031, 13S -> 0x002a }
            X.13N r0 = r4.gson     // Catch:{ IOException -> 0x0027, 13S -> 0x0024, all -> 0x003a }
            java.lang.Object r0 = r0.A04(r2, r6)     // Catch:{ IOException -> 0x0027, 13S -> 0x0024, all -> 0x003a }
            r2.close()     // Catch:{ IOException -> 0x0023 }
        L_0x0023:
            return r0
        L_0x0024:
            r1 = move-exception
            r3 = r2
            goto L_0x002b
        L_0x0027:
            r1 = move-exception
            r3 = r2
            goto L_0x0032
        L_0x002a:
            r1 = move-exception
        L_0x002b:
            retrofit.converter.ConversionException r0 = new retrofit.converter.ConversionException     // Catch:{ all -> 0x0038 }
            r0.<init>(r1)     // Catch:{ all -> 0x0038 }
            throw r0     // Catch:{ all -> 0x0038 }
        L_0x0031:
            r1 = move-exception
        L_0x0032:
            retrofit.converter.ConversionException r0 = new retrofit.converter.ConversionException
            r0.<init>(r1)
            throw r0
        L_0x0038:
            r0 = move-exception
            goto L_0x003c
        L_0x003a:
            r0 = move-exception
            r3 = r2
        L_0x003c:
            if (r3 == 0) goto L_0x0041
            r3.close()     // Catch:{ IOException -> 0x0041 }
        L_0x0041:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: retrofit.converter.GsonConverter.fromBody(retrofit.mime.TypedInput, java.lang.reflect.Type):java.lang.Object");
    }

    @Override // retrofit.converter.Converter
    public TypedOutput toBody(Object obj) {
        try {
            return new JsonTypedOutput(this.gson.A06(obj).getBytes(this.charset), this.charset);
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public GsonConverter(AnonymousClass13N r2) {
        this(r2, "UTF-8");
    }

    public GsonConverter(AnonymousClass13N r1, String str) {
        this.gson = r1;
        this.charset = str;
    }
}
