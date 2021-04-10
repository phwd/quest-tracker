package com.fasterxml.jackson.databind.deser.impl;

import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AnonymousClass0jg;
import X.AnonymousClass0m9;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public final class TypeWrappedDeserializer extends JsonDeserializer<Object> {
    public final JsonDeserializer<Object> A00;
    public final AnonymousClass0m9 A01;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A09(AbstractC04100gp r3, AbstractC04020gg r4) throws IOException, AnonymousClass0jg {
        return this.A00.A0C(r3, r4, this.A01);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0A(AbstractC04100gp r2, AbstractC04020gg r3, Object obj) throws IOException, AnonymousClass0jg {
        return this.A00.A0A(r2, r3, obj);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC04100gp r3, AbstractC04020gg r4, AnonymousClass0m9 r5) throws IOException, AnonymousClass0jg {
        throw new IllegalStateException("Type-wrapped deserializer's deserializeWithType should never get called");
    }

    public TypeWrappedDeserializer(AnonymousClass0m9 r1, JsonDeserializer<Object> jsonDeserializer) {
        this.A01 = r1;
        this.A00 = jsonDeserializer;
    }
}
