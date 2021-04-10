package com.fasterxml.jackson.databind.ser;

import X.AbstractC02120i3;
import X.AbstractC02220iI;
import X.AbstractC02230iJ;
import X.AbstractC04550qd;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public abstract class ContainerSerializer<T> extends StdSerializer<T> {
    public abstract ContainerSerializer<?> A03(AbstractC04550qd v);

    public abstract boolean A04(T t);

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public abstract boolean isEmpty(T t);

    public static final boolean A02(AbstractC02120i3 r2, AbstractC02220iI r3) {
        AbstractC02230iJ A01;
        if (r3 == null || (A01 = r2._config.A01()) == null || A01.A0Q(r3.A4N(), r3.A59()) == null) {
            return false;
        }
        return true;
    }

    public ContainerSerializer(ContainerSerializer<?> containerSerializer) {
        super(containerSerializer.A00, false);
    }

    public ContainerSerializer(Class<T> cls) {
        super(cls);
    }

    public ContainerSerializer(Class<?> cls, boolean z) {
        super(cls, false);
    }
}
