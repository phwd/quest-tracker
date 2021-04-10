package com.facebook.common.json;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.C1013qh;
import X.NX;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.RegularImmutableMap;

public class ImmutableMapDeserializer extends JsonDeserializer {
    public boolean A00;
    public final Class A01;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        NX A0U;
        if (!qiVar.A0g() || (A0U = qiVar.A0U()) == NX.VALUE_NULL) {
            qiVar.A0T();
            return RegularImmutableMap.A03;
        } else if (A0U == NX.START_OBJECT) {
            if (!this.A00) {
                if (this.A01 != String.class) {
                    throw new NullPointerException("findDeserializer");
                }
                this.A00 = true;
            }
            throw new NullPointerException("findDeserializer");
        } else {
            throw new C1013qh("Failed to deserialize to a map - missing start_object token", qiVar.A0R());
        }
    }
}
