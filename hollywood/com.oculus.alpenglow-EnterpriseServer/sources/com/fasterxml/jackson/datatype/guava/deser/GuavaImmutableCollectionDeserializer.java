package com.fasterxml.jackson.datatype.guava.deser;

import X.AbstractC02570aK;
import X.AbstractC07410rk;
import X.AnonymousClass0aT;
import X.AnonymousClass0o3;
import X.C006606d;
import X.C05910ld;
import X.EnumC05930lf;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.ImmutableCollection;
import java.io.IOException;

public abstract class GuavaImmutableCollectionDeserializer<T extends ImmutableCollection<Object>> extends GuavaCollectionDeserializer<T> {
    public abstract AbstractC07410rk<Object> A0R();

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public final Object A0Q(AnonymousClass0aT r6, AbstractC02570aK r7) throws IOException, C05910ld {
        Object A0C;
        JsonDeserializer<?> jsonDeserializer = this._valueDeserializer;
        AnonymousClass0o3 r3 = this._typeDeserializerForValue;
        AbstractC07410rk<Object> A0R = A0R();
        while (true) {
            EnumC05930lf A0a = r6.A0a();
            if (A0a == EnumC05930lf.END_ARRAY) {
                return A0R.build();
            }
            if (A0a == EnumC05930lf.VALUE_NULL) {
                A0C = null;
            } else if (r3 == null) {
                A0C = jsonDeserializer.A09(r6, r7);
            } else {
                A0C = jsonDeserializer.A0C(r6, r7, r3);
            }
            A0R.add(A0C);
        }
    }

    public GuavaImmutableCollectionDeserializer(C006606d r1, AnonymousClass0o3 r2, JsonDeserializer<?> jsonDeserializer) {
        super(r1, r2, jsonDeserializer);
    }
}
