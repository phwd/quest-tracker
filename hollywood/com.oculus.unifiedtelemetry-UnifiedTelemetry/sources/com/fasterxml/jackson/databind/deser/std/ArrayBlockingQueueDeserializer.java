package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0224Wl;
import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.AbstractC0262Ym;
import X.EnumC0470q2;
import X.V4;
import X.q0;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;

public final class ArrayBlockingQueueDeserializer extends CollectionDeserializer {
    public static final long serialVersionUID = 5471961369237518580L;

    @Override // com.fasterxml.jackson.databind.deser.std.CollectionDeserializer
    public final CollectionDeserializer A0Q(JsonDeserializer jsonDeserializer, JsonDeserializer jsonDeserializer2, V4 v4) {
        if (jsonDeserializer == this._delegateDeserializer && jsonDeserializer2 == this._valueDeserializer && v4 == this._valueTypeDeserializer) {
            return this;
        }
        return new ArrayBlockingQueueDeserializer(this._collectionType, jsonDeserializer2, v4, this._valueInstantiator, jsonDeserializer);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.CollectionDeserializer
    public final Collection<Object> A0R(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        Object A0A;
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            A0A = this._valueInstantiator.A09(wn, jsonDeserializer.A09(ww, wn));
        } else {
            if (ww.A0Z() == EnumC0470q2.VALUE_STRING) {
                String A0d = ww.A0d();
                if (A0d.length() == 0) {
                    A0A = this._valueInstantiator.A0A(wn, A0d);
                }
            }
            return A0A(ww, wn, null);
        }
        return (Collection) A0A;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer, com.fasterxml.jackson.databind.deser.std.CollectionDeserializer
    public final /* bridge */ /* synthetic */ Object A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        return A09(ww, wn);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.CollectionDeserializer
    public final Collection<Object> A0S(AbstractC0232Ww ww, AbstractC0226Wn wn, Collection<Object> collection) throws IOException, q0 {
        Object A0C;
        if (!ww.A0F()) {
            ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(1);
            A0T(ww, wn, arrayBlockingQueue);
            return arrayBlockingQueue;
        }
        ArrayList arrayList = new ArrayList();
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        V4 v4 = this._valueTypeDeserializer;
        while (true) {
            EnumC0470q2 A0a = ww.A0a();
            if (A0a == EnumC0470q2.END_ARRAY) {
                break;
            }
            if (A0a == EnumC0470q2.VALUE_NULL) {
                A0C = null;
            } else if (v4 == null) {
                A0C = jsonDeserializer.A09(ww, wn);
            } else {
                A0C = jsonDeserializer.A0C(ww, wn, v4);
            }
            arrayList.add(A0C);
        }
        if (collection == null) {
            return new ArrayBlockingQueue(arrayList.size(), false, arrayList);
        }
        collection.addAll(arrayList);
        return collection;
    }

    public ArrayBlockingQueueDeserializer(AbstractC0224Wl wl, JsonDeserializer<Object> jsonDeserializer, V4 v4, AbstractC0262Ym ym, JsonDeserializer<Object> jsonDeserializer2) {
        super(wl, jsonDeserializer, v4, ym, jsonDeserializer2);
    }
}
