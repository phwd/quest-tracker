package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AnonymousClass0jg;
import X.EnumC04820ji;
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
    public final BigInteger A09(AbstractC04100gp r3, AbstractC04020gg r4) throws IOException, AnonymousClass0jg {
        EnumC04820ji A0a = r3.A0a();
        if (A0a == EnumC04820ji.VALUE_NUMBER_INT) {
            switch (r3.A0Q().intValue()) {
                case 0:
                case 1:
                    return BigInteger.valueOf(r3.A0N());
            }
        } else if (A0a == EnumC04820ji.VALUE_NUMBER_FLOAT) {
            return r3.A0T().toBigInteger();
        } else {
            if (A0a != EnumC04820ji.VALUE_STRING) {
                throw r4.A07(this._valueClass, A0a);
            }
        }
        String trim = r3.A0e().trim();
        if (trim.length() == 0) {
            return null;
        }
        try {
            return new BigInteger(trim);
        } catch (IllegalArgumentException unused) {
            throw null;
        } catch (Exception unused2) {
            throw null;
        }
    }
}
