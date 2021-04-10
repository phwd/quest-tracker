package com.facebook.common.json;

import X.AbstractC02120i3;
import X.AbstractC02300iS;
import X.AnonymousClass0IH;
import X.AnonymousClass0Iq;
import X.C03620oC;
import com.fasterxml.jackson.databind.JsonSerializer;
import java.io.IOException;
import java.util.Collection;

public class FbSerializerProvider$1 extends JsonSerializer<Collection> {
    public final /* synthetic */ AnonymousClass0IH A00;

    public FbSerializerProvider$1(AnonymousClass0IH r1) {
        this.A00 = r1;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0iS, X.0i3] */
    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(Collection collection, AbstractC02300iS r4, AbstractC02120i3 r5) throws IOException, C03620oC {
        Collection<Object> collection2 = collection;
        if (collection2 != null) {
            r4.A0H();
            for (Object obj : collection2) {
                AnonymousClass0Iq.A00(r4, r5, obj);
            }
            r4.A0E();
        }
    }
}
