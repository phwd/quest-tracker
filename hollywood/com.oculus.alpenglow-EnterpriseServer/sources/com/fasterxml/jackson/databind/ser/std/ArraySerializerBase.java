package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02580aL;
import X.AbstractC02640aV;
import X.AnonymousClass0a8;
import X.AnonymousClass0a9;
import X.AnonymousClass0o6;
import X.C02650aW;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import java.io.IOException;

public abstract class ArraySerializerBase<T> extends ContainerSerializer<T> {
    public final AbstractC02580aL A00;

    public abstract void A0G(T t, AbstractC02640aV v, AnonymousClass0a8 v2) throws IOException, C02650aW;

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void A0D(T t, AbstractC02640aV r4, AnonymousClass0a8 r5) throws IOException, C02650aW {
        if (!r5._config.A06(AnonymousClass0a9.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED) || !A0F(t)) {
            r4.A0E();
            A0G(t, r4, r5);
            r4.A0B();
            return;
        }
        A0G(t, r4, r5);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void A0A(T t, AbstractC02640aV r2, AnonymousClass0a8 r3, AnonymousClass0o6 r4) throws IOException, C02650aW {
        r4.A01(t, r2);
        A0G(t, r2, r3);
        r4.A04(t, r2);
    }

    public ArraySerializerBase(Class<T> cls, AbstractC02580aL r3) {
        super(cls);
        this.A00 = null;
    }

    public ArraySerializerBase(ArraySerializerBase<?> arraySerializerBase, AbstractC02580aL r4) {
        super(((StdSerializer) arraySerializerBase).A00, false);
        this.A00 = r4;
    }

    public ArraySerializerBase(Class<T> cls) {
        super(cls);
        this.A00 = null;
    }
}
