package retrofit.converter;

import X.AnonymousClass006;
import X.C08780ya;
import X.C08830yf;
import X.C09120zR;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import retrofit.mime.MimeUtil;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

public class GsonConverter implements Converter {
    public String charset;
    public final C08780ya gson;

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
            this.mimeType = AnonymousClass006.A05("application/json; charset=", str);
        }

        @Override // retrofit.mime.TypedOutput
        public String mimeType() {
            return this.mimeType;
        }
    }

    @Override // retrofit.converter.Converter
    public Object fromBody(TypedInput typedInput, Type type) throws ConversionException {
        Throwable th;
        IOException e;
        C08830yf e2;
        String str = this.charset;
        if (typedInput.mimeType() != null) {
            str = MimeUtil.parseCharset(typedInput.mimeType(), str);
        }
        InputStreamReader inputStreamReader = null;
        try {
            InputStreamReader inputStreamReader2 = new InputStreamReader(typedInput.in(), str);
            try {
                C08780ya r2 = this.gson;
                C09120zR r1 = new C09120zR(inputStreamReader2);
                r1.A07 = false;
                Object A00 = C08780ya.A00(r2, r1, type);
                C08780ya.A02(A00, r1);
                try {
                    inputStreamReader2.close();
                } catch (IOException unused) {
                }
                return A00;
            } catch (IOException e3) {
                e = e3;
                throw new ConversionException(e);
            } catch (C08830yf e4) {
                e2 = e4;
                inputStreamReader = inputStreamReader2;
                try {
                    throw new ConversionException(e2);
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                inputStreamReader = inputStreamReader2;
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (IOException unused2) {
                    }
                }
                throw th;
            }
        } catch (IOException e5) {
            e = e5;
            throw new ConversionException(e);
        } catch (C08830yf e6) {
            e2 = e6;
            throw new ConversionException(e2);
        }
    }

    @Override // retrofit.converter.Converter
    public TypedOutput toBody(Object obj) {
        try {
            return new JsonTypedOutput(this.gson.A06(obj).getBytes(this.charset), this.charset);
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public GsonConverter(C08780ya r2) {
        this(r2, "UTF-8");
    }

    public GsonConverter(C08780ya r1, String str) {
        this.gson = r1;
        this.charset = str;
    }
}
