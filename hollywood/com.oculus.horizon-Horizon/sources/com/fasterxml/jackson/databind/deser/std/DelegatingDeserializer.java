package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AbstractC05430l6;
import X.AbstractC05470lE;
import X.AnonymousClass0jg;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public abstract class DelegatingDeserializer extends StdDeserializer<Object> implements AbstractC05430l6, AbstractC05470lE {
    public static final long serialVersionUID = 1;
    public final JsonDeserializer<?> _delegatee;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A09(AbstractC04100gp r2, AbstractC04020gg r3) throws IOException, AnonymousClass0jg {
        return this._delegatee.A09(r2, r3);
    }
}
