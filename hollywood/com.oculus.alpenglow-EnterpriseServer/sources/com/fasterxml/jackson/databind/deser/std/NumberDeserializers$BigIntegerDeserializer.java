package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.AnonymousClass0aT;
import X.C05910ld;
import X.EnumC05930lf;
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
    public final BigInteger A09(AnonymousClass0aT r4, AbstractC02570aK r5) throws IOException, C05910ld {
        EnumC05930lf A0G = r4.A0G();
        if (A0G == EnumC05930lf.VALUE_NUMBER_INT) {
            switch (r4.A0J().intValue()) {
                case 0:
                case 1:
                    return BigInteger.valueOf(r4.A0B());
            }
        } else if (A0G == EnumC05930lf.VALUE_NUMBER_FLOAT) {
            return r4.A0R().toBigInteger();
        } else {
            if (A0G != EnumC05930lf.VALUE_STRING) {
                throw r5.A0C(this._valueClass, A0G);
            }
        }
        String trim = r4.A0P().trim();
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
