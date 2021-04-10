package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.AbstractC04520qa;
import X.AnonymousClass0q6;
import X.C03620oC;
import X.EnumC02200iG;
import X.EnumC03640oE;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.oculus.library.database.contract.LibraryDBContract;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

@JacksonStdImpl
public final class NumberDeserializers$NumberDeserializer extends StdScalarDeserializer<Number> {
    public static final NumberDeserializers$NumberDeserializer A00 = new NumberDeserializers$NumberDeserializer();

    public NumberDeserializers$NumberDeserializer() {
        super(Number.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer, com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer
    public final Object A0B(AbstractC02280iQ r3, AbstractC02210iH r4, AbstractC04520qa r5) throws IOException, C03620oC {
        int i = AnonymousClass0q6.A00[r3.A0i().ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            return A0A(r3, r4);
        }
        return r5.A0A(r3, r4);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Number A0A(AbstractC02280iQ r7, AbstractC02210iH r8) throws IOException, C03620oC {
        EnumC03640oE A0i = r7.A0i();
        if (A0i == EnumC03640oE.VALUE_NUMBER_INT) {
            if (r8.A0P(EnumC02200iG.USE_BIG_INTEGER_FOR_INTS)) {
                return r7.A0b();
            }
            return r7.A0Y();
        } else if (A0i == EnumC03640oE.VALUE_NUMBER_FLOAT) {
            if (r8.A0P(EnumC02200iG.USE_BIG_DECIMAL_FOR_FLOATS)) {
                return r7.A0a();
            }
            return Double.valueOf(r7.A0R());
        } else if (A0i == EnumC03640oE.VALUE_STRING) {
            String trim = r7.A0m().trim();
            try {
                if (trim.indexOf(46) >= 0) {
                    if (r8.A0P(EnumC02200iG.USE_BIG_DECIMAL_FOR_FLOATS)) {
                        return new BigDecimal(trim);
                    }
                    return new Double(trim);
                } else if (r8.A0P(EnumC02200iG.USE_BIG_INTEGER_FOR_INTS)) {
                    return new BigInteger(trim);
                } else {
                    long parseLong = Long.parseLong(trim);
                    if (parseLong > 2147483647L || parseLong < LibraryDBContract.VERSION_NOT_INSTALLED) {
                        return Long.valueOf(parseLong);
                    }
                    return Integer.valueOf((int) parseLong);
                }
            } catch (IllegalArgumentException unused) {
                throw r8.A0G(trim, this._valueClass, "not a valid number");
            }
        } else {
            throw r8.A0C(this._valueClass, A0i);
        }
    }
}
