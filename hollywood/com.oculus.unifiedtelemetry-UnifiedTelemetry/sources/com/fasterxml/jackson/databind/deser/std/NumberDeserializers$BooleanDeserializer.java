package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.V4;
import X.q0;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$BooleanDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Boolean> {
    public static final NumberDeserializers$BooleanDeserializer A00 = new NumberDeserializers$BooleanDeserializer(Boolean.class, Boolean.FALSE);
    public static final NumberDeserializers$BooleanDeserializer A01 = new NumberDeserializers$BooleanDeserializer(Boolean.TYPE, null);
    public static final long serialVersionUID = 1;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        return A0J(ww, wn);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer, com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer
    public final Object A0C(AbstractC0232Ww ww, AbstractC0226Wn wn, V4 v4) throws IOException, q0 {
        return A0J(ww, wn);
    }

    public NumberDeserializers$BooleanDeserializer(Class<Boolean> cls, Boolean bool) {
        super(cls, bool);
    }

    private final void A00(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        A0J(ww, wn);
    }
}
