package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.C03620oC;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;

public class DateDeserializers$SqlDateDeserializer extends DateDeserializers$DateBasedDeserializer<Date> {
    public static final DateDeserializers$SqlDateDeserializer A00 = new DateDeserializers$SqlDateDeserializer();

    /* Return type fixed from 'com.fasterxml.jackson.databind.deser.std.DateDeserializers$DateBasedDeserializer' to match base method */
    @Override // com.fasterxml.jackson.databind.deser.std.DateDeserializers$DateBasedDeserializer
    public final DateDeserializers$DateBasedDeserializer<Date> A0P(DateFormat dateFormat, String str) {
        return new DateDeserializers$SqlDateDeserializer(this, dateFormat, str);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Date A0A(AbstractC02280iQ r4, AbstractC02210iH r5) throws IOException, C03620oC {
        java.util.Date A0M = A0M(r4, r5);
        if (A0M == null) {
            return null;
        }
        return new Date(A0M.getTime());
    }

    public DateDeserializers$SqlDateDeserializer() {
        super(Date.class);
    }

    public DateDeserializers$SqlDateDeserializer(DateDeserializers$SqlDateDeserializer dateDeserializers$SqlDateDeserializer, DateFormat dateFormat, String str) {
        super(dateDeserializers$SqlDateDeserializer, dateFormat, str);
    }
}
