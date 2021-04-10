package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.AnonymousClass0aT;
import X.AnonymousClass0o3;
import X.C05910ld;
import X.C06600nW;
import java.io.IOException;

public final class NullifyingDeserializer extends StdDeserializer<Object> {
    public static final NullifyingDeserializer A00 = new NullifyingDeserializer();
    public static final long serialVersionUID = 1;

    public NullifyingDeserializer() {
        super(Object.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A09(AnonymousClass0aT r2, AbstractC02570aK r3) throws IOException, C05910ld {
        r2.A0F();
        return null;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AnonymousClass0aT r3, AbstractC02570aK r4, AnonymousClass0o3 r5) throws IOException, C05910ld {
        int i = C06600nW.A00[r3.A0G().ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            return r5.A07(r3, r4);
        }
        return null;
    }
}
