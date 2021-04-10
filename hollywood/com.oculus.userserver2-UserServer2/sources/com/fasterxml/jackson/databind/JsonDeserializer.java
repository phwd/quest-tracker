package com.fasterxml.jackson.databind;

import X.AbstractC0122Rd;
import X.AnonymousClass1X;
import X.AnonymousClass9r;
import X.Rn;
import com.fasterxml.jackson.databind.deser.std.BaseNodeDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers$PrimitiveOrWrapperDeserializer;
import java.io.IOException;

public abstract class JsonDeserializer<T> {

    public static abstract class None extends JsonDeserializer<Object> {
    }

    public abstract T A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r;

    public final T A02() {
        if (this instanceof NumberDeserializers$PrimitiveOrWrapperDeserializer) {
            return ((NumberDeserializers$PrimitiveOrWrapperDeserializer) this)._nullValue;
        }
        if (!(this instanceof BaseNodeDeserializer)) {
            return null;
        }
        return (T) AnonymousClass1X.A00;
    }
}
