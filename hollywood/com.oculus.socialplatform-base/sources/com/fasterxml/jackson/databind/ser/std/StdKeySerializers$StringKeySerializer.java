package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02120i3;
import X.AbstractC02300iS;
import X.C02310iT;
import java.io.IOException;

public class StdKeySerializers$StringKeySerializer extends StdSerializer<String> {
    public StdKeySerializers$StringKeySerializer() {
        super(String.class);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0iS, X.0i3] */
    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void serialize(String str, AbstractC02300iS r2, AbstractC02120i3 r3) throws IOException, C02310iT {
        r2.A0R(str);
    }
}
