package com.fasterxml.jackson.datatype.guava.deser;

import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.AnonymousClass2W;
import X.AnonymousClass3J;
import X.EnumC0470q2;
import X.V4;
import X.q0;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.ImmutableCollection;
import java.io.IOException;

public abstract class GuavaImmutableCollectionDeserializer<T extends ImmutableCollection<Object>> extends GuavaCollectionDeserializer<T> {
    public abstract AnonymousClass3J<Object> A0R();

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public final Object A0Q(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        Object A0C;
        JsonDeserializer<?> jsonDeserializer = this._valueDeserializer;
        V4 v4 = this._typeDeserializerForValue;
        AnonymousClass3J<Object> A0R = A0R();
        while (true) {
            EnumC0470q2 A0a = ww.A0a();
            if (A0a == EnumC0470q2.END_ARRAY) {
                return A0R.build();
            }
            if (A0a == EnumC0470q2.VALUE_NULL) {
                A0C = null;
            } else if (v4 == null) {
                A0C = jsonDeserializer.A09(ww, wn);
            } else {
                A0C = jsonDeserializer.A0C(ww, wn, v4);
            }
            A0R.add(A0C);
        }
    }

    public GuavaImmutableCollectionDeserializer(AnonymousClass2W r1, V4 v4, JsonDeserializer<?> jsonDeserializer) {
        super(r1, v4, jsonDeserializer);
    }
}
