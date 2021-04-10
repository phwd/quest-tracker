package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AnonymousClass0jg;
import X.AnonymousClass0m9;
import X.C05630lb;
import java.io.IOException;

public final class NullifyingDeserializer extends StdDeserializer<Object> {
    public static final NullifyingDeserializer A00 = new NullifyingDeserializer();
    public static final long serialVersionUID = 1;

    public NullifyingDeserializer() {
        super(Object.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A09(AbstractC04100gp r2, AbstractC04020gg r3) throws IOException, AnonymousClass0jg {
        r2.A0Z();
        return null;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC04100gp r3, AbstractC04020gg r4, AnonymousClass0m9 r5) throws IOException, AnonymousClass0jg {
        int i = C05630lb.A00[r3.A0a().ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            return r5.A07(r3, r4);
        }
        return null;
    }
}
