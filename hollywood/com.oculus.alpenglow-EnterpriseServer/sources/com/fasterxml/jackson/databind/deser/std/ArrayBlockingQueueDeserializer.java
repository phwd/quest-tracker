package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.AnonymousClass0aI;
import X.AnonymousClass0aT;
import X.AnonymousClass0nB;
import X.AnonymousClass0o3;
import X.C05910ld;
import X.EnumC05930lf;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;

public final class ArrayBlockingQueueDeserializer extends CollectionDeserializer {
    public static final long serialVersionUID = 5471961369237518580L;

    @Override // com.fasterxml.jackson.databind.deser.std.CollectionDeserializer
    public final CollectionDeserializer A0Q(JsonDeserializer jsonDeserializer, JsonDeserializer jsonDeserializer2, AnonymousClass0o3 r9) {
        if (jsonDeserializer == this._delegateDeserializer && jsonDeserializer2 == this._valueDeserializer && r9 == this._valueTypeDeserializer) {
            return this;
        }
        return new ArrayBlockingQueueDeserializer(this._collectionType, jsonDeserializer2, r9, this._valueInstantiator, jsonDeserializer);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.CollectionDeserializer
    /* renamed from: A0R */
    public final Collection<Object> A09(AnonymousClass0aT r3, AbstractC02570aK r4) throws IOException, C05910ld {
        Object A0A;
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            A0A = this._valueInstantiator.A09(r4, jsonDeserializer.A09(r3, r4));
        } else {
            if (r3.A0G() == EnumC05930lf.VALUE_STRING) {
                String A0P = r3.A0P();
                if (A0P.length() == 0) {
                    A0A = this._valueInstantiator.A0A(r4, A0P);
                }
            }
            return A0A(r3, r4, null);
        }
        return (Collection) A0A;
    }

    @Override // com.fasterxml.jackson.databind.deser.std.CollectionDeserializer
    /* renamed from: A0S */
    public final Collection<Object> A0A(AnonymousClass0aT r6, AbstractC02570aK r7, Collection<Object> collection) throws IOException, C05910ld {
        Object A0C;
        if (!r6.A0V()) {
            ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(1);
            A0T(r6, r7, arrayBlockingQueue);
            return arrayBlockingQueue;
        }
        ArrayList arrayList = new ArrayList();
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        AnonymousClass0o3 r2 = this._valueTypeDeserializer;
        while (true) {
            EnumC05930lf A0a = r6.A0a();
            if (A0a == EnumC05930lf.END_ARRAY) {
                break;
            }
            if (A0a == EnumC05930lf.VALUE_NULL) {
                A0C = null;
            } else if (r2 == null) {
                A0C = jsonDeserializer.A09(r6, r7);
            } else {
                A0C = jsonDeserializer.A0C(r6, r7, r2);
            }
            arrayList.add(A0C);
        }
        if (collection == null) {
            return new ArrayBlockingQueue(arrayList.size(), false, arrayList);
        }
        collection.addAll(arrayList);
        return collection;
    }

    public ArrayBlockingQueueDeserializer(AnonymousClass0aI r1, JsonDeserializer<Object> jsonDeserializer, AnonymousClass0o3 r3, AnonymousClass0nB r4, JsonDeserializer<Object> jsonDeserializer2) {
        super(r1, jsonDeserializer, r3, r4, jsonDeserializer2);
    }
}
