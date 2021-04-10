package retrofit.mime;

import X.AnonymousClass006;
import java.io.UnsupportedEncodingException;

public class TypedString extends TypedByteArray {
    public static byte[] convertToBytes(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // retrofit.mime.TypedByteArray
    public String toString() {
        try {
            return AnonymousClass006.A07("TypedString[", new String(this.bytes, "UTF-8"), "]");
        } catch (UnsupportedEncodingException unused) {
            throw new AssertionError("Must be able to decode UTF-8");
        }
    }

    public TypedString(String str) {
        super("text/plain; charset=UTF-8", convertToBytes(str));
    }
}
