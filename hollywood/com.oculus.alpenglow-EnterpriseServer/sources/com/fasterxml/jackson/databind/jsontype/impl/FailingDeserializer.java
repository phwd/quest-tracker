package com.fasterxml.jackson.databind.jsontype.impl;

import X.AbstractC02570aK;
import X.AnonymousClass0aG;
import X.AnonymousClass0aT;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public final class FailingDeserializer extends StdDeserializer<Object> {
    public static final long serialVersionUID = 1;
    public final String _message = "No _valueDeserializer assigned";

    public FailingDeserializer() {
        super(Object.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A09(AnonymousClass0aT r3, AbstractC02570aK r4) throws AnonymousClass0aG {
        throw AnonymousClass0aG.A00(r4.A00, this._message);
    }
}
