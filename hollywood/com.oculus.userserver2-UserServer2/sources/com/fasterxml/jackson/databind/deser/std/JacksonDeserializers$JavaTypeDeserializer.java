package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0122Rd;
import X.AnonymousClass9p;
import X.AnonymousClass9r;
import X.B3;
import X.RZ;
import X.Rn;
import java.io.IOException;

public class JacksonDeserializers$JavaTypeDeserializer extends StdScalarDeserializer<RZ> {
    public static final JacksonDeserializers$JavaTypeDeserializer A00 = new JacksonDeserializers$JavaTypeDeserializer();

    private final RZ A00(Rn rn) throws IOException, AnonymousClass9r {
        Object A07;
        AnonymousClass9p r1 = ((B3) rn).A00;
        if (r1 == AnonymousClass9p.VALUE_STRING) {
            if (rn.A09().trim().length() == 0) {
                A07 = A02();
            } else {
                throw null;
            }
        } else if (r1 == AnonymousClass9p.VALUE_EMBEDDED_OBJECT) {
            A07 = rn.A07();
        } else {
            throw null;
        }
        return (RZ) A07;
    }

    public JacksonDeserializers$JavaTypeDeserializer() {
        super(RZ.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        return A00(rn);
    }
}
