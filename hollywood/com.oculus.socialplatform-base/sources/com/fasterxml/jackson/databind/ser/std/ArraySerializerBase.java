package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02120i3;
import X.AbstractC02220iI;
import X.AbstractC02300iS;
import X.AbstractC04550qd;
import X.AnonymousClass0i4;
import X.C02310iT;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import java.io.IOException;

public abstract class ArraySerializerBase<T> extends ContainerSerializer<T> {
    public final AbstractC02220iI A00;

    public abstract void A05(T t, AbstractC02300iS v, AbstractC02120i3 v2) throws IOException, C02310iT;

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void serialize(T t, AbstractC02300iS r4, AbstractC02120i3 r5) throws IOException, C02310iT {
        if (!r5._config.A06(AnonymousClass0i4.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED) || !A04(t)) {
            r4.A0H();
            A05(t, r4, r5);
            r4.A0E();
            return;
        }
        A05(t, r4, r5);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serializeWithType(T t, AbstractC02300iS r2, AbstractC02120i3 r3, AbstractC04550qd r4) throws IOException, C02310iT {
        r4.A01(t, r2);
        A05(t, r2, r3);
        r4.A04(t, r2);
    }

    public ArraySerializerBase(Class<T> cls, AbstractC02220iI r3) {
        super(cls);
        this.A00 = null;
    }

    public ArraySerializerBase(ArraySerializerBase<?> arraySerializerBase, AbstractC02220iI r4) {
        super(((StdSerializer) arraySerializerBase).A00, false);
        this.A00 = r4;
    }

    public ArraySerializerBase(Class<T> cls) {
        super(cls);
        this.A00 = null;
    }
}
