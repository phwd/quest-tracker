package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.AbstractC04520qa;
import X.C03620oC;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$BooleanDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Boolean> {
    public static final NumberDeserializers$BooleanDeserializer A00 = new NumberDeserializers$BooleanDeserializer(Boolean.class, Boolean.FALSE);
    public static final NumberDeserializers$BooleanDeserializer A01 = new NumberDeserializers$BooleanDeserializer(Boolean.TYPE, null);
    public static final long serialVersionUID = 1;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A0A(AbstractC02280iQ r2, AbstractC02210iH r3) throws IOException, C03620oC {
        return A0J(r2, r3);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer, com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer
    public final Object A0B(AbstractC02280iQ r2, AbstractC02210iH r3, AbstractC04520qa r4) throws IOException, C03620oC {
        return A0J(r2, r3);
    }

    public NumberDeserializers$BooleanDeserializer(Class<Boolean> cls, Boolean bool) {
        super(cls, bool);
    }

    private final void A00(AbstractC02280iQ r1, AbstractC02210iH r2) throws IOException, C03620oC {
        A0J(r1, r2);
    }
}
