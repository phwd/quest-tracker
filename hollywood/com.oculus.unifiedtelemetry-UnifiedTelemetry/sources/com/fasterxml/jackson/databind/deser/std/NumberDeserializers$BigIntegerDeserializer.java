package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.EnumC0470q2;
import X.q0;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.math.BigInteger;

@JacksonStdImpl
public class NumberDeserializers$BigIntegerDeserializer extends StdScalarDeserializer<BigInteger> {
    public static final NumberDeserializers$BigIntegerDeserializer A00 = new NumberDeserializers$BigIntegerDeserializer();

    public NumberDeserializers$BigIntegerDeserializer() {
        super(BigInteger.class);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final BigInteger A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        EnumC0470q2 A0Z = ww.A0Z();
        if (A0Z == EnumC0470q2.VALUE_NUMBER_INT) {
            switch (ww.A0P().intValue()) {
                case 0:
                case 1:
                    return BigInteger.valueOf(ww.A0M());
            }
        } else if (A0Z == EnumC0470q2.VALUE_NUMBER_FLOAT) {
            return ww.A0S().toBigInteger();
        } else {
            if (A0Z != EnumC0470q2.VALUE_STRING) {
                throw wn.A09(this._valueClass, A0Z);
            }
        }
        String trim = ww.A0d().trim();
        if (trim.length() == 0) {
            return null;
        }
        try {
            return new BigInteger(trim);
        } catch (IllegalArgumentException unused) {
            throw wn.A0D(trim, this._valueClass, "not a valid representation");
        }
    }
}
