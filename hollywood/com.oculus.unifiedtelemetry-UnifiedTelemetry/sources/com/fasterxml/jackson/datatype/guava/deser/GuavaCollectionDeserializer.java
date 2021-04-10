package com.fasterxml.jackson.datatype.guava.deser;

import X.AbstractC0226Wn;
import X.AbstractC0227Wo;
import X.AbstractC0232Ww;
import X.AnonymousClass2W;
import X.C0223Wj;
import X.EnumC0470q2;
import X.V4;
import X.Zy;
import X.q0;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;

public abstract class GuavaCollectionDeserializer<T> extends StdDeserializer<T> implements Zy {
    public final AnonymousClass2W _containerType;
    public final V4 _typeDeserializerForValue;
    public final JsonDeserializer<?> _valueDeserializer;

    public abstract GuavaCollectionDeserializer<T> A0P(V4 v4, JsonDeserializer<?> jsonDeserializer);

    public abstract T A0Q(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0;

    @Override // X.Zy
    public final JsonDeserializer<?> A1g(AbstractC0226Wn wn, AbstractC0227Wo wo) throws C0223Wj {
        JsonDeserializer<?> jsonDeserializer = this._valueDeserializer;
        V4 v4 = this._typeDeserializerForValue;
        if (jsonDeserializer == null) {
            jsonDeserializer = wn.A06(this._containerType.A03(), wo);
        }
        if (v4 != null) {
            v4 = v4.A04(wo);
        }
        if (jsonDeserializer == this._valueDeserializer && v4 == this._typeDeserializerForValue) {
            return this;
        }
        return A0P(v4, jsonDeserializer);
    }

    public GuavaCollectionDeserializer(AnonymousClass2W r1, V4 v4, JsonDeserializer<?> jsonDeserializer) {
        super(r1);
        this._containerType = r1;
        this._typeDeserializerForValue = v4;
        this._valueDeserializer = jsonDeserializer;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final T A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        if (ww.A0Z() == EnumC0470q2.START_ARRAY) {
            return A0Q(ww, wn);
        }
        throw wn.A08(this._containerType._class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC0232Ww ww, AbstractC0226Wn wn, V4 v4) throws IOException, q0 {
        return v4.A08(ww, wn);
    }
}
