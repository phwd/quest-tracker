package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02120i3;
import X.AbstractC02300iS;
import X.C02310iT;
import com.fasterxml.jackson.databind.JsonSerializer;
import java.io.IOException;
import java.util.Date;

public class StdKeySerializers$DateKeySerializer extends StdSerializer<Date> {
    public static final JsonSerializer<?> A00 = new StdKeySerializers$DateKeySerializer();

    public StdKeySerializers$DateKeySerializer() {
        super(Date.class);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0iS, X.0i3] */
    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void serialize(Date date, AbstractC02300iS r2, AbstractC02120i3 r3) throws IOException, C02310iT {
        r3.A0G(date, r2);
    }
}
