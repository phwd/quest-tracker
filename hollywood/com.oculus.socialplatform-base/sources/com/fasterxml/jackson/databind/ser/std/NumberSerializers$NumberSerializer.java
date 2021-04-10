package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC01900ha;
import X.AbstractC02120i3;
import X.AbstractC02190iF;
import X.AbstractC02300iS;
import X.AnonymousClass0OD;
import X.AnonymousClass0i4;
import X.C02180iD;
import X.C02310iT;
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
    public final void acceptJsonFormatVisitor(AbstractC01900ha r3, AbstractC02190iF r4) throws C02180iD {
        throw new NullPointerException("expectNumberFormat");
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void serialize(Object obj, AbstractC02300iS r4, AbstractC02120i3 r5) throws IOException, C02310iT {
        String obj2;
        Number number = (Number) obj;
        if (number instanceof BigDecimal) {
            if (!r5._config.A06(AnonymousClass0i4.WRITE_BIGDECIMAL_AS_PLAIN) || (r4 instanceof AnonymousClass0OD)) {
                r4.A0V((BigDecimal) number);
                return;
            }
            obj2 = ((BigDecimal) number).toPlainString();
        } else if (number instanceof BigInteger) {
            r4.A0W((BigInteger) number);
            return;
        } else {
            if (!(number instanceof Integer)) {
                if (number instanceof Long) {
                    r4.A0N(number.longValue());
                    return;
                } else if (number instanceof Double) {
                    r4.A0K(number.doubleValue());
                    return;
                } else if (number instanceof Float) {
                    r4.A0L(number.floatValue());
                    return;
                } else if (!(number instanceof Byte) && !(number instanceof Short)) {
                    obj2 = number.toString();
                }
            }
            r4.A0M(number.intValue());
            return;
        }
        r4.A0S(obj2);
    }
}
