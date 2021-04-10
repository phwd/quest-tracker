package com.facebook.common.json;

import X.AbstractC02190iF;
import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.AnonymousClass0It;
import X.AnonymousClass0P3;
import X.C01210Vd;
import X.C02290iR;
import X.C03620oC;
import X.EnumC03640oE;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.lang.reflect.Type;

public class ImmutableListDeserializer<T> extends JsonDeserializer<ImmutableList<T>> {
    public JsonDeserializer<T> A00;
    public final AbstractC02190iF A01;
    public final Class<T> A02;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0A(AbstractC02280iQ r5, AbstractC02210iH r6) throws IOException, C03620oC {
        EnumC03640oE A0i;
        JsonDeserializer<T> A07;
        C01210Vd r3 = (C01210Vd) r5.A0N();
        if (!r5.A0o() || (A0i = r5.A0i()) == EnumC03640oE.VALUE_NULL) {
            r5.A0h();
            return ImmutableList.of();
        } else if (A0i == EnumC03640oE.START_ARRAY) {
            if (this.A00 == null) {
                Type type = this.A02;
                if (type == null) {
                    type = this.A01;
                }
                Type type2 = type;
                if (type2 instanceof Class) {
                    A07 = r3.A08(r6, (Class) type2);
                } else {
                    A07 = r3.A07(r6, r3._typeFactory.A09(type2, null));
                }
                this.A00 = A07;
            }
            ImmutableList.Builder A022 = ImmutableList.A02();
            while (AnonymousClass0It.A00(r5) != EnumC03640oE.END_ARRAY) {
                try {
                    T A0A = this.A00.A0A(r5, r6);
                    if (A0A != null) {
                        A022.add((Object) A0A);
                    }
                } catch (AnonymousClass0P3 unused) {
                }
            }
            return A022.build();
        } else {
            throw new C02290iR("Failed to deserialize to a list - missing start_array token", r5.A0V());
        }
    }

    public ImmutableListDeserializer(AbstractC02190iF r3) {
        this.A02 = null;
        this.A01 = r3.A06(0);
        this.A00 = null;
    }

    public ImmutableListDeserializer(Class<T> cls) {
        this.A02 = cls;
        this.A01 = null;
        this.A00 = null;
    }
}
