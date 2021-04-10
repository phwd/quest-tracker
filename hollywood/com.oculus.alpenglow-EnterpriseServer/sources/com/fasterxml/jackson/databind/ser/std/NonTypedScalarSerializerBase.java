package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02640aV;
import X.AnonymousClass0a8;
import X.AnonymousClass0o6;
import X.C02650aW;
import java.io.IOException;

public abstract class NonTypedScalarSerializerBase<T> extends StdScalarSerializer<T> {
    public NonTypedScalarSerializerBase(Class<T> cls) {
        super(cls);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdScalarSerializer
    public final void A0A(T t, AbstractC02640aV r2, AnonymousClass0a8 r3, AnonymousClass0o6 r4) throws IOException, C02650aW {
        A0D(t, r2, r3);
    }
}
