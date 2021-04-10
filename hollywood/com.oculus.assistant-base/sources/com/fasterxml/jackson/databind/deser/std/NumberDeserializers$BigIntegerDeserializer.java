package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.NX;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.math.BigInteger;

@JacksonStdImpl
public class NumberDeserializers$BigIntegerDeserializer extends StdScalarDeserializer {
    public static final NumberDeserializers$BigIntegerDeserializer A00 = new NumberDeserializers$BigIntegerDeserializer();

    public NumberDeserializers$BigIntegerDeserializer() {
        super(BigInteger.class);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final BigInteger A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        NX A0U = qiVar.A0U();
        if (A0U == NX.VALUE_NUMBER_INT) {
            switch (qiVar.A0X().intValue()) {
                case 0:
                case 1:
                    return BigInteger.valueOf(qiVar.A0O());
            }
        } else if (A0U == NX.VALUE_NUMBER_FLOAT) {
            return qiVar.A0c().toBigInteger();
        } else {
            if (A0U != NX.VALUE_STRING) {
                throw qrVar.A0A(this._valueClass, A0U);
            }
        }
        String trim = qiVar.A0p().trim();
        if (trim.length() == 0) {
            return null;
        }
        try {
            return new BigInteger(trim);
        } catch (IllegalArgumentException unused) {
            qrVar.A0L(this._valueClass);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }
}
