package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02190iF;
import X.AbstractC02210iH;
import X.AbstractC02220iI;
import X.AbstractC02280iQ;
import X.AbstractC04230pb;
import X.C02180iD;
import X.C03620oC;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class JdkDeserializers$AtomicReferenceDeserializer extends StdScalarDeserializer<AtomicReference<?>> implements AbstractC04230pb {
    public final AbstractC02190iF _referencedType;
    public final JsonDeserializer<?> _valueDeserializer;

    public JdkDeserializers$AtomicReferenceDeserializer(AbstractC02190iF r2, JsonDeserializer<?> jsonDeserializer) {
        super(AtomicReference.class);
        this._referencedType = r2;
        this._valueDeserializer = jsonDeserializer;
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final AtomicReference<?> A0A(AbstractC02280iQ r3, AbstractC02210iH r4) throws IOException, C03620oC {
        return new AtomicReference<>(this._valueDeserializer.A0A(r3, r4));
    }

    @Override // X.AbstractC04230pb
    public final JsonDeserializer<?> A2O(AbstractC02210iH r4, AbstractC02220iI r5) throws C02180iD {
        if (this._valueDeserializer != null) {
            return this;
        }
        AbstractC02190iF r2 = this._referencedType;
        return new JdkDeserializers$AtomicReferenceDeserializer(r2, r4.A09(r2, r5));
    }
}
