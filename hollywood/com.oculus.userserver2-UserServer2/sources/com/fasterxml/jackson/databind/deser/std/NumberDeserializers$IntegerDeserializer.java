package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0122Rd;
import X.AnonymousClass9r;
import X.Rn;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$IntegerDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Integer> {
    public static final NumberDeserializers$IntegerDeserializer A00 = new NumberDeserializers$IntegerDeserializer(Integer.class, 0);
    public static final NumberDeserializers$IntegerDeserializer A01 = new NumberDeserializers$IntegerDeserializer(Integer.TYPE, null);
    public static final long serialVersionUID = 1;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        return A06(rn);
    }

    public NumberDeserializers$IntegerDeserializer(Class<Integer> cls, Integer num) {
        super(cls, num);
    }

    private final void A00(Rn rn) throws IOException, AnonymousClass9r {
        A06(rn);
    }
}
