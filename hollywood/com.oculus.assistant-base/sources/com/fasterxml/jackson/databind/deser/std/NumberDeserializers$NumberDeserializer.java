package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.EnumC1023qs;
import X.NX;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.math.BigDecimal;
import java.math.BigInteger;

@JacksonStdImpl
public final class NumberDeserializers$NumberDeserializer extends StdScalarDeserializer {
    public static final NumberDeserializers$NumberDeserializer A00 = new NumberDeserializers$NumberDeserializer();

    public NumberDeserializers$NumberDeserializer() {
        super(Number.class);
    }

    public static final Number A00(NumberDeserializers$NumberDeserializer numberDeserializers$NumberDeserializer, AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        NX A0U = qiVar.A0U();
        if (A0U == NX.VALUE_NUMBER_INT) {
            if (qrVar.A0O(EnumC1023qs.USE_BIG_INTEGER_FOR_INTS)) {
                return qiVar.A0d();
            }
            return qiVar.A0Y();
        } else if (A0U == NX.VALUE_NUMBER_FLOAT) {
            if (qrVar.A0O(EnumC1023qs.USE_BIG_DECIMAL_FOR_FLOATS)) {
                return qiVar.A0c();
            }
            return Double.valueOf(qiVar.A0F());
        } else if (A0U == NX.VALUE_STRING) {
            String trim = qiVar.A0p().trim();
            try {
                if (trim.indexOf(46) >= 0) {
                    if (qrVar.A0O(EnumC1023qs.USE_BIG_DECIMAL_FOR_FLOATS)) {
                        return new BigDecimal(trim);
                    }
                    return new Double(trim);
                } else if (qrVar.A0O(EnumC1023qs.USE_BIG_INTEGER_FOR_INTS)) {
                    return new BigInteger(trim);
                } else {
                    long parseLong = Long.parseLong(trim);
                    if (parseLong > 2147483647L || parseLong < -2147483648L) {
                        return Long.valueOf(parseLong);
                    }
                    return Integer.valueOf((int) parseLong);
                }
            } catch (IllegalArgumentException unused) {
                qrVar.A0L(numberDeserializers$NumberDeserializer._valueClass);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        } else {
            throw qrVar.A0A(numberDeserializers$NumberDeserializer._valueClass, A0U);
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        return A00(this, qiVar, qrVar);
    }
}
