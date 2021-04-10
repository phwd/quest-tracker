package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AnonymousClass0jg;
import X.AnonymousClass0m9;
import java.io.IOException;

public abstract class StdScalarDeserializer<T> extends StdDeserializer<T> {
    public static final long serialVersionUID = 1;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Object A0C(AbstractC04100gp r2, AbstractC04020gg r3, AnonymousClass0m9 r4) throws IOException, AnonymousClass0jg {
        return r4.A0A(r2, r3);
    }

    public StdScalarDeserializer(Class<?> cls) {
        super(cls);
    }
}
