package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.AnonymousClass0aT;
import X.AnonymousClass0o3;
import X.C05910ld;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$BooleanDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Boolean> {
    public static final NumberDeserializers$BooleanDeserializer A00 = new NumberDeserializers$BooleanDeserializer(Boolean.class, Boolean.FALSE);
    public static final NumberDeserializers$BooleanDeserializer A01 = new NumberDeserializers$BooleanDeserializer(Boolean.TYPE, null);
    public static final long serialVersionUID = 1;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A09(AnonymousClass0aT r2, AbstractC02570aK r3) throws IOException, C05910ld {
        return A0J(r2, r3);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer, com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer
    public final Object A0C(AnonymousClass0aT r2, AbstractC02570aK r3, AnonymousClass0o3 r4) throws IOException, C05910ld {
        return A0J(r2, r3);
    }

    public NumberDeserializers$BooleanDeserializer(Class<Boolean> cls, Boolean bool) {
        super(cls, bool);
    }

    private final void A00(AnonymousClass0aT r1, AbstractC02570aK r2) throws IOException, C05910ld {
        A0J(r1, r2);
    }
}
