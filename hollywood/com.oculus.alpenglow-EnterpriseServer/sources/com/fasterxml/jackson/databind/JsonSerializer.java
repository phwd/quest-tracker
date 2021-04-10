package com.fasterxml.jackson.databind;

import X.AbstractC02640aV;
import X.AbstractC06790ns;
import X.AbstractC07200ov;
import X.AnonymousClass006;
import X.AnonymousClass0a8;
import X.AnonymousClass0o6;
import X.C05910ld;
import java.io.IOException;

public abstract class JsonSerializer<T> implements AbstractC06790ns {

    public static abstract class None extends JsonSerializer<Object> {
        public None() {
            throw null;
        }
    }

    public boolean A07() {
        return false;
    }

    public boolean A08() {
        return false;
    }

    public JsonSerializer<T> A09(AbstractC07200ov r1) {
        return this;
    }

    public boolean A0B(T t) {
        return t == null;
    }

    public Class<T> A0C() {
        return null;
    }

    public abstract void A0D(T t, AbstractC02640aV v, AnonymousClass0a8 v2) throws IOException, C05910ld;

    public void A0A(T t, AbstractC02640aV r4, AnonymousClass0a8 r5, AnonymousClass0o6 r6) throws IOException, C05910ld {
        Class A0C = A0C();
        if (A0C == null) {
            A0C = t.getClass();
        }
        throw new UnsupportedOperationException(AnonymousClass006.A05("Type id handling not implemented for type ", A0C.getName()));
    }
}
