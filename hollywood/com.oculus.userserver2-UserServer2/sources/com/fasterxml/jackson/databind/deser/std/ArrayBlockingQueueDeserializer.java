package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0122Rd;
import X.AnonymousClass9p;
import X.AnonymousClass9r;
import X.B3;
import X.Rn;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;

public final class ArrayBlockingQueueDeserializer extends CollectionDeserializer {
    public static final long serialVersionUID = 5471961369237518580L;

    @Override // com.fasterxml.jackson.databind.deser.std.CollectionDeserializer
    public final Collection<Object> A09(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        Object A03;
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            jsonDeserializer.A03(rn, rd);
            throw null;
        }
        B3 b3 = (B3) rn;
        if (b3.A00 == AnonymousClass9p.VALUE_STRING && rn.A09().length() == 0) {
            throw null;
        } else if (b3.A00 == AnonymousClass9p.START_ARRAY) {
            ArrayList arrayList = new ArrayList();
            JsonDeserializer<Object> jsonDeserializer2 = this._valueDeserializer;
            while (true) {
                AnonymousClass9p A04 = rn.A04();
                if (A04 == AnonymousClass9p.END_ARRAY) {
                    return new ArrayBlockingQueue(arrayList.size(), false, arrayList);
                }
                if (A04 == AnonymousClass9p.VALUE_NULL) {
                    A03 = null;
                } else {
                    A03 = jsonDeserializer2.A03(rn, rd);
                }
                arrayList.add(A03);
            }
        } else {
            new ArrayBlockingQueue(1);
            throw null;
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer, com.fasterxml.jackson.databind.deser.std.CollectionDeserializer
    public final /* bridge */ /* synthetic */ Object A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        return A03(rn, rd);
    }
}
