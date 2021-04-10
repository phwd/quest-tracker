package com.fasterxml.jackson.databind.jsontype.impl;

import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.C0223Wj;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public final class FailingDeserializer extends StdDeserializer<Object> {
    public static final long serialVersionUID = 1;
    public final String _message = "No _valueDeserializer assigned";

    public FailingDeserializer() {
        super(Object.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws C0223Wj {
        throw C0223Wj.A00(wn.A00, this._message);
    }
}
