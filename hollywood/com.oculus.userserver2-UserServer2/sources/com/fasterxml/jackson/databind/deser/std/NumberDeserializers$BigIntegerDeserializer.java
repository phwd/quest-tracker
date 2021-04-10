package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0122Rd;
import X.AnonymousClass9p;
import X.AnonymousClass9r;
import X.B3;
import X.Rn;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.math.BigInteger;

@JacksonStdImpl
public class NumberDeserializers$BigIntegerDeserializer extends StdScalarDeserializer<BigInteger> {
    public static final NumberDeserializers$BigIntegerDeserializer A00 = new NumberDeserializers$BigIntegerDeserializer();

    private final BigInteger A00(Rn rn) throws IOException, AnonymousClass9r {
        AnonymousClass9p r1 = ((B3) rn).A00;
        if (r1 == AnonymousClass9p.VALUE_NUMBER_INT) {
            switch (rn.A05().intValue()) {
                case 0:
                case 1:
                    return BigInteger.valueOf(rn.A06().longValue());
            }
        } else if (r1 == AnonymousClass9p.VALUE_NUMBER_FLOAT) {
            return rn.A0A().toBigInteger();
        } else {
            if (r1 != AnonymousClass9p.VALUE_STRING) {
                throw null;
            }
        }
        String trim = rn.A09().trim();
        if (trim.length() == 0) {
            return null;
        }
        try {
            return new BigInteger(trim);
        } catch (IllegalArgumentException unused) {
            throw null;
        }
    }

    public NumberDeserializers$BigIntegerDeserializer() {
        super(BigInteger.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        return A00(rn);
    }
}
