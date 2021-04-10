package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import java.text.DateFormat;
import java.util.Date;

public class DateDeserializers$DateDeserializer extends DateDeserializers$DateBasedDeserializer {
    public static final DateDeserializers$DateDeserializer A00 = new DateDeserializers$DateDeserializer();

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        return A0K(qiVar, qrVar);
    }

    private final void A00(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        A0K(qiVar, qrVar);
    }

    public DateDeserializers$DateDeserializer() {
        super(Date.class);
    }

    public DateDeserializers$DateDeserializer(DateDeserializers$DateDeserializer dateDeserializers$DateDeserializer, DateFormat dateFormat, String str) {
        super(dateDeserializers$DateDeserializer, dateFormat, str);
    }
}
