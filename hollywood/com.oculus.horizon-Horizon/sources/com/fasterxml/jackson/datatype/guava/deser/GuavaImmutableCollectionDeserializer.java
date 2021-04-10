package com.fasterxml.jackson.datatype.guava.deser;

import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AbstractC06730pi;
import X.AnonymousClass02Z;
import X.AnonymousClass0jg;
import X.AnonymousClass0m9;
import X.EnumC04820ji;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.ImmutableCollection;
import java.io.IOException;

public abstract class GuavaImmutableCollectionDeserializer<T extends ImmutableCollection<Object>> extends GuavaCollectionDeserializer<T> {
    public abstract AbstractC06730pi<Object> A0R();

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaCollectionDeserializer
    public final Object A0Q(AbstractC04100gp r6, AbstractC04020gg r7) throws IOException, AnonymousClass0jg {
        Object A0C;
        JsonDeserializer<?> jsonDeserializer = this._valueDeserializer;
        AnonymousClass0m9 r3 = this._typeDeserializerForValue;
        AbstractC06730pi<Object> A0R = A0R();
        while (true) {
            EnumC04820ji A0b = r6.A0b();
            if (A0b == EnumC04820ji.END_ARRAY) {
                return A0R.build();
            }
            if (A0b == EnumC04820ji.VALUE_NULL) {
                A0C = null;
            } else if (r3 == null) {
                A0C = jsonDeserializer.A09(r6, r7);
            } else {
                A0C = jsonDeserializer.A0C(r6, r7, r3);
            }
            A0R.add(A0C);
        }
    }

    public GuavaImmutableCollectionDeserializer(AnonymousClass02Z r1, AnonymousClass0m9 r2, JsonDeserializer<?> jsonDeserializer) {
        super(r1, r2, jsonDeserializer);
    }
}
