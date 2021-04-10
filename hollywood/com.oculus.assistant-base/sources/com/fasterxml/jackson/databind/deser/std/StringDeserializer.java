package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.NQ;
import X.NX;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
public final class StringDeserializer extends StdScalarDeserializer {
    public static final StringDeserializer A00 = new StringDeserializer();
    public static final long serialVersionUID = 1;

    public StringDeserializer() {
        super(String.class);
    }

    public static final String A00(StringDeserializer stringDeserializer, AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        String A0n = qiVar.A0n();
        if (A0n != null) {
            return A0n;
        }
        NX A0U = qiVar.A0U();
        if (A0U == NX.VALUE_EMBEDDED_OBJECT) {
            Object A0Z = qiVar.A0Z();
            if (A0Z == null) {
                return null;
            }
            if (A0Z instanceof byte[]) {
                return NQ.A01.A02((byte[]) A0Z, false);
            }
            return A0Z.toString();
        }
        throw qrVar.A0A(stringDeserializer._valueClass, A0U);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        return A00(this, qiVar, qrVar);
    }
}
