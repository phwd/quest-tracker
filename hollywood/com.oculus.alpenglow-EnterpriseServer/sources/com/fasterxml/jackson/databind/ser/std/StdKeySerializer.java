package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02640aV;
import X.AnonymousClass0a8;
import X.C02650aW;
import java.io.IOException;
import java.util.Date;

public final class StdKeySerializer extends StdSerializer<Object> {
    public static final StdKeySerializer A00 = new StdKeySerializer();

    public StdKeySerializer() {
        super(Object.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void A0D(Object obj, AbstractC02640aV r3, AnonymousClass0a8 r4) throws IOException, C02650aW {
        if (obj instanceof Date) {
            r4.A0E((Date) obj, r3);
        } else {
            r3.A0P(obj.toString());
        }
    }
}
