package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02640aV;
import X.AnonymousClass0a8;
import X.C02650aW;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public final class StdJdkSerializers$AtomicBooleanSerializer extends StdScalarSerializer<AtomicBoolean> {
    public StdJdkSerializers$AtomicBooleanSerializer() {
        super(AtomicBoolean.class, false);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void A0D(Object obj, AbstractC02640aV r3, AnonymousClass0a8 r4) throws IOException, C02650aW {
        r3.A0W(((AtomicBoolean) obj).get());
    }
}
