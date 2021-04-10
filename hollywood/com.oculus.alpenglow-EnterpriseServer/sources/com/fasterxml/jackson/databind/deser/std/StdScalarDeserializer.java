package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.AnonymousClass0aT;
import X.AnonymousClass0o3;
import X.C05910ld;
import java.io.IOException;

public abstract class StdScalarDeserializer<T> extends StdDeserializer<T> {
    public static final long serialVersionUID = 1;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Object A0C(AnonymousClass0aT r2, AbstractC02570aK r3, AnonymousClass0o3 r4) throws IOException, C05910ld {
        return r4.A0A(r2, r3);
    }

    public StdScalarDeserializer(Class<?> cls) {
        super(cls);
    }
}
