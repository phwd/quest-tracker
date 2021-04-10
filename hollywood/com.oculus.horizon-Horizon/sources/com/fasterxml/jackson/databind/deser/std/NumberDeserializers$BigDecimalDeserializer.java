package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AnonymousClass0jg;
import X.EnumC04820ji;
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
    public final BigDecimal A09(AbstractC04100gp r3, AbstractC04020gg r4) throws IOException, AnonymousClass0jg {
        EnumC04820ji A0a = r3.A0a();
        if (A0a == EnumC04820ji.VALUE_NUMBER_INT || A0a == EnumC04820ji.VALUE_NUMBER_FLOAT) {
            return r3.A0T();
        }
        if (A0a == EnumC04820ji.VALUE_STRING) {
            String trim = r3.A0e().trim();
            if (trim.length() == 0) {
                return null;
            }
            try {
                return new BigDecimal(trim);
            } catch (IllegalArgumentException unused) {
                throw null;
            } catch (Exception unused2) {
                throw null;
            }
        } else {
            throw r4.A07(this._valueClass, A0a);
        }
    }
}
