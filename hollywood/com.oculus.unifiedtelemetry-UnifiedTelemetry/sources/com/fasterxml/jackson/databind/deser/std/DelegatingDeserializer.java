package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.AbstractC0264Yo;
import X.Zy;
import X.q0;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public abstract class DelegatingDeserializer extends StdDeserializer<Object> implements Zy, AbstractC0264Yo {
    public static final long serialVersionUID = 1;
    public final JsonDeserializer<?> _delegatee;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        return this._delegatee.A09(ww, wn);
    }
}
