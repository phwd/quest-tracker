package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0122Rd;
import X.AnonymousClass9r;
import X.Rn;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$BooleanDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Boolean> {
    public static final NumberDeserializers$BooleanDeserializer A00 = new NumberDeserializers$BooleanDeserializer(Boolean.class, Boolean.FALSE);
    public static final NumberDeserializers$BooleanDeserializer A01 = new NumberDeserializers$BooleanDeserializer(Boolean.TYPE, null);
    public static final long serialVersionUID = 1;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        return A04(rn);
    }

    public NumberDeserializers$BooleanDeserializer(Class<Boolean> cls, Boolean bool) {
        super(cls, bool);
    }

    private final void A00(Rn rn) throws IOException, AnonymousClass9r {
        A04(rn);
    }
}
