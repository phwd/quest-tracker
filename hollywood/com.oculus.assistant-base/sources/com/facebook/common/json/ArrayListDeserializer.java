package com.facebook.common.json;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.C1013qh;
import X.NX;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.util.ArrayList;

public class ArrayListDeserializer extends JsonDeserializer {
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        NX A0U;
        if (!qiVar.A0g() || (A0U = qiVar.A0U()) == NX.VALUE_NULL) {
            qiVar.A0T();
            return new ArrayList();
        } else if (A0U == NX.START_ARRAY) {
            throw new NullPointerException("findDeserializer");
        } else {
            throw new C1013qh("Failed to deserialize to a list - missing start_array token", qiVar.A0R());
        }
    }
}
