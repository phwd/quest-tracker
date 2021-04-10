package com.fasterxml.jackson.databind;

import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.AbstractC04520qa;
import X.AbstractC04870rR;
import X.AnonymousClass006;
import X.C03620oC;
import java.io.IOException;
import java.util.Collection;

public abstract class JsonDeserializer<T> {

    public static abstract class None extends JsonDeserializer<Object> {
    }

    public T A08() {
        return null;
    }

    public JsonDeserializer<T> A09(AbstractC04870rR r1) {
        return this;
    }

    public abstract T A0A(AbstractC02280iQ v, AbstractC02210iH v2) throws IOException, C03620oC;

    public Collection<Object> A0D() {
        return null;
    }

    public boolean A0E() {
        return false;
    }

    public T A0C(AbstractC02280iQ r6, AbstractC02210iH r7, T t) throws IOException, C03620oC {
        throw new UnsupportedOperationException(AnonymousClass006.A0C("Can not update object of type ", t.getClass().getName(), " (by deserializer of type ", getClass().getName(), ")"));
    }

    public Object A0B(AbstractC02280iQ r2, AbstractC02210iH r3, AbstractC04520qa r4) throws IOException, C03620oC {
        return r4.A07(r2, r3);
    }
}
