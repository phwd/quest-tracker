package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0122Rd;
import X.AnonymousClass9r;
import X.Rn;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$DoubleDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Double> {
    public static final NumberDeserializers$DoubleDeserializer A00 = new NumberDeserializers$DoubleDeserializer(Double.class, Double.valueOf(0.0d));
    public static final NumberDeserializers$DoubleDeserializer A01 = new NumberDeserializers$DoubleDeserializer(Double.TYPE, null);
    public static final long serialVersionUID = 1;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        return A05(rn);
    }

    public NumberDeserializers$DoubleDeserializer(Class<Double> cls, Double d) {
        super(cls, d);
    }

    private final void A00(Rn rn) throws IOException, AnonymousClass9r {
        A05(rn);
    }
}
