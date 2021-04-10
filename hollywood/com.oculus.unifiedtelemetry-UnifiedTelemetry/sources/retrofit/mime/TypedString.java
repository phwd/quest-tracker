package retrofit.mime;

import X.AnonymousClass06;
import com.facebook.acra.LogCatCollector;
import java.io.UnsupportedEncodingException;

public class TypedString extends TypedByteArray {
    public static byte[] convertToBytes(String str) {
        try {
            return str.getBytes(LogCatCollector.UTF_8_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // retrofit.mime.TypedByteArray
    public String toString() {
        try {
            return AnonymousClass06.A05("TypedString[", new String(this.bytes, LogCatCollector.UTF_8_ENCODING), "]");
        } catch (UnsupportedEncodingException unused) {
            throw new AssertionError("Must be able to decode UTF-8");
        }
    }

    public TypedString(String str) {
        super("text/plain; charset=UTF-8", convertToBytes(str));
    }
}
