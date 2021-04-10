package com.fasterxml.jackson.databind.ser.std;

import java.util.Collection;

public abstract class StaticListSerializerBase<T extends Collection<?>> extends StdSerializer<T> {
    public StaticListSerializerBase(Class<?> cls) {
        super(cls, false);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final boolean A0B(Object obj) {
        Collection collection = (Collection) obj;
        if (collection == null || collection.size() == 0) {
            return true;
        }
        return false;
    }
}
