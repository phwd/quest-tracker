package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02640aV;
import X.AnonymousClass0a8;
import X.AnonymousClass0o6;
import X.C02650aW;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class ToStringSerializer extends StdSerializer<Object> {
    public static final ToStringSerializer A00 = new ToStringSerializer();

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final boolean A0B(Object obj) {
        String obj2;
        return obj == null || (obj2 = obj.toString()) == null || obj2.length() == 0;
    }

    public ToStringSerializer() {
        super(Object.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void A0A(Object obj, AbstractC02640aV r2, AnonymousClass0a8 r3, AnonymousClass0o6 r4) throws IOException, C02650aW {
        r4.A03(obj, r2);
        A0D(obj, r2, r3);
        r4.A06(obj, r2);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void A0D(Object obj, AbstractC02640aV r3, AnonymousClass0a8 r4) throws IOException, C02650aW {
        r3.A0S(obj.toString());
    }
}
