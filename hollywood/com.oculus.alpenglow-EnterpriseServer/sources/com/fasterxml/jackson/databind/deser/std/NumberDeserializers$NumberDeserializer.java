package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.AnonymousClass0aT;
import X.AnonymousClass0o3;
import X.C05910ld;
import X.C06610nX;
import X.EnumC02560aJ;
import X.EnumC05930lf;
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
    public final Object A0C(AnonymousClass0aT r3, AbstractC02570aK r4, AnonymousClass0o3 r5) throws IOException, C05910ld {
        int i = C06610nX.A00[r3.A0G().ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            return A09(r3, r4);
        }
        return r5.A0A(r3, r4);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Number A09(AnonymousClass0aT r7, AbstractC02570aK r8) throws IOException, C05910ld {
        EnumC05930lf A0G = r7.A0G();
        if (A0G == EnumC05930lf.VALUE_NUMBER_INT) {
            if (r8.A0O(EnumC02560aJ.USE_BIG_INTEGER_FOR_INTS)) {
                return r7.A0S();
            }
            return r7.A0K();
        } else if (A0G == EnumC05930lf.VALUE_NUMBER_FLOAT) {
            if (r8.A0O(EnumC02560aJ.USE_BIG_DECIMAL_FOR_FLOATS)) {
                return r7.A0R();
            }
            return Double.valueOf(r7.A03());
        } else if (A0G == EnumC05930lf.VALUE_STRING) {
            String trim = r7.A0P().trim();
            try {
                if (trim.indexOf(46) >= 0) {
                    if (r8.A0O(EnumC02560aJ.USE_BIG_DECIMAL_FOR_FLOATS)) {
                        return new BigDecimal(trim);
                    }
                    return new Double(trim);
                } else if (r8.A0O(EnumC02560aJ.USE_BIG_INTEGER_FOR_INTS)) {
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
            throw r8.A0C(this._valueClass, A0G);
        }
    }
}
