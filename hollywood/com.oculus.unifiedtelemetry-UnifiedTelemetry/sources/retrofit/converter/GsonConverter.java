package retrofit.converter;

import X.AnonymousClass06;
import X.HY;
import X.M7;
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
    public final HY gson;

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
            this.mimeType = AnonymousClass06.A04("application/json; charset=", str);
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
        M7 e2;
        String str = this.charset;
        if (typedInput.mimeType() != null) {
            str = MimeUtil.parseCharset(typedInput.mimeType(), str);
        }
        InputStreamReader inputStreamReader = null;
        try {
            InputStreamReader inputStreamReader2 = new InputStreamReader(typedInput.in(), str);
            try {
                Object A05 = this.gson.A05(inputStreamReader2, type);
                try {
                    inputStreamReader2.close();
                } catch (IOException unused) {
                }
                return A05;
            } catch (IOException e3) {
                e = e3;
                throw new ConversionException(e);
            } catch (M7 e4) {
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
        } catch (M7 e6) {
            e2 = e6;
            throw new ConversionException(e2);
        }
    }

    @Override // retrofit.converter.Converter
    public TypedOutput toBody(Object obj) {
        try {
            return new JsonTypedOutput(this.gson.A07(obj).getBytes(this.charset), this.charset);
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public GsonConverter(HY hy) {
        this(hy, LogCatCollector.UTF_8_ENCODING);
    }

    public GsonConverter(HY hy, String str) {
        this.gson = hy;
        this.charset = str;
    }
}
