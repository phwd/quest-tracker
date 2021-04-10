package com.fasterxml.jackson.databind.jsontype.impl;

import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.C03990gZ;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public final class FailingDeserializer extends StdDeserializer<Object> {
    public static final long serialVersionUID = 1;
    public final String _message = "No _valueDeserializer assigned";

    public FailingDeserializer() {
        super(Object.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A09(AbstractC04100gp r3, AbstractC04020gg r4) throws C03990gZ {
        throw C03990gZ.A00(null, this._message);
    }
}
