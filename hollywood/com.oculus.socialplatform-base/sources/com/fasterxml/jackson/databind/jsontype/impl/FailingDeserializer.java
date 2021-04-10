package com.fasterxml.jackson.databind.jsontype.impl;

import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.C02180iD;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class FailingDeserializer extends StdDeserializer<Object> {
    public static final long serialVersionUID = 1;
    public final String _message = "No _valueDeserializer assigned";

    public FailingDeserializer() {
        super(Object.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0A(AbstractC02280iQ r3, AbstractC02210iH r4) throws C02180iD {
        throw C02180iD.A00(r4.A00, this._message);
    }
}
