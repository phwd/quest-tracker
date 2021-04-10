package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0122Rd;
import X.AnonymousClass9p;
import X.AnonymousClass9r;
import X.B3;
import X.Rn;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$NumberDeserializer extends StdScalarDeserializer<Number> {
    public static final NumberDeserializers$NumberDeserializer A00 = new NumberDeserializers$NumberDeserializer();

    private final void A00(Rn rn) throws IOException, AnonymousClass9r {
        AnonymousClass9p r1 = ((B3) rn).A00;
        if (r1 == AnonymousClass9p.VALUE_NUMBER_INT || r1 == AnonymousClass9p.VALUE_NUMBER_FLOAT || r1 != AnonymousClass9p.VALUE_STRING) {
            throw null;
        }
        rn.A09();
        try {
            throw null;
        } catch (IllegalArgumentException unused) {
            throw null;
        }
    }

    public NumberDeserializers$NumberDeserializer() {
        super(Number.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        A00(rn);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }
}
