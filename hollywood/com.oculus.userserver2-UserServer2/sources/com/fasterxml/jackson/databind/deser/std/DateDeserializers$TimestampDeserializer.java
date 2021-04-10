package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0122Rd;
import X.AnonymousClass9r;
import X.Rn;
import java.io.IOException;
import java.sql.Timestamp;

public class DateDeserializers$TimestampDeserializer extends DateDeserializers$DateBasedDeserializer<Timestamp> {
    public static final DateDeserializers$TimestampDeserializer A00 = new DateDeserializers$TimestampDeserializer();

    public DateDeserializers$TimestampDeserializer() {
        super(Timestamp.class);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Timestamp A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        return new Timestamp(A07(rn, rd).getTime());
    }
}
