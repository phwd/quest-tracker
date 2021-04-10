package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers$BigDecimalDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers$BigIntegerDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers$BooleanDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers$ByteDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers$CharacterDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers$DoubleDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers$FloatDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers$IntegerDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers$LongDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers$NumberDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers$ShortDeserializer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;

/* renamed from: X.0q7  reason: invalid class name */
public final class AnonymousClass0q7 {
    public static final HashSet<String> A00 = new HashSet<>();

    static {
        int i = 0;
        Class[] clsArr = {Boolean.class, Byte.class, Short.class, Character.class, Integer.class, Long.class, Float.class, Double.class, Number.class, BigDecimal.class, BigInteger.class};
        do {
            A00.add(clsArr[i].getName());
            i++;
        } while (i < 11);
    }

    public static JsonDeserializer<?> A00(Class<?> cls, String str) {
        if (cls.isPrimitive()) {
            if (cls == Integer.TYPE) {
                return NumberDeserializers$IntegerDeserializer.A00;
            }
            if (cls == Boolean.TYPE) {
                return NumberDeserializers$BooleanDeserializer.A00;
            }
            if (cls == Long.TYPE) {
                return NumberDeserializers$LongDeserializer.A00;
            }
            if (cls == Double.TYPE) {
                return NumberDeserializers$DoubleDeserializer.A00;
            }
            if (cls == Character.TYPE) {
                return NumberDeserializers$CharacterDeserializer.A00;
            }
            if (cls == Byte.TYPE) {
                return NumberDeserializers$ByteDeserializer.A00;
            }
            if (cls == Short.TYPE) {
                return NumberDeserializers$ShortDeserializer.A00;
            }
            if (cls == Float.TYPE) {
                return NumberDeserializers$FloatDeserializer.A00;
            }
        } else if (!A00.contains(str)) {
            return null;
        } else {
            if (cls == Integer.class) {
                return NumberDeserializers$IntegerDeserializer.A01;
            }
            if (cls == Boolean.class) {
                return NumberDeserializers$BooleanDeserializer.A01;
            }
            if (cls == Long.class) {
                return NumberDeserializers$LongDeserializer.A01;
            }
            if (cls == Double.class) {
                return NumberDeserializers$DoubleDeserializer.A01;
            }
            if (cls == Character.class) {
                return NumberDeserializers$CharacterDeserializer.A01;
            }
            if (cls == Byte.class) {
                return NumberDeserializers$ByteDeserializer.A01;
            }
            if (cls == Short.class) {
                return NumberDeserializers$ShortDeserializer.A01;
            }
            if (cls == Float.class) {
                return NumberDeserializers$FloatDeserializer.A01;
            }
            if (cls == Number.class) {
                return NumberDeserializers$NumberDeserializer.A00;
            }
            if (cls == BigDecimal.class) {
                return NumberDeserializers$BigDecimalDeserializer.A00;
            }
            if (cls == BigInteger.class) {
                return NumberDeserializers$BigIntegerDeserializer.A00;
            }
        }
        throw new IllegalArgumentException(AnonymousClass006.A07("Internal error: can't find deserializer for ", cls.getName()));
    }
}
