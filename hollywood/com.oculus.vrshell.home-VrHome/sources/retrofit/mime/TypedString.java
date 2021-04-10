package retrofit.mime;

import java.io.UnsupportedEncodingException;

public class TypedString extends TypedByteArray {
    public TypedString(String string) {
        super("text/plain; charset=UTF-8", convertToBytes(string));
    }

    private static byte[] convertToBytes(String string) {
        try {
            return string.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // retrofit.mime.TypedByteArray
    public String toString() {
        try {
            return "TypedString[" + new String(getBytes(), "UTF-8") + "]";
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError("Must be able to decode UTF-8");
        }
    }
}
