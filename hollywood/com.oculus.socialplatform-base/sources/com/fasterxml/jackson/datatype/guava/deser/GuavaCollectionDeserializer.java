package com.fasterxml.jackson.datatype.guava.deser;

import X.AbstractC02210iH;
import X.AbstractC02220iI;
import X.AbstractC02280iQ;
import X.AbstractC04230pb;
import X.AbstractC04520qa;
import X.AnonymousClass03E;
import X.C02180iD;
import X.C03620oC;
import X.EnumC03640oE;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;

public abstract class GuavaCollectionDeserializer<T> extends StdDeserializer<T> implements AbstractC04230pb {
    public final AnonymousClass03E _containerType;
    public final AbstractC04520qa _typeDeserializerForValue;
    public final JsonDeserializer<?> _valueDeserializer;

    public abstract GuavaCollectionDeserializer<T> A0P(AbstractC04520qa v, JsonDeserializer<?> jsonDeserializer);

    public abstract T A0Q(AbstractC02280iQ v, AbstractC02210iH v2) throws IOException, C03620oC;

    @Override // X.AbstractC04230pb
    public final JsonDeserializer<?> A2O(AbstractC02210iH r4, AbstractC02220iI r5) throws C02180iD {
        JsonDeserializer<?> jsonDeserializer = this._valueDeserializer;
        AbstractC04520qa r1 = this._typeDeserializerForValue;
        if (jsonDeserializer == null) {
            jsonDeserializer = r4.A09(this._containerType.A04(), r5);
        }
        if (r1 != null) {
            r1 = r1.A04(r5);
        }
        if (jsonDeserializer == this._valueDeserializer && r1 == this._typeDeserializerForValue) {
            return this;
        }
        return A0P(r1, jsonDeserializer);
    }

    public GuavaCollectionDeserializer(AnonymousClass03E r1, AbstractC04520qa r2, JsonDeserializer<?> jsonDeserializer) {
        super(r1);
        this._containerType = r1;
        this._typeDeserializerForValue = r2;
        this._valueDeserializer = jsonDeserializer;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final T A0A(AbstractC02280iQ r3, AbstractC02210iH r4) throws IOException, C03620oC {
        if (r3.A0i() == EnumC03640oE.START_ARRAY) {
            return A0Q(r3, r4);
        }
        throw r4.A0B(this._containerType._class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0B(AbstractC02280iQ r2, AbstractC02210iH r3, AbstractC04520qa r4) throws IOException, C03620oC {
        return r4.A08(r2, r3);
    }
}
