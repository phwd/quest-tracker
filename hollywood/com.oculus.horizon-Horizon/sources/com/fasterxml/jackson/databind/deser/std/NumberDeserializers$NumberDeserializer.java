package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AnonymousClass0jg;
import X.AnonymousClass0m9;
import X.C05640lc;
import X.EnumC04010gf;
import X.EnumC04820ji;
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
    public final Object A0C(AbstractC04100gp r3, AbstractC04020gg r4, AnonymousClass0m9 r5) throws IOException, AnonymousClass0jg {
        int i = C05640lc.A00[r3.A0a().ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            return A09(r3, r4);
        }
        return r5.A0A(r3, r4);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Number A09(AbstractC04100gp r6, AbstractC04020gg r7) throws IOException, AnonymousClass0jg {
        EnumC04820ji A0a = r6.A0a();
        if (A0a == EnumC04820ji.VALUE_NUMBER_INT) {
            if (r7.A0I(EnumC04010gf.USE_BIG_INTEGER_FOR_INTS)) {
                return r6.A0U();
            }
            return r6.A0R();
        } else if (A0a == EnumC04820ji.VALUE_NUMBER_FLOAT) {
            if (r7.A0I(EnumC04010gf.USE_BIG_DECIMAL_FOR_FLOATS)) {
                return r6.A0T();
            }
            return Double.valueOf(r6.A0K());
        } else if (A0a == EnumC04820ji.VALUE_STRING) {
            String trim = r6.A0e().trim();
            try {
                if (trim.indexOf(46) >= 0) {
                    if (r7.A0I(EnumC04010gf.USE_BIG_DECIMAL_FOR_FLOATS)) {
                        return new BigDecimal(trim);
                    }
                    return new Double(trim);
                } else if (r7.A0I(EnumC04010gf.USE_BIG_INTEGER_FOR_INTS)) {
                    return new BigInteger(trim);
                } else {
                    long parseLong = Long.parseLong(trim);
                    if (parseLong > 2147483647L || parseLong < LibraryDBContract.VERSION_NOT_INSTALLED) {
                        return Long.valueOf(parseLong);
                    }
                    return Integer.valueOf((int) parseLong);
                }
            } catch (IllegalArgumentException unused) {
                throw null;
            } catch (Exception unused2) {
                throw null;
            }
        } else {
            throw r7.A07(this._valueClass, A0a);
        }
    }
}
