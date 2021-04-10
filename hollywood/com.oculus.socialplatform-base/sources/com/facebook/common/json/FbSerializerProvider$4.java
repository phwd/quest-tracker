package com.facebook.common.json;

import X.AbstractC02120i3;
import X.AbstractC02300iS;
import X.AnonymousClass0Nh;
import X.C03620oC;
import com.facebook.graphservice.staticcontext.StaticGraphServiceFactory;
import com.fasterxml.jackson.databind.JsonSerializer;
import java.io.IOException;

public class FbSerializerProvider$4 extends JsonSerializer<AnonymousClass0Nh> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0iS, X.0i3] */
    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final /* bridge */ /* synthetic */ void serialize(AnonymousClass0Nh r3, AbstractC02300iS r4, AbstractC02120i3 r5) throws IOException, C03620oC {
        StaticGraphServiceFactory.getTreeSerializer();
        throw new NullPointerException("serializeTreeToByteBuffer");
    }
}
