package com.fasterxml.jackson.databind.ser.impl;

import X.AbstractC02640aV;
import X.AnonymousClass0a8;
import X.C02650aW;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public final class FailingSerializer extends StdSerializer<Object> {
    public final String A00 = "Null key for a Map not allowed in JSON (use a converting NullKeySerializer?)";

    public FailingSerializer() {
        super(Object.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void A0D(Object obj, AbstractC02640aV r4, AnonymousClass0a8 r5) throws IOException, C02650aW {
        throw new C02650aW(this.A00);
    }
}
