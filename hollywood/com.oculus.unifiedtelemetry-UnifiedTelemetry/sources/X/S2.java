package X;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.SerializerCache;
import java.util.HashMap;

public final class S2 {
    public HashMap<SerializerCache.TypeKey, JsonSerializer<Object>> A00 = new HashMap<>(64);
}
