package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02120i3;
import X.AbstractC02300iS;
import X.C02310iT;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public final class StdJdkSerializers$AtomicReferenceSerializer extends StdSerializer<AtomicReference<?>> {
    public StdJdkSerializers$AtomicReferenceSerializer() {
        super(AtomicReference.class, false);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0iS, X.0i3] */
    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void serialize(AtomicReference<?> atomicReference, AbstractC02300iS r3, AbstractC02120i3 r4) throws IOException, C02310iT {
        r4.A0F(atomicReference.get(), r3);
    }
}
