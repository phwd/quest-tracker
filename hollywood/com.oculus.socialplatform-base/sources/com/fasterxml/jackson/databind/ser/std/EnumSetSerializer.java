package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02120i3;
import X.AbstractC02190iF;
import X.AbstractC02220iI;
import X.AbstractC02300iS;
import X.AbstractC04550qd;
import X.C02310iT;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import java.io.IOException;
import java.util.AbstractCollection;
import java.util.EnumSet;
import java.util.Iterator;

public class EnumSetSerializer extends AsArraySerializerBase<EnumSet<? extends Enum<?>>> {
    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final boolean A04(Object obj) {
        if (((AbstractCollection) obj).size() != 1) {
            return false;
        }
        return true;
    }

    /* Return type fixed from 'com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase' to match base method */
    @Override // com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase
    public final AsArraySerializerBase<EnumSet<? extends Enum<?>>> A07(AbstractC02220iI r2, AbstractC04550qd r3, JsonSerializer jsonSerializer) {
        return new EnumSetSerializer(this, r2, r3, jsonSerializer);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0iS, X.0i3] */
    @Override // com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase
    public final void A08(EnumSet<? extends Enum<?>> enumSet, AbstractC02300iS r6, AbstractC02120i3 r7) throws IOException, C02310iT {
        JsonSerializer<Object> jsonSerializer = this.A04;
        Iterator<E> it = enumSet.iterator();
        while (it.hasNext()) {
            E next = it.next();
            if (jsonSerializer == null) {
                jsonSerializer = r7.A0B(next.getDeclaringClass(), this.A03);
            }
            jsonSerializer.serialize(next, r6, r7);
        }
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public final boolean isEmpty(Object obj) {
        AbstractCollection abstractCollection = (AbstractCollection) obj;
        if (abstractCollection == null || abstractCollection.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final ContainerSerializer A03(AbstractC04550qd r1) {
        return this;
    }

    public EnumSetSerializer(AbstractC02190iF r8) {
        super(EnumSet.class, r8, true, null, null, null);
    }

    public EnumSetSerializer(EnumSetSerializer enumSetSerializer, AbstractC02220iI r2, AbstractC04550qd r3, JsonSerializer<?> jsonSerializer) {
        super(enumSetSerializer, r2, r3, jsonSerializer);
    }
}
