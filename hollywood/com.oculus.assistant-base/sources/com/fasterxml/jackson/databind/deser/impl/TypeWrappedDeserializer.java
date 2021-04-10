package com.fasterxml.jackson.databind.deser.impl;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.PR;
import com.fasterxml.jackson.databind.JsonDeserializer;

public final class TypeWrappedDeserializer extends JsonDeserializer {
    public final PR A00;
    public final JsonDeserializer A01;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        return this.A01.A09(qiVar, qrVar, this.A00);
    }

    public TypeWrappedDeserializer(PR pr, JsonDeserializer jsonDeserializer) {
        this.A00 = pr;
        this.A01 = jsonDeserializer;
    }
}
