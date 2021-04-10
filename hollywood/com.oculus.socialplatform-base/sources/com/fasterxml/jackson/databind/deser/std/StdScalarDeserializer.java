package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.AbstractC04520qa;
import X.C03620oC;
import java.io.IOException;

public abstract class StdScalarDeserializer<T> extends StdDeserializer<T> {
    public static final long serialVersionUID = 1;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Object A0B(AbstractC02280iQ r2, AbstractC02210iH r3, AbstractC04520qa r4) throws IOException, C03620oC {
        return r4.A0A(r2, r3);
    }

    public StdScalarDeserializer(Class<?> cls) {
        super(cls);
    }
}
