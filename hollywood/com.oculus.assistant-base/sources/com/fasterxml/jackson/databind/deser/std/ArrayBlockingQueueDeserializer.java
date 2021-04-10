package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.AbstractC1024qt;
import X.NX;
import X.Ok;
import X.PR;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.util.Collection;

public class ArrayBlockingQueueDeserializer extends CollectionDeserializer {
    public static final long serialVersionUID = 5471961369237518580L;

    @Override // com.fasterxml.jackson.databind.deser.std.CollectionDeserializer
    public final Collection A0N(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        Object A03;
        JsonDeserializer jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            A03 = this._valueInstantiator.A02(qrVar, jsonDeserializer.A0C(qiVar, qrVar));
        } else {
            if (qiVar.A0U() == NX.VALUE_STRING) {
                String A0p = qiVar.A0p();
                if (A0p.length() == 0) {
                    A03 = this._valueInstantiator.A03(qrVar, A0p);
                }
            }
            return A0O(qiVar, qrVar, null);
        }
        return (Collection) A03;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer, com.fasterxml.jackson.databind.deser.std.CollectionDeserializer
    public final /* bridge */ /* synthetic */ Object A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        return A0C(qiVar, qrVar);
    }

    public ArrayBlockingQueueDeserializer(AbstractC1024qt qtVar, JsonDeserializer jsonDeserializer, PR pr, Ok ok, JsonDeserializer jsonDeserializer2) {
        super(qtVar, jsonDeserializer, pr, ok, jsonDeserializer2);
    }
}
