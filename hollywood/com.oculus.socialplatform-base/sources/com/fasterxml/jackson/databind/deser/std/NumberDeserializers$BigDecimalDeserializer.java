package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.C03620oC;
import X.EnumC03640oE;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.math.BigDecimal;

@JacksonStdImpl
public class NumberDeserializers$BigDecimalDeserializer extends StdScalarDeserializer<BigDecimal> {
    public static final NumberDeserializers$BigDecimalDeserializer A00 = new NumberDeserializers$BigDecimalDeserializer();

    public NumberDeserializers$BigDecimalDeserializer() {
        super(BigDecimal.class);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final BigDecimal A0A(AbstractC02280iQ r4, AbstractC02210iH r5) throws IOException, C03620oC {
        EnumC03640oE A0i = r4.A0i();
        if (A0i == EnumC03640oE.VALUE_NUMBER_INT || A0i == EnumC03640oE.VALUE_NUMBER_FLOAT) {
            return r4.A0a();
        }
        if (A0i == EnumC03640oE.VALUE_STRING) {
            String trim = r4.A0m().trim();
            if (trim.length() == 0) {
                return null;
            }
            try {
                return new BigDecimal(trim);
            } catch (IllegalArgumentException unused) {
                throw r5.A0G(trim, this._valueClass, "not a valid representation");
            }
        } else {
            throw r5.A0C(this._valueClass, A0i);
        }
    }
}
