package com.fasterxml.jackson.datatype.guava.deser;

import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.AbstractC04520qa;
import X.AnonymousClass03D;
import X.AnonymousClass0p6;
import X.C03620oC;
import X.EnumC03640oE;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;

public abstract class GuavaImmutableMapDeserializer<T extends ImmutableMap<Object, Object>> extends GuavaMapDeserializer<T> {
    public abstract ImmutableMap.Builder<Object, Object> A0H();

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaMapDeserializer
    public final Object A0G(AbstractC02280iQ r8, AbstractC02210iH r9) throws IOException, C03620oC {
        Object A0B;
        AnonymousClass0p6 r6 = this.A01;
        JsonDeserializer<?> jsonDeserializer = this.A00;
        AbstractC04520qa r4 = this.A02;
        ImmutableMap.Builder<Object, Object> A0H = A0H();
        while (r8.A0i() == EnumC03640oE.FIELD_NAME) {
            String A0l = r8.A0l();
            String str = A0l;
            if (r6 != null) {
                str = r6.A00(A0l, r9);
            }
            if (r8.A0j() == EnumC03640oE.VALUE_NULL) {
                A0B = null;
            } else if (r4 == null) {
                A0B = jsonDeserializer.A0A(r8, r9);
            } else {
                A0B = jsonDeserializer.A0B(r8, r9, r4);
            }
            A0H.put(str, A0B);
            r8.A0j();
        }
        return A0H.build();
    }

    public GuavaImmutableMapDeserializer(AnonymousClass03D r1, AnonymousClass0p6 r2, AbstractC04520qa r3, JsonDeserializer<?> jsonDeserializer) {
        super(r1, r2, r3, jsonDeserializer);
    }
}
