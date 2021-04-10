package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.AnonymousClass0aT;
import X.C05910ld;
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
    public final /* bridge */ /* synthetic */ Object A09(AnonymousClass0aT r2, AbstractC02570aK r3) throws IOException, C05910ld {
        return A0M(r2, r3);
    }

    private final void A00(AnonymousClass0aT r1, AbstractC02570aK r2) throws IOException, C05910ld {
        A0M(r1, r2);
    }

    public DateDeserializers$DateDeserializer() {
        super(Date.class);
    }

    public DateDeserializers$DateDeserializer(DateDeserializers$DateDeserializer dateDeserializers$DateDeserializer, DateFormat dateFormat, String str) {
        super(dateDeserializers$DateDeserializer, dateFormat, str);
    }
}
