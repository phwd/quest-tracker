package com.fasterxml.jackson.datatype.guava.deser;

import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.AbstractC04520qa;
import X.AbstractC05160uM;
import X.AnonymousClass03E;
import X.C03620oC;
import X.EnumC03640oE;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.ImmutableCollection;
import java.io.IOException;

public abstract class GuavaImmutableCollectionDeserializer<T extends ImmutableCollection<Object>> extends GuavaCollectionDeserializer<T> {
    public abstract AbstractC05160uM<Object> A0R();

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public final Object A0Q(AbstractC02280iQ r6, AbstractC02210iH r7) throws IOException, C03620oC {
        Object A0B;
        JsonDeserializer<?> jsonDeserializer = this._valueDeserializer;
        AbstractC04520qa r3 = this._typeDeserializerForValue;
        AbstractC05160uM<Object> A0R = A0R();
        while (true) {
            EnumC03640oE A0j = r6.A0j();
            if (A0j == EnumC03640oE.END_ARRAY) {
                return A0R.build();
            }
            if (A0j == EnumC03640oE.VALUE_NULL) {
                A0B = null;
            } else if (r3 == null) {
                A0B = jsonDeserializer.A0A(r6, r7);
            } else {
                A0B = jsonDeserializer.A0B(r6, r7, r3);
            }
            A0R.add(A0B);
        }
    }

    public GuavaImmutableCollectionDeserializer(AnonymousClass03E r1, AbstractC04520qa r2, JsonDeserializer<?> jsonDeserializer) {
        super(r1, r2, jsonDeserializer);
    }
}
