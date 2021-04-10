package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0122Rd;
import X.AnonymousClass0Y;
import X.AnonymousClass1V;
import X.AnonymousClass9r;
import X.RZ;
import X.Rn;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.util.Collection;

@JacksonStdImpl
public final class StringCollectionDeserializer extends ContainerDeserializerBase<Collection<String>> implements AnonymousClass1V {
    public static final long serialVersionUID = 1;
    public final RZ _collectionType;
    public final JsonDeserializer<Object> _delegateDeserializer;
    public final JsonDeserializer<String> _valueDeserializer;
    public final AnonymousClass0Y _valueInstantiator;

    /* JADX WARN: Incorrect return type in method signature: (LX/Rn;LX/Rd;)Ljava/util/Collection<Ljava/lang/String;>; */
    private final void A00(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            jsonDeserializer.A03(rn, rd);
        }
        throw null;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        A00(rn, rd);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }
}
