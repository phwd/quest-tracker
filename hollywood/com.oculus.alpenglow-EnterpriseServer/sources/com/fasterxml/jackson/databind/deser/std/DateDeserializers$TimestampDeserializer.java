package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.AnonymousClass0aT;
import X.C05910ld;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;

public class DateDeserializers$TimestampDeserializer extends DateDeserializers$DateBasedDeserializer<Timestamp> {
    public static final DateDeserializers$TimestampDeserializer A00 = new DateDeserializers$TimestampDeserializer();

    /* Return type fixed from 'com.fasterxml.jackson.databind.deser.std.DateDeserializers$DateBasedDeserializer' to match base method */
    @Override // com.fasterxml.jackson.databind.deser.std.DateDeserializers$DateBasedDeserializer
    public final DateDeserializers$DateBasedDeserializer<Timestamp> A0P(DateFormat dateFormat, String str) {
        return new DateDeserializers$TimestampDeserializer(this, dateFormat, str);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Timestamp A09(AnonymousClass0aT r4, AbstractC02570aK r5) throws IOException, C05910ld {
        return new Timestamp(A0M(r4, r5).getTime());
    }

    public DateDeserializers$TimestampDeserializer() {
        super(Timestamp.class);
    }

    public DateDeserializers$TimestampDeserializer(DateDeserializers$TimestampDeserializer dateDeserializers$TimestampDeserializer, DateFormat dateFormat, String str) {
        super(dateDeserializers$TimestampDeserializer, dateFormat, str);
    }
}
