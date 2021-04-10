package com.fasterxml.jackson.datatype.guava.deser;

import X.AbstractC04000gb;
import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AnonymousClass0jg;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.google.common.base.Optional;
import java.io.IOException;

public final class GuavaOptionalDeserializer extends StdDeserializer<Optional<?>> {
    public final AbstractC04000gb _referenceType;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Optional<?> A09(AbstractC04100gp r2, AbstractC04020gg r3) throws IOException, AnonymousClass0jg {
        return Optional.of(r3.A04(this._referenceType).A09(r2, r3));
    }
}
