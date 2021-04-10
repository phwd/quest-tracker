package com.fasterxml.jackson.datatype.guava.deser;

import X.AbstractC0224Wl;
import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.q0;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import java.io.IOException;

public final class GuavaOptionalDeserializer extends StdDeserializer<Optional<?>> {
    public final AbstractC0224Wl _referenceType;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Optional<?> A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        Object A09 = wn.A05(this._referenceType).A09(ww, wn);
        if (A09 != null) {
            return new Present(A09);
        }
        throw null;
    }
}
