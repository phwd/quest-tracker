package com.fasterxml.jackson.datatype.guava.deser;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.AbstractC1024qt;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.google.common.base.Optional;
import com.google.common.base.Present;

public final class GuavaOptionalDeserializer extends StdDeserializer {
    public final AbstractC1024qt _referenceType;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Optional A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        Object A0C = qrVar.A07(this._referenceType).A0C(qiVar, qrVar);
        if (A0C != null) {
            return new Present(A0C);
        }
        throw null;
    }
}
