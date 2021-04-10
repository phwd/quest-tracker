package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02120i3;
import X.AbstractC02300iS;
import X.AbstractC04550qd;
import X.C02310iT;
import java.io.IOException;

public abstract class StdScalarSerializer<T> extends StdSerializer<T> {
    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serializeWithType(T t, AbstractC02300iS r2, AbstractC02120i3 r3, AbstractC04550qd r4) throws IOException, C02310iT {
        r4.A03(t, r2);
        serialize(t, r2, r3);
        r4.A06(t, r2);
    }

    public StdScalarSerializer(Class<T> cls) {
        super(cls);
    }

    public StdScalarSerializer(Class<?> cls, boolean z) {
        super(cls);
    }
}
