package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02640aV;
import X.AbstractC06310mX;
import X.AnonymousClass0a8;
import X.AnonymousClass0o6;
import X.C02530aB;
import X.C02650aW;
import X.C05910ld;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

@JacksonStdImpl
public final class SerializableSerializer extends StdSerializer<AbstractC06310mX> {
    public static final SerializableSerializer A00 = new SerializableSerializer();
    public static final AtomicReference<C02530aB> A01 = new AtomicReference<>();

    public SerializableSerializer() {
        super(AbstractC06310mX.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void A0A(Object obj, AbstractC02640aV r2, AnonymousClass0a8 r3, AnonymousClass0o6 r4) throws IOException, C05910ld {
        ((AbstractC06310mX) obj).A7i(r2, r3, r4);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0aV, X.0a8] */
    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void A0D(AbstractC06310mX r1, AbstractC02640aV r2, AnonymousClass0a8 r3) throws IOException, C02650aW {
        r1.A7h(r2, r3);
    }
}
