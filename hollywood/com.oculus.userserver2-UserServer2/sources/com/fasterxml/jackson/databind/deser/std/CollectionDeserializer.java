package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0122Rd;
import X.AnonymousClass0Y;
import X.AnonymousClass1V;
import X.AnonymousClass7F;
import X.AnonymousClass9p;
import X.AnonymousClass9r;
import X.B3;
import X.RZ;
import X.Rn;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.util.Collection;

@JacksonStdImpl
public class CollectionDeserializer extends ContainerDeserializerBase<Collection<Object>> implements AnonymousClass1V {
    public static final long serialVersionUID = -2003828398549708958L;
    public final RZ _collectionType;
    public final JsonDeserializer<Object> _delegateDeserializer;
    public final JsonDeserializer<Object> _valueDeserializer;
    public final AnonymousClass0Y _valueInstantiator;
    public final AnonymousClass7F _valueTypeDeserializer;

    /* renamed from: A09 */
    public Collection<Object> A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            jsonDeserializer.A03(rn, rd);
            throw null;
        } else if (((B3) rn).A00 == AnonymousClass9p.VALUE_STRING) {
            rn.A09();
            throw null;
        } else {
            throw null;
        }
    }
}
