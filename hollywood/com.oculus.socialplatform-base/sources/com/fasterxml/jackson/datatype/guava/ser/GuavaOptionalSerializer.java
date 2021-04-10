package com.fasterxml.jackson.datatype.guava.ser;

import X.AbstractC02120i3;
import X.AbstractC02190iF;
import X.AbstractC02300iS;
import X.C02310iT;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.google.common.base.Optional;
import java.io.IOException;

public final class GuavaOptionalSerializer extends StdSerializer<Optional<?>> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0iS, X.0i3] */
    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void serialize(Optional<?> optional, AbstractC02300iS r3, AbstractC02120i3 r4) throws IOException, C02310iT {
        Optional<?> optional2 = optional;
        if (optional2.isPresent()) {
            r4.A0F(optional2.get(), r3);
        } else {
            r4.A0E(r3);
        }
    }

    public GuavaOptionalSerializer(AbstractC02190iF r1) {
        super(r1);
    }
}
