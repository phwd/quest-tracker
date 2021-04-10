package com.fasterxml.jackson.databind.jsontype.impl;

import X.AbstractC0122Rd;
import X.RW;
import X.Rn;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public final class FailingDeserializer extends StdDeserializer<Object> {
    public static final long serialVersionUID = 1;
    public final String _message = "No _valueDeserializer assigned";

    public FailingDeserializer() {
        super(Object.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A03(Rn rn, AbstractC0122Rd rd) throws RW {
        throw null;
    }
}
