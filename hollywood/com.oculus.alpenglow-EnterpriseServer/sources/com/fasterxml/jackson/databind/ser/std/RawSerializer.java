package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02640aV;
import X.AnonymousClass0a8;
import X.AnonymousClass0o6;
import X.C02650aW;
import X.C05910ld;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class RawSerializer<T> extends StdSerializer<T> {
    public RawSerializer(Class<?> cls) {
        super(cls, false);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void A0A(T t, AbstractC02640aV r2, AnonymousClass0a8 r3, AnonymousClass0o6 r4) throws IOException, C05910ld {
        r4.A03(t, r2);
        A0D(t, r2, r3);
        r4.A06(t, r2);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void A0D(T t, AbstractC02640aV r3, AnonymousClass0a8 r4) throws IOException, C02650aW {
        r3.A0A(t.toString());
    }
}
