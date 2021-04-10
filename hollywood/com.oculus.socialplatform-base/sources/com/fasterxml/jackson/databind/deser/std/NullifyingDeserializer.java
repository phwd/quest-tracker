package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.AbstractC04520qa;
import X.AnonymousClass0q5;
import X.C03620oC;
import java.io.IOException;

public class NullifyingDeserializer extends StdDeserializer<Object> {
    public static final NullifyingDeserializer A00 = new NullifyingDeserializer();
    public static final long serialVersionUID = 1;

    public NullifyingDeserializer() {
        super(Object.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0A(AbstractC02280iQ r2, AbstractC02210iH r3) throws IOException, C03620oC {
        r2.A0h();
        return null;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0B(AbstractC02280iQ r3, AbstractC02210iH r4, AbstractC04520qa r5) throws IOException, C03620oC {
        int i = AnonymousClass0q5.A00[r3.A0i().ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            return r5.A07(r3, r4);
        }
        return null;
    }
}
