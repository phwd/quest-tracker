package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0264Od;
import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.Oi;
import com.fasterxml.jackson.databind.JsonDeserializer;

public abstract class DelegatingDeserializer extends StdDeserializer implements AbstractC0264Od, Oi {
    public static final long serialVersionUID = 1;
    public final JsonDeserializer _delegatee;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        return this._delegatee.A0C(qiVar, qrVar);
    }
}
