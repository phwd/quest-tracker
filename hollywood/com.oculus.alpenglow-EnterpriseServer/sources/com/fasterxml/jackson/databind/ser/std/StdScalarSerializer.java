package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02640aV;
import X.AnonymousClass0a8;
import X.AnonymousClass0o6;
import X.C02650aW;
import java.io.IOException;

public abstract class StdScalarSerializer<T> extends StdSerializer<T> {
    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void A0A(T t, AbstractC02640aV r2, AnonymousClass0a8 r3, AnonymousClass0o6 r4) throws IOException, C02650aW {
        r4.A03(t, r2);
        A0D(t, r2, r3);
        r4.A06(t, r2);
    }

    public StdScalarSerializer(Class<T> cls) {
        super(cls);
    }

    public StdScalarSerializer(Class<?> cls, boolean z) {
        super(cls);
    }
}
