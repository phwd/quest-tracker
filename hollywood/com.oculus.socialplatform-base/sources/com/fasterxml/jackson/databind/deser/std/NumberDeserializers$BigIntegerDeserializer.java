package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.C03620oC;
import X.EnumC03640oE;
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
    public final BigInteger A0A(AbstractC02280iQ r4, AbstractC02210iH r5) throws IOException, C03620oC {
        EnumC03640oE A0i = r4.A0i();
        if (A0i == EnumC03640oE.VALUE_NUMBER_INT) {
            switch (r4.A0X().intValue()) {
                case 0:
                case 1:
                    return BigInteger.valueOf(r4.A0U());
            }
        } else if (A0i == EnumC03640oE.VALUE_NUMBER_FLOAT) {
            return r4.A0a().toBigInteger();
        } else {
            if (A0i != EnumC03640oE.VALUE_STRING) {
                throw r5.A0C(this._valueClass, A0i);
            }
        }
        String trim = r4.A0m().trim();
        if (trim.length() == 0) {
            return null;
        }
        try {
            return new BigInteger(trim);
        } catch (IllegalArgumentException unused) {
            throw r5.A0G(trim, this._valueClass, "not a valid representation");
        }
    }
}
