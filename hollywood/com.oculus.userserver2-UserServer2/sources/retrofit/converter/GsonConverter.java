package retrofit.converter;

import X.AnonymousClass06;
import X.C0240hl;
import X.C0246hr;
import com.facebook.acra.LogCatCollector;
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
    public final C0246hr gson;

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
            this.mimeType = AnonymousClass06.A03("application/json; charset=", str);
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
        C0240hl e2;
        String str = this.charset;
        if (typedInput.mimeType() != null) {
            str = MimeUtil.parseCharset(typedInput.mimeType(), str);
        }
        InputStreamReader inputStreamReader = null;
        try {
            InputStreamReader inputStreamReader2 = new InputStreamReader(typedInput.in(), str);
            try {
                Object A04 = this.gson.A04(inputStreamReader2, type);
                try {
                    inputStreamReader2.close();
                } catch (IOException unused) {
                }
                return A04;
            } catch (IOException e3) {
                e = e3;
                throw new ConversionException(e);
            } catch (C0240hl e4) {
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
        } catch (C0240hl e6) {
            e2 = e6;
            throw new ConversionException(e2);
        }
    }

    @Override // retrofit.converter.Converter
    public TypedOutput toBody(Object obj) {
        try {
            return new JsonTypedOutput(this.gson.A05(obj).getBytes(this.charset), this.charset);
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public GsonConverter(C0246hr hrVar) {
        this(hrVar, LogCatCollector.UTF_8_ENCODING);
    }

    public GsonConverter(C0246hr hrVar, String str) {
        this.gson = hrVar;
        this.charset = str;
    }
}
