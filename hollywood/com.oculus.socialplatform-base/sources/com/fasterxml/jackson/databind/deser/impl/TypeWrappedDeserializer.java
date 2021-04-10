package com.fasterxml.jackson.databind.deser.impl;

import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.AbstractC04520qa;
import X.C03620oC;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public final class TypeWrappedDeserializer extends JsonDeserializer<Object> {
    public final JsonDeserializer<Object> A00;
    public final AbstractC04520qa A01;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0A(AbstractC02280iQ r3, AbstractC02210iH r4) throws IOException, C03620oC {
        return this.A00.A0B(r3, r4, this.A01);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0B(AbstractC02280iQ r3, AbstractC02210iH r4, AbstractC04520qa r5) throws IOException, C03620oC {
        throw new IllegalStateException("Type-wrapped deserializer's deserializeWithType should never get called");
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC02280iQ r2, AbstractC02210iH r3, Object obj) throws IOException, C03620oC {
        return this.A00.A0C(r2, r3, obj);
    }

    public TypeWrappedDeserializer(AbstractC04520qa r1, JsonDeserializer<Object> jsonDeserializer) {
        this.A01 = r1;
        this.A00 = jsonDeserializer;
    }
}
