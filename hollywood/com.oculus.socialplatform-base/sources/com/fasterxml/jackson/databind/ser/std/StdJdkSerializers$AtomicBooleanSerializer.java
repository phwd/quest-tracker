package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC01900ha;
import X.AbstractC02120i3;
import X.AbstractC02190iF;
import X.AbstractC02300iS;
import X.C02180iD;
import X.C02310iT;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public final class StdJdkSerializers$AtomicBooleanSerializer extends StdScalarSerializer<AtomicBoolean> {
    public StdJdkSerializers$AtomicBooleanSerializer() {
        super(AtomicBoolean.class, false);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void acceptJsonFormatVisitor(AbstractC01900ha r3, AbstractC02190iF r4) throws C02180iD {
        throw new NullPointerException("expectBooleanFormat");
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void serialize(Object obj, AbstractC02300iS r3, AbstractC02120i3 r4) throws IOException, C02310iT {
        r3.A0Y(((AtomicBoolean) obj).get());
    }
}
