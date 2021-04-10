package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02640aV;
import X.AnonymousClass0a8;
import X.AnonymousClass0a9;
import X.C01570Jk;
import X.C02650aW;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

@JacksonStdImpl
public final class NumberSerializers$NumberSerializer extends StdScalarSerializer<Number> {
    public static final NumberSerializers$NumberSerializer A00 = new NumberSerializers$NumberSerializer();

    public NumberSerializers$NumberSerializer() {
        super(Number.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void A0D(Object obj, AbstractC02640aV r4, AnonymousClass0a8 r5) throws IOException, C02650aW {
        String obj2;
        Number number = (Number) obj;
        if (number instanceof BigDecimal) {
            if (!r5._config.A06(AnonymousClass0a9.WRITE_BIGDECIMAL_AS_PLAIN) || (r4 instanceof C01570Jk)) {
                r4.A0T((BigDecimal) number);
                return;
            }
            obj2 = ((BigDecimal) number).toPlainString();
        } else if (number instanceof BigInteger) {
            r4.A0U((BigInteger) number);
            return;
        } else {
            if (!(number instanceof Integer)) {
                if (number instanceof Long) {
                    r4.A0K(number.longValue());
                    return;
                } else if (number instanceof Double) {
                    r4.A0H(number.doubleValue());
                    return;
                } else if (number instanceof Float) {
                    r4.A0I(number.floatValue());
                    return;
                } else if (!(number instanceof Byte) && !(number instanceof Short)) {
                    obj2 = number.toString();
                }
            }
            r4.A0J(number.intValue());
            return;
        }
        r4.A0Q(obj2);
    }
}
