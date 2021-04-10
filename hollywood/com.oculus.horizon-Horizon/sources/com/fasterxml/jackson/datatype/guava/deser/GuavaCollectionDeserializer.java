package com.fasterxml.jackson.datatype.guava.deser;

import X.AbstractC04020gg;
import X.AbstractC04030gh;
import X.AbstractC04100gp;
import X.AbstractC05430l6;
import X.AnonymousClass02Z;
import X.AnonymousClass0jg;
import X.AnonymousClass0m9;
import X.C03990gZ;
import X.EnumC04820ji;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;

public abstract class GuavaCollectionDeserializer<T> extends StdDeserializer<T> implements AbstractC05430l6 {
    public final AnonymousClass02Z _containerType;
    public final AnonymousClass0m9 _typeDeserializerForValue;
    public final JsonDeserializer<?> _valueDeserializer;

    public abstract GuavaCollectionDeserializer<T> A0P(AnonymousClass0m9 v, JsonDeserializer<?> jsonDeserializer);

    public abstract T A0Q(AbstractC04100gp v, AbstractC04020gg v2) throws IOException, AnonymousClass0jg;

    @Override // X.AbstractC05430l6
    public final JsonDeserializer<?> A21(AbstractC04020gg r4, AbstractC04030gh r5) throws C03990gZ {
        JsonDeserializer<?> jsonDeserializer = this._valueDeserializer;
        AnonymousClass0m9 r1 = this._typeDeserializerForValue;
        if (jsonDeserializer == null) {
            jsonDeserializer = r4.A05(this._containerType.A03(), r5);
        }
        if (r1 != null) {
            r1 = r1.A04(r5);
        }
        if (jsonDeserializer == this._valueDeserializer && r1 == this._typeDeserializerForValue) {
            return this;
        }
        return A0P(r1, jsonDeserializer);
    }

    public GuavaCollectionDeserializer(AnonymousClass02Z r1, AnonymousClass0m9 r2, JsonDeserializer<?> jsonDeserializer) {
        super(r1);
        this._containerType = r1;
        this._typeDeserializerForValue = r2;
        this._valueDeserializer = jsonDeserializer;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final T A09(AbstractC04100gp r3, AbstractC04020gg r4) throws IOException, AnonymousClass0jg {
        if (r3.A0a() == EnumC04820ji.START_ARRAY) {
            return A0Q(r3, r4);
        }
        throw null;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC04100gp r2, AbstractC04020gg r3, AnonymousClass0m9 r4) throws IOException, AnonymousClass0jg {
        return r4.A08(r2, r3);
    }
}
