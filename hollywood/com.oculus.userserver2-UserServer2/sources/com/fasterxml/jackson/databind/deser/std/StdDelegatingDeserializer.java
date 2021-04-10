package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC00010j;
import X.AbstractC0122Rd;
import X.AnonymousClass1V;
import X.AnonymousClass80;
import X.AnonymousClass9r;
import X.RZ;
import X.Rn;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public final class StdDelegatingDeserializer<T> extends StdDeserializer<T> implements AnonymousClass1V, AbstractC00010j {
    public static final long serialVersionUID = 1;
    public final AnonymousClass80<Object, T> _converter;
    public final JsonDeserializer<Object> _delegateDeserializer;
    public final RZ _delegateType;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final T A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        if (this._delegateDeserializer.A03(rn, rd) == null) {
            return null;
        }
        throw null;
    }
}
