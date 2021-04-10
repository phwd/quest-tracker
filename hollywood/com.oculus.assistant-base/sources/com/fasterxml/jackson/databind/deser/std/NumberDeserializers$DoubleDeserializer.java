package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
public final class NumberDeserializers$DoubleDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer {
    public static final NumberDeserializers$DoubleDeserializer A00 = new NumberDeserializers$DoubleDeserializer(Double.class, Double.valueOf(0.0d));
    public static final NumberDeserializers$DoubleDeserializer A01 = new NumberDeserializers$DoubleDeserializer(Double.TYPE, null);
    public static final long serialVersionUID = 1;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        return A0I(qiVar, qrVar);
    }

    public NumberDeserializers$DoubleDeserializer(Class cls, Double d) {
        super(cls, d);
    }

    private final void A00(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        A0I(qiVar, qrVar);
    }
}
