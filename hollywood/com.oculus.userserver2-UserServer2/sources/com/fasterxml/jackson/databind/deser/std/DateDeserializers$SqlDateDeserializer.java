package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0122Rd;
import X.AnonymousClass9r;
import X.Rn;
import java.io.IOException;
import java.sql.Date;

public class DateDeserializers$SqlDateDeserializer extends DateDeserializers$DateBasedDeserializer<Date> {
    public static final DateDeserializers$SqlDateDeserializer A00 = new DateDeserializers$SqlDateDeserializer();

    public DateDeserializers$SqlDateDeserializer() {
        super(Date.class);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Date A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        java.util.Date A07 = A07(rn, rd);
        if (A07 == null) {
            return null;
        }
        return new Date(A07.getTime());
    }
}
