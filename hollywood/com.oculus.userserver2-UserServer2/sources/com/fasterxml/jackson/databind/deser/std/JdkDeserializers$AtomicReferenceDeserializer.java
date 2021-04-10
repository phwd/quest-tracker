package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0122Rd;
import X.AnonymousClass1V;
import X.AnonymousClass9r;
import X.RZ;
import X.Rn;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class JdkDeserializers$AtomicReferenceDeserializer extends StdScalarDeserializer<AtomicReference<?>> implements AnonymousClass1V {
    public final RZ _referencedType;
    public final JsonDeserializer<?> _valueDeserializer;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final AtomicReference<?> A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        return new AtomicReference<>(this._valueDeserializer.A03(rn, rd));
    }
}
