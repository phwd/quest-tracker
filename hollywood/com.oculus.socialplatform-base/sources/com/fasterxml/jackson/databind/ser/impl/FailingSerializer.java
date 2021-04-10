package com.fasterxml.jackson.databind.ser.impl;

import X.AbstractC01900ha;
import X.AbstractC02120i3;
import X.AbstractC02190iF;
import X.AbstractC02300iS;
import X.C02310iT;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public final class FailingSerializer extends StdSerializer<Object> {
    public final String A00 = "Null key for a Map not allowed in JSON (use a converting NullKeySerializer?)";

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void acceptJsonFormatVisitor(AbstractC01900ha r1, AbstractC02190iF r2) {
    }

    public FailingSerializer() {
        super(Object.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void serialize(Object obj, AbstractC02300iS r4, AbstractC02120i3 r5) throws IOException, C02310iT {
        throw new C02310iT(this.A00);
    }
}
