package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02640aV;
import X.AnonymousClass0a8;
import X.C02650aW;
import com.fasterxml.jackson.databind.JsonSerializer;
import java.io.IOException;
import java.util.Date;

public class StdKeySerializers$DateKeySerializer extends StdSerializer<Date> {
    public static final JsonSerializer<?> A00 = new StdKeySerializers$DateKeySerializer();

    public StdKeySerializers$DateKeySerializer() {
        super(Date.class);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0aV, X.0a8] */
    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void A0D(Date date, AbstractC02640aV r2, AnonymousClass0a8 r3) throws IOException, C02650aW {
        r3.A0E(date, r2);
    }
}
