package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02120i3;
import X.AbstractC02300iS;
import X.AbstractC04550qd;
import X.C02310iT;
import java.io.IOException;

public abstract class NonTypedScalarSerializerBase<T> extends StdScalarSerializer<T> {
    public NonTypedScalarSerializerBase(Class<T> cls) {
        super(cls);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdScalarSerializer
    public final void serializeWithType(T t, AbstractC02300iS r2, AbstractC02120i3 r3, AbstractC04550qd r4) throws IOException, C02310iT {
        serialize(t, r2, r3);
    }
}
