package com.fasterxml.jackson.databind.jsontype.impl;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.C1025qv;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class FailingDeserializer extends StdDeserializer {
    public static final long serialVersionUID = 1;
    public final String _message = "No _valueDeserializer assigned";

    public FailingDeserializer() {
        super(Object.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        throw C1025qv.A00(null, this._message);
    }
}
