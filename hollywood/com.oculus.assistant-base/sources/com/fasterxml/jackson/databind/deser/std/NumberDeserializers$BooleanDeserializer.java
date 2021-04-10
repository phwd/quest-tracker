package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
public final class NumberDeserializers$BooleanDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer {
    public static final NumberDeserializers$BooleanDeserializer A00 = new NumberDeserializers$BooleanDeserializer(Boolean.class, Boolean.FALSE);
    public static final NumberDeserializers$BooleanDeserializer A01 = new NumberDeserializers$BooleanDeserializer(Boolean.TYPE, null);
    public static final long serialVersionUID = 1;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        return A0H(qiVar, qrVar);
    }

    public NumberDeserializers$BooleanDeserializer(Class cls, Boolean bool) {
        super(cls, bool);
    }

    private final void A00(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        A0H(qiVar, qrVar);
    }
}
