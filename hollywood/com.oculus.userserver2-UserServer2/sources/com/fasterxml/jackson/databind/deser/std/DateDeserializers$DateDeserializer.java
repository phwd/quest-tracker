package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0122Rd;
import X.AnonymousClass9r;
import X.Rn;
import java.io.IOException;
import java.util.Date;

public class DateDeserializers$DateDeserializer extends DateDeserializers$DateBasedDeserializer<Date> {
    public static final DateDeserializers$DateDeserializer A00 = new DateDeserializers$DateDeserializer();

    public DateDeserializers$DateDeserializer() {
        super(Date.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        return A07(rn, rd);
    }

    private final void A00(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        A07(rn, rd);
    }
}
