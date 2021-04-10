package com.fasterxml.jackson.databind.ser.impl;

import X.AbstractC02120i3;
import X.AbstractC02300iS;
import X.AbstractC04550qd;
import X.C03620oC;
import com.fasterxml.jackson.databind.JsonSerializer;
import java.io.IOException;

public final class TypeWrappedSerializer extends JsonSerializer<Object> {
    public final JsonSerializer<Object> A00;
    public final AbstractC04550qd A01;

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(Object obj, AbstractC02300iS r4, AbstractC02120i3 r5) throws IOException, C03620oC {
        this.A00.serializeWithType(obj, r4, r5, this.A01);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serializeWithType(Object obj, AbstractC02300iS r3, AbstractC02120i3 r4, AbstractC04550qd r5) throws IOException, C03620oC {
        this.A00.serializeWithType(obj, r3, r4, r5);
    }

    public TypeWrappedSerializer(AbstractC04550qd r1, JsonSerializer<?> jsonSerializer) {
        this.A01 = r1;
        this.A00 = jsonSerializer;
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final Class<Object> handledType() {
        return Object.class;
    }
}
