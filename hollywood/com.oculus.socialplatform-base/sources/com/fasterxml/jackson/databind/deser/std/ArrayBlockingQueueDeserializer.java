package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02190iF;
import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.AbstractC04300pk;
import X.AbstractC04520qa;
import X.C03620oC;
import X.EnumC03640oE;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueDeserializer extends CollectionDeserializer {
    public static final long serialVersionUID = 5471961369237518580L;

    @Override // com.fasterxml.jackson.databind.deser.std.CollectionDeserializer
    public final CollectionDeserializer A0Q(JsonDeserializer jsonDeserializer, JsonDeserializer jsonDeserializer2, AbstractC04520qa r9) {
        if (jsonDeserializer == this._delegateDeserializer && jsonDeserializer2 == this._valueDeserializer && r9 == this._valueTypeDeserializer) {
            return this;
        }
        return new ArrayBlockingQueueDeserializer(this._collectionType, jsonDeserializer2, r9, this._valueInstantiator, jsonDeserializer);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.CollectionDeserializer
    public final Collection<Object> A0R(AbstractC02280iQ r3, AbstractC02210iH r4) throws IOException, C03620oC {
        Object A0A;
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            A0A = this._valueInstantiator.A09(r4, jsonDeserializer.A0A(r3, r4));
        } else {
            if (r3.A0i() == EnumC03640oE.VALUE_STRING) {
                String A0m = r3.A0m();
                if (A0m.length() == 0) {
                    A0A = this._valueInstantiator.A0A(r4, A0m);
                }
            }
            return A0C(r3, r4, null);
        }
        return (Collection) A0A;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer, com.fasterxml.jackson.databind.deser.std.CollectionDeserializer
    public final /* bridge */ /* synthetic */ Object A0A(AbstractC02280iQ r2, AbstractC02210iH r3) throws IOException, C03620oC {
        return A0A(r2, r3);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.CollectionDeserializer
    public final Collection<Object> A0S(AbstractC02280iQ r6, AbstractC02210iH r7, Collection<Object> collection) throws IOException, C03620oC {
        Object A0B;
        if (!r6.A0K()) {
            ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(1);
            A0T(r6, r7, arrayBlockingQueue);
            return arrayBlockingQueue;
        }
        ArrayList arrayList = new ArrayList();
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        AbstractC04520qa r2 = this._valueTypeDeserializer;
        while (true) {
            EnumC03640oE A0j = r6.A0j();
            if (A0j == EnumC03640oE.END_ARRAY) {
                break;
            }
            if (A0j == EnumC03640oE.VALUE_NULL) {
                A0B = null;
            } else if (r2 == null) {
                A0B = jsonDeserializer.A0A(r6, r7);
            } else {
                A0B = jsonDeserializer.A0B(r6, r7, r2);
            }
            arrayList.add(A0B);
        }
        if (collection == null) {
            return new ArrayBlockingQueue(arrayList.size(), false, arrayList);
        }
        collection.addAll(arrayList);
        return collection;
    }

    public ArrayBlockingQueueDeserializer(AbstractC02190iF r1, JsonDeserializer<Object> jsonDeserializer, AbstractC04520qa r3, AbstractC04300pk r4, JsonDeserializer<Object> jsonDeserializer2) {
        super(r1, jsonDeserializer, r3, r4, jsonDeserializer2);
    }
}
