package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AnonymousClass0jg;
import X.AnonymousClass0m9;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$IntegerDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Integer> {
    public static final NumberDeserializers$IntegerDeserializer A00 = new NumberDeserializers$IntegerDeserializer(Integer.class, 0);
    public static final NumberDeserializers$IntegerDeserializer A01 = new NumberDeserializers$IntegerDeserializer(Integer.TYPE, null);
    public static final long serialVersionUID = 1;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A09(AbstractC04100gp r2, AbstractC04020gg r3) throws IOException, AnonymousClass0jg {
        return A0L(r2, r3);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer, com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer
    public final Object A0C(AbstractC04100gp r2, AbstractC04020gg r3, AnonymousClass0m9 r4) throws IOException, AnonymousClass0jg {
        return A0L(r2, r3);
    }

    public NumberDeserializers$IntegerDeserializer(Class<Integer> cls, Integer num) {
        super(cls, num);
    }

    private final void A00(AbstractC04100gp r1, AbstractC04020gg r2) throws IOException, AnonymousClass0jg {
        A0L(r1, r2);
    }
}
