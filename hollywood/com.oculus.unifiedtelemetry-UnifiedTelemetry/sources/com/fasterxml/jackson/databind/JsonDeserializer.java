package com.fasterxml.jackson.databind;

import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.AnonymousClass06;
import X.KI;
import X.V4;
import X.q0;
import java.io.IOException;
import java.util.Collection;

public abstract class JsonDeserializer<T> {
    public T A08() {
        return null;
    }

    public abstract T A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0;

    public JsonDeserializer<T> A0B(KI ki) {
        return this;
    }

    public Collection<Object> A0D() {
        return null;
    }

    public boolean A0E() {
        return false;
    }

    public T A0A(AbstractC0232Ww ww, AbstractC0226Wn wn, T t) throws IOException, q0 {
        throw new UnsupportedOperationException(AnonymousClass06.A07("Can not update object of type ", t.getClass().getName(), " (by deserializer of type ", getClass().getName(), ")"));
    }

    public static abstract class None extends JsonDeserializer<Object> {
        public None() {
            throw null;
        }
    }

    public Object A0C(AbstractC0232Ww ww, AbstractC0226Wn wn, V4 v4) throws IOException, q0 {
        return v4.A07(ww, wn);
    }
}
