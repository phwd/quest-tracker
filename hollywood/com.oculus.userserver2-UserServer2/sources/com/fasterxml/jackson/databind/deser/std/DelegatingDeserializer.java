package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC00010j;
import X.AbstractC0122Rd;
import X.AnonymousClass1V;
import X.AnonymousClass9r;
import X.Rn;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public abstract class DelegatingDeserializer extends StdDeserializer<Object> implements AnonymousClass1V, AbstractC00010j {
    public static final long serialVersionUID = 1;
    public final JsonDeserializer<?> _delegatee;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        return this._delegatee.A03(rn, rd);
    }
}
