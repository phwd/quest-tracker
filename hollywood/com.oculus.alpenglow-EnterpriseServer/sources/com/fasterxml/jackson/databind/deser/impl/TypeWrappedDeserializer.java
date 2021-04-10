package com.fasterxml.jackson.databind.deser.impl;

import X.AbstractC02570aK;
import X.AnonymousClass0aT;
import X.AnonymousClass0o3;
import X.C05910ld;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public final class TypeWrappedDeserializer extends JsonDeserializer<Object> {
    public final JsonDeserializer<Object> A00;
    public final AnonymousClass0o3 A01;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A09(AnonymousClass0aT r3, AbstractC02570aK r4) throws IOException, C05910ld {
        return this.A00.A0C(r3, r4, this.A01);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0A(AnonymousClass0aT r2, AbstractC02570aK r3, Object obj) throws IOException, C05910ld {
        return this.A00.A0A(r2, r3, obj);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AnonymousClass0aT r3, AbstractC02570aK r4, AnonymousClass0o3 r5) throws IOException, C05910ld {
        throw new IllegalStateException("Type-wrapped deserializer's deserializeWithType should never get called");
    }

    public TypeWrappedDeserializer(AnonymousClass0o3 r1, JsonDeserializer<Object> jsonDeserializer) {
        this.A01 = r1;
        this.A00 = jsonDeserializer;
    }
}
