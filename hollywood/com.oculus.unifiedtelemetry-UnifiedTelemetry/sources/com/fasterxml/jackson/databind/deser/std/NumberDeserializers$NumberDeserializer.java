package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.EnumC0225Wm;
import X.EnumC0470q2;
import X.V4;
import X.VZ;
import X.q0;
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
    public final Object A0C(AbstractC0232Ww ww, AbstractC0226Wn wn, V4 v4) throws IOException, q0 {
        int i = VZ.A00[ww.A0Z().ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            return A09(ww, wn);
        }
        return v4.A0A(ww, wn);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Number A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        EnumC0470q2 A0Z = ww.A0Z();
        if (A0Z == EnumC0470q2.VALUE_NUMBER_INT) {
            if (wn.A0L(EnumC0225Wm.USE_BIG_INTEGER_FOR_INTS)) {
                return ww.A0T();
            }
            return ww.A0Q();
        } else if (A0Z == EnumC0470q2.VALUE_NUMBER_FLOAT) {
            if (wn.A0L(EnumC0225Wm.USE_BIG_DECIMAL_FOR_FLOATS)) {
                return ww.A0S();
            }
            return Double.valueOf(ww.A0J());
        } else if (A0Z == EnumC0470q2.VALUE_STRING) {
            String trim = ww.A0d().trim();
            try {
                if (trim.indexOf(46) >= 0) {
                    if (wn.A0L(EnumC0225Wm.USE_BIG_DECIMAL_FOR_FLOATS)) {
                        return new BigDecimal(trim);
                    }
                    return new Double(trim);
                } else if (wn.A0L(EnumC0225Wm.USE_BIG_INTEGER_FOR_INTS)) {
                    return new BigInteger(trim);
                } else {
                    long parseLong = Long.parseLong(trim);
                    if (parseLong > 2147483647L || parseLong < LibraryDBContract.VERSION_NOT_INSTALLED) {
                        return Long.valueOf(parseLong);
                    }
                    return Integer.valueOf((int) parseLong);
                }
            } catch (IllegalArgumentException unused) {
                throw wn.A0D(trim, this._valueClass, "not a valid number");
            }
        } else {
            throw wn.A09(this._valueClass, A0Z);
        }
    }
}
