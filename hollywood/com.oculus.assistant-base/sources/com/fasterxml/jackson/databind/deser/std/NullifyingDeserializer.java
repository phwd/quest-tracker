package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC1014qi;
import X.AbstractC1022qr;

public class NullifyingDeserializer extends StdDeserializer {
    public static final NullifyingDeserializer A00 = new NullifyingDeserializer();
    public static final long serialVersionUID = 1;

    public NullifyingDeserializer() {
        super(Object.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        qiVar.A0T();
        return null;
    }
}
