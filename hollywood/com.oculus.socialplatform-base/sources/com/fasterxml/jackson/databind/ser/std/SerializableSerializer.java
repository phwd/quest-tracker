package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02120i3;
import X.AbstractC02300iS;
import X.AbstractC04550qd;
import X.AnonymousClass0p5;
import X.C02130i6;
import X.C02310iT;
import X.C03620oC;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

@JacksonStdImpl
public class SerializableSerializer extends StdSerializer<AnonymousClass0p5> {
    public static final SerializableSerializer A00 = new SerializableSerializer();
    public static final AtomicReference<C02130i6> A01 = new AtomicReference<>();

    public SerializableSerializer() {
        super(AnonymousClass0p5.class);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0iS, X.0i3] */
    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void serialize(AnonymousClass0p5 r1, AbstractC02300iS r2, AbstractC02120i3 r3) throws IOException, C02310iT {
        r1.A9c(r2, r3);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serializeWithType(Object obj, AbstractC02300iS r2, AbstractC02120i3 r3, AbstractC04550qd r4) throws IOException, C03620oC {
        ((AnonymousClass0p5) obj).A9d(r2, r3, r4);
    }
}
