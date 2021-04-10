package com.fasterxml.jackson.databind.ser.impl;

import X.AbstractC02640aV;
import X.AnonymousClass0a8;
import X.AnonymousClass0o6;
import X.C05910ld;
import com.fasterxml.jackson.databind.JsonSerializer;
import java.io.IOException;

public final class TypeWrappedSerializer extends JsonSerializer<Object> {
    public final JsonSerializer<Object> A00;
    public final AnonymousClass0o6 A01;

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void A0A(Object obj, AbstractC02640aV r3, AnonymousClass0a8 r4, AnonymousClass0o6 r5) throws IOException, C05910ld {
        this.A00.A0A(obj, r3, r4, r5);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final Class<Object> A0C() {
        return Object.class;
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void A0D(Object obj, AbstractC02640aV r4, AnonymousClass0a8 r5) throws IOException, C05910ld {
        this.A00.A0A(obj, r4, r5, this.A01);
    }

    public TypeWrappedSerializer(AnonymousClass0o6 r1, JsonSerializer<?> jsonSerializer) {
        this.A01 = r1;
        this.A00 = jsonSerializer;
    }
}
