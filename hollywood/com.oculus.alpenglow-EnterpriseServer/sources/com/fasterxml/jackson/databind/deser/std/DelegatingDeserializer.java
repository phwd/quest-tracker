package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.AbstractC06520n2;
import X.AbstractC06550n9;
import X.AnonymousClass0aT;
import X.C05910ld;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public abstract class DelegatingDeserializer extends StdDeserializer<Object> implements AbstractC06520n2, AbstractC06550n9 {
    public static final long serialVersionUID = 1;
    public final JsonDeserializer<?> _delegatee;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A09(AnonymousClass0aT r2, AbstractC02570aK r3) throws IOException, C05910ld {
        return this._delegatee.A09(r2, r3);
    }
}
