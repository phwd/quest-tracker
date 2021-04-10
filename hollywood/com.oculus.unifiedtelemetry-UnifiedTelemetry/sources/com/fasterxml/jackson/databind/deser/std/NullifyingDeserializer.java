package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.C0194Va;
import X.V4;
import X.q0;
import java.io.IOException;

public final class NullifyingDeserializer extends StdDeserializer<Object> {
    public static final NullifyingDeserializer A00 = new NullifyingDeserializer();
    public static final long serialVersionUID = 1;

    public NullifyingDeserializer() {
        super(Object.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        ww.A0Y();
        return null;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC0232Ww ww, AbstractC0226Wn wn, V4 v4) throws IOException, q0 {
        int i = C0194Va.A00[ww.A0Z().ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            return v4.A07(ww, wn);
        }
        return null;
    }
}
