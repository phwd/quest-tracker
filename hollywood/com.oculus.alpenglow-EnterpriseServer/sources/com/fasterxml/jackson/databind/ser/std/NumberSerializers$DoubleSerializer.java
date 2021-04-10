package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02640aV;
import X.AnonymousClass0a8;
import X.C02650aW;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class NumberSerializers$DoubleSerializer extends NonTypedScalarSerializerBase<Double> {
    public static final NumberSerializers$DoubleSerializer A00 = new NumberSerializers$DoubleSerializer();

    public NumberSerializers$DoubleSerializer() {
        super(Double.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void A0D(Object obj, AbstractC02640aV r4, AnonymousClass0a8 r5) throws IOException, C02650aW {
        r4.A0H(((Double) obj).doubleValue());
    }
}
