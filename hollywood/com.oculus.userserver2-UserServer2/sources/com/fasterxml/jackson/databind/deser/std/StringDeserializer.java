package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0122Rd;
import X.AnonymousClass9p;
import X.AnonymousClass9r;
import X.AnonymousClass9z;
import X.B3;
import X.Rn;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class StringDeserializer extends StdScalarDeserializer<String> {
    public static final StringDeserializer A00 = new StringDeserializer();
    public static final long serialVersionUID = 1;

    private final String A00(Rn rn) throws IOException, AnonymousClass9r {
        String A09;
        B3 b3 = (B3) rn;
        AnonymousClass9p r1 = b3.A00;
        if ((r1 == AnonymousClass9p.VALUE_STRING || (r1 != null && r1 != AnonymousClass9p.VALUE_NULL && r1.isScalarValue())) && (A09 = b3.A09()) != null) {
            return A09;
        }
        if (b3.A00 == AnonymousClass9p.VALUE_EMBEDDED_OBJECT) {
            Object A07 = rn.A07();
            if (A07 == null) {
                return null;
            }
            if (A07 instanceof byte[]) {
                return AnonymousClass9z.A01.A00((byte[]) A07, false);
            }
            return A07.toString();
        }
        throw null;
    }

    public StringDeserializer() {
        super(String.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        return A00(rn);
    }
}
