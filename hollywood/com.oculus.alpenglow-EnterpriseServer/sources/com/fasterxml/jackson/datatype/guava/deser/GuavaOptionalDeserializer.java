package com.fasterxml.jackson.datatype.guava.deser;

import X.AbstractC02570aK;
import X.AnonymousClass0aI;
import X.AnonymousClass0aT;
import X.C05910ld;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import java.io.IOException;

public final class GuavaOptionalDeserializer extends StdDeserializer<Optional<?>> {
    public final AnonymousClass0aI _referenceType;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Optional<?> A09(AnonymousClass0aT r3, AbstractC02570aK r4) throws IOException, C05910ld {
        Object A09 = r4.A08(this._referenceType).A09(r3, r4);
        if (A09 != null) {
            return new Present(A09);
        }
        throw null;
    }
}
