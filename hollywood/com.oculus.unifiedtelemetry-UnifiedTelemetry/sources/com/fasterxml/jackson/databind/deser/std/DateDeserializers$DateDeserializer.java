package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.q0;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

public class DateDeserializers$DateDeserializer extends DateDeserializers$DateBasedDeserializer<Date> {
    public static final DateDeserializers$DateDeserializer A00 = new DateDeserializers$DateDeserializer();

    /* Return type fixed from 'com.fasterxml.jackson.databind.deser.std.DateDeserializers$DateBasedDeserializer' to match base method */
    @Override // com.fasterxml.jackson.databind.deser.std.DateDeserializers$DateBasedDeserializer
    public final DateDeserializers$DateBasedDeserializer<Date> A0P(DateFormat dateFormat, String str) {
        return new DateDeserializers$DateDeserializer(this, dateFormat, str);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        return A0M(ww, wn);
    }

    private final void A00(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        A0M(ww, wn);
    }

    public DateDeserializers$DateDeserializer() {
        super(Date.class);
    }

    public DateDeserializers$DateDeserializer(DateDeserializers$DateDeserializer dateDeserializers$DateDeserializer, DateFormat dateFormat, String str) {
        super(dateDeserializers$DateDeserializer, dateFormat, str);
    }
}
