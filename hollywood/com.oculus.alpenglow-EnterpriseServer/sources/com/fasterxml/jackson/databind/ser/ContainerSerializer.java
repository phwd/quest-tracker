package com.fasterxml.jackson.databind.ser;

import X.AbstractC02580aL;
import X.AbstractC02590aM;
import X.AnonymousClass0a8;
import X.AnonymousClass0o6;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public abstract class ContainerSerializer<T> extends StdSerializer<T> {
    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public abstract boolean A0B(T t);

    public abstract ContainerSerializer<?> A0E(AnonymousClass0o6 v);

    public abstract boolean A0F(T t);

    public static final boolean A02(AnonymousClass0a8 r2, AbstractC02580aL r3) {
        AbstractC02590aM A01;
        if (r3 == null || (A01 = r2._config.A01()) == null || A01.A0Q(r3.A41(), r3.A4h()) == null) {
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
