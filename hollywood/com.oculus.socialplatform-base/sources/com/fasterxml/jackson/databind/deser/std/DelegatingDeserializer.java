package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.AbstractC04230pb;
import X.AbstractC04280pi;
import X.C03620oC;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public abstract class DelegatingDeserializer extends StdDeserializer<Object> implements AbstractC04230pb, AbstractC04280pi {
    public static final long serialVersionUID = 1;
    public final JsonDeserializer<?> _delegatee;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0A(AbstractC02280iQ r2, AbstractC02210iH r3) throws IOException, C03620oC {
        return this._delegatee.A0A(r2, r3);
    }
}
