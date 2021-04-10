package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.V4;
import X.q0;
import java.io.IOException;

public abstract class StdScalarDeserializer<T> extends StdDeserializer<T> {
    public static final long serialVersionUID = 1;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Object A0C(AbstractC0232Ww ww, AbstractC0226Wn wn, V4 v4) throws IOException, q0 {
        return v4.A0A(ww, wn);
    }

    public StdScalarDeserializer(Class<?> cls) {
        super(cls);
    }
}
