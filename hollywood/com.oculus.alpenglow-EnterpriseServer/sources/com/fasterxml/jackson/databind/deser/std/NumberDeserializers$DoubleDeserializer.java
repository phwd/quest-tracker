package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.AnonymousClass0aT;
import X.AnonymousClass0o3;
import X.C05910ld;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$DoubleDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Double> {
    public static final NumberDeserializers$DoubleDeserializer A00 = new NumberDeserializers$DoubleDeserializer(Double.class, Double.valueOf(0.0d));
    public static final NumberDeserializers$DoubleDeserializer A01 = new NumberDeserializers$DoubleDeserializer(Double.TYPE, null);
    public static final long serialVersionUID = 1;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A09(AnonymousClass0aT r2, AbstractC02570aK r3) throws IOException, C05910ld {
        return A0K(r2, r3);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer, com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer
    public final Object A0C(AnonymousClass0aT r2, AbstractC02570aK r3, AnonymousClass0o3 r4) throws IOException, C05910ld {
        return A0K(r2, r3);
    }

    public NumberDeserializers$DoubleDeserializer(Class<Double> cls, Double d) {
        super(cls, d);
    }

    private final void A00(AnonymousClass0aT r1, AbstractC02570aK r2) throws IOException, C05910ld {
        A0K(r1, r2);
    }
}
