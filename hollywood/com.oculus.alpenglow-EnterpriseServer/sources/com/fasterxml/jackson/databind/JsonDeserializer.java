package com.fasterxml.jackson.databind;

import X.AbstractC02570aK;
import X.AbstractC07200ov;
import X.AnonymousClass006;
import X.AnonymousClass0aT;
import X.AnonymousClass0o3;
import X.C05910ld;
import java.io.IOException;
import java.util.Collection;

public abstract class JsonDeserializer<T> {

    public static abstract class None extends JsonDeserializer<Object> {
        public None() {
            throw null;
        }
    }

    public T A08() {
        return null;
    }

    public abstract T A09(AnonymousClass0aT v, AbstractC02570aK v2) throws IOException, C05910ld;

    public JsonDeserializer<T> A0B(AbstractC07200ov r1) {
        return this;
    }

    public Collection<Object> A0D() {
        return null;
    }

    public boolean A0E() {
        return false;
    }

    public T A0A(AnonymousClass0aT r6, AbstractC02570aK r7, T t) throws IOException, C05910ld {
        throw new UnsupportedOperationException(AnonymousClass006.A09("Can not update object of type ", t.getClass().getName(), " (by deserializer of type ", getClass().getName(), ")"));
    }

    public Object A0C(AnonymousClass0aT r2, AbstractC02570aK r3, AnonymousClass0o3 r4) throws IOException, C05910ld {
        return r4.A07(r2, r3);
    }
}
