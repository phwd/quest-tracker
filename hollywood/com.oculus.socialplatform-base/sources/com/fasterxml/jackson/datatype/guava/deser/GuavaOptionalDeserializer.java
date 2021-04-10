package com.fasterxml.jackson.datatype.guava.deser;

import X.AbstractC02190iF;
import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.C03620oC;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import java.io.IOException;

public final class GuavaOptionalDeserializer extends StdDeserializer<Optional<?>> {
    public final AbstractC02190iF _referenceType;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Optional<?> A0A(AbstractC02280iQ r2, AbstractC02210iH r3) throws IOException, C03620oC {
        return Optional.of(r3.A08(this._referenceType).A0A(r2, r3));
    }

    public GuavaOptionalDeserializer(AbstractC02190iF r2) {
        super(r2);
        this._referenceType = r2.A06(0);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A08() {
        return Absent.INSTANCE;
    }
}
