package com.facebook.common.json;

import X.AbstractC02120i3;
import X.AbstractC02300iS;
import X.C03620oC;
import com.fasterxml.jackson.databind.JsonSerializer;
import java.io.IOException;

public class FbSerializerProvider$5 extends JsonSerializer<Enum> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0iS, X.0i3] */
    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(Enum r2, AbstractC02300iS r3, AbstractC02120i3 r4) throws IOException, C03620oC {
        r3.A0U(r2.name());
    }
}
