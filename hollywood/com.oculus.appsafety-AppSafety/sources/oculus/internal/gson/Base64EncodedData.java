package oculus.internal.gson;

import android.util.Base64;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class Base64EncodedData {
    private byte[] bytes;

    private Base64EncodedData(byte[] bytes2) {
        this.bytes = bytes2;
    }

    public static Base64EncodedData fromRawBytes(byte[] bytes2) {
        return new Base64EncodedData(bytes2);
    }

    public static Base64EncodedData fromEncodedString(String string) {
        return new Base64EncodedData(toBytes(string));
    }

    public static byte[] toBytes(String string) {
        return Base64.decode(string, 2);
    }

    public byte[] getRawBytes() {
        return this.bytes;
    }

    public String getEncodedString() {
        return getEncodedString(this.bytes);
    }

    public static String getEncodedString(byte[] bytes2) {
        return getEncodedString(bytes2, 2);
    }

    public static String getEncodedString(byte[] bytes2, int flags) {
        return Base64.encodeToString(bytes2, flags);
    }

    public static class GsonTypeAdapter implements JsonSerializer<byte[]>, JsonDeserializer<byte[]> {
        public JsonElement serialize(byte[] src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(Base64EncodedData.getEncodedString(src));
        }

        @Override // com.google.gson.JsonDeserializer
        public byte[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
            return Base64EncodedData.toBytes(json.getAsString());
        }
    }
}
