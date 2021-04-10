package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02640aV;
import X.AnonymousClass0a8;
import X.C02650aW;
import java.io.IOException;

public final class StdJdkSerializers$ClassSerializer extends StdScalarSerializer<Class<?>> {
    public StdJdkSerializers$ClassSerializer() {
        super(Class.class, false);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void A0D(Object obj, AbstractC02640aV r3, AnonymousClass0a8 r4) throws IOException, C02650aW {
        r3.A0S(((Class) obj).getName());
    }
}
