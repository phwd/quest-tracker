package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC01900ha;
import X.AbstractC02190iF;
import X.C02180iD;
import java.util.Collection;

public abstract class StaticListSerializerBase<T extends Collection<?>> extends StdSerializer<T> {
    public StaticListSerializerBase(Class<?> cls) {
        super(cls, false);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void acceptJsonFormatVisitor(AbstractC01900ha r3, AbstractC02190iF r4) throws C02180iD {
        throw new NullPointerException("expectArrayFormat");
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final boolean isEmpty(Object obj) {
        Collection collection = (Collection) obj;
        if (collection == null || collection.size() == 0) {
            return true;
        }
        return false;
    }
}
