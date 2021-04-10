package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02640aV;
import X.AnonymousClass0a8;
import X.C02650aW;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

public final class StdJdkSerializers$AtomicLongSerializer extends StdScalarSerializer<AtomicLong> {
    public StdJdkSerializers$AtomicLongSerializer() {
        super(AtomicLong.class, false);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void A0D(Object obj, AbstractC02640aV r4, AnonymousClass0a8 r5) throws IOException, C02650aW {
        r4.A0K(((AtomicLong) obj).get());
    }
}
