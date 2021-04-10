package com.fasterxml.jackson.databind;

import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AbstractC06410n2;
import X.AnonymousClass006;
import X.AnonymousClass0jg;
import X.AnonymousClass0m9;
import java.io.IOException;
import java.util.Collection;

public abstract class JsonDeserializer<T> {
    public T A08() {
        return null;
    }

    public abstract T A09(AbstractC04100gp v, AbstractC04020gg v2) throws IOException, AnonymousClass0jg;

    public JsonDeserializer<T> A0B(AbstractC06410n2 r1) {
        return this;
    }

    public Collection<Object> A0D() {
        return null;
    }

    public boolean A0E() {
        return false;
    }

    public static abstract class None extends JsonDeserializer<Object> {
        public None() {
            throw null;
        }
    }

    public T A0A(AbstractC04100gp r6, AbstractC04020gg r7, T t) throws IOException, AnonymousClass0jg {
        throw new UnsupportedOperationException(AnonymousClass006.A09("Can not update object of type ", t.getClass().getName(), " (by deserializer of type ", getClass().getName(), ")"));
    }

    public Object A0C(AbstractC04100gp r2, AbstractC04020gg r3, AnonymousClass0m9 r4) throws IOException, AnonymousClass0jg {
        return r4.A07(r2, r3);
    }
}
