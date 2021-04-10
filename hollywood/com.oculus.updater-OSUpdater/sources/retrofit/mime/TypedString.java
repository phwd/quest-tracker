package retrofit.mime;

import java.io.UnsupportedEncodingException;

public class TypedString extends TypedByteArray {
    public TypedString(String str) {
        super("text/plain; charset=UTF-8", convertToBytes(str));
    }

    private static byte[] convertToBytes(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // retrofit.mime.TypedByteArray
    public String toString() {
        try {
            return "TypedString[" + new String(getBytes(), "UTF-8") + "]";
        } catch (UnsupportedEncodingException unused) {
            throw new AssertionError("Must be able to decode UTF-8");
        }
    }
}
