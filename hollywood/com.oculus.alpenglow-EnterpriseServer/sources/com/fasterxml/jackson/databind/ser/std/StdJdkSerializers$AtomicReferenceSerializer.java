package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02640aV;
import X.AnonymousClass0a8;
import X.C02650aW;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public final class StdJdkSerializers$AtomicReferenceSerializer extends StdSerializer<AtomicReference<?>> {
    public StdJdkSerializers$AtomicReferenceSerializer() {
        super(AtomicReference.class, false);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0aV, X.0a8] */
    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void A0D(AtomicReference<?> atomicReference, AbstractC02640aV r5, AnonymousClass0a8 r6) throws IOException, C02650aW {
        Object obj = atomicReference.get();
        if (obj == null) {
            r6._nullValueSerializer.A0D(null, r5, r6);
        } else {
            r6.A0A(obj.getClass(), null).A0D(obj, r5, r6);
        }
    }
}
