package com.fasterxml.jackson.databind.deser.impl;

import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.V4;
import X.q0;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public final class TypeWrappedDeserializer extends JsonDeserializer<Object> {
    public final JsonDeserializer<Object> A00;
    public final V4 A01;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        return this.A00.A0C(ww, wn, this.A01);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0A(AbstractC0232Ww ww, AbstractC0226Wn wn, Object obj) throws IOException, q0 {
        return this.A00.A0A(ww, wn, obj);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC0232Ww ww, AbstractC0226Wn wn, V4 v4) throws IOException, q0 {
        throw new IllegalStateException("Type-wrapped deserializer's deserializeWithType should never get called");
    }

    public TypeWrappedDeserializer(V4 v4, JsonDeserializer<Object> jsonDeserializer) {
        this.A01 = v4;
        this.A00 = jsonDeserializer;
    }
}
