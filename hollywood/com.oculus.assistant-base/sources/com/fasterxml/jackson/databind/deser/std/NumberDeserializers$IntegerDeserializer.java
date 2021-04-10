package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
public final class NumberDeserializers$IntegerDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer {
    public static final NumberDeserializers$IntegerDeserializer A00 = new NumberDeserializers$IntegerDeserializer(Integer.class, 0);
    public static final NumberDeserializers$IntegerDeserializer A01 = new NumberDeserializers$IntegerDeserializer(Integer.TYPE, null);
    public static final long serialVersionUID = 1;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        return A0J(qiVar, qrVar);
    }

    public NumberDeserializers$IntegerDeserializer(Class cls, Integer num) {
        super(cls, num);
    }

    private final void A00(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        A0J(qiVar, qrVar);
    }
}
