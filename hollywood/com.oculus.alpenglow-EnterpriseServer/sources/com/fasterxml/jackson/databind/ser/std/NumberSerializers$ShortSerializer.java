package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02640aV;
import X.AnonymousClass0a8;
import X.C02650aW;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class NumberSerializers$ShortSerializer extends StdScalarSerializer<Short> {
    public static final NumberSerializers$ShortSerializer A00 = new NumberSerializers$ShortSerializer();

    public NumberSerializers$ShortSerializer() {
        super(Short.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void A0D(Object obj, AbstractC02640aV r3, AnonymousClass0a8 r4) throws IOException, C02650aW {
        r3.A0V(((Short) obj).shortValue());
    }
}
