package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04000gb;
import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AnonymousClass0jg;
import X.AnonymousClass0lG;
import X.AnonymousClass0m9;
import X.EnumC04820ji;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;

public final class ArrayBlockingQueueDeserializer extends CollectionDeserializer {
    public static final long serialVersionUID = 5471961369237518580L;

    @Override // com.fasterxml.jackson.databind.deser.std.CollectionDeserializer
    public final CollectionDeserializer A0Q(JsonDeserializer jsonDeserializer, JsonDeserializer jsonDeserializer2, AnonymousClass0m9 r9) {
        if (jsonDeserializer == this._delegateDeserializer && jsonDeserializer2 == this._valueDeserializer && r9 == this._valueTypeDeserializer) {
            return this;
        }
        return new ArrayBlockingQueueDeserializer(this._collectionType, jsonDeserializer2, r9, this._valueInstantiator, jsonDeserializer);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.CollectionDeserializer
    public final Collection<Object> A0R(AbstractC04100gp r3, AbstractC04020gg r4) throws IOException, AnonymousClass0jg {
        Object A0A;
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            A0A = this._valueInstantiator.A09(r4, jsonDeserializer.A09(r3, r4));
        } else {
            if (r3.A0a() == EnumC04820ji.VALUE_STRING) {
                String A0e = r3.A0e();
                if (A0e.length() == 0) {
                    A0A = this._valueInstantiator.A0A(r4, A0e);
                }
            }
            return A0A(r3, r4, null);
        }
        return (Collection) A0A;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer, com.fasterxml.jackson.databind.deser.std.CollectionDeserializer
    public final /* bridge */ /* synthetic */ Object A09(AbstractC04100gp r2, AbstractC04020gg r3) throws IOException, AnonymousClass0jg {
        return A09(r2, r3);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.CollectionDeserializer
    public final Collection<Object> A0S(AbstractC04100gp r6, AbstractC04020gg r7, Collection<Object> collection) throws IOException, AnonymousClass0jg {
        Object A0C;
        if (!r6.A0G()) {
            ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(1);
            A0T(r6, r7, arrayBlockingQueue);
            return arrayBlockingQueue;
        }
        ArrayList arrayList = new ArrayList();
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        AnonymousClass0m9 r2 = this._valueTypeDeserializer;
        while (true) {
            EnumC04820ji A0b = r6.A0b();
            if (A0b == EnumC04820ji.END_ARRAY) {
                break;
            }
            if (A0b == EnumC04820ji.VALUE_NULL) {
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

    public ArrayBlockingQueueDeserializer(AbstractC04000gb r1, JsonDeserializer<Object> jsonDeserializer, AnonymousClass0m9 r3, AnonymousClass0lG r4, JsonDeserializer<Object> jsonDeserializer2) {
        super(r1, jsonDeserializer, r3, r4, jsonDeserializer2);
    }
}
