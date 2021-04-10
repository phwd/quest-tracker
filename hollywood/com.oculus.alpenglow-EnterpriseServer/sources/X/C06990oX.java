package X;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.std.StdArraySerializers$BooleanArraySerializer;
import com.fasterxml.jackson.databind.ser.std.StdArraySerializers$ByteArraySerializer;
import com.fasterxml.jackson.databind.ser.std.StdArraySerializers$CharArraySerializer;
import com.fasterxml.jackson.databind.ser.std.StdArraySerializers$DoubleArraySerializer;
import com.fasterxml.jackson.databind.ser.std.StdArraySerializers$FloatArraySerializer;
import com.fasterxml.jackson.databind.ser.std.StdArraySerializers$IntArraySerializer;
import com.fasterxml.jackson.databind.ser.std.StdArraySerializers$LongArraySerializer;
import com.fasterxml.jackson.databind.ser.std.StdArraySerializers$ShortArraySerializer;
import java.util.HashMap;

/* renamed from: X.0oX  reason: invalid class name and case insensitive filesystem */
public final class C06990oX {
    public static final HashMap<String, JsonSerializer<?>> A00;

    static {
        HashMap<String, JsonSerializer<?>> hashMap = new HashMap<>();
        A00 = hashMap;
        hashMap.put(boolean[].class.getName(), new StdArraySerializers$BooleanArraySerializer());
        HashMap<String, JsonSerializer<?>> hashMap2 = A00;
        hashMap2.put(byte[].class.getName(), new StdArraySerializers$ByteArraySerializer());
        hashMap2.put(char[].class.getName(), new StdArraySerializers$CharArraySerializer());
        hashMap2.put(short[].class.getName(), new StdArraySerializers$ShortArraySerializer());
        hashMap2.put(int[].class.getName(), new StdArraySerializers$IntArraySerializer());
        hashMap2.put(long[].class.getName(), new StdArraySerializers$LongArraySerializer());
        hashMap2.put(float[].class.getName(), new StdArraySerializers$FloatArraySerializer());
        hashMap2.put(double[].class.getName(), new StdArraySerializers$DoubleArraySerializer());
    }
}
