package com.fasterxml.jackson.datatype.guava.deser;

import X.AbstractC02570aK;
import X.AbstractC02580aL;
import X.AbstractC06520n2;
import X.AnonymousClass0aG;
import X.AnonymousClass0aT;
import X.AnonymousClass0o3;
import X.C006606d;
import X.C05910ld;
import X.EnumC05930lf;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;

public abstract class GuavaCollectionDeserializer<T> extends StdDeserializer<T> implements AbstractC06520n2 {
    public final C006606d _containerType;
    public final AnonymousClass0o3 _typeDeserializerForValue;
    public final JsonDeserializer<?> _valueDeserializer;

    public abstract GuavaCollectionDeserializer<T> A0P(AnonymousClass0o3 v, JsonDeserializer<?> jsonDeserializer);

    public abstract T A0Q(AnonymousClass0aT v, AbstractC02570aK v2) throws IOException, C05910ld;

    @Override // X.AbstractC06520n2
    public final JsonDeserializer<?> A1w(AbstractC02570aK r4, AbstractC02580aL r5) throws AnonymousClass0aG {
        JsonDeserializer<?> jsonDeserializer = this._valueDeserializer;
        AnonymousClass0o3 r1 = this._typeDeserializerForValue;
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

    public GuavaCollectionDeserializer(C006606d r1, AnonymousClass0o3 r2, JsonDeserializer<?> jsonDeserializer) {
        super(r1);
        this._containerType = r1;
        this._typeDeserializerForValue = r2;
        this._valueDeserializer = jsonDeserializer;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final T A09(AnonymousClass0aT r3, AbstractC02570aK r4) throws IOException, C05910ld {
        if (r3.A0G() == EnumC05930lf.START_ARRAY) {
            return A0Q(r3, r4);
        }
        throw r4.A0B(this._containerType._class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AnonymousClass0aT r2, AbstractC02570aK r3, AnonymousClass0o3 r4) throws IOException, C05910ld {
        return r4.A08(r2, r3);
    }
}
