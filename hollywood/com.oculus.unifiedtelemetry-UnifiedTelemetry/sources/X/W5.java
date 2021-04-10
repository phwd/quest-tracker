package X;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.std.BooleanSerializer;
import com.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers$DoubleSerializer;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers$FloatSerializer;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers$IntLikeSerializer;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers$IntegerSerializer;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers$LongSerializer;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers$NumberSerializer;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers$ShortSerializer;
import com.fasterxml.jackson.databind.ser.std.SqlDateSerializer;
import com.fasterxml.jackson.databind.ser.std.SqlTimeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdJdkSerializers$AtomicBooleanSerializer;
import com.fasterxml.jackson.databind.ser.std.StdJdkSerializers$AtomicIntegerSerializer;
import com.fasterxml.jackson.databind.ser.std.StdJdkSerializers$AtomicLongSerializer;
import com.fasterxml.jackson.databind.ser.std.StdJdkSerializers$AtomicReferenceSerializer;
import com.fasterxml.jackson.databind.ser.std.StdJdkSerializers$ClassSerializer;
import com.fasterxml.jackson.databind.ser.std.StdJdkSerializers$FileSerializer;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.ser.std.TokenBufferSerializer;
import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.AbstractMap;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

public abstract class W5 extends AbstractC0140Rw implements Serializable {
    public static final HashMap<String, JsonSerializer<?>> A00;
    public static final HashMap<String, Class<? extends JsonSerializer<?>>> A01 = new HashMap<>();
    public final C0332cz _factoryConfig = new C0332cz();

    static {
        AbstractMap abstractMap;
        HashMap<String, JsonSerializer<?>> hashMap = new HashMap<>();
        A00 = hashMap;
        hashMap.put(String.class.getName(), new StringSerializer());
        ToStringSerializer toStringSerializer = ToStringSerializer.A00;
        HashMap<String, JsonSerializer<?>> hashMap2 = A00;
        hashMap2.put(StringBuffer.class.getName(), toStringSerializer);
        hashMap2.put(StringBuilder.class.getName(), toStringSerializer);
        hashMap2.put(Character.class.getName(), toStringSerializer);
        hashMap2.put(Character.TYPE.getName(), toStringSerializer);
        NumberSerializers$IntegerSerializer numberSerializers$IntegerSerializer = new NumberSerializers$IntegerSerializer();
        hashMap2.put(Integer.class.getName(), numberSerializers$IntegerSerializer);
        hashMap2.put(Integer.TYPE.getName(), numberSerializers$IntegerSerializer);
        String name = Long.class.getName();
        NumberSerializers$LongSerializer numberSerializers$LongSerializer = NumberSerializers$LongSerializer.A00;
        hashMap2.put(name, numberSerializers$LongSerializer);
        hashMap2.put(Long.TYPE.getName(), numberSerializers$LongSerializer);
        String name2 = Byte.class.getName();
        NumberSerializers$IntLikeSerializer numberSerializers$IntLikeSerializer = NumberSerializers$IntLikeSerializer.A00;
        hashMap2.put(name2, numberSerializers$IntLikeSerializer);
        hashMap2.put(Byte.TYPE.getName(), numberSerializers$IntLikeSerializer);
        String name3 = Short.class.getName();
        NumberSerializers$ShortSerializer numberSerializers$ShortSerializer = NumberSerializers$ShortSerializer.A00;
        hashMap2.put(name3, numberSerializers$ShortSerializer);
        hashMap2.put(Short.TYPE.getName(), numberSerializers$ShortSerializer);
        String name4 = Float.class.getName();
        NumberSerializers$FloatSerializer numberSerializers$FloatSerializer = NumberSerializers$FloatSerializer.A00;
        hashMap2.put(name4, numberSerializers$FloatSerializer);
        hashMap2.put(Float.TYPE.getName(), numberSerializers$FloatSerializer);
        String name5 = Double.class.getName();
        NumberSerializers$DoubleSerializer numberSerializers$DoubleSerializer = NumberSerializers$DoubleSerializer.A00;
        hashMap2.put(name5, numberSerializers$DoubleSerializer);
        hashMap2.put(Double.TYPE.getName(), numberSerializers$DoubleSerializer);
        hashMap2.put(Boolean.TYPE.getName(), new BooleanSerializer());
        hashMap2.put(Boolean.class.getName(), new BooleanSerializer());
        NumberSerializers$NumberSerializer numberSerializers$NumberSerializer = new NumberSerializers$NumberSerializer();
        hashMap2.put(BigInteger.class.getName(), numberSerializers$NumberSerializer);
        hashMap2.put(BigDecimal.class.getName(), numberSerializers$NumberSerializer);
        hashMap2.put(Calendar.class.getName(), CalendarSerializer.A00);
        DateSerializer dateSerializer = DateSerializer.A00;
        hashMap2.put(Date.class.getName(), dateSerializer);
        hashMap2.put(Timestamp.class.getName(), dateSerializer);
        HashMap<String, Class<? extends JsonSerializer<?>>> hashMap3 = A01;
        hashMap3.put(java.sql.Date.class.getName(), SqlDateSerializer.class);
        hashMap3.put(Time.class.getName(), SqlTimeSerializer.class);
        HashMap hashMap4 = new HashMap();
        hashMap4.put(URL.class, toStringSerializer);
        hashMap4.put(URI.class, toStringSerializer);
        hashMap4.put(Currency.class, toStringSerializer);
        hashMap4.put(UUID.class, toStringSerializer);
        hashMap4.put(Pattern.class, toStringSerializer);
        hashMap4.put(Locale.class, toStringSerializer);
        hashMap4.put(Locale.class, toStringSerializer);
        hashMap4.put(AtomicReference.class, StdJdkSerializers$AtomicReferenceSerializer.class);
        hashMap4.put(AtomicBoolean.class, StdJdkSerializers$AtomicBooleanSerializer.class);
        hashMap4.put(AtomicInteger.class, StdJdkSerializers$AtomicIntegerSerializer.class);
        hashMap4.put(AtomicLong.class, StdJdkSerializers$AtomicLongSerializer.class);
        hashMap4.put(File.class, StdJdkSerializers$FileSerializer.class);
        hashMap4.put(Class.class, StdJdkSerializers$ClassSerializer.class);
        hashMap4.put(Void.TYPE, NullSerializer.class);
        for (Map.Entry entry : hashMap4.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof JsonSerializer) {
                abstractMap = A00;
            } else if (value instanceof Class) {
                abstractMap = A01;
            } else {
                throw new IllegalStateException(AnonymousClass06.A04("Internal error: unrecognized value of type ", entry.getClass().getName()));
            }
            abstractMap.put(((Class) entry.getKey()).getName(), value);
        }
        A01.put(Br.class.getName(), TokenBufferSerializer.class);
    }
}
